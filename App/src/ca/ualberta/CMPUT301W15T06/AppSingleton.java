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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.EditText;

/**
 * This class is a static class to store some information that we want to share 
 * between the whole application. The role is to ensure a class has only one 
 * instance and provide a global point of access to it.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.text.DateFormat
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * @see java.util.Locale
 * @see android.widget.EditText
 */
public class AppSingleton {
	/**
	 * Set a static AppSingleton type object appsingleton.
	 */
	private static AppSingleton appsingleton;
	/**
	 * Set a User object currentUser to access to <code>User</code>.
	 */
	private User currentUser;
	
	
	private String userName;
	/**
	 * Set a Claim object currentClaim to access to <code>Claim</code>.
	 */
	private Claim currentClaim;
	/**
	 * Set a Item object currentItem to access to <code>Item</code>.
	 */
	private Item currentItem;
	
	private Destination currentDestination;
	/**
	 * Set a Android EditText object dateEditText to allow user edit
	 * text in different classes.
	 * 
	 * @see android.widget.EditText
	 */
	private EditText dateEditText;
	/**
	 * Set a Date object editDate to access to the date of <code>Claim</code>
	 * and <code>Item</code>.
	 * 
	 * @see java.util.Date
	 */
	private Date editDate;
	/**
	 * Set a MapController object mapController that controls the map of <code>Claim</code>
	 */
	private MapController mapController;
	/**
	 * Set a Location object location that controls the location of destination
	 */
	private Location location;
	/**
	 * Set a static final DateFormat object format with Date and Locale to record
	 * the date and locale of the <code>Claim</code>.
	 * 
	 * @see java.text.DateFormat
	 * @see java.util.Date
	 */
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	/**
	 * Set a UserList object userList to record all the user of the application
	 */
	private UserList userList;
	/**
	 * Set an ArrayList needApproveList to contain all the Claims that need to be approved by the approver
	 */
	private ArrayList<Claim> needApproveList;
	/**
	 * Set an ArrayList approverUserList to contain all the Users who are approvers
	 */
	private ArrayList<User> approverUserList;
	/**
	 * Set a boolean variable cMod
	 */
	private boolean cMod;
	/**
	 * Set a boolean variable suc
	 */
	private boolean suc;
    
	
	private boolean test;
    
    	/**
     	 * General constructor. 
     	 */
    	private AppSingleton() {  
    		test=false;  	
    	}  
    
    	/**
	 * This static method will check if there's an existing AppSingleton object, if not, 
	 * create a new one and return it for further use.
     	 * 
         * @return an AppSingleton that shares all the data within different classes
         */
    	public static AppSingleton getInstance() {  
		if (appsingleton == null) {  
        		appsingleton = new AppSingleton();  
        	}  
        	return appsingleton;  
    	}
    
	 /**
	 * Return the currentUser. This method will be used when other class need 
	 * to use or display the currentUser. 
	 * 
	 * @return currentUser  the user that is currenly using the program
	 */
  	public User getCurrentUser(){
  		return currentUser;
  	}

  	/**
  	 * Set up a new claim along with its status. This method will be called 
  	 * when user wants to create a new claim.
  	 * 
  	 * @param claim  an Claim that the claimant added onto the claim list.
  	 */
	public void setCurrentClaim(Claim claim) {
		currentClaim=claim;

	}

	/**
	 * Return the a Claim object currentClaim. This method will be used when 
	 * other class need to use or display the currentClaim. 
	 * 
	 * @return a Claim that the user is currently viewing
	 */
	public Claim getCurrentClaim() {
		// TODO Auto-generated method stub
		return currentClaim;
	}
	
	/**
  	 * Set up a new item. This method will be called when user wants to add a new item.
  	 * 
  	 * @param item  an expense item within the Claim
  	 */
	public void setCurrentItem(Item item) {
		currentItem=item;
	}
	
	/**
  	 * Return the a Item object currentItem. This method will be used when 
	 * other class need to use or display the currentItem. 
  	 * 
  	 * @return the Item that the user currently viewing
  	 */
	public Item getCurrentItem() {
		return currentItem;	
	}

