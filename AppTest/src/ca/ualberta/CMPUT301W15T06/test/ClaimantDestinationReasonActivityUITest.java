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
import ca.ualberta.CMPUT301W15T06.ClaimantAddDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

// test for US01.02.01
@SuppressWarnings("unused")
public class ClaimantDestinationReasonActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantAddDestinationActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText claimant_des;
	EditText claimant_reason;
	Button finish;
	
	public ClaimantDestinationReasonActivityUITest() {
		super(ClaimantAddDestinationActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		claimant_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
		claimant_reason = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
		finish = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton);
	}

	/*
	 * test US01.02.01 Basic Flow 9
	 */
	// This is layout test for DestinationEditText
	public void testClaimantDestinationTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, claimant_des);
	    assertTrue(View.GONE == claimant_des.getVisibility());
	}
	
	// This is layout test for ReasonEditText
	public void testClaimantReasonTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, claimant_reason);
	    assertTrue(View.GONE == claimant_reason.getVisibility());
	}

	/*
	 * test US01.02.01 Basic Flow 10 and 11
	 */
	//US01.02.01 fill blank
	private void testEditText(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
	}
	
	/*
	 * test US01.02.01 Basic Flow 12
	 */
	//US01.02.01 test finish button layout
	public void testApproverButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, finish);

	    final ViewGroup.LayoutParams layoutParams =
	    		finish.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	    assertEquals("Incorrect label of the button", "Finish", view.getText());
	}
	
	//US01.02.01 fill blank
	private void testAddButton(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton)).performClick();
	}
	
	//US01.02.01 test finish button activity
	public void testOpenNextActivity() {
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimDetailActivity.class.getName(), null, false);

		  // open current activity.
		  ClaimantAddDestinationActivity myActivity = getActivity();
		  final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		  myActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		  });

		  ClaimantClaimDetailActivity nextActivity = (ClaimantClaimDetailActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		  // next activity is opened and captured.
		  assertNotNull(nextActivity);
		  nextActivity .finish();
		}

	
	//test if add a new claim to list
	public void testClickAddButton(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton)).performClick();
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
	}

}
