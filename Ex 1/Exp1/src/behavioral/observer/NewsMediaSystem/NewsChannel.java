package behavioral.observer.NewsMediaSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class NewsChannel implements Subject {
    private List<Observer> observers;
    private String latestNews;
    private static final Logger logger = Logger.getLogger(NewsChannel.class.getName());
    
    public NewsChannel() {
        this.observers = new ArrayList<>();
        logger.info("NewsChannel created");
    }
    
    public void setNews(String news) {
        this.latestNews = news;
        logger.info("News updated: " + news);
        notifyObservers();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            logger.info("Observer registered: " + observer.getObserverName());
        }
    }
    
    @Override
    public void unregisterObserver(Observer observer) {
        if (observers.remove(observer)) {
            logger.info("Observer unregistered: " + observer.getObserverName());
        }
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            try {
                observer.update(latestNews);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error notifying observer: " + observer.getObserverName(), e);
            }
        }
    }
    
    public String getLatestNews() {
        return latestNews;
    }
    
    public int getObserverCount() {
        return observers.size();
    }
}
