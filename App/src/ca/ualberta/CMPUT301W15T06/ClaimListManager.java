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

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

/**
 * This <code>ClaimListManager</code> class is a management class of <code>User</code>.
 * By calling this class, the claimList can be loaded and saved.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.io.FileInputStream
 * @see java.io.FileNotFoundException
 * @see java.io.FileOutputStream
 * @see java.io.IOException
 * @see java.io.InputStreamReader
 * @see java.io.OutputStreamWriter
 * @see java.lang.reflect.Type
 * @see android.content.Context
 * @see com.google.gson.Gson
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
	 * General construction. Set a Context context as the context that user
	 * entered.
	 * 
	 * @param context  a Context object
	 * @see android.content.Context
	 */
	private ClaimListManager(Context context){
		this.context=context;
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
	 * @return cl  a User object
	 */
	public User load(String name){
		Gson gson =new Gson();
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
		
		Gson gson=new Gson();
		final User user=AppSingleton.getInstance().getCurrentUser();
		
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		  
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

		});
		
		thread.start();
		
		try {
			FileOutputStream fos = context.openFileOutput(USER_FILE+name, 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			
//			user.setLastModify(new Date());
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

	public void saveLocal(User user){
		Gson gson=new Gson();
		try {
			FileOutputStream fos = context.openFileOutput(USER_FILE+user.getUserName(), 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);		
//			user.setLastModify(new Date());
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
	
	
	private void saveUserList(final User user) {
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {    	

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
		});
		thread.start();
		
	}

	public UserList loadUserList(){
		Gson gson =new Gson();
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
	private void syn() {
		// TODO Auto-generated method stub
		for (String name:AppSingleton.getInstance().getUserList().getUserList()){
			final User user=load(name);
			if(user.isNeedSyn()){
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
		}
	}

	public void saveUsers(ArrayList<User> ul) {
		// TODO Auto-generated method stub
		for(User user:ul){
			user.setNeedSynSimple(false);
			saveLocal(user);
		}
	}

	public void saveUserListLocal() {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		final UserList userList=AppSingleton.getInstance().getUserList();
		try {
			FileOutputStream fos = context.openFileOutput("usrList", 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			
//			user.setLastModify(new Date());
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
}
