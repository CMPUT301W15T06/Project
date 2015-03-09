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
 * This <code>ClaimantAddDestinationActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * adding Destination. The interface need Bundle, EditText, option menu bar 
 * and finishBbutton. 
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.view.Menu
 * @see android.view.View
 * @see android.widget.EditText
 * @see android.widget.Toast
 */
package ca.ualberta.CMPUT301W15T06;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClaimantAddDestinationActivity extends Activity {

	/**
	 * Set a ClaimantAddDestinationController object cadc with initial 
	 * default value null.
	 */
	private ClaimantAddDestinationController cadc=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_destination_reason);
		
		cadc=new ClaimantAddDestinationController(AppSingleton.getInstance().getCurrentClaim());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_destination_reason, menu);
		return true;
	}
	
	/**
	 * This method will create a finish button to trigger the action finishAdd. 
	 * It will also check the exceptions to prevent crush.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 * @exception StatusException
	 */
	public void finishAdd(View v){
		EditText destinationView= (EditText) findViewById(R.id.DestinationEditText);
		EditText reasonView=(EditText) findViewById(R.id.ReasonEditText);
		try {
			cadc.addDestination(destinationView.getText().toString(),reasonView.getText().toString());
		} catch (StatusException e) {
			Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}
		finish();
	}

}
