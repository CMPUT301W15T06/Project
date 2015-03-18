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
 * This <code>ClaimantAddItemController</code> class is a controller class
  * of <code>Item</code>, <code>ItemList</code> and <code>Claim</code>. 
  * By calling this class, user(claimant) can add a <code>Item/code> with its 
  * detail to the itemList in claim.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.text.DateFormat
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * @see java.util.Locale
 */
package ca.ualberta.CMPUT301W15T06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClaimantAddItemController {
	
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
	public ClaimantAddItemController(Claim claim){
		this.claim=claim;
	}
	
	/**
	 * This method will create a new String variable dateSTR, category, 
	 * description and currency, and a Double variable amount, all to the 
	 * Item. Then set a boolean variable missValueto to justify the 
	 * <code>Item</code>. It also gonna notify all Listener in listeners 
	 * list about this update.
	 * 
	 * @param dateSTR  a String variable
	 * @param category  a String variable
	 * @param description  a String variable
	 * @param amount  a Double variable
	 * @param currency  a String variable
	 * @throws StatusException
	 * @see java.text.DateFormat
	 * @see java.text.ParseException
	 * @see java.text.SimpleDateFormat
	 * @see java.util.Date
	 * @see java.util.Locale
	 */
	public void addItem(String dateSTR, String category, String description, Double amount, String currency) throws StatusException{
		
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		
		Date date = null;
		
		try {
			date = AppSingleton.getDateFormat().parse(dateSTR);
		} catch (ParseException e) {
			date=null;
		}
		Item item=new Item();
		item.setDate(date);
		item.setCategory(category);
		item.setDescription(description);
		item.setAmount(amount);
		item.setCurrency(currency);
		
		if (date.equals("")||description.equals("")||amount==null){
			item.setMissValue(true);
		}else{
			item.setMissValue(false);
		}
		
		item.addModelListener(new Listener() {
			
			@Override
			public void update() {
				claim.notifyListeners();				
			}
		});
		
		claim.getItemList().add(item);
		claim.notifyListeners();

	}
}
