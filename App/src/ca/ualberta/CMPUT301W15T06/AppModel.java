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
	
	private transient ArrayList<Listener> listners;
	private transient ArrayList<Listener> modelListners;
	private boolean missValue;
	
	/**
	 * This method creates a ArrayList for listeners that 
	 * records all listener
	 */
	public AppModel(){
		listners=new ArrayList<Listener>();
		modelListners=new ArrayList<Listener>();
	}
	
	/**
	 * This method allows user to add a listener to the 
	 * ArrayList listeners
	 * 
	 * @param l  a Listener type l
	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
	}
	
	/**
	 * This method allows user to remove a listener from
	 * the ArrayList listeners
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
	private ArrayList<Listener> getListeners(){
		if (listners==null){
			listners=new ArrayList<Listener>();
		}
		return listners;
	}
	

	/**
	 * This method is design for notifying all listener 
	 * in listeners ArrayList if there's any update, and
	 * the ClaimListManager will save the change 
	 */

	public void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
		for (Listener  listener : getModelListeners()) {
			listener.update();
		}
		ClaimListManager.getInstance().save();
	}

	/**
	 * This method allows user to add a listener to the 
	 * model if the model does not contains a Listener l
	 * 
	 * @param l a Listener type l
	 */
	public void addModelListener(Listener l){
		if (!getModelListeners().contains(l)){
			getModelListeners().add(l);
		}
	}
	
	/**
	 * This model is designed for remove a listener from
	 * the model.
	 * 
	 * @param l a Listener type l
	 */
	public void removeModelListener(Listener l){
		getModelListeners().remove(l);
	}
	
	/**
	 * This method will create a Listeners ArrayList model 
	 * if there's no model and return the new Listeners model
	 * 
	 * @return a ArrayList modelListners
	 */
	private ArrayList<Listener> getModelListeners(){
		if (modelListners==null){
			modelListners=new ArrayList<Listener>();
		}
		return modelListners;
	}
	
	/**
	 * Set a missValue to b, and notify all listener in
	 * listeners ArrayList about this update.
	 *  
	 * @param b a boolean type b
	 */
	public void setMissValue(boolean b) {
		missValue=b;
		notifyListeners();
	}

	/**
	 * return the misValue. This method will be used
	 * when user needs to use the value of missValue 
	 * to justify the model.
	 * 
	 * @return missValue a boolean type missValue
	 */
	public boolean getMissValue() {
		return missValue;
	}
}
