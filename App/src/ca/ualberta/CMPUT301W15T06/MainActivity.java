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

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This <code>MainActivity</code> class is an extended class of 
 * <code>Activity</code> class. This class will control the
 * Main Application view. It will also creates an content view,
 * an option menu, and allows user to enter the program as a 
 * claimant.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.content.Intent
 * @see android.view.Menu
 * @see android.view.View
 */
public class MainActivity extends Activity {


	private ProgressDialog pg;
	private User user;
	private TextView userName;
	
	private Dialog dialog;
	private EditText inputET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		


		setTitle("HINT: Title Bar will Show Path");
		setTitleColor(Color.RED);
		ClaimListManager.initial(getApplicationContext());
		pg =ProgressDialog.show(this, "Loading Data...","Please wait patiently :)", true);
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {    	
		    	threadLoad();
		    }
		    
		});
		thread.start();
	
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Can't join!");
		}
		
		AppSingleton.getInstance().setCurrentUser("Guest");
		
		user=AppSingleton.getInstance().getCurrentUser();
		userName=(TextView) findViewById(R.id.userTextView);
		userName.setText(user.getUserName());
	}

	/**
	 * This method will load a new thread to the application.
	 */
	private void threadLoad() {
		// TODO Auto-generated method stub
		AppSingleton.getInstance().setUserList();
		
		runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
              pg.dismiss();
            }
          });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
	
	
	/**
	 * This method will be used when there's a need to change user of the application.
	 * 
	 * @param v  the basic building block for user interface components
	 */
	public void changeUser(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Enter your Name");
		final EditText input=new EditText(MainActivity.this);
		inputET = input;
		input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);			
		builder.setView(input);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				AppSingleton.getInstance().setCurrentUser(input.getText().toString());
				userName.setText(AppSingleton.getInstance().getCurrentUser().getUserName());
			}
		});
		builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		AlertDialog dialog =builder.create();  
		dialog.show();
		
	}
	
	/**
	 * This method will allows user to start and enter the application
	 * as a claimant.
	 * 
	 * @param v  the basic building block for user interface components
	 * @see android.view.View
	 * @see android.content.Intent
	 */
	public void startClaimant(View v){
		Intent intent =new Intent(MainActivity.this,ClaimantClaimListActivity.class);
		startActivity(intent);
		AppSingleton.getInstance().setcMod(true);
	}
	
	/**
	 * This method will allows user to start and enter the application
	 * as a approver.
	 * 
	 * @param v  the basic building block for user interface components
	 * @see android.view.View
	 * @see android.content.Intent
	 */
	public void startApprover(View v){
		
		checkInternet();
		
		if(AppSingleton.getInstance().isSuc()){
			Intent intent =new Intent(MainActivity.this,ApproverClaimListActivity.class);
    		startActivity(intent);
    		AppSingleton.getInstance().setcMod(false);
		}else{
			Toast.makeText(MainActivity.this, "Can't work as Approver when offline!", Toast.LENGTH_LONG).show();
		}
		

	}

	/**
	 * This method will call <code>ESClient</code> to check if the Internet is available.
	 */
	private void checkInternet() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {    	
		    	if(new ESClient().getUserList()==null){
					AppSingleton.getInstance().setSuc(false);
		    	}else{
		    		AppSingleton.getInstance().setSuc(true);
		    		
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

	/**
	 * This method will return a dialog window for the interface.
	 * 
	 * @return a dialog window
	 */
	public Dialog getDialog() {
		return dialog;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args){
		super.onPrepareDialog(id, dialog, args);
		this.dialog = dialog;
	}
	
	/**
	 * This method will return a edit text object for editing text.
	 * 
	 * @return the input text
	 */
	public EditText getInputField() {
		return inputET;
	}
}
