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

import java.util.Date;

import android.location.Location;

/**
 * The <code>Item</code> class is an sub-class of <code>AppModel</code>.
 * The class allows the user to call <code>ClaimantAddItemController</code> 
 * to create a new item, set up and edit item detail(including date, category, 
 * description and amount), call <code>receipt</code> class to create a receipt.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.util.Date
 */
public class Item extends AppModel{

	/**
	 * Set a Date object date to record the date of the <code>Item</code> expenses.
	 * 
	 * @see java.util.Date
	 */
	protected Date date;
	/**
	 * Set a String variable category to record the category of 
	 * the <code>Item</code> expenses.
	 */
	protected String category;
	/**
	 * Set a String variable description to add a comment description to the
	 * <code>Item</code> expenses.
	 */
	protected String description;
	/**
	 * Set a Double object amount to record the <code>Item</code> expenses
	 * amount.
	 */
	protected Double amount;
	/**
	 * Set a String variable currency to record the appropriate currency of
	 * the <code>Item</code> expenses.
	 */
	protected String currency;
	/**
	 * Set a Receipt object receipt to create a receipt of the 
	 * <code>Item</code> expenses. The initial default value is null.
	 */
	protected Receipt receipt=null;
	/**
	 * Set a boolean variable flag to add flag to the <code>Item</code> expenses.
	 * The initial default value is false.
	 */
	protected boolean flag=false;
	protected Location location;
	

	public Item() {
		super();
		receipt=new ModifiableReceipt();;
		
		date=AppSingleton.removeTime(new Date());
		
		category="air fare";
		currency="CAD";
		
		description="";
		location=null;
	}
	
	public Item(Item item) {
		// TODO Auto-generated constructor stub
		super(item);
		date=item.getDate();
		category=item.getCategory();
		description=item.getDescription();
		amount=item.getAmount();
		currency=item.getCurrency();
		flag=item.getFlag();
		location=item.getLocation();
		
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
	 * Return the String variable category. This method will be used when other 
	 * class need to use or display the category of <code>Item</code> expenses. 
	 * 
	 * @return category  a String variable
	 */
	public String getCategory() {
		return category;
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
	 * Return the Double variable amount. This method will be used when other 
	 * class need to use or display the amount of <code>Item</code> expenses. 
	 * 
	 * @return amount  a Double variable
	 */
	public Double getAmount() {
		return amount;
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
				"Photographic Receipt: "+(receipt.getPhotoStr()==null?"Not Have":"Have")+'\n'+"Incompleteness: "+(flag?"YES":"NO")
				+'\n'+"Geolocation: "+(location!=null?"HAVE":"NOT HAVE");	
	}
	
	/**
	 * Return the Receipt object receipt as default value null. This method 
	 * will be used when other class need to use or display the receipt of 
	 * <code>Item</code> expenses.
	 * 
	 * @return null  a Receipt object
	 */
	public Receipt getRecipt() {
		return receipt;
	}
	
	/**
	 * 
	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
		
		receipt.addListener(l);
	}
	
	public void addModelListener(Listener l){
		if (!getModelListeners().contains(l)){
			getModelListeners().add(l);
		}
		receipt.addModelListener(l);
	}
	


	@Override
	public boolean getMissValue() {
		boolean result=receipt.getMissValue();
		
		if(amount==null){
			result=true;
		}
		
		if(description.equals("")){
			result=true;
		}
		
		if(flag==true){
			result=true;
		}
			
		return result;	
	}
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) throws NetWorkException, StatusException {
	}
	
	public void setDate(Date itemDate) throws StatusException, NetWorkException {
	}


	public  void setCategory(String category) throws StatusException, NetWorkException {
	}


	public  void setDescription(String description) throws StatusException, NetWorkException {
	}


	public  void setAmount(Double amount) throws StatusException, NetWorkException {
	}


	public  void setCurrency(String currency) throws StatusException, NetWorkException {
	}

	public  void setFlag(boolean b) throws StatusException, NetWorkException {
	}
	
}
