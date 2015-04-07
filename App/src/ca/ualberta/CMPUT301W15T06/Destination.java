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

import android.location.Location;

/**
 * The <code>Destination</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a <code>Destination</code> with a name and a reason. 
 * This method will be called when users need to add a <code>Destination</code>,
 * edit a <code>Destination</code> or access to <code>Destination</code> detail.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 */
public class Destination extends AppModel{
	
	/**
	 * Set protected string name to record destination name. 
	 */
	protected String name;
	/**
	 * Set protected string reason to record the reason of 
	 * the destination. 
	 */
	protected String reason;
	
	protected Location location;
	/**
	 * General constructor. This public method sets up a 
	 * Destination object with name.
	 * 
	 * @param name  a String variable
	 */
	public Destination(){
		super();
		name="";
		reason="";
		location=null;
	}
	
	/**
	 * This method will call <code>getName()</code>, <code>getReason()</code>, 
	 * and <code>getLocation()</code> to complete the destination information.
	 * 
	 * @param dest  the Destination object which contains the name, reason and location of a certain travel destination
	 */
	public Destination(Destination dest) {
		// TODO Auto-generated constructor stub
		super(dest);
		name=dest.getName();
		reason=dest.getReason();
		location=dest.getLocation();
		
	}

	/**
	 * Return the string variable name. This method will be used when 
	 * other class need to use or display the name. 
	 * 
	 * @return name  the name of the destination (like "Trip1")
	 */
	public String getName(){		
		return name;
	}
	
	/**
	 * Return the string variable reason. This method will be used when 
	 * other class need to use or display the reason. 
	 * 
	 * @return reason  the reason of the destination (like "Business")
	 */
	public String getReason(){		
		return reason;
	}
	
	/**
	 * This method will combine the name and the reason of the destination to
	 * one big String.
	 * 
	 * @return a conversion to string for display
	 */
	public String toString(){
		return name+'\n'+reason;
	}
	
	/**
	 * Return the Location object location. This method will be used when 
	 * other class need to use or display the reason. 
	 * 
	 * @return location  the location of the destination (like "Edmonton")
	 */
	public Location getLocation() {
		return location;
	}
	
	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		if (name.equals("")||reason.equals("")){
			return true;
		}
		return false;
	}

	/**
	 * Set a new name of the destination. Check for warnings to prevent crush.
	 * 
	 * @param name  the name of the destination (like "Trip1")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void setName(String name) throws StatusException, NetWorkException {
	}

	/**
	 * Set a new reason of the destination. Check for warnings to prevent crush.
	 * 
	 * @param reason  the reason of the destination (like "Business")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void setReason(String reason) throws StatusException, NetWorkException {
	}

	/**
	 * Set a new location of the destination. Check for warnings to prevent crush.
	 * 
	 * @param location  the location of the destination (like "Edmonton")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setLocation(Location location) throws NetWorkException, StatusException {
	}
}
