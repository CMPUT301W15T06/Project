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

import android.util.Log;

/**
 * <p>
 * The <code>AppModel</code> class can create an ArrayList named 
 * listeners, which contains Listener subject. This class can 
 * set up listeners, add a Listener to listeners, remove a listener 
 * from listeners, notify listeners for updates, set up missValue.
 * <p>
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.util.ArrayList
 * @see java.util.HashSet
 * @see java.util.Set
 */
public abstract class AppModel{
	
	/**
	 * Set two private ArrayList variable named listeners and modelListeners to contain Listener object
	 * 
	 * @see java.util.ArrayList
	 */
	private transient ArrayList<Listener> listeners;
	private transient ArrayList<Listener> modelListeners;

	
	
	/**
	 * General construction. This method creates an ArrayList listeners and an ArrayList modelListers for listeners that records all Listener.
	 */
	public AppModel(){
		listeners=new ArrayList<Listener>();
		modelListeners=new ArrayList<Listener>();
	}
	
	public AppModel(AppModel app) {
		// TODO Auto-generated constructor stub
		listeners=app.getListeners();
		modelListeners=app.getModelListeners();
	}

	/**
	 * This method allows user to add a Listener to the ArrayList listeners
	 * 
	 * @param l  a observer that watches all the updates of objects
	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
	}
	
	/**
	 * This method allows user to remove a Listener from the listeners ArrayList.
	 * 
	 * @param l  a observer that watches all the updates of objects
	 */
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	/**
	 * This method checks if there's any listeners ArrayList, if not, set up a new one and return it for further use
	 * 
	 * @return an ArrayList which contains Listeners object to track all the updates of objects
	 */
	protected ArrayList<Listener> getListeners(){
		if (listeners==null){
			listeners=new ArrayList<Listener>();
		}
		return listeners;
	}
	

	/**
	 * <p>
	 * This method is design for notifying all Listener 
	 * in listeners ArrayList and modelListeners ArrayList 
	 * if there's any update, and the ClaimListManager 
	 * will save the change.
	 * <p>
	 * 
	 * @throws NetWorkException 
	 */
	public void notifyListeners() throws NetWorkException {
		if(AppSingleton.getInstance().iscMod()){
			ClaimListManager.getInstance().save(AppSingleton.getInstance().getUserName());
		}else{
			ClaimListManager.getInstance().approverSave();
			if(!AppSingleton.getInstance().isSuc()){
				throw new NetWorkException();
			}else{
				ClaimListManager.getInstance().saveLocal(AppSingleton.getInstance().getTempUser());
			}
		}
		for (Listener  listener : getListeners()) {
			listener.update();
		}
		for (Listener  listener : getModelListeners()) {
			listener.update();
		}
		
	}

	/**
	 * This method allows user to add a Listener to the modelListeners if the model does not contains a Listener l
	 * 
	 * @param l  a observer that watches all the updates of objects
	 */
	public void addModelListener(Listener l){
		if (!getModelListeners().contains(l)){
			getModelListeners().add(l);
		}
	}
	
	/**
	 * This method is designed to remove a Listener from the modelListeners ArrayList
	 * 
	 * @param l  a observer that watches all the updates of objects
	 */
	public void removeModelListener(Listener l){
		getModelListeners().remove(l);
	}
	
	/**
	 * This method will create a new ArrayList modelListeners if there's none and return the new modelListeners
	 * 
	 * @return modelListners  an ArrayList contains all the listeners to check all model objects
	 */
	protected ArrayList<Listener> getModelListeners(){
		if (modelListeners==null){
			modelListeners=new ArrayList<Listener>();
		}
		return modelListeners;
	}
	

	/**
	 * return the missValue. This method will be used when user needs to use the value of missValue to justify the <code>AppModel</code>.
	 * 
	 * @return a boolean variable to check if there is a miss value
	 */
	public abstract boolean getMissValue();
}
