import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private static final ConfigurableApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        App app = (App) CONTEXT.getBean("app");
        try {
            app.logEvent("Some event for user 1");
            app.logEvent("Some event for user 2");
            app.logEvent("Some event for user 3");
            app.logEvent("Some event for user 4");
            app.logEvent("Some event for user 5");
            app.logEvent("Some event for user 6");
            app.logEvent("Some event for user 7");
            app.logEvent("Some event for user 8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CONTEXT.close();
    }

    private void logEvent(String msg) throws Exception {
        Event event = (Event) CONTEXT.getBean("event");
        event.setMsg(msg.replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }
}
