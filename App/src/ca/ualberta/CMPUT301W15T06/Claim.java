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

import android.location.Location;


/**
 * <p>
 * The <code>Claim</code> class is an sub-class of <code>AppModel</code>. 
 * This class can set up a claim with the information including claimant's name, 
 * travel begin and end date, travel status, item list, destination list with reason, 
 * approve status, comment and tag list. The class also allows the user to call 
 * <code>ClaimantAddClaimController</code> to create a new claim, set up and 
 * edit claim detail(such as name, start date and end date), set status, call 
 * <code>ClaimantAddDestinationController</code> class to add destination and reason, 
 * get item list from <code>ItemList</code> and get tag list from <code>TagList</code>. 
 * <p>
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.ArrayList
 * @see java.util.Date
 * @see java.util.Calendar
 * @see android.location.Location
 */
public class Claim extends AppModel{

	/**
	 * Set protected Date beginDate and endDate to record the beginning and ending date for travel.
	 * 
	 *  @see java.util.Date
	 */
	protected Date beginDate;
	/**
	 * Set a String variable name to record the name of the claimant
	 */
	protected String name;
	/**
	 * Set a Date object to record the ending date of the claimed travel
	 */
	protected Date endDate;
	/**
	 * Set protected String status to track whether the Claim travel status and the default value is "In progress".
	 */
	protected String status;
	/**
	 * Set an ArrayList named itemList to contain all the Item within the Claim.
	 * Set an ArrayList named destinationList to record all the Destination information, including location and reason. 
	 * 
	 * @see java.util.ArrayList
	 */
	protected ArrayList<Item> itemList;
	protected ArrayList<Destination> destinationList;
	
	protected Comments comments;
	/**
	 * Set protected ArrayList named tagIDList.
	 * 
	 * @see java.util.ArrayList
	 */
	protected ArrayList<Long> tagIDList;
	

	/**
	 * General constructor.
	 */	
	public Claim(){
		super();
	}
	
	/**
	 * This public method sets up a Claim object with itemList, destinationList and tagList, name, begin and end date.
	 * 
	 * @param name  the full name of the claimant (like "Tom Smith")
	 * @see java.util.ArrayList
	 */
	public Claim(String name) {
		super();
		itemList=new ArrayList<Item>();
		destinationList=new ArrayList<Destination>();
		tagIDList=new ArrayList<Long>();
		comments=new Comments();
		
		this.name=name;
		
		status="In progress";
		
		beginDate=AppSingleton.removeTime(new Date());
		endDate=AppSingleton.removeTime(new Date());
	}
	
	/**
	 * This method will switch the subclass and re-record all the informations.
	 * 
	 * @param oldClaim  an old claim with out-of-date information
	 * @param status  the current status of the claim ("In progress" or "Submitted")
	 */
	public Claim(Claim oldClaim,String status) {
		super(oldClaim);
		beginDate=oldClaim.getBeginDate();
		name=oldClaim.getName();
		endDate=oldClaim.getEndDate();
		this.status=status;
		comments=oldClaim.getComments();
		tagIDList=oldClaim.getTagIDList();
		itemList=new ArrayList<Item>();
		destinationList=new ArrayList<Destination>();
	}
	
	/**
	 * Return the Date variable beginDate. This method will be used when other class need to use or display the beginDate. 
	 * 
	 * @return the date of when the travel started (like "15-Mar-2015")
	 * @see java.util.Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * Return the Date variable endDate. This method will be used when other class need to use or display the endDate. 
	 * 
	 * @return endDate  the date of when the travel ended (like "31-Mar-2015")
	 * @see java.util.Date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>
	 * Set up the status and notify all the Listener in both listeners and 
	 * modelListeners ArrayList. This public method will be used when the 
	 * claimant added a new claim or edited a current claim. The default 
	 * value of status is "In progress".
	 * <p>
	 * 
	 * @param status  the submission status of a claim ("In Progress" or "Submitted")
	 * @throws NetWorkException 
	 */
	public void setStatus(String status) throws NetWorkException{
		this.status=status;
		notifyListeners();
	}
	
	/**
	 * Return the string variable status. This method will be used when other class need to use or display the status. 
	 * 
	 * @return the submission status of a claim ("In Progress" or "Submitted")
	 */
	public String getStatus(){
		return status;	
	}

