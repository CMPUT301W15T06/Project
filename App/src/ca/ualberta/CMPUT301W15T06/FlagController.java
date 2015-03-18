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
 
 /**
  * This <code>FlagController</code> is a controller class
  * of flag of <code>Item</code>. By calling this class, 
  * user(claimant) can add a new flag or change a existing 
  * flag to an <code>Item</code>.
  * 
  * @author CMPUT301W15T06
  * @version 03/16/2015
  */
package ca.ualberta.CMPUT301W15T06;

public class FlagController {

	/**
	 * Set a Item object item with a default value of null.
	 */
	private Item item=null;
	
	
	/**
	 * General construction. Set up the FlagController by 
	 * creating a new item with currentItem 
	 * 
	 * @param currentItem  an Item object
	 */
	public FlagController(Item currentItem) {
		// TODO Auto-generated constructor stub
		item=currentItem;
	}
	
	/**
	 * Change flag when needed. If there's a existing flag, set it to be
	 * false, otherwise set it to be true. This method also uses
	 * <code>AppSinleton</code> class to use shared data.
	 * 
	 * @throws StatusException
	 */
	public void changeFlag() throws StatusException{
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		if (item.getFlag()){
			item.setFlag(false);
		}else{
			item.setFlag(true);
		}
	}

}
