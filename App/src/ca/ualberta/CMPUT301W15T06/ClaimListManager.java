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
 * This <code>ClaimListManager</code> class is a management class of <code>ClaimList</code>.
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
package ca.ualberta.CMPUT301W15T06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

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
	private static final String FILENAME="data";
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
	 * @throw RuntimeException
	 * @return clm  a ClaimManager 
	 */
	public static ClaimListManager getInstance() {  
        if (clm == null) {  
    		throw new RuntimeException("Did not initialize ClaimListManager");
        }  
        return clm;  
    }
	
	/**
	 * This method initialize a new clm if there's none or not intialize
	 * before. 
	 * 
	 * @param ct  a Context object'
	 * @see android.content.Context
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
	 * @throw RuntimeException
	 * @see com.google.gson.Gson
 	 * @see java.io.FileInputStream
	 * @see java.io.FileNotFoundException
	 * @see java.io.FileOutputStream
	 * @see java.io.IOException
	 * @see java.io.InputStreamReader
	 * @see java.io.OutputStreamWriter
	 * @return cl  a ClaimList object
	 */
	public ClaimList load(){
		Gson gson =new Gson();
		ClaimList cl=null;
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			Type dataType = new TypeToken<ClaimList>(){}.getType();
			InputStreamReader isr =new InputStreamReader(fis);
			cl = gson.fromJson(isr, dataType);
			fis.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		}

		if (cl==null){
			cl=new ClaimList();		
		}
		return cl;
		
	}
	
	/**
	 * This method will save the ClaimList using a <code>OutputStreamWriter</code>.
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
	public void save(){
		Gson gson=new Gson();
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			gson.toJson(AppSingleton.getInstance().getClaimList(),osw);
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
