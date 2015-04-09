package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
	List<IObserver> observers = new ArrayList<IObserver>();
	
	public void addObserver(IObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(IObserver o) {
		observers.remove(o);
	}
	
	public void notifyObservers(String arg) {
		for(IObserver o: observers) {
			o.update(this, arg);
		}
	}
}