	/**
  	 * Set up a new EditText object date. This method will be called when user 
  	 * wants to set a new editable text date.
  	 * 
  	 * @param date  a editable date with certain format (like "15-Mar-2015")
  	 * @see android.widget.EditText
  	 */
	public void setDateEditText(EditText date){
		dateEditText=date;
	}
	
	/**
  	 * Return the a EditText object dateEditText. This method will be used when 
	 * other class need to use or display the dateEditText. 
  	 * 
  	 * @return a editable date with certain format (like "15-Mar-2015")
  	 * @see android.widget.EditText
  	 */
	public EditText getDateEditText() {
		// TODO Auto-generated method stub
		return dateEditText;
	}
	
	/**
  	 * Set up a new Date object date. This method will be called when user 
  	 * wants to set a new date.
  	 * 
  	 * @param date  a date with certain format (like "15-Mar-2015")
  	 * @see java.util.Date
  	 */
	public void setEditDate(Date date){
		editDate=date;
	}
	
	/**
  	 * Return the a Date object dateEditText. This method will be used when 
	 * other class need to use or display the editDate. 
  	 * 
  	 * @return a editable date with certain format (like "15-Mar-2015")
  	 * @see java.util.Date
  	 */
	public Date getEditDate(){
		return editDate;
	}

	
	/**
	 * This method will provide a standard format for Date object and 
	 * return it as a String variable. It also checks whether the Date 
	 * object date is null, if it is, return empty value.
	 * 
	 * @param date  a date with certain format (like "15-Mar-2015")
	 * @return format.format(date)  a String variable
	 * @see java.text.DateFormat
         * @see java.text.SimpleDateFormat
         * @see java.util.Date
	 */
	public static java.lang.String formatDate(Date date){
		if (date==null){
			return "";
		}
		return format.format(date);
	}

	/**
	 * This method return a DateFormat object format for further use or display.
	 * 
	 * @return a desired format of date (like "DD-MMM-YYYY")
	 * @see java.text.DateFormat
     	 * @see java.text.SimpleDateFormat
	 */
	public static DateFormat getDateFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	/**
	 * This method will check if it is successfully finished the action.
	 * 
	 * @return a boolean variable gives "True" or "False" represent if the action is finished successfully
	 */
	public boolean isSuc() {
		return suc;
	}

	/**
	 * Set a boolean that shows if it is successfully finished the action.
	 * 
	 * @param suc  a boolean variable gives "True" or "False" represent if the action is finished successfully
	 */ 
	public void setSuc(boolean suc) {
		this.suc = suc;
	}

	/**
	 * This method will set a user who is using the program.
	 * 
	 * @param name  the name of the user who is currenly using the application
	 */
	public void setCurrentUser(String name) {
		// TODO Auto-generated method stub	
		userName=name;
		
		currentUser=ClaimListManager.getInstance().load(name);		
	}

	/**
	 * This method will set a list of users who is using the program.
	 */
	public void setUserList() {
		// TODO Auto-generated method stub
		userList=ClaimListManager.getInstance().loadUserList();

		if(userList==null){		
			userList=new ESClient().getUserList();
			if(userList==null){
				userList=new UserList();
			}else{
				new ESClient().downloadUsers(userList);
				ClaimListManager.getInstance().saveUserListLocal();
			}
					
		}else if(userList.isNeedSyn()){			  	
	    	try {
				new ESClient().synByUserList(userList);				
			} catch (Exception e) {
				// TODO Auto-generated catch block			
			} 	
		}
	
	}

	/**
	 * This method will return the name of the user for further use or display. 
	 * 
	 * @return a desired user's full name in the user list (like "Tom Smith")
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method will set the name of the user.
	 * 
	 * @param userName  the desired user's full name (like "Tom Smith")
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * This method will return the current destination of the claim for further use or display.
	 * 
	 * @return the current destination of the claim (including destination location and reason)
	 */
	public Destination getCurrentDestination() {
		return currentDestination;
	}

