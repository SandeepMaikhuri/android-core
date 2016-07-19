package eu.fiveminutes.demo.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import eu.fiveminutes.demo.R;
import eu.fiveminutes.util.Blur;
import eu.fiveminutes.util.BlurImpl;

public final class BlurExampleActivity extends Activity {

    private static final int BLUR_RADIUS = 10;

    @InjectView(R.id.activity_blur_example_imageview)
    protected ImageView imageView;

    private Blur blur;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_example);
        injectViews();

        imageView.setImageResource(R.drawable.ic_launcher);
        blur = new BlurImpl(getApplicationContext());
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    @OnClick(R.id.activity_blur_button)
    protected void onStartButtonClicked() {
        new BlurTask(blur, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), imageView).execute(BLUR_RADIUS);
    }

    private static final class BlurTask extends AsyncTask<Integer, Void, Bitmap> {

        private final Blur blur;
        private final Bitmap bitmap;
        private final WeakReference<ImageView> imageViewWeakReference;

        public BlurTask(final Blur blur, final Bitmap bitmap, final ImageView imageView) {
            this.blur = blur;
            this.bitmap = bitmap;
            this.imageViewWeakReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(final Integer... params) {
            final int blurRadius = params[0];
            return blur.fastblur(bitmap, blurRadius);
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {
            super.onPostExecute(bitmap);
            final ImageView imageView = imageViewWeakReference.get();

            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
