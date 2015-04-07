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

import java.util.Date;

import android.util.Log;

/**
 * This is a subclass of Claim. It will check if the Claim is edible.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.Date
 * @see android.util.Log
 */
public class UnmodifiableClaim extends Claim {

	/**
	 * Switch sub-class.
	 * 
	 * @param oldClaim  a Claim object
	 * @param status  a String object
	 */
	public UnmodifiableClaim(Claim oldClaim,String status) {
		super(oldClaim,status);
		// TODO Auto-generated constructor stub

		for (Item item:oldClaim.getItemList()){
			itemList.add(new UnmodifiableItem(item));
		}
		
		for (Destination dest:oldClaim.getDestinationList()){
			destinationList.add(new UnmodifiableDestination(dest));
		}	
		
	}
	
	/**
	 * This method will call the super class to modify the Claim.
	 */
	public UnmodifiableClaim() {
		super();
	}
	
	/**
	 * Set the beginning date of the travel. Throw exceptions if necessary.
	 * 
	 * @param beginDate  the date of when the travel started (like "15-Mar-2015")
	 * @throws WrongEndDateException
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setBeginDate(Date beginDate) throws StatusException{
		throw new StatusException();							
	}
	
	/**
	 * Set the ending date of the travel. Throw exceptions if necessary.
	 * 
	 * @param endDate  the date of when the travel ended (like "31-Mar-2015")
	 * @throws WrongEndDateException
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setEndDate(Date endDate) throws StatusException{
		throw new StatusException();							
	}


}