	/**
	 * Return the ArrayList itemList. This method will be used when other class need to use or display the itemList. 
	 * 
	 * @see java.util.ArrayList
	 * @return itemList  an ArrayList contains all <code>Item</code> objects (like "{Item1, Item2, ...}")
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	/**
	 * Return the ArrayList destinationList. This method will be used when other class need to use or display the destinationList. 
	 * 
	 * @see java.util.ArrayList
	 * @return an ArrayList contains all <code>Destination</code> object (like "{Destination1, Destination2, ...}")
	 */
	public ArrayList<Destination> getDestinationList() {
		return destinationList;
	}
	
	/**
	 * This method will choose to go to one of two different subclass, <code>aToString()</code> or <code>cToString()</code>
	 */
	public String toString(){
		if(AppSingleton.getInstance().iscMod()){
			return cToString();
		}else{
			return aToString();
		}
	}

	/**
	 * <p>
	 * This method translates Destination startDate and endDate from Date object to String object. 
	 * Then combines all Destination in destinationList and comment in commentList to a big String. 
	 * Use <code>getCM()</code> to display the amount with corresponding currency. It will 
	 * be operated when the program need the destinationList and tagList to display.
	 * <p>
	 * 
	 * @return a String that combine with many small String variable
	 */
	protected String aToString() {
		// TODO Auto-generated method stub
		String approver="";
		ArrayList<String> nal= new ArrayList<String>();
		for(Comment comment:comments.getCommentList()){
			if(!nal.contains(comment.getApproverName())){
				nal.add(comment.getApproverName());
				approver+='\n'+"      "+comment.getApproverName();
			}
		}
		
		return "Claimant: "+name+'\n'+sameReturn()+"Approver(s): "+approver;
	}

	
	/**
	 * <p>
	 * This method will change the subclass. It translates Destination startDate and endDate 
	 * from Date object to String object. Then combines all Destination in destinationList 
	 * and Tag in tagList to a big String. Use <code>getCM()</code> to display the amount 
	 * with corresponding currency. It will be operated when the program need the 
	 * destinationList and tagList to display.
	 * <p>
	 * 
	 * @return a String that combine with many small String variable
	 */
	protected String cToString() {
		// TODO Auto-generated method stub
		
		String tag="";
		for (Long l:tagIDList){
			tag+='\n'+"      "+AppSingleton.getInstance().getCurrentUser().getTagByID(l).getName();
		}
		return sameReturn()+"Tag(s) : "+tag+'\n'+"Distance to first Destionation:"+"\n    "+colorDistance();
	}

	/**
	 * This method will ask user to set a home location and travel location and return the distance between home and 
	 * destination for further use or display.
	 * 
	 * @return the distance between the home location and first destination in meters (like "1000 meters")
	 */
	private String colorDistance() {
		// TODO Auto-generated method stub
		if(AppSingleton.getInstance().getCurrentUser().getHomeLocation()==null){
			return "You haven't set home location yet!";
		}else if(!hasFirstDestLoca()){
			return "You haven't set the location \n  of the first destination!";
		}
		
		float distance = AppSingleton.getInstance().getCurrentUser().getHomeLocation().distanceTo(getFirstDestLoca());
		int i=(int) (distance/2003900);
		String star=new String(new char[i+1]).replace('\0', '*');
		return (String.valueOf(distance)+" meters\n    "+star);


	}
	
	/**
	 * This method will return the location of the first travel distination for further use or dispaly.
	 * 
	 * @return the first travel destination (like "Calgary")
	 */
	private Location getFirstDestLoca() {
		// TODO Auto-generated method stub
		return destinationList.get(0).getLocation();
	}
	/**
	 * This method will return a boolean variable to check if there's a first destination.
	 * 
	 * @return a boolean variable shows whether or not there is at least one destination in the destination list
	 */
	private boolean hasFirstDestLoca() {
		// TODO Auto-generated method stub
		if(destinationList.size()==0){
			return false;
		}else if(destinationList.get(0).getLocation()==null){
			return false;
		}
		return true;
	}
	
