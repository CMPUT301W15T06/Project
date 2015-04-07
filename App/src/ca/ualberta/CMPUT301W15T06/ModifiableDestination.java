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
 * This is a subclass of Destination. It will check if the Destination is edible.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see android.location.Location
 */
public class ModifiableDestination extends Destination {
	

	/**
	 * This method will call the super class to modify the Destination.
	 * 
	 * @param dest  a Destination object
	 */
	public ModifiableDestination(Destination dest){
		super(dest);
	}

	/**
	 * This method will call the super class to modify the Destination.
	 */
	public ModifiableDestination(){
		super();
	}
	
	/**
	 * Set a new name of the destination. Check for warnings to prevent crush.
	 * 
	 * @param name  the name of the destination (like "Trip1")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void setName(String name) throws NetWorkException {
		this.name=name;
		notifyListeners();
	}

	/**
	 * Set a new reason of the destination. Check for warnings to prevent crush.
	 * 
	 * @param reason  the reason of the destination (like "Business")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void setReason(String reason) throws NetWorkException {
		this.reason=reason;
		notifyListeners();
	}

	/**
	 * Set a new location of the destination. Check for warnings to prevent crush.
	 * 
	 * @param location  the location of the destination (like "Edmonton")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setLocation(Location location) throws NetWorkException {
		this.location = location;
		notifyListeners();
	}

}
