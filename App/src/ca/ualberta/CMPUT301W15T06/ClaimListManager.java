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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

/**
 * This <code>ClaimListManager</code> class is a management class of <code>User</code>.
 * By calling this class, the claimList can be loaded and saved.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.io.FileInputStream
 * @see java.io.FileNotFoundException
 * @see java.io.FileOutputStream
 * @see java.io.IOException
 * @see java.io.InputStreamReader
 * @see java.io.OutputStreamWriter
 * @see java.lang.reflect.Type
 * @see java.util.ArrayList
 * @see java.util.Date
 * @see android.content.Context
 * @see android.util.Log
 * @see com.google.gson.Gson
 * @see com.google.gson.GsonBuilder
 * @see com.google.gson.JsonIOException
 * @see com.google.gson.reflect.TypeToken
 */
public class ClaimListManager {
	/**
	 * Set a static type ClaimListManager object clm with a default value 
	 * of null.
	 */
	private static ClaimListManager clm=null;
	/**
	 * Set a static final type String variable FILENAME with initial default
	 * value of "data".
	 */
	private static final String USER_FILE="usr";
	/**
	 * Set a Context object context with default value of null.
	 * 
	 * @see android.content.Context
	 */
	private Context context=null;
	/**
	 *  Set a Gson object which is a Java library that can be used to convert Java Objects into their JSON representation
	 */
	private Gson gson;
	
	
	/**
	 * General construction. Set a Context context as the context that user ntered using Gson Adapter.
	 * 
	 * @param context  a Context object
	 * @see android.content.Context
	 * @see com.google.gson.Gson
	 */
	private ClaimListManager(Context context){
		this.context=context;
		gson = new GsonBuilder()
	    .registerTypeAdapter(Claim.class, new GsonAdapter<Claim>())
	    .registerTypeAdapter(Item.class, new GsonAdapter<Item>())
	    .registerTypeAdapter(Destination.class, new GsonAdapter<Destination>())
	    .registerTypeAdapter(Receipt.class, new GsonAdapter<Receipt>())
	    .create();
	}
	
	/**
	 * This method checks if the ClaimListManager object clm has been 
	 * initialized.
	 * 
	 * @throws RuntimeException
	 * @return clm  a ClaimManager 
	 */
	public static ClaimListManager getInstance() {  
        if (clm == null) {  
    		throw new RuntimeException("Did not initialize ClaimListManager");
        }  
        return clm;  
    }
	
	/**
	 * This method initialize a new clm if there's none or not initialize
	 * before. 
	 * 
	 * @param ct  a Context object'
	 */
	public static void initial(Context ct){
		if (clm==null){
			clm=new ClaimListManager(ct);	
		}
	}
	
