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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This <code>ClaimantEditItemController</code> is a controller class
 * of <code>Item</code>. By calling this class, user(claimant) can edit 
 * the detail information of a <code>Item</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.text.DateFormat
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * @see java.util.Locale
 */
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
	 * @param item a Item object to represent an expense item in the claim
	 */
	public ClaimantEditItemController(Item item){
		this.item=item;
	}
	
	/**
	 * This method will set the date of the expense item and check warnings to prevent crush. 
	 * 
	 * @param string  the date of the travel expense (like "15-Mar-2015")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void editDate(String string) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		Date date = null;	
		try {
			date = AppSingleton.getDateFormat().parse(string);
		} catch (ParseException e) {
			date=null;
		}
		item.setDate(date);
	}

	/**
	 * This method will set the description of the expense item and check warnings to prevent crush. 
	 * 
	 * @param description  the description of the travel expense
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void editDescription(String description) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setDescription(description);
	}

	/**
	 * This method will set the expense amount of the expense item and check warnings to prevent crush. 
	 * 
	 * @param amount  the amount of the travel expense (like "150")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void editAmount(Double amount) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setAmount(amount);
	}

	/**
	 * This method will set the currency of the expense amount of the expense item and 
	 * check warnings to prevent crush. 
	 * 
	 * @param currency  the currency of the travel expense amount (like "CAD")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void editCurrency(String currency) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setCurrency(currency);
	}

	/**
	 * This method will set the category of the expense item and check warnings to prevent crush.
	 * 
	 * @param category  the category of the travel expense (like "air fare")
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void editCategory(String category) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setCategory(category);
	}
}
