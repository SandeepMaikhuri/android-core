package eu.fiveminutes.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

public final class ImageUtilsImpl implements ImageUtils {

    private static final String TAG = ImageUtilsImpl.class.getSimpleName();

    private final Resources resources;
    private final File filesDir;
    private final Blur blur;
    private final ResourceUtils resourceUtils;
    private final WindowManager windowManager;

    public ImageUtilsImpl(Resources resources, File filesDir,
                          Blur blur, ResourceUtils resourceUtils,
                          WindowManager windowManager) {
        this.blur = blur;
        this.resourceUtils = resourceUtils;
        this.resources = resources;
        this.filesDir = filesDir;
        this.windowManager = windowManager;
//        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * Stores an image on the storage
     *
     * @param image       the image to store.
     * @param pictureFile the file in which it must be stored
     */
    @Override
    public void storeImage(Bitmap image, File pictureFile) {
        if (pictureFile == null) {
            Log.d(TAG, "Error creating media file, check storage permissions: ");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    /**
     * Get the screen height.
     *
     * @return the screen height
     */
    @Override
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public int getScreenHeight() {
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        }
        return display.getHeight();
    }

    /**
     * Get the screen width.
     *
     * @return the screen width
     */
    @Override
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public int getScreenWidth() {

        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        }
        return display.getWidth();
    }

    @Override
    public void blurAndLoadImage(Context applicationContext, String url, ImageView target, String fileName) {
        if (resourceUtils.isInTabletMode()) {
            PicassoWrapper.with(applicationContext).load(url)
                    .into(target);
        } else {
            PicassoWrapper.with(applicationContext).load(url)
                    .transform(blurTransformation(fileName))
                    .into(target);
        }

    }

    @Override
    public String getImageName(int resId) {
        return resId + "_cached.png";
    }

    @Override
    public void getBlurredImage(final int bitmapRes, final String nameToSave, final int radius,
                                final ImageUtilsImpl.BlurEffectListener listener) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                File saveFile = new File(filesDir, nameToSave);
                Bitmap blurredBitmap = null;
                if (!saveFile.exists()) {
                    try {
                        Bitmap orgBitmap = BitmapFactory.decodeResource(
                                resources, bitmapRes);
                        blurredBitmap = blur.fastblur(orgBitmap,
                                radius);
                        saveFile.createNewFile();
                        storeImage(blurredBitmap, saveFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    blurredBitmap = BitmapFactory.decodeFile(saveFile
                            .getAbsolutePath());
                }
                final Bitmap tempBitmap = blurredBitmap;
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        listener.onDone(tempBitmap);
                    }
                });

            }
        }).start();
    }

    @Override
    public Bitmap getBlurredImage(final Bitmap orgBitmap, final String nameToSave, final int radius) {
        File saveFile = new File(filesDir, nameToSave);
        Bitmap blurredBitmap = null;
        if (!saveFile.exists()) {
            try {
                blurredBitmap = blur.fastblur(orgBitmap, radius);
                saveFile.createNewFile();
                storeImage(blurredBitmap, saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            blurredBitmap = BitmapFactory.decodeFile(saveFile
                    .getAbsolutePath());
        }
        orgBitmap.recycle();
        return blurredBitmap;
    }

    @Override
    public Transformation blurTransformation(final String fileName) {
        return new Transformation() {

            private Bitmap blurredImage;

            @Override
            public Bitmap transform(Bitmap source) {
                return getBlurredImage(source,
                        fileName, 20);
            }

            @Override
            public String key() {
                return fileName;
            }
        };
    }

    @Override
    public Transformation roundedTransformation(final String filename, final float radius) {
        return new Transformation() {

            @Override
            public Bitmap transform(final Bitmap source) {
                final Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

                Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, source.getWidth(), source.getHeight()), radius, radius, paint);

                if (source != output) {
                    source.recycle();
                }

                return output;
            }

            @Override
            public String key() {
                return "rounded transf " + filename;
            }
        };
    }

    @Override
    public Transformation circleTransformation(final String filename) {
        return new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());

                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);

                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle transf " + filename;
            }
        };
    }

    public static interface BlurEffectListener {
        public void onDone(Bitmap bitmap);
    }
}