	/**
	 * Return the Claim in string type for further use or display
	 * 
	 * @return a string of claim information
	 */
	private String sameReturn(){
		return "Starting Date: "+AppSingleton.formatDate(beginDate)+'\n'+"Destination(s): "+destToString()+'\n'
				+"Status: "+status+'\n'+getCM("CAD")+'\n'+getCM("USD")+'\n'+getCM("EUR")+'\n'
				+getCM("GBP")+'\n'+getCM("CHF")+'\n'+getCM("JPY")+'\n'+getCM("CNY")+'\n';
	}
	
	/**
	 * Return the destination as a string variable for further use or display
	 * 
	 * @return a string variable destination in the destination list
	 */
	private String destToString(){
		String dest="";
		for (Destination d:destinationList){
			dest+='\n'+"      "+d.getName();
		}
		return dest;
	}
	/**
	 * <p>
	 * Calculate the total amount and return the value. This method first 
	 * set up a Double variable total with a default value null, then 
	 * use <code>getCurrency()</code> and <code>getAmount()</code> method 
	 * to get a total amount of the claim and return it using <code>toString()</code>. 
	 * <p>
	 * 
	 * @param currency  the currency to the item expense amount (like "CAD")
	 * @return combine currency with total amount (like "120CAD")
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
	 * This method allows user to add a Listener to the ArrayList listeners and add listener to destinations and items in this claim
	 * 
	 * @param l  a observer that watches all the updates of objects
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
	 * This method allows user to add a Listener to the ArrayList ModelListeners and add listener to destinations and items in this claim
	 * 
	 * @param l  a observer that watches all the updates of objects
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
	 * Return the ArrayList tagIDList as null. This method will be used when other class need to use or display the list of Tag. 
	 * 
	 * @see java.util.ArrayList
	 * @return tagIDList  an ArrayList contains all the tags of a claim (like "{TagID1, TagID2, ...}")
	 */
	public ArrayList<Long> getTagIDList(){		
		return tagIDList;
	}	



	/**
	 * Return the integer itemSize. This method will be used when other class need to use or display the size of the item. 
	 * 
	 * @return the size of the itemList
	 */
	public int getItemSize() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	/**
	 * This class will check the ArrayList object TagList to see if it contains certain tag by checking the tag ID. It will return the result of checking the arrayList <code>tagIDList</code>.
	 * 
	 * @return the result of checking the arrayList <code>tagIDList</code>
	 */
	public boolean[] toCheckArray() {
		// TODO Auto-generated method stub
		ArrayList<Tag> tl = AppSingleton.getInstance().getCurrentUser().getTagList();
		boolean[] result = new boolean[tl.size()];
		int i=0;
		for (Tag tag:tl){
			result[i] = tagIDList.contains(tag.getID()); 
			i++;
		}
		return result;
	}
	
	/**
	 * Return the boolean variable result to check if the itemList and destinationList have any miss value.
	 * 
	 * @return the result of checking miss value
	 */
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

	/**
	 * Return a String variable name for the claimant. This method will be used when other class need to use or display the beginDate. 
	 * 
	 * @return the full name of the claimant (like "Tom Smith")
	 */
	public String getName() {
		return name;
	}


	/**
	 * Set the status of the claim.
	 * 
	 * @param string  a string as status ("In progress" or "Submitted")
	 */
	public void setStatusSimple(String string) {
		// TODO Auto-generated method stub
		this.status=string;
	}
	
	/**
	 * Return the comment of the claim for further use or display.
	 * 
	 * @return the comment of the claim by the approver (like "Incomplete Claim Information")
	 */
	public Comments getComments() {
		return comments;
	}

	/**
	 * Set the begining date of the travel. Throw exceptions if necessary.
	 * 
	 * @param beginDate  the date of when the travel started (like "15-Mar-2015")
	 * @throws WrongEndDateException
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setBeginDate(Date beginDate) throws WrongEndDateException, NetWorkException, StatusException {
	}

	/**
	 * Set the ending date of the travel. Throw exceptions if necessary.
	 * 
	 * @param endDate  the date of when the travel ended (like "31-Mar-2015")
	 * @throws WrongEndDateException
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setEndDate(Date endDate) throws StatusException, WrongEndDateException, NetWorkException {
	}

}
