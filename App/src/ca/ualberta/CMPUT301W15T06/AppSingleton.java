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
import android.util.Log;
import android.widget.EditText;

/**
 * This class is a static class to store some information that we want to share 
 * between the whole application. The role is to ensure a class has only one 
 * instance and provide a global point of access to it.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
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
	private User tempUser;

	
	/**
	 * Set a static final DateFormat object format with Date and Locale to record
	 * the date and locale of the <code>Claim</code>.
	 * 
	 * @see java.text.DateFormat
	 * @see java.util.Date
	 */
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

	private Context testContext;
	
	private UserList userList;
	
	
	private ArrayList<Claim> needApproveList;
	
	private ArrayList<User> approverUserList;
	
	private boolean cMod;
	
	private boolean suc;
	private Object pg;
	/**
	 * This method will load the currentUser by calling <code>ClaimListManager</code>
	 * and using <code>getInstance()</code> and <code>load()</code>.
	 */
    private AppSingleton() {  
    	
    	
//    	userList=new ESClient().getUserList();
//    	for(String name:userList.getUserList()){
//    		
//    	}
    	
    	
    	
//    	Thread thread = new Thread(new Runnable(){
//		    @Override
//		    public void run() {
//		    	User online=new ESClient().getClaimList();
//		    	User local=ClaimListManager.getInstance().load();
//		    	if (online ==null){
//		    		currentUser=local;	    		
//		    	}else if (online.getLastModify()==null){
//		    		currentUser=local;
//		    	}else if (local.getLastModify()==null){
//		    		currentUser=online;
//		    	}else if (local.getLastModify().after(online.getLastModify())){
//		    		currentUser=local;
//		    	}else{
//		    		currentUser=online;
//		    	}
//		    }
//		});
//
//		thread.start();
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException(e.getMessage());
//		}
    }  
    
    /**
     * This static method will check if there's an existing AppSingleton object,
     * if not, create a new one and return it for further use.
     * 
     * @return appsingleton  an AppSingleton object
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
	 * @return currentUser  a User object
	 */
  	public User getCurrentUser(){
  		return currentUser;
  	}

  	/**
  	 * Set up a new claim along with its status. This method will be called 
  	 * when user wants to create a new claim.
  	 * 
  	 * @param claim  an Claim object
  	 */
	public void setCurrentClaim(Claim claim) {
		currentClaim=claim;

	}

	/**
	 * Return the a Claim object currentClaim. This method will be used when 
	 * other class need to use or display the currentClaim. 
	 * 
	 * @return currentClaim  an Claim object
	 */
	public Claim getCurrentClaim() {
		// TODO Auto-generated method stub
		return currentClaim;
	}
	
	/**
  	 * Set up a new item. This method will be called when user wants to add
  	 * a new item.
  	 * 
  	 * @param item  an Item object
  	 */
	public void setCurrentItem(Item item) {
		currentItem=item;
	}
	
	/**
  	 * Return the a Item object currentItem. This method will be used when 
	 * other class need to use or display the currentItem. 
  	 * 
  	 * @return currentItem  an Item object
  	 */
	public Item getCurrentItem() {
		return currentItem;	
	}

	/**
  	 * Set up a new EditText object date. This method will be called when user 
  	 * wants to set a new editable text date.
  	 * 
  	 * @param date  an EditText object
  	 * @see android.widget.EditText
  	 */
	public void setDateEditText(EditText date){
		dateEditText=date;
	}
	
	/**
  	 * Return the a EditText object dateEditText. This method will be used when 
	 * other class need to use or display the dateEditText. 
  	 * 
  	 * @return date  an EditText object
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
  	 * @param date  a Date object
  	 * @see java.util.Date
  	 */
	public void setEditDate(Date date){
		editDate=date;
	}
	
	/**
  	 * Return the a Date object dateEditText. This method will be used when 
	 * other class need to use or display the editDate. 
  	 * 
  	 * @return date  a Date object
  	 * @see java.util.Date
  	 */
	public Date getEditDate(){
		return editDate;
	}

	/**
	 * Return the a String variable status. This method will be used when 
	 * other class need to use or display the status.
	 * 
	 * @return status  a String variable
	 */
	public String getStatus() {
		return currentClaim.getStatus();

	}
	
	/**
	 * This method will provide a standard format for Date object and 
	 * return it as a String variable. It also checks whether the Date 
	 * object date is null, if it is, return empty value.
	 * 
	 * @param date  a Date object
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
	 * This method return a DateFormat object format for further use
	 * or display.
	 * 
	 * @return format  a DateFormat object
	 * @see java.text.DateFormat
     * @see java.text.SimpleDateFormat
	 */
	public static DateFormat getDateFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	public boolean isSuc() {
		return suc;
	}

	public void setSuc(boolean suc) {
		this.suc = suc;
	}

	public void setCurrentUser(final String name) {
		// TODO Auto-generated method stub
		
		
		userName=name;
		
		currentUser=ClaimListManager.getInstance().load(name);
		
	}

	public void setUserList() {
		// TODO Auto-generated method stub
		userList=ClaimListManager.getInstance().loadUserList();
		Log.i("ttttttt","2");
		if(userList==null){
			
			userList=new ESClient().getUserList();
			if(userList==null){
				userList=new UserList();
			}else{
				Log.i("ttttttt","4");
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public Destination getCurrentDestination() {
		return currentDestination;
	}

	public void setCurrentDestination(Destination currentDestination) {
		this.currentDestination = currentDestination;
	}

	public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

	public Context getTestContext() {
		return testContext;
	}

	public void setTestContext(Context testContext) {
		this.testContext = testContext;
	}

	public UserList getUserList() {
		return userList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

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

	public ArrayList<Claim> getNeedApproveList() {
		return needApproveList;
	}

	public boolean iscMod() {
		return cMod;
	}

	public void setcMod(boolean cMod) {
		this.cMod = cMod;
	}


	public User getTempUser() {
		for (User user:approverUserList){
			if(user.getUserName().equals(currentClaim.getName())){
				return user;
			}
		}
		return null;
	}

	public void setTempUser(String string) {
		for (User user:approverUserList){
			if(user.getUserName().equals(string)){
				tempUser=user;
			}
		}
	}

	public ArrayList<User> getApproverUserList() {
		return approverUserList;
	}

	
	
	
//	public static int getYear(String date){
//		return Integer.valueOf(date.split("-")[0]);
//	}
//	
//	public static int getMonth(String date){
//		return Integer.valueOf(date.split("-")[1]);
//	}
//	
//	public static int getDay(String date){
//		return Integer.valueOf(date.split("-")[2]);
//	}
    
}
