package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AppModel{
	private transient Set<Listener> listners;
	
	public AppModel(){
		listners=new HashSet<Listener>();
	}
	
	public void addListener(Listener l){
		getListeners().add(l);
	}
	
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	private Set<Listener> getListeners(){
		if (listners==null){
			listners=new HashSet<Listener>();
		}
		return listners;
	}
	
	protected void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
	}


}
