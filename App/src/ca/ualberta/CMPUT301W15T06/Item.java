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
 * @see java.util.Date
 */
package ca.ualberta.CMPUT301W15T06;

import java.util.Date;



public class Item extends AppModel{

	/**
	 * Set the Date object date to record the date of <code>Item</code> expenses.
	 */
	private Date date;
	/**
	 * Set the String variable category to record the category of the item expenses.
	 */
	private String category;
	/**
	 * Set the String variable description to add comment and descriptions to 
	 * the <code>Item</code>.
	 */
	private String description;
	/**
	 * Set Double variable amount to record the  amount of <code>Item</code> expenses.
	 */
	private Double amount;
	/**
	 * Set String variable currency to record the currency of the <code>Item</code> 
	 * expenses.
	 */
	private String currency;
	/**
	 * Set a Receipt object receipt to contain a receipt of the <code>Item</code> 
	 * expenses. The initial default value is null.
	 */
	private Receipt recipt=null;
	/**
	 * Set a boolean variable flag to add a flag to <code>Item</code>. The initial 
	 * default value is false.
	 */
	private boolean flag=false;
	

	public Item() {	
	}
	

	public void setDate(Date itemDate) {
		date=itemDate;
		notifyListeners();
	}

	public Date getDate() {
		return date;
	}

	public void setCategory(String category) {
		this.category=category;
		notifyListeners();
	}

	public String getCategory() {
		return category;
	}

	public void setDescription(String description) {
		this.description=description;
		notifyListeners();
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(Double amount) {
		this.amount=amount;
		notifyListeners();
	}

	public Double getAmount() {
		return amount;
	}

	public void setCurrency(String currency) {
		this.currency=currency;
		notifyListeners();
	}

	public String getCurrency() {
		return currency;
	}
	
	public void setFlag(boolean b) {
		flag=b;
		notifyListeners();
	}

	public boolean getFlag() {
		return flag;
	}
	

	
	public String toString(){
		return "Date: "+AppSingleton.formatDate(date)+'\n'+"Category: "+category+'\n'+"Description: "+description+'\n'+"Spend: "+(amount==null?"   ":amount)+' '+currency+'\n'+
				"Photographic Receipt: "+(recipt==null?"Not Have":"Have")+'\n'+"Incompleteness: "+(flag?"YES":"NO");	
	}
	
	public void setRecipt(Receipt recipt) {
		
	}
	
	public Receipt getRecipt() {
		return null;
	}
	
	public boolean takePhoto(){
		return false;
	}
	public static boolean getPhoto(){
		return false;
	}
	public boolean deletePhoto(){
		return false;
	}
	public static int photoSize(){
		return 0;
	}
	public void savePhoto(){
		
	}



	public boolean infoComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setIncomplete() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
