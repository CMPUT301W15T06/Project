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
* This <code>ClaimantDeleteItemController</code> class controls the action of 
* <code>ItemList</code> and <code>Claim</code> for claimant. This controller 
* class will remove a <code>Item</code> from <code>ItemList</code> in the
* <code>Claim</code>. 
* 
* @author CMPUT301W15T06
* @version 03/16/2015
*/
public class ClaimantDeleteItemController {
	
	/**
	 * Set a <code>Claim</code> object claimList with initial 
	 * default value null.
	 */
	private Claim claim=null;
	
	
	/**
	 * General construction. Create a new claim.
	 * 
	 * @param claim  a Claim object
	 */
	public ClaimantDeleteItemController(Claim claim){
		this.claim=claim;
	}
	
	/**
	 * Remove a Item from the itemList and notify all Listener in listeners.
	 * It also checks warnings and errors to prevent crush.
	 * 
	 * @param item  a Item object
	 * @throws StatusException
	 * @throws NetWorkException 
	 */
	public void removeItem(Item item) throws StatusException, NetWorkException{
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		claim.getItemList().remove(item);
		claim.notifyListeners();
	}
}
