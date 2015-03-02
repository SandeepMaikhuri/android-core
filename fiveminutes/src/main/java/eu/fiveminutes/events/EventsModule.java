package eu.fiveminutes.events;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EventsModule {

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new EventBusImpl();
    }
}
