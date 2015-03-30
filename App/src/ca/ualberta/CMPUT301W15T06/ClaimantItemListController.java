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
 * This <code>FlagController</code> is a controller class
 * of flag of <code>Item</code>. By calling this class, 
 * user(claimant) can add a new flag or change a existing 
 * flag to an <code>Item</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 */
public class ClaimantItemListController {

	/**
	 * Set a Item object item with a default value of null.
	 */
	private Claim claim=null;
	
	
	/**
	 * General construction. Set up the FlagController by 
	 * creating a new item with currentItem 
	 * 
	 * @param currentItem  an Item object
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

	public void sumbmit() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (claim.getStatus().equals("Submitted")||claim.getStatus().equals("Approved")){
			throw new StatusException();					
		}	
		claim.setStatus("Submitted");	
	}

	public void delete() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (claim.getStatus().equals("Submitted")||claim.getStatus().equals("Approved")){
			throw new StatusException();					
		}	
		User user=AppSingleton.getInstance().getCurrentUser();
		user.getClaimList().remove(claim);
		user.notifyListeners();
	}

	public void addItem() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (claim.getStatus().equals("Submitted")||claim.getStatus().equals("Approved")){
			throw new StatusException();					
		}	
		Item item=new Item();
		
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

	public void addTag(long id) throws NetWorkException {
		// TODO Auto-generated method stub
		claim.getTagIDList().add(id);
		claim.notifyListeners();
	}

	public void removeTag(long id) throws NetWorkException {
		// TODO Auto-generated method stub
		claim.getTagIDList().remove(id);
		claim.notifyListeners();
	}

}
