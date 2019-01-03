package loggers;

import loggers.entity.Event;

public interface EventLogger {
    void logEvent(Event event) throws Exception;
}
