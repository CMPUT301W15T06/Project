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
 * This <code>ClaimantItemListControllerr</code> is a controller class
 * of <code>Item</code> and <code>ClaimantItemListActivity</code>. By calling this class, 
 * user(claimant) can add a new flag or change a existing 
 * flag to an <code>Item</code>, add and delete an <code>Item</code>, add and remove tags 
 * of an <code>Item</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 */
public class ClaimantItemListController {

	/**
	 * Set a Claim object claim with a default value of null.
	 */
	private Claim claim=null;
	
	
	/**
	 * General construction. Set up the claim of the ClaimantItemListController 
	 * with an existing claim.
	 * 
	 * @param claim  an Claim object
	 */
	public ClaimantItemListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}
	
	/**
	 * Change flag when needed. If there's a existing flag, set it to be
	 * false, otherwise set it to be true. This method also uses
	 * <code>AppSinleton</code> class to use shared data.
	 * 
	 * @throws StatusException
	 * @throws NetWorkException 
	 */
	public void changeFlag() throws StatusException, NetWorkException{
		Item item=AppSingleton.getInstance().getCurrentItem();
		if (item.getFlag()){
			item.setFlag(false);
		}else{
			item.setFlag(true);
		}
	}

	/**
	 * This method will submit the item along with the claim and notify all the 
	 * Listener in the listeners and modelListeners. 
	 * 
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void sumbmit() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (!AppSingleton.getInstance().isEditable()){
			throw new StatusException();					
		}	
		User user=AppSingleton.getInstance().getCurrentUser();
		user.getClaimList().remove(claim);
		claim=new UnmodifiableClaim(claim,"Submitted");
		user.getClaimList().add(claim);
		AppSingleton.getInstance().setCurrentClaim(claim);
		user.notifyListeners();
		
	}

	/**
	 * This method will delete the item in the claim and notify all the 
	 * Listener in the listeners and modelListeners. 
	 * 
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void delete() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (!AppSingleton.getInstance().isEditable()){
			throw new StatusException();					
		}	
		User user=AppSingleton.getInstance().getCurrentUser();
		user.getClaimList().remove(claim);
		user.notifyListeners();
	}

	/**
	 * This method will add the item along in claim and notify all the 
	 * Listener in the listeners and modelListeners. 
	 * 
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void addItem() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (!AppSingleton.getInstance().isEditable()){
			throw new StatusException();					
		}	
		ModifiableItem item=new ModifiableItem();
		
		item.addModelListener(new Listener() {
			
			@Override
			public void update() throws NetWorkException {
				// TODO Auto-generated method stub
				claim.notifyListeners();
			}
		});
		
		claim.getItemList().add(item);
		claim.notifyListeners();
		AppSingleton.getInstance().setCurrentItem(item);
	}

	/**
	 * This method will add a tag by using tagID to the item and notify all the 
	 * Listener in the listeners and modelListeners. 
	 * 
	 * @param id
	 * @throws NetWorkException
	 */
	public void addTag(long id) throws NetWorkException {
		// TODO Auto-generated method stub
		claim.getTagIDList().add(id);
		claim.notifyListeners();
	}

	/**
	 * 
	 * This method will remove a tag by using tagID to the item and notify all the 
	 * Listener in the listeners and modelListeners. 
	 * 
	 * @param id
	 * @throws NetWorkException
	 */
	public void removeTag(long id) throws NetWorkException {
		// TODO Auto-generated method stub
		claim.getTagIDList().remove(id);
		claim.notifyListeners();
	}

}
