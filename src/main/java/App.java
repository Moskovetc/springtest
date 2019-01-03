import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        App app = (App) CONTEXT.getBean("app");
        app.logEvent("Some event for user 1");
    }

    private void logEvent(String msg) {
        Event event = (Event) CONTEXT.getBean("event");
        event.setMsg(msg.replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }
}
