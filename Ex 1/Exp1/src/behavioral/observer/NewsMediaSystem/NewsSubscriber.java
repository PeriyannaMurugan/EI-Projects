package behavioral.observer.NewsMediaSystem;

import java.util.logging.Logger;

public class NewsSubscriber implements Observer {
    private String subscriberName;
    private String subscriberType;
    private static final Logger logger = Logger.getLogger(NewsSubscriber.class.getName());
    
    public NewsSubscriber(String name, String type) {
        this.subscriberName = name;
        this.subscriberType = type;
        logger.info("NewsSubscriber created: " + name + " (" + type + ")");
    }
    
    @Override
    public void update(String news) {
        logger.info("News received by " + subscriberName + ": " + news);
        processNews(news);
    }
    
    private void processNews(String news) {
        switch (subscriberType.toLowerCase()) {
            case "mobile":
                System.out.println("[MOBILE APP] " + subscriberName + " received: " + news);
                break;
            case "email":
                System.out.println("[EMAIL] Sending to " + subscriberName + ": " + news);
                break;
            case "website":
                System.out.println("[WEBSITE] Displaying for " + subscriberName + ": " + news);
                break;
            default:
                System.out.println("[GENERAL] " + subscriberName + " notified: " + news);
        }
    }
    
    @Override
    public String getObserverName() {
        return subscriberName + " (" + subscriberType + ")";
    }
}
