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
 * @version 03/16/2015
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
	 * @param item a Item object
	 */
	public ClaimantEditItemController(Item item){
		this.item=item;
	}
	

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

	public void editDescription(String description) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setDescription(description);
	}

	public void editAmount(Double amount) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setAmount(amount);
	}

	public void editCurrency(String currency) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setCurrency(currency);
	}

	public void editCategory(String category) throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		item.setCategory(category);
	}
}
