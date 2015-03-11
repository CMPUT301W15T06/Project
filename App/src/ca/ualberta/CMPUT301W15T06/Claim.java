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
 * The <code>Claim</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a claim with the information including claimant's name, 
 * travel begin and end date, travel status, item list, destination list with reason, 
 * approve status, comment and tag list. The class also allows the user to call
 * <code>ClaimantAddClaimController</code> to create a new claim, set up and 
 * edit claim detail(such as name, start date and end date), set status, call 
 * <code>ClaimantAddDestinationController</code> class to add destination and reason, 
 * get item list from <code>ItemList</code> and get tag list from <code>TagList</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/07/2015
 * @see java.util.ArrayList
 */

package ca.ualberta.CMPUT301W15T06;


import java.util.ArrayList;
import java.util.Date;


public class Claim extends AppModel{
	/**
	 * Set private string name to record claimant's name. 
	 */
	private String name;
	/**
	 * Set private String beginDate and endDate to record the 
	 * beginning and ending date for travel. 
	 */
	private Date beginDate;
	private Date endDate;
	/**
	 * Set private Sting status to track whether the Claim travel 
	 * status and the default value is "In progress".
	 */
	private String status="In progress";
	/**
	 * Set an ArrayList named itemList to contain all the Item  within the Claim.
	 * Set an ArrayList named destinationList to record all the Destination 
	 * information, including location and reason. 
	 * 
	 * @see java.util.ArrayList
	 */
	private ArrayList<Item> itemList;
	private ArrayList<Destination> destinationList;
	/**
	 * Set private String approver to track if the claim is approved. Set private
	 * String comment to add comment to the Claim.
	 */
	private String approver;
	private String comment;	
	/**
	 * Set private ArrayList named tagList which contains all the tags of the Claim.
	 * 
	 * @see java.util.ArrayList
	 */
	private ArrayList<Tag> tagList;
	

	/**
	 * General construction. This public method sets up itemList, destinationList 
	 * and tagList and name.
	 * 
	 * @param claimName  a String variable
	 * @see java.util.ArrayList
	 */
	public Claim(String claimName) {
		super();
		itemList=new ArrayList<Item>();
		destinationList=new ArrayList<Destination>();
		tagList=new ArrayList<Tag>();
		name=claimName;
	}
	
	/**
	 * Set up the name and use <code>nitifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a name to a new 
	 * Claim or editing a current Claim.
	 * 
	 * @param name  a String variable
	 */
	public void setName(String name){
		this.name=name;
		notifyListeners();
	}
	
	/**
	 * Return the string variable name. This method will be used when 
	 * other class need to use or display the name. 
	 * 
	 * @return name  a String variable
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set up the beginDate and use <code>nitifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a travel beginning 
	 * date to a new Claim or editing a current Claim.
	 * 
	 * @param beginDate  a Date object
	 */
	public void setBeginDate(Date beginDate){
		this.beginDate=beginDate;
		notifyListeners();
	}
	
	/**
	 * Return the Date variable beginDate. This method will be used when 
	 * other class need to use or display the beginDate. 
	 * 
	 * @return beginDate  a Date object
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * Set up the endDate and use <code>nitifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a travel ending 
	 * date to a new Claim or editing a current Claim.
	 * 
	 * @param endDate  a Date object
	 */
	public void setEndDate(Date endDate){
		this.endDate=endDate;
		notifyListeners();
	}
	
	/**
	 * Return the Date variable endDate. This method will be used when 
	 * other class need to use or display the endDate. 
	 * 
	 * @return endDate  a Date object
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set up the status and notify all the Listener in both listeners and
	 * modelListeners ArrayList. This public method will be used when the 
	 * claimant added a new Claim or edited a current Claim. The default 
	 * value of status is "In progress".
	 * 
	 * @param status  a String variable
	 */
	public void setStatus(String status){
		this.status=status;
		notifyListeners();
	}
	
	/**
	 * Return the string variable status. This method will be used when 
	 * other class need to use or display the status. 
	 * 
	 * @return status  a String variable
	 */
	public String getStatus(){
		return status;	
	}

