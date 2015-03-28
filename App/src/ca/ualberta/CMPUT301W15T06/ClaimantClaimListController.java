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

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.google.gson.JsonElement;

/**
* This <code>ClaimantClaimListController</code> class controls the action of 
* <code>User</code> for claimant. This controller class will displays a 
* list of <code>Claim</code>, add new <code>Claim</code> to the <code>User</code>,
* change the status and delete <code>Claim</code>. It will be used when the 
* claimant asks to access to the <code>User</code>.
* 
* @author CMPUT301W15T06
* @version 03/16/2015
* @see java.io.IOException
* @see com.google.gson.JsonElement
*/
public class ClaimantClaimListController {
	
	/**
	 * Set a <code>User</code> object user with initial 
	 * default value null.
	 */
	private User user=null;
	/**
	 * Set a <code>Claim</code> object user with initial 
	 * default value null.
	 */
	private Claim claim=null;
	
	
	/**
	 * General construction. Create a new claim.
	 * 
	 * @param claim  a Claim object
	 */
	public ClaimantClaimListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}

	/**
	 * General construction. Create a new user.
	 * 
	 * @param user  a User object
	 */
	public ClaimantClaimListController(User user) {
		// TODO Auto-generated constructor stub
		this.user=user;
	}

	/**
	 * Add a new claim to the user and change status to 
	 * "Submitted". It also checks warnings and errors to
	 * prevent crush.
	 * 
	 * @throws StatusException
	 * @see java.io.IOException
	 */
	public void submit() throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		claim.setStatus("Submitted");
	}

	/**
	 * Remove a claim from the user and notify all Listener in listeners.
	 * It also checks warnings and errors to prevent crush.
	 * 
	 * @param claim  a Claim object
	 * @throws StatusException
	 * @see java.io.IOException
	 */
	public void delete(Claim claim) throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		user.getClaimList().remove(claim);
		user.notifyListeners();
		
	}

	public void addTag(long l) {
		// TODO Auto-generated method stub
		claim.getTagIDList().add(l);
		claim.notifyListeners();
		
	}

	public void removeTag(long l) {
		// TODO Auto-generated method stub
		claim.getTagIDList().remove(l);
		claim.notifyListeners();
	}

	public void addClaim() {
		// TODO Auto-generated method stub
		Claim claim=new Claim();
		
		claim.addModelListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				user.notifyListeners();
			}
		});
		user.getClaimList().add(claim);
		user.notifyListeners();
		AppSingleton.getInstance().setCurrentClaim(claim);
	}
	

}
