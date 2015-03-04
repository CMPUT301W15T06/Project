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

package ca.ualberta.CMPUT301W15T06.test;


import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClaimantAddClaimActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantAddClaimActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	Button FinishButton;
	
	public ClaimantAddClaimActivityUITest() {
		super(ClaimantAddClaimActivity.class);
	}
	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		claimant_name = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameEditText));
		claimant_starting_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateEditText));
		claimant_ending_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndDateEditText));
		input_name = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameTextView);
		input_start = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateTextView);
		input_end = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndingDateTextView);
		FinishButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
	}

	/*
	 * test US01.01.01 Basic Flow 7
	 */
	
	// test text view: createClaimNameEditText
	public void testClaimantNameTextView_layout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, input_name);
	    assertTrue(View.GONE == input_name.getVisibility());
	}
	
	// test text view: createClaimStartingDateEditText
	public void testClaimantStartingDateTextView_layout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, input_start);
	    assertTrue(View.GONE == input_start.getVisibility());
	}
	
	// test text view: createClaimEndDateEditText
	public void testClaimantEndingDateTextView_layout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, input_end);
	    assertTrue(View.GONE == input_end.getVisibility());
	}
	

	/*
	 * test for US01.06.01 Basic Flow 1
	 */
	// fill blank (Test US01.01.01 & US01.04.01 & US01.05.01)
	@SuppressWarnings("unused")
	private void testAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton));
		/*
		 * test US01.01.01 Basic Flow 8
		 */
		claimant_name.setText(claimantName);
		/*
		 * test US01.01.01 Basic Flow 9
		 */
		claimant_starting_date.setText(claimantStartingDate);
		/*
		 * test US01.01.01 Basic Flow 10
		 */
		claimant_ending_date.setText(itemEndingDate);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton)).performClick();
	}
	
	/*
	 * test US01.01.01 Basic Flow 11
	 * test US01.06.01 Basic Flow 2
	 */
	// US01.01.01 & US01.04.01 test Finish button layout
	public void testApproverButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, FinishButton);

	    final ViewGroup.LayoutParams layoutParams =
	           FinishButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	    assertEquals("Incorrect label of the button", "Finish", view.getText());
	}

	/*
	 * test for US01.06.01 Basic Flow 3
	 */
	
	//test if add a new claim to list (US01.03.01 & US 01.06.01)
	public void testClickAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton));
		
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton)).performClick();
	    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));	    
	}
	
	//US01.01.01 test finish button activity
	public void testOpenNextActivity() {
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		  // open current activity.
		  ClaimantAddClaimActivity myActivity = getActivity();
		  final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
		  myActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		  });

		  ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		  // next activity is opened and captured.
		  assertNotNull(nextActivity);
		  nextActivity .finish();
	}
}