package eu.fiveminutes.event;

/**
 * Created by tomo on 10/22/14.
 */
@Deprecated
public interface EventBus {

    void post(Object event);

    void register(Object observer);

    void unregister(Object observer);
}
