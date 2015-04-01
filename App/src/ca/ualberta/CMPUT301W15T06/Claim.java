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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

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
 * @version 03/16/2015
 * @see java.util.ArrayList
 * @see java.util.Date
 */
public class Claim extends AppModel{

	/**
	 * Set private Date beginDate and endDate to record the 
	 * beginning and ending date for travel.
	 * 
	 *  @see java.util.Date
	 */
	protected Date beginDate;
	
	private String name;
	
	private Date endDate;
	/**
	 * Set private String status to track whether the Claim travel 
	 * status and the default value is "In progress".
	 */
	private String status="In progress";
	/**
	 * Set an ArrayList named itemList to contain all the Item within the Claim.
	 * Set an ArrayList named destinationList to record all the Destination 
	 * information, including location and reason. 
	 * 
	 * @see java.util.ArrayList
	 */
	private ArrayList<Item> itemList;
	private ArrayList<Destination> destinationList;
	
	private ArrayList<Comment> commentList;
	/**
	 * Set private String approver to track if the claim is approved. Set private
	 * String comment to add comment to the Claim.
	 */
	private String approver;
	private String comment;	
	/**
	 * Set private ArrayList named tagIDList.
	 * 
	 * @see java.util.ArrayList
	 */
	private ArrayList<Long> tagIDList;
	

<<<<<<< HEAD
	/**
	 * General construction. This public method sets up a Claim object with
	 * itemList, destinationList and tagList and name.
	 * 
	 * @param claimName  the name of the claim(like "Claim1")
	 * @see java.util.ArrayList
	 */
	public Claim(String claimName) {
=======
//	/**
//	 * General construction. This public method sets up a Claim object with
//	 * itemList, destinationList and tagList and name.
//	 * 
//	 * @param claimName  a String variable
//	 * @see java.util.ArrayList
//	 */
	public Claim(String name) {
>>>>>>> e2cb2f6020ebcdead6487c917139e2c4dfed24f4
		super();
		itemList=new ArrayList<Item>();
		destinationList=new ArrayList<Destination>();
		tagIDList=new ArrayList<Long>();
<<<<<<< HEAD
		name=claimName;
	}
	
	/**
	 * Set up the name and use <code>notifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a name to a new 
	 * claim or editing a current claim.
	 * 
	 * @param name  the full name of the claimant (like "Tom Smith")
	 */
	public void setName(String name){
=======
		commentList=new ArrayList<Comment>();
		
>>>>>>> e2cb2f6020ebcdead6487c917139e2c4dfed24f4
		this.name=name;
		
		beginDate=AppSingleton.removeTime(new Date());
		endDate=AppSingleton.removeTime(new Date());
	}
	
<<<<<<< HEAD
	/**
	 * Return the string variable name. This method will be used when 
	 * other class need to use or display the name. 
	 * 
	 * @return the full name of the claimant (like "Tom Smith")
	 */
	public String getName() {
		return name;
	}
=======

>>>>>>> e2cb2f6020ebcdead6487c917139e2c4dfed24f4
	
	/**
	 * Set up the beginDate and use <code>notifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a travel beginning 
	 * date to a new claim or editing a current claim.
	 * 
<<<<<<< HEAD
	 * @param the date of when the travel started (like "15-Mar-2015")
	 * 
=======
	 * @param beginDate  a Date object
	 * @throws StatusException 
	 * @throws WrongEndDateException 
	 * @throws NetWorkException 
>>>>>>> e2cb2f6020ebcdead6487c917139e2c4dfed24f4
	 * @see java.util.Date
	 */
	public void setBeginDate(Date beginDate) throws StatusException, WrongEndDateException, NetWorkException{
		if (status.equals("Submitted")||status.equals("Approved")){
			throw new StatusException();					
		}
		if (beginDate.after(endDate)){
			throw new WrongEndDateException();
		}
		this.beginDate=beginDate;
		notifyListeners();
	}
	
	/**
	 * Return the Date variable beginDate. This method will be used when 
	 * other class need to use or display the beginDate. 
	 * 
	 * @return the date of when the travel started (like "15-Mar-2015")
	 * @see java.util.Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * Set up the endDate and use <code>notifyListeners()</code> in <code>AppModel</code> 
	 * to notify all the Listener in both listeners and modelListeners ArrayList. 
	 * This public method will be used when the claimant entering a travel ending 
	 * date to a new claim or editing a current claim.
	 * 
<<<<<<< HEAD
	 * @param endDate  date of when the travel ended (like "31-Mar-2015")
=======
	 * @param endDate  a Date object
	 * @throws StatusException 
	 * @throws WrongEndDateException 
	 * @throws NetWorkException 
>>>>>>> e2cb2f6020ebcdead6487c917139e2c4dfed24f4
	 * @see java.util.Date
	 */
	public void setEndDate(Date endDate) throws StatusException, WrongEndDateException, NetWorkException{
		Log.i("before set",this.endDate.toString());
		Log.i("end set",endDate.toString());
		if (status.equals("Submitted")||status.equals("Approved")){
			throw new StatusException();					
		}
		if (beginDate.after(endDate)){
			throw new WrongEndDateException();
		}
		this.endDate=endDate;
		notifyListeners();
	}
	
	/**
	 * Return the Date variable endDate. This method will be used when 
	 * other class need to use or display the endDate. 
	 * 
	 * @return the date of when the travel ended (like "31-Mar-2015")
	 * @see java.util.Date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set up the status and notify all the Listener in both listeners and
	 * modelListeners ArrayList. This public method will be used when the 
	 * claimant added a new claim or edited a current claim. The default 
	 * value of status is "In progress".
	 * 
<<<<<<< HEAD
	 * @param status  the submission status of a claim ("In Progress" or "Submitted")
=======
	 * @param status  a String variable
	 * @throws NetWorkException 
>>>>>>> e2cb2f6020ebcdead6487c917139e2c4dfed24f4
	 */
	public void setStatus(String status) throws NetWorkException{
		this.status=status;
		notifyListeners();
	}
	
	/**
	 * Return the string variable status. This method will be used when 
	 * other class need to use or display the status. 
	 * 
	 * @return the submission status of a claim ("In Progress" or "Submitted")
	 */
	public String getStatus(){
		return status;	
	}

	/**
	 * Return the ArrayList itemList. This method will be used when
	 * other class need to use or display the itemList. 
	 * 
	 * @see java.util.ArrayList
	 * @return an ArrayList contains all <code>Item</code> objects (like "{Item1, Item2, ...}")
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	/**
	 * Return the ArrayList destinationList. This method will be used when
	 * other class need to use or display the destinationList. 
	 * 
	 * @see java.util.ArrayList
	 * @return an ArrayList contains all <code>Destination</code> object (like "{Destination1, Destination2, ...}")
	 */
	public ArrayList<Destination> getDestinationList() {
		return destinationList;
	}
	
	/**
	 * This method translates Destination startDate and endDate from Date object to String object. 
	 * Then combines all Destination in destinationList and Tag in tagList to a big String. 
	 * Use <code>getCM()</code> to display the amount with corresponding currency. It will 
	 * be operated when the program need the destinationList and tagList to display.
	 * 
	 * @return 
	 */
	public String toString(){
		if(AppSingleton.getInstance().iscMod()){
			return cToString();
		}else{
			return aToString();
		}
		

	}
	
	
	
	
	private String aToString() {
		// TODO Auto-generated method stub
		String dest="";
		for (Destination d:destinationList){
			dest+='\n'+"      "+d.getName();
		}
		String approver="";
		ArrayList<String> nal= new ArrayList<String>();
		for(Comment comment:getCommentList()){
			if(!nal.contains(comment.getApproverName())){
				nal.add(comment.getApproverName());
				approver+='\n'+"      "+comment.getApproverName();
			}
		}
		
		return "Claimant: "+name+'\n'+"Starting Date: "+AppSingleton.formatDate(beginDate)+'\n'+"Destination(s): "+dest+'\n'+"Status: "+status+'\n'+
				getCM("CAD")+'\n'+getCM("USD")+'\n'+getCM("EUR")+'\n'+getCM("GBP")+'\n'+getCM("CHF")+'\n'+getCM("JPY")+'\n'+getCM("CNY")+'\n'+"Approver(s): "+approver;
	}



	private String cToString() {
		// TODO Auto-generated method stub
		String dest="";
		for (Destination d:destinationList){
			dest+='\n'+"      "+d.getName();
		}
		String tag="";
		for (Long l:tagIDList){
			tag+='\n'+"      "+AppSingleton.getInstance().getCurrentUser().getTagByID(l).getName();
		}
		return "Starting Date: "+AppSingleton.formatDate(beginDate)+'\n'+"Destination(s): "+dest+'\n'+"Status: "+status+'\n'+"Tag(s) : "+tag+'\n'+
				getCM("CAD")+'\n'+getCM("USD")+'\n'+getCM("EUR")+'\n'+getCM("GBP")+'\n'+getCM("CHF")+'\n'+getCM("JPY")+'\n'+getCM("CNY");
	}



	/**
	 * Calculate the total amount and return the value. This method first 
	 * set up a Double variable total with a default value null, then 
	 * use <code>getCurrency()</code> and <code>getAmount()</code> method 
	 * to get a total amount of the claim and return it using <code>toString()</code>. 
	 * 
	 * @param currency  the currency to the item expense amount (like "CAD", combine with amount "120CAD")
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
	 * This method allows user to add a Listener to the 
	 * ArrayList listeners and add listener to destinations
	 * and items in this claim
	 * 
	 * @param l  a Listener type
	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
		
		for (Item item:itemList){
			item.addListener(l);
		}
		
		for (Destination dest:destinationList){
			dest.addListener(l);
		}
	}
	
	/**
	 * This method allows user to add a Listener to the 
	 * ArrayList ModelListeners and add listener to destinations
	 * and items in this claim
	 * 
	 * @param l  a Listener type
	 */
	public void addModelListener(Listener l){
		if (!getModelListeners().contains(l)){
			getModelListeners().add(l);
		}
		for (Item item:itemList){
			item.addModelListener(l);
		}
		
		for (Destination dest:destinationList){
			dest.addModelListener(l);
		}
	}
	
	/**
	 * Return the ArrayList cLaimDetail as null. This method will be used when 
	 * other class need to use or display the detail of the claim. 
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
	 * Return the ArrayList tagIDList as null. This method will be used when 
	 * other class need to use or display the list of Tag. 
	 * 
	 * @see java.util.ArrayList
	 * @return tagIDList  an ArrayList object
	 */
	public ArrayList<Long> getTagIDList(){		
		return tagIDList;
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
	 * @return <code>itemList.size()</code>  an Integer variable
	 */
	public int getItemSize() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	/**
	 * This class will check the ArrayList object TagList to see if it contains certain
	 * tag by checking the tag ID. It will return a   
	 * 
	 */
	public boolean[] toCheckArray() {
		// TODO Auto-generated method stub
		ArrayList<Tag> tl = AppSingleton.getInstance().getCurrentUser().getTagList();
		boolean[] result = new boolean[tl.size()];
		int i=0;
		for (Tag tag:tl){
			if(tagIDList.contains(tag.getID())){
				result[i]=true;
			}else{
				result[i]=false;
			}
			i++;
		}
		return result;
	}
	
	public boolean getMissValue(){
		boolean result=false;
		
		for(Item item:itemList){
			if(item.getMissValue()){
				result=true;
			}
		}
		
		for(Destination dest:destinationList){
			if(dest.getMissValue()){
				result=true;
			}
		}
		return result;		
	}



	public String getName() {
		return name;
	}



	public ArrayList<Comment> getCommentList() {
		return commentList;
	}



	public void setStatusSimple(String string) {
		// TODO Auto-generated method stub
		this.status=string;
	}




}
