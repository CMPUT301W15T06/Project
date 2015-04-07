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
 * This <code> ClaimantTagListController</code> is a controller class
 * of <code>Tag</code> and <code> ClaimantTagListActivity</code>. By calling this class, 
 * user(claimant) can add a new tag, delete or change an existing 
 * tag to an <code>Item</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 */
public class ClaimantTagListController {

	/**
	 * Set a User object user who is currenly using the application.
	 */
	private User user=null;
	
	/**
	 * General Constructor. Set the user to an exsiting user.
	 * 
	 * @param u  a User object who is currenly using the application
	 */
	public ClaimantTagListController(User u) {
		// TODO Auto-generated constructor stub
		user=u;
	}

	/**
	 * Add new tag to the item and notify all the Listener in the listeners and modelListeners. 
	 * 
	 * @param tagName  the name of the tag
	 * @throws NetWorkException
	 */
	public void addTag(String tagName) throws NetWorkException {
		// TODO Auto-generated method stub
		Tag tag=new Tag(tagName);
		user.getTagList().add(tag);
		tag.addModelListener(new Listener() {
			
			@Override
			public void update() throws NetWorkException {
				// TODO Auto-generated method stub
				user.notifyListeners();
			}
		});
		user.getFilterTagIDList().add(tag.getID());
		
		user.notifyListeners();
	}

	/**
	 * Delete a existing tag to the item and notify all the Listener in the listeners and modelListeners. 
	 * 
	 * @param tag  an existing tag object
	 * @throws NetWorkException
	 */
	public void delete(Tag tag) throws NetWorkException {
		// TODO Auto-generated method stub
		for (Claim claim:user.getClaimList()){
			claim.getTagIDList().remove(tag.getID());
		}
		user.getFilterTagIDList().remove(tag.getID());
		user.getTagList().remove(tag);
		user.notifyListeners();
	}
	
	/**
	 * Edit an existing tag to the item. 
	 * 
	 * @param tag  an existing tag object
	 * @throws NetWorkException
	 */
	public void edit(Tag tag, String string) throws NetWorkException {
		// TODO Auto-generated method stub
		tag.setName(string);
	}

}
