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
	 * General construction. Create a new user.
	 * 
	 * @param user  a User object
	 */
	public ClaimantClaimListController(User user) {
		// TODO Auto-generated constructor stub
		this.user=user;
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




	public void addTag(long id) {
		// TODO Auto-generated method stub
		user.getFilterTagIDList().add(id);
		user.notifyListeners();
	}




	public void removeTag(long id) {
		// TODO Auto-generated method stub
		user.getFilterTagIDList().remove(id);
		user.notifyListeners();
	}




	public void filter() {
		// TODO Auto-generated method stub
		user.setFilter(true);
	}




	public void showAll() {
		// TODO Auto-generated method stub
		user.setFilter(false);
	}
	

}