	/**
	 * This method will set the current destination of the claim for further use or display
	 * 
	 * @param currentDestination  the current destination of the claim (including destination location and reason)
	 */
	public void setCurrentDestination(Destination currentDestination) {
		this.currentDestination = currentDestination;
	}

	/**
	 * This method will set the date to the calendar and added time to it.
	 * 
	 * @param date  a date with certain format (like "15-Mar-2015")
	 */
	public static Date removeTime(Date date) {
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(date);
        	cal.set(Calendar.HOUR_OF_DAY, 0);
        	cal.set(Calendar.MINUTE, 0);
        	cal.set(Calendar.SECOND, 0);
        	cal.set(Calendar.MILLISECOND, 0);
        	return cal.getTime();
    	}

	/**
	 * Return the list of users for further use or display.
	 * 
	 * @return a UserList object which contains all the users of the application
	 */
	public UserList getUserList() {
		return userList;
	}

	/**
	 * Set up the user list to record all the users information and co-responding claims. 
	 * 
	 * @param userList  a UserList object which contains all the users of the application
	 */
	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	/**
	 * Set up the approver who has access to the submitted claims and approve them after review.
	 */
	public void setUpApprover() {
		// TODO Auto-generated method stub
		needApproveList=new ArrayList<Claim>();
		approverUserList=new ArrayList<User>();
		for(String name:userList.getUserList()){
			User user=ClaimListManager.getInstance().load(name);
			approverUserList.add(user);
			for(Claim claim:user.getClaimList()){
				if (claim.getStatus().equals("Submitted")){
					needApproveList.add(claim);
				}
			}
		}
	}

	/**
	 * Return the list of claims which need to be approved for further use or display.
	 * 
	 * @return a list of claims which need to be approved by the approver (like "{Claim1, Claim2, ...}")
	 */
	public ArrayList<Claim> getNeedApproveList() {
		return needApproveList;
	}
	
	/**
	 * Return a boolean variable to check if the application contains eligible model.
	 * 
	 * @return a boolean variable gives "True" or "False" represent if there's an eligible model of the application
	 */
	public boolean iscMod() {
		return cMod;
	}

	/**
	 * Set a boolean variable to check if the application contains eligible model
	 * 
	 * @param cMod  a boolean variable gives "True" or "False" represent if there's an eligible model of the application
	 */
	public void setcMod(boolean cMod) {
		this.cMod = cMod;
	}
	
	/**
	 * Check if the user name in user list is as same as in the claim. This method will be cause for further use or display.
	 * 
	 * @return if the user name in user list is as same as in the claim, return the user name, otherwise return null
	 */
	public User getTempUser() {
		for (User user:approverUserList){
			if(user.getUserName().equals(currentClaim.getName())){
				return user;
			}
		}
		return null;
	}

	/**
	 * Return an ArrayList which contains all the users who are approver for further use.
	 * 
	 * @return a list of users who are approvers (like "{Approver1, Approver2, ...}")
	 */
	public ArrayList<User> getApproverUserList() {
		return approverUserList;
	}
	
	/**
	 * This method will return a boolean variable to check if it is editable for further use or display.
	 * 
	 * @return a boolean variable to check if it is editable
	 */
	public boolean isEditable() {
		return currentClaim.getStatus().equals("In progress")||currentClaim.getStatus().equals("Returned");
	}

	/**
	 * This method will return a MapController object to control the map for further use or display.
	 * 
	 * @return a mapController that will control the map of the application and will be used for destination location
	 */
	public MapController getMapController() {
		return mapController;
	}

	/**
	 * This method will set a MapController object to control the map.
	 * 
	 * @param mapController  a mapController that will control the map of the application
	 */
	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}
	
	/**
	 * This method will return a location for the destination in the claim for further use or display.
	 * 
	 * @return the location of a destination within a claim
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * This method will set up a location for the destination in the claim.
	 * 
	 * @param location the location of a destination within a claim
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isTest() {
		// TODO Auto-generated method stub
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

    
}
