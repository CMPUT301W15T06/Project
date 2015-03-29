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
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
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
 * @version 03/16/2015
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i("ttttttt","1");

		ClaimListManager.initial(getApplicationContext());
		pg =ProgressDialog.show(this, "Pushing Onling...","Please wait patiently :)", true);
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {    	

		    	AppSingleton.getInstance().setUserList();
				
				runOnUiThread(new Runnable() {
		            @Override
		            public void run()
		            {
		              pg.dismiss();
		            }
		          });
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
		
		AppSingleton.getInstance().setTestContext(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	public void changeUser(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Enter your Name");
		final EditText input=new EditText(MainActivity.this);
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
		builder.create();  
		builder.show();
		
	}
	
	/**
	 * This method will allows user to start and enter the application
	 * as a claimant.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.content.Intent
	 */
	public void startClaimant(View v){
		Intent intent =new Intent(MainActivity.this,ClaimantClaimListActivity.class);
		startActivity(intent);
	}

}
