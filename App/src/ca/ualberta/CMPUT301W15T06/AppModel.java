/*
UA CMPUT 301 Project Group: CMPUT301W15T06

Copyright {2015} {Jingjiao Ni

              Tianqi Xiao

              Jiafeng Wu

              Xinyi Pan 

              Xinyi Wu

              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.

 */

package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The <code>AppModel</code> class can create an ArrayList named
 * listeners, which contains Listener subject. This class can
 * set up listeners, add a Listener to listeners, remove a listener
 * from listeners, notify listeners for updates, set up missValue.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.util.ArrayList
 * @see java.util.HashSet
 * @see java.util.Set
 */
public class AppModel{
	
	/**
	 * Set two private ArrayList variable named listeners
	 * and modelListeners to contain Listener subject
	 * 
	 * @see java.util.ArrayList;
	 */
	private transient ArrayList<Listener> listeners;
	private transient ArrayList<Listener> modelListeners;
	/**
	 * Set a boolean variable missValue to justify the 
	 * <code>AppModel</code>
	 */
	private boolean missValue;
	
	
	/**
	 * General construction. This method creates an ArrayList 
	 * listeners and an ArrayList modelListers for listeners 
	 * that records all Listener.
	 */
	public AppModel(){
		listeners=new ArrayList<Listener>();
		modelListeners=new ArrayList<Listener>();
	}
	
	/**
	 * This method allows user to add a Listener to the 
	 * ArrayList listeners
	 * 
	 * @param l  a Listener type
	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
	}
	
	/**
	 * This method allows user to remove a Listener from
	 * the listeners ArrayList.
	 * 
	 * @param l  a Listener type
	 */
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	/**
	 * This method checks if there's any listeners ArrayList, 
	 * if not, set up a new one and return it for further use
	 * 
	 * @return listeners  an ArrayList type
	 */
	protected ArrayList<Listener> getListeners(){
		if (listeners==null){
			listeners=new ArrayList<Listener>();
		}
		return listeners;
	}
	

	/**
	 * This method is design for notifying all Listener 
	 * in listeners ArrayList and modelListeners ArrayList 
	 * if there's any update, and the ClaimListManager 
	 * will save the change 
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
	 * This method allows user to add a Listener to the 
	 * modelListeners if the model does not contains 
	 * a Listener l
	 * 
	 * @param l  a Listener type
	 */
	public void addModelListener(Listener l){
		if (!getModelListeners().contains(l)){
			getModelListeners().add(l);
		}
	}
	
	/**
	 * This method is designed to remove a Listener from
	 * the modelListeners ArrayList
	 * 
	 * @param l  a Listener type
	 */
	public void removeModelListener(Listener l){
		getModelListeners().remove(l);
	}
	
	/**
	 * This method will create a new ArrayList modelListeners 
	 * if there's none and return the new modelListeners
	 * 
	 * @return modelListners  an ArrayList type
	 */
	protected ArrayList<Listener> getModelListeners(){
		if (modelListeners==null){
			modelListeners=new ArrayList<Listener>();
		}
		return modelListeners;
	}
	
	/**
	 * Set missValue to b, and notify all Listener in
	 * listeners ArrayList and modelListeners ArrayList 
	 * about this update.
	 *  
	 * @param b  a boolean type b
	 */
	public void setMissValue(boolean b) {
		missValue=b;
		notifyListeners();
	}

	/**
	 * return the missValue. This method will be used
	 * when user needs to use the value of missValue 
	 * to justify the <code>AppModel</code>.
	 * 
	 * @return missValue  a boolean type
	 */
	public boolean getMissValue() {
		return missValue;
	}
}
