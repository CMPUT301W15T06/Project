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

/**
 * The <code>Destination</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a <code>Destination</code> with a name and a reason. 
 * This method will be called when users need to add a <code>Destination</code>,
 * edit a <code>Destination</code> or access to <code>Destination</code> detail.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 */
package ca.ualberta.CMPUT301W15T06;

public class Destination extends AppModel{
	
	/**
	 * Set private string name to record destination name. 
	 */
	private String name;
	/**
	 * Set private string reason to record the reason of 
	 * the destination. 
	 */
	private String reason;
	
	
	/**
	 * General construction. This public method sets up a 
	 * Destination object with name.
	 * 
	 * @param name  a String variable
	 */
	public Destination(String name){
		super();
		this.name=name;
	}
	
	/**
	 * Set up the name and use <code>notifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a name to a new 
	 * destination or editing a current destination.
	 * 
	 * @param name  a String variable
	 */
	public void setName(String name){
		this.name=name;
		notifyListeners();
	}
	
	/**
	 * Return the string variable name. This method will be used when 
	 * other class need to use or display the name. 
	 * 
	 * @return name  a String variable
	 */
	public String getName(){		
		return name;
	}
	
	/**
	 * Set up the reason and use <code>notifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a reason to a new 
	 * destination or editing the reason of a current destination.
	 * 
	 * @param reason  a String variable
	 */
	public void setReason(String reason){
		this.reason=reason;
		notifyListeners();
	}
	
	/**
	 * Return the string variable reason. This method will be used when 
	 * other class need to use or display the reason. 
	 * 
	 * @return reason  a String variable
	 */
	public String getReason(){		
		return reason;
	}
	
	/**
	 * This method will combine the name and the reason of the destination to
	 * one big String.
	 * 
	 * @return ""  a String variable
	 */
	public String toString(){
		return name+'\n'+reason;
	}

}
