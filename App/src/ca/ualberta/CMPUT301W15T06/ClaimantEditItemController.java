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
  * This <code>ClaimantEditItemController</code> is a controller class
  * of <code>Item</code>. By calling this class, user(claimant) can edit 
  * the detail information of a <code>Item</code>.
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


public class ClaimantEditItemController {
	
	/**
	 * Set a Item object item with a default value 
	 * as null
	 */
	private Item item;

	
	/**
	 * General construction. This method will create a Item object 
	 * item for other classes and methods to use.
	 * 
	 * @param item a Item object
	 */
	public ClaimantEditItemController(Item item){
		this.item=item;
	}
	
	/**
	 * This class will allows claimants edit the detail information
	 * of the claim, including date, category, description, amount
	 * and currency. It will also checks <code>StatusException()</code> 
	 * warning to prevent crush.
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
	 */
	public void editItem(String dateSTR, String category, String description, Double amount, String currency) throws StatusException{	
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		
		Date date = null;
		
		try {
			date = AppSingleton.getDateFormat().parse(dateSTR);
		} catch (ParseException e) {
			date=null;
		}
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


	}
}
