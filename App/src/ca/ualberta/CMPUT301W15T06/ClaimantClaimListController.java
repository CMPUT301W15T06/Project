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
* @version 04/07/2015
* @see java.io.IOException
* @see java.text.ParseException
* @see java.util.Date
* @see com.google.gson.JsonElement
*/
public class ClaimantClaimListController {
	
	/**
	 * Set a <code>User</code> object user with initial 
	 * default value null.
	 */
	private User user=null;


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
	 * This method will add a edible claim to claim list and notify all the Listener in the listeners and modelListener list.
	 * 
	 * @throws NetWorkException
	 */
	public void addClaim() throws NetWorkException {
		// TODO Auto-generated method stub
		ModifiableClaim claim=new ModifiableClaim(user.getUserName());
		
		claim.addModelListener(new Listener() {
			
			@Override
			public void update() throws NetWorkException {
				// TODO Auto-generated method stub
				user.notifyListeners();
			}
		});
		user.getClaimList().add(claim);
		user.notifyListeners();
		AppSingleton.getInstance().setCurrentClaim(claim);
	}

	/**
	 * Add a tag with its id number to the claim and notify all the Listener in the listeners and modelListener list.
	 * 
	 * @param id  a long variable that represent the id of the tag
	 * @throws NetWorkException
	 */
	public void addTag(long id) throws NetWorkException {
		// TODO Auto-generated method stub
		user.getFilterTagIDList().add(id);
		user.notifyListeners();
	}

	/**
	 * Remove a tag with its id number to the claim and notify all the Listener in the listeners and modelListener list.
	 * 
	 * @param id  a long variable that represent the id of the tag
	 * @throws NetWorkException
	 */
	public void removeTag(long id) throws NetWorkException {
		// TODO Auto-generated method stub
		user.getFilterTagIDList().remove(id);
		user.notifyListeners();
	}

	/**
	 * Set a filter to the claim list by calling <code>setFilter()</code>.
	 * 
	 * @throws NetWorkException
	 */
	public void filter() throws NetWorkException {
		// TODO Auto-generated method stub
		user.setFilter(true);
	}

	/**
	 * Show all the information in claim list by calling <code>setFilter()</code>.
	 * 
	 * @throws NetWorkException
	 */
	public void showAll() throws NetWorkException {
		// TODO Auto-generated method stub
		user.setFilter(false);
	}
	

}
