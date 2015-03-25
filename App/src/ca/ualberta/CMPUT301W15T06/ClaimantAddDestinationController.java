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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * This <code>ClaimantAddDestinationController</code> is a controller class
 * of <code>Destination</code> and <code>Claim</code>. By calling 
 * this class, user(claimant) can add a <code>Claim</code> with its 
 * detail to the claimList.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.text.DateFormat
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.ArrayList
 * @see java.util.Date
 * @see java.util.Locale
 */
public class ClaimantAddDestinationController {
	
	/**
	 * Set Claim object claim to record the claim detail. The initial
	 * default value is null.
	 */
	private Claim claim=null;
	
	
	/**
	 * General construction. This method is will create a Claim object 
	 * claim for other classes and methods to use.
	 * 
	 * @param claim  a Claim object
	 */
	public ClaimantAddDestinationController(Claim claim){
		this.claim=claim;
	}
	
	/**
	 * This method will create a new String variable destination and reason
	 * to the claim. Then set a boolean variable missValueto to justify
	 * the <code>Claim</code> and <code>Destination</code>. It also gonna 
	 * notify all Listener in listeners list about this update.
	 * 
	 * @param dest  a String variable
	 * @param reason  a String variable
	 * @throws StatusException
	 * @see java.text.DateFormat
	 * @see java.text.ParseException
	 * @see java.text.SimpleDateFormat
	 * @see java.util.ArrayList
	 * @see java.util.Date
	 * @see java.util.Locale
	 */
	public void addDestination(String dest, String reason) throws StatusException {
		
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		Destination destination=new Destination(dest);
		destination.setReason(reason);
		if (dest.equals("")||reason.equals("")){
			destination.setMissValue(true);
		}else{
			destination.setMissValue(false);
		}
		
		destination.addModelListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				claim.notifyListeners();
			}
		});
		claim.getDestinationList().add(destination);	
		claim.notifyListeners();
	}
}
