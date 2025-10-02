package behavioral.observer.NewsMediaSystem;

public interface Observer {
    void update(String news);
    String getObserverName();
}