	/**
	 * This method will load the ClamList and return it for further
	 * use or display. It also checks exceptions to prevent crush.
	 * 
	 * @param name  a String that is the full name of the user.
	 * @exception FileNotFoundException
	 * @exception IOException
	 * @throws RuntimeException
	 * @see com.google.gson.Gson
 	 * @see java.io.FileInputStream
	 * @see java.io.FileNotFoundException
	 * @see java.io.FileOutputStream
	 * @see java.io.IOException
	 * @see java.io.InputStreamReader
	 * @see java.io.OutputStreamWriter
	 * @return the user that is currenly using the application
	 */
	public User load(String name){
		
		User user=null;
		try {
			FileInputStream fis = context.openFileInput(USER_FILE+name);
			Type dataType = new TypeToken<User>(){}.getType();
			InputStreamReader isr =new InputStreamReader(fis);
			user = gson.fromJson(isr, dataType);
			fis.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		}

		if (user==null){
			user=new User(name);	
			UserList userList = AppSingleton.getInstance().getUserList();
			if(!userList.getUserList().contains(name)){
				userList.getUserList().add(name);
				saveUserList(user);
			}	
		}
		return user;	
	}
	
	
	
	
	/**
	 * This method will save the User using a <code>OutputStreamWriter</code>.
	 * It also checks exceptions to prevent crush.
	 * 
	 * @exception FileNotFoundException
	 * @exception JsonIOException
	 * @exception IOException
	 * @see com.google.gson.Gson
 	 * @see java.io.FileInputStream
	 * @see java.io.FileNotFoundException
	 * @see java.io.FileOutputStream
	 * @see java.io.IOException
	 * @see java.io.InputStreamReader
	 * @see java.io.OutputStreamWriter
	 * @see com.google.gson.JsonIOException
	 * @see com.google.gson.reflect.TypeToken
	 */
	public void save(String name){
		
		final User user=AppSingleton.getInstance().getCurrentUser();
		
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {		  
		       pushOL(user);	        
		    }
		});
		
		thread.start();
		
		try {
			FileOutputStream fos = context.openFileOutput(USER_FILE+name, 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			gson.toJson(user,osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException when save");
		}catch (JsonIOException e) {
			throw new RuntimeException("JsonIOException when save");
		}catch (IOException e) {
			throw new RuntimeException();
		}
	
	}

	/**
	 * This method will push the changes online and save it remotely.
	 * 
	 * @param uesr  the user that is currenly using the application
	 */
	private void pushOL(User user) {
		// TODO Auto-generated method stub
		 try {
        	new ESClient().pushUser(user);
        	user.setNeedSyn(false);
        	if(AppSingleton.getInstance().getUserList().isNeedSynList()){
        		new ESClient().pushUserList(AppSingleton.getInstance().getUserList());
        		AppSingleton.getInstance().getUserList().setNeedSynList(false);
        		saveUserListLocal();
        	}
        	if(AppSingleton.getInstance().getUserList().isNeedSyn()){
        		syn();        		
        	}
        	
        } catch (Exception e) {
        	user.setNeedSyn(true);
        
        }
	}

	/**
	 * This method will save the changes of the program locally. Throws exception if it is necessary.
	 * 
	 * @param user  the user that is currenly using the application
	 * @throws FileNotFoundException
	 * @throws JsonIOException
	 * @throws IOException
	 */
	public void saveLocal(User user){		
		try {
			FileOutputStream fos = context.openFileOutput(USER_FILE+user.getUserName(), 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);		
			gson.toJson(user,osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException when save");
		}catch (JsonIOException e) {
			throw new RuntimeException("JsonIOException when save");
		}catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * This method will save a list of users who are using the application.
	 * 
	 * @param user  the user that is using the application
	 */
	private void saveUserList(final User user) {
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {    	
				usrlistOL(user);		
		    }
		});
		thread.start();		
	}

	/**
	 * Set a online copy of the list of user who are using the application.
	 * 
	 * @param user  the user that is using the application
	 */
	private void usrlistOL(User user) {
		// TODO Auto-generated method stub
		try {
			user.setNeedSyn(false);
			new ESClient().pushUserList(AppSingleton.getInstance().getUserList());
			new ESClient().pushUser(user);
			saveUserListLocal();
			saveLocal(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			user.setNeedSyn(true);
			AppSingleton.getInstance().getUserList().setNeedSynList(true);
			saveUserListLocal();
			saveLocal(user);
		}
	}

	/**
	 * Load the list of user from online to local. Throw error exceptions if necessary.
	 * 
	 * @return the list of users who are using the application
	 * @see FileNotFoundException
	 * @see IOException
	 */
	public UserList loadUserList(){		
		UserList userList=null;
		try {
			FileInputStream fis = context.openFileInput("usrList");
			Type dataType = new TypeToken<UserList>(){}.getType();
			InputStreamReader isr =new InputStreamReader(fis);
			userList = gson.fromJson(isr, dataType);
			fis.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			throw new RuntimeException("IOException");
		}

		return userList;
		
	}
	
	/**
	 * This method will syncs the data between online and local. 
	 */
	private void syn() {
		// TODO Auto-generated method stub
		for (String name:AppSingleton.getInstance().getUserList().getUserList()){
			final User user=load(name);
			if(user.isNeedSyn()){
				threadRunSyn(user);		
			}
		}
	}
	
	/**
	 * This method will create a new thread for syncs purpose. 
	 * 
	 * @param user  the user that is using the application
	 */
	private void threadRunSyn(final User user) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {			  
		        try {
		        	new ESClient().pushUser(user); 
		        	user.setNeedSyn(false);
		        } catch (Exception e) {
		        	user.setNeedSyn(true);
		        }			        
		    }
		});
		thread.start();
	}

	/**
	 * Save all the users to a ArrayList and syncs it by calling <code>setNeedSynSimple()</code>
	 */
	public void saveUsers(ArrayList<User> ul) {
		// TODO Auto-generated method stub
		for(User user:ul){
			user.setNeedSynSimple(false);
			saveLocal(user);
		}
	}

	/**
	 * Save the list of user locally by calling <code>AppSingleton</code> class. Throw error exceptions if necessary.
	 * 
	 * @see com.google.gson.Gson
	 * @throws FileNotFoundException
	 * @throws JsonIOException
	 * @throws IOException
	 */
	public void saveUserListLocal() {
		// TODO Auto-generated method stub
		
		final UserList userList=AppSingleton.getInstance().getUserList();
		try {
			FileOutputStream fos = context.openFileOutput("usrList", 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			gson.toJson(userList,osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException when save");
		}catch (JsonIOException e) {
			throw new RuntimeException("JsonIOException when save");
		}catch (IOException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Save the approving status by creating a new thread. Throw error exceptions if necessary.
	 * 
	 * @throws InterruptedException
	 * @throws RuntimeException
	 */
	public void approverSave() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {		    	
		        try {		   
		        	new ESClient().pushUser(AppSingleton.getInstance().getTempUser());
		        	AppSingleton.getInstance().setSuc(true);
		        	
		        } catch (Exception e) {
		        	AppSingleton.getInstance().setSuc(false);
		        }
		        
		    }

		});
		
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Can't join!");
		}
	}
}
