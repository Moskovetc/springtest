package loggers;

import loggers.entity.Event;

import java.util.List;

public class CombinedEventlogger implements EventLogger {
    private List<EventLogger> loggers;

    public CombinedEventlogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws Exception {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}
