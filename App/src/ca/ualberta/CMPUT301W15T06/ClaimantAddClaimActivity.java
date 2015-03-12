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
 * This <code>ClaimantAddClaimActivity</code> class is an extended class
 * of <code>Activity</code> class. This class will control the interface
 * of <code>Claim</code> detail and <code>ClaimList</code> for claimant. 
 * This view displays <code>Claim</code> details and creates an option menu,
 * a finish add button, a starting date picker and an ending date picker. 
 * It will be used when the approver asks to access to the <code>Claim</code> 
 * detail. The associated class including <code>ClaimantAddClaimController</code>,
 * <code>Claim</code>, <code>ClaimList</code> and <code>ClaimListManager</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.text.SimpleDateFormat
 * @see java.util.Calendar
 * @see java.util.Locale
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.app.DatePickerDialog
 * @see android.app.DatePickerDialog.OnDateSetListener
 * @see android.app.DialogFragment
 * @see android.view.Menu
 * @see android.view.View
 * @see android.widget.DatePicker
 * @see android.widget.EditText
 */
package ca.ualberta.CMPUT301W15T06;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class ClaimantAddClaimActivity extends Activity {

	/**
	 * Set a ClaimantAddDestinationController object cadc with initial 
	 * default value null.
	 */
	private ClaimantAddClaimController cacc=null;
	/**
	 * Set EditText object beginView to record the starting date with
	 * initial default value null. Set EditText object endView to 
	 * record the ending date with initial default value null.
	 * 
	 * @see android.widget.EditText
	 */
	private EditText beginView=null;
	private EditText endView=null;


	/**
	 * This method is an extened version of onCreate content view. 
	 * It will set up a new ClaimantAddClaimController object cacc to
	 * store the claimList. Then set up the EditText object beginView 
	 * and endView.
	 * 
	 * @param savedInstanceState  a Bundle object
	 * @see android.os.Bundle
	 * @see android.view.View
	 * @see android.widget.EditText
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_add_claim);
		
		cacc=new ClaimantAddClaimController(AppSingleton.getInstance().getClaimList());

		beginView=(EditText) findViewById(R.id.createClaimStartingDateEditText);
		endView=(EditText) findViewById(R.id.createClaimEndDateEditText);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_add_claim, menu);
		return true;
	}
	
	/**
	 * This method will create a finish button to trigger the action finishAdd. 
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 */
	public void finishAdd(View v){
		EditText nameView= (EditText) findViewById(R.id.createClaimNameEditText);
		if (beginView.getText().toString().equals("")){
			Toast.makeText(getApplicationContext(), "Start Date can't be empty", Toast.LENGTH_LONG).show();
		}else{
			cacc.addClaim(nameView.getText().toString(), beginView.getText().toString(),endView.getText().toString());
		}
		finish();
	}
	
	/**
	 * Create a DatePicker object to help user(claimant) to enter 
	 * the starting date.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 * @see android.widget.DatePicker
	 * @see android.app.DialogFragment;
	 * @see android.app.DatePickerDialog
	 * @see android.app.DatePickerDialog.OnDateSetListener
	 */
	public void showDatePickerDialogBegin(View v) {
		AppSingleton.getInstance().setDateEditText(beginView);
		DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	/**
	 * Create a DatePicker object to help user(claimant) to enter 
	 * the ending date.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 * @see android.widget.DatePicker
	 * @see android.app.DialogFragment;
	 * @see android.app.DatePickerDialog
	 * @see android.app.DatePickerDialog.OnDateSetListener
	 */
	public void showDatePickerDialogEnd(View v) {
		AppSingleton.getInstance().setDateEditText(endView);
		DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	



	



}
