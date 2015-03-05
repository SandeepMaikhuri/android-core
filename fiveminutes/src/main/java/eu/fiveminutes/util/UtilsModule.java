package eu.fiveminutes.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.view.WindowManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.dagger.ForActivity;
import eu.fiveminutes.dagger.ForApplication;

@Module
public class UtilsModule {

    private final Application application;

    public UtilsModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(@ForApplication Context applicationContext) {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    WindowManager provideWindowManager() {
        return (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    Blur provideBlur(@ForApplication Context context) {
        return new BlurImpl(context);
    }

    @Provides
    @Singleton
    ColorUtils provideColorUtils() {
        return new ColorUtilsImpl();
    }

    @Provides
    @Singleton
    CryptoUtils provideCryptoUtils() {
        return new CryptoUtilsImpl();
    }

    @Provides
    @Singleton
    DisplayUtils provideDisplayUtils(Resources resources, WindowManager windowManager) {
        return new DisplayUtilsImpl(resources, windowManager);
    }

    @Provides
    @Singleton
    Foreground provideForeground() {
        return new ForegroundImpl(application);
    }

    @Provides
    @Singleton
    GeolocationUtils provideGeolocationUtils(@ForApplication Context context) {
        return new GeolocationUtilsImpl(context);
    }

    @Provides
    @Singleton
    ImageUtils provideImageUtilities(Resources resources, @ForActivity Context context, Blur blur,
                                     ResourceUtils resourceUtils, WindowManager windowManager) {
        return new ImageUtilsImpl(resources, context.getFilesDir(), blur, resourceUtils, windowManager);
    }

    @Provides
    @Singleton
    IntentUtils provideIntentUtils(@ForApplication Context applicationContext) {
        return new IntentUtilsImpl(applicationContext);
    }

    @Provides
    @Singleton
    ListUtils provideListUtils() {
        return new ListUtilsImpl();
    }

    @Provides
    @Singleton
    MathUtils provideMathUtils() {
        return new MathUtilsImpl();
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(ConnectivityManager connectivityManager) {
        return new NetworkUtilsImpl(connectivityManager);
    }

    @Provides
    @Singleton
    PicassoWrapper providePicassoWrapper() {
        return new PicassoWrapper();
    }

    @Provides
    @Singleton
    ResourceUtils provideResourceUtils(@ForApplication Context applicationContext) {
        return new ResourceUtilsImpl(applicationContext);
    }

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtilsImpl();
    }
}
