package views.observer;

public interface IObservable {
    void notify_All(String msj);
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
}