	/**
	 * Return the ArrayList itemList. This method will be used when
	 * other class need to use or display the itemList. 
	 * 
	 * @see java.util.ArrayList
	 * @return itemList  an ArrayList object.
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	/**
	 * Return the ArrayList destinationList. This method will be used when
	 * other class need to use or display the destinationList. 
	 * 
	 * @see java.util.ArrayList
	 * @return destinationList  an ArrayList object.
	 */
	public ArrayList<Destination> getDestinationList() {
		return destinationList;
	}
	
//	/**
//	 * This method uses <code>getName()</code> to combine all Destination in destinationList
//	 * and Tag in tagList to a big String. Use <code>getCM()</code> to display the amount 
//	 * with corresponding currency. It will be operate when the program need the 
//	 * destinationList and tagList to display.
//	 * 
//	 * @return ""  a string object combine with many small string variable
//	 */
	public String toString(){
		String dest="";
		for (Destination d:destinationList){
			dest+='\n'+"      "+d.getName();
		}
		String tag="";
		for (Tag t:tagList){
			dest+='\n'+"      "+t.getName();
		}
		return "Starting Date: "+AppSingleton.formatDate(beginDate)+'\n'+"Destination(s): "+dest+'\n'+"Status: "+status+'\n'+"Tag(s) : "+tag+'\n'+
				getCM("CAD")+'\n'+getCM("USD")+'\n'+getCM("EUR")+'\n'+getCM("GBP")+'\n'+getCM("CHF")+'\n'+getCM("JPY")+'\n'+getCM("CNY");

	}
	
	/**
	 * Calculate the total amount and return the value. This method first 
	 * set up a Double variable total with a default value null, then 
	 * use <code>getCurrency()</code> and <code>getAmount()</code> method 
	 * to get a total amount of the Claim and return it using <code>toString()</code>. 
	 * 
	 * @param currency  a String variable
	 * @return total  a Double variable
	 */
	public String getCM(String currency){
		Double total = null;

		for (Item item:itemList){
			if(item.getCurrency().equals(currency)&&item.getAmount()!=null){
				if(total==null){
					total=item.getAmount();
				}else{
					total+=item.getAmount();
				}
			}		
		}

		return total==null?(currency+": "):(currency+": "+total.toString());
	}
	
	/**
	 * Return the ArrayList cLaimDetail as null. This method will be used when 
	 * other class need to use or display the detail of the Claim. 
	 * 
	 * @see java.util.ArrayList
	 * @return null  an ArrayList object
	 */
	public ArrayList<Item> getClaimDetail() {
		return null;
		
	}
	
	/**
	 * Set the approver using a String variable name.
	 * 
	 * @param name  a String variable
	 */
	public void setApprover(String name){
		
	}
	
	/**
	 * Return the approver as null. This method will be used when
	 * other class need to use the approver. 
	 * 
	 * @return null  a String variable
	 */
	public String getApprover(){
		return null;
	}
	
	/**
	 * Set comment to a String comment.
	 * 
	 * @param comment  a String variable
	 */
	public void setComment(String comment){
		
	}
	
	/**
	 * Return the String comment as null. This method will be used when
	 * other class need to use or display the comment. 
	 * 
	 * @return null  a String variable
	 */
	public String getComment(){
		return null;
	}
	
	/**
	 * Add a String tagName to tagList as new Tag. 
	 * 
	 * @param tagName  a String variable
	 */
	public void addTag(String tagName){
		
	}
	
	/**
	 * Return the ArrayList tagList as null. This method will be used when 
	 * other class need to use or display the list of Tag. 
	 * 
	 * @see java.util.ArrayList
	 * @return null  an ArrayList object
	 */
	public ArrayList<Tag> getTagList(){		
		return null;
	}	

	/**
	 * Return a boolean variable as false. This method will be used when 
	 * other class need to justify whether the Claim can be edited or not. 
	 * 
	 * @return false  a boolean variable
	 */
	public boolean getEdiable(){
		return false;
	}

	/**
	 * Return the String string as null. This method will be used when
	 * other class need to use or display the item. 
	 * 
	 * @param string  a String variable
	 * @return null  a String variable
	 */
	public Object getItem(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the Item object itemDate as null. This method will 
	 * be used when other class need to use or display the date of 
	 * the item expense. 
	 * 
	 * @param expenseTest1  an Item object
	 * @return null  an Item object
	 */
	public Object getItemDate(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Return the String itemCategory as null. This method will be 
	 * used when other class need to use or display the category of 
	 * the item expense. 
	 * 
	 * @param expenseTest1  an Item object
	 * @return null  a String Variable
	 */
	public String getItemCategory(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the Item object itemAmount as null. This method will 
	 * be used when other class need to use or display the amount of 
	 * the item expense. 
	 * 
	 * @param expenseTest1  an Item object
	 * @return null  an Item object
	 */
	public Object getItemAmount(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the String itemCurrency as null. This method will 
	 * be used when other class need to use or display the currency of 
	 * the item expense. 
	 * 
	 * @param expenseTest1  an Item object
	 * @return null  a String variable
	 */
	public String getItemCurrency(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the String itemDescription as null. This method will 
	 * be used when other class need to use or display the description of 
	 * the item expense. 
	 * 
	 * @param expenseTest1  an Item object
	 * @return null  a String variable
	 */
	public String getItemDescription(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the integer itemSize. This method will be used when 
	 * other class need to use or display the size of the item. 
	 * 
	 * @return <code>itemList.size()</code>  an int variable
	 */
	public int getItemSize() {
		// TODO Auto-generated method stub
		return itemList.size();
	}
	
	
}
