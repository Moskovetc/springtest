import loggers.entity.Event;
import loggers.EventLogger;
import loggers.entity.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private Map<EventType, EventLogger>  loggers;
    private static final ConfigurableApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("spring.xml");
    private EventLogger defaultLogger;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        App app = (App) CONTEXT.getBean("app");
        try {
            app.logEvent(EventType.INFO, "Some event for user 1");
            app.logEvent(EventType.ERROR, "Some event for user 2");
            app.logEvent(EventType.INFO, "Some event for user 3");
            app.logEvent(EventType.ERROR, "Some event for user 4");
            app.logEvent(null, "Some event for user 5");
            app.logEvent(null, "Some event for user 6");
            app.logEvent(EventType.INFO, "Some event for user 7");
            app.logEvent(EventType.ERROR, "Some event for user 8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CONTEXT.close();
    }

    private void logEvent(EventType type, String msg) throws Exception {
        EventLogger logger = loggers.get(type);

        if (logger == null) {
            logger = defaultLogger;
        }
        Event event = (Event) CONTEXT.getBean("event");
        event.setMsg(msg.replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }
}
