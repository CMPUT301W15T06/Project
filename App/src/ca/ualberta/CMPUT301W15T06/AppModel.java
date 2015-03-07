/**
 * The <code>AppModel</code> class can create a hash set for listeners,
 * set up listeners, add a listener, remove a listener, 
 * and notify listeners for updates.
 * 
 * @author CMPUT301W15T06
 * @version 02/28/2015
 * @see java.util.ArrayList
 * @see java.util.HashSet
 * @see java.util.Set
 */

package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AppModel{
	private transient Set<Listener> listners;
	
	/**
	 * This method creates a hash set for listeners that 
	 * records all listener
	 */
	public AppModel(){
		listners=new HashSet<Listener>();
	}
	
	/**
	 * This method allows user to add a listener to the 
	 * hash set listeners
	 * 
	 * @param l  a Listener type l
	 */
	public void addListener(Listener l){
		getListeners().add(l);
	}
	
	/**
	 * This method allows user to remove a listener from
	 * the hash set listeners
	 * 
	 * @param l  a Listener type l
	 */
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	/**
	 * This method checks if there's any listeners, 
	 * if not, set up a new one and return it for further use
	 * 
	 * @return listeners
	 */
	private Set<Listener> getListeners(){
		if (listners==null){
			listners=new HashSet<Listener>();
		}
		return listners;
	}
	
	/**
	 * This method is design for notifying all listener 
	 * in listeners hash set if there's any update
	 */
	public void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
		ClaimListManager.getInstance().save();
	}


}
