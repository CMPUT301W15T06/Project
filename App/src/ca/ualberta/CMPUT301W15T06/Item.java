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
 * The <code>Item</code> class is an sub-class of <code>AppModel</code>.
<<<<<<< HEAD
 * This class can set up a claim with the information including claimant's name, 
 * travel begin and end date, travel status, item list, destination list with reason, 
 * approve status, comment and tag list. The class also allows the user to call
 * <code>ClaimantAddItemController</code> to create a new item, call 
 * <code>ClaimantEditItemController</code> class to edit and make changes to 
 * current item, set up the category of item, call <code>Receipt</code> to set up and 
 * edit the photo receipt.
 * 
 * @author CMPUT301W15T06
 * @version 03/07/2015
=======
 * The class allows the user to call <code>ClaimantAddItemController</code> 
 * to create a new item, set up and edit item detail(including date, category, 
 * description and amount), call <code>receipt</code> class to create a receipt.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
>>>>>>> fa7674dceeaf733bdd9de48163710f0c8356d4cc
 * @see java.util.Date
 */
package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

public class Item extends AppModel{

	/**
	 * Set a Date object date to record the date of the <code>Item</code> expenses.
	 * 
	 * @see java.util.Date
	 */
	private Date date;
	/**
	 * Set a String variable category to record the category of 
	 * the <code>Item</code> expenses.
	 */
	private String category;
	/**
	 * Set a String variable description to add a comment description to the
	 * <code>Item</code> expenses.
	 */
	private String description;
	/**
	 * Set a Double object amount to record the <code>Item</code> expenses
	 * amount.
	 */
	private Double amount;
	/**
	 * Set a String variable currency to record the appropriate currency of
	 * the <code>Item</code> expenses.
	 */
	private String currency;
	/**
	 * Set a Receipt object receipt to create a receipt of the 
	 * <code>Item</code> expenses. The initial default value is null.
	 */
	private Receipt recipt=null;
	/**
	 * Set a boolean variable flag to add flag to the <code>Item</code> expenses.
	 * The initial default value is false.
	 */
	private boolean flag=false;
	

	public Item() {	
	}
	
	/**
	 * This method will set the date of <code>Item</code> expenses and 
	 * notify Listener in listeners about this update.
	 * 
	 * @param itemDate  a Date object
	 * @see java.util.Date
	 */
	public void setDate(Date itemDate) {
		date=itemDate;
		notifyListeners();
	}

	/**
	 * Return the Date object date. This method will be used when other 
	 * class need to use or display the date of <code>Item</code> expenses. 
	 * 
	 * @return date  a Date object
	 * @see java.util.Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * This method will set the category of <code>Item</code> expenses and 
	 * notify Listener in listeners about this update.
	 * 
	 * @param category  a String variable
	 */
	public void setCategory(String category) {
		this.category=category;
		notifyListeners();
	}

	/**
	 * Return the String variable category. This method will be used when other 
	 * class need to use or display the category of <code>Item</code> expenses. 
	 * 
	 * @return category  a String variable
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * This method will add a description to <code>Item</code> expenses and 
	 * notify Listener in listeners about this update.
	 * 
	 * @param description  a String variable
	 */
	public void setDescription(String description) {
		this.description=description;
		notifyListeners();
	}

	/**
	 * Return the String variable description. This method will be used when other 
	 * class need to use or display the description of <code>Item</code> expenses. 
	 * 
	 * @return description  a String variable
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method will set an amount to <code>Item</code> expenses and 
	 * notify Listener in listeners about this update.
	 * 
	 * @param amount  a Double variable
	 */
	public void setAmount(Double amount) {
		this.amount=amount;
		notifyListeners();
	}

	/**
	 * Return the Double variable amount. This method will be used when other 
	 * class need to use or display the amount of <code>Item</code> expenses. 
	 * 
	 * @return amount  a Double variable
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * This method will set a currency to the amount of <code>Item</code> 
	 * expenses and notify Listener in listeners about this update.
	 * 
	 * @param currency  a String variable
	 */
	public void setCurrency(String currency) {
		this.currency=currency;
		notifyListeners();
	}

	/**
	 * Return the String variable currency. This method will be used when other 
	 * class need to use or display the currency of <code>Item</code> 
	 * expenses amount.
	 * 
	 * @return currency  a String variable
	 */
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * This method will set a flag to the <code>Item</code> 
	 * expenses and notify Listener in listeners about this update.
	 * 
	 * @param b  a boolean variable
	 */
	public void setFlag(boolean b) {
		flag=b;
		notifyListeners();
	}

	/**
	 * Return the boolean variable flag. This method will be used when other 
	 * class need to use or display the flag of <code>Item</code> expenses.
	 * 
	 * @return b  a boolean variable
	 */
	public boolean getFlag() {
		return flag;
	}
	
	/**
	 * This method translates date from Date object to string object. It also 
	 * translates receipt from Receipt object to String object. Then combines 
	 * date, category, description, receipt and flag all together into one big 
	 * String. It will be operate when the program need the <code>Item</code> 
	 * detail to display.
	 * 
	 * @return ""  a String object combine with many small String variable
	 */
	public String toString(){
		return "Date: "+AppSingleton.formatDate(date)+'\n'+"Category: "+category+'\n'+"Description: "+description+'\n'+"Spend: "+(amount==null?"   ":amount)+' '+currency+'\n'+
				"Photographic Receipt: "+(recipt==null?"Not Have":"Have")+'\n'+"Incompleteness: "+(flag?"YES":"NO");	
	}
	
	/**
	 * This method will create a receipt for the <code>Item</code> 
	 * expenses.
	 * 
	 * @param receipt  a Receipt object
	 */
	public void setRecipt(Receipt receipt) {
		
	}
	
	/**
	 * Return the Receipt object receipt as default value null. This method 
	 * will be used when other class need to use or display the receipt of 
	 * <code>Item</code> expenses.
	 * 
	 * @return null  a Receipt object
	 */
	public Receipt getRecipt() {
		return null;
	}
	
	/**
	 * This method will justify if the program has taken a photo of the 
	 * <code>Item</code> expenses detail. The default value of the boolean 
	 * variable is false.
	 * 
	 * @return false  a boolean variable
	 */
	public boolean takePhoto(){
		return false;
	}
	
	/**
	 * This method will justify if the program can get the photo of the 
	 * <code>Item</code> expenses detail. The default value of the boolean 
	 * variable is false.
	 * 
	 * @return false  a boolean variable
	 */
	public static boolean getPhoto(){
		return false;
	}
	
	/**
	 * This method will justify if the program can delete the photo of the 
	 * <code>Item</code> expenses detail. The default value of the boolean 
	 * variable is false. 
	 * 
	 * @return false  a boolean variable
	 */
	public boolean deletePhoto(){
		return false;
	}
	
	/**
	 * This method will return the size of the photo. It will be called when 
	 * other classes need to use or display the size of the photo. The default 
	 * value is 0.
	 * 
	 * @return 0  an integer variable
	 */
	public static int photoSize(){
		return 0;
	}
	public void savePhoto(){
		
	}

	/**
	 * Check if the information of the item is fulfilled and return a
	 * boolean value with default as false. This method will be used 
	 * when user finished editing Item.
	 * 
	 * @return false  a boolean value
	 */
	public boolean infoComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setIncomplete() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
