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
/**
 * This <code>ClaimantEditDestinationController</code> is a controller class
 * of <code>Claim</code> and <code>Destination</code> class. By calling this class, 
 * user(claimant) can edit the detail information of a <code>Destination</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 */
public class ClaimantEditDestinationController {

	/**
	 * Set a Destination object dest to represent the destination in the claim.
	 */
	private Destination dest;
	
	/**
	 * Set the Destination object to the currentDestination that a user just entered. 
	 * 
	 * @param currentDestination
	 */
	public ClaimantEditDestinationController(Destination currentDestination) {
		// TODO Auto-generated constructor stub
		dest=currentDestination;
	}

	/**
	 * This method will allow user to edit the name and reason of the destination.
	 * 
	 * @param name  the name of the destination (like "Edmonton")
	 * @param reason  the reason of travel (like "Business Trip")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void editDestination(String name,String reason) throws StatusException, NetWorkException{
		dest.setName(name);
		dest.setReason(reason);
	}

}
