package eu.fiveminutes.util;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    private final Application application;

    public UtilsModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    PicassoWrapper providePicassoWrapper() {
        return new PicassoWrapper();
    }

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtils();
    }

    @Provides
    @Singleton
    Foreground provideForeground() {
        return new Foreground(application);
    }
}
