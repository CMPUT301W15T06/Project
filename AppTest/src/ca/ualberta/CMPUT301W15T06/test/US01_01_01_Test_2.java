package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class US01_01_01_Test_2 extends
	ActivityInstrumentationTestCase2<MainActivity> {

	public US01_01_01_Test_2() {
		super(MainActivity.class);
	}
	Button ApproverButton;
	Button ClaimantButton;
	Button FinishButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);	
	}
	/*
	 * Test for US01.01.01 Basic Flow 7
	 */
	// test text view: createClaimNameEditText
	public void testTextView_layout() {
		//test if the button can create next activity
		//register next activity that need to be monitored
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(),null, false);
		//open current activity
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				ClaimantButton.performClick();
			}
		});
		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		assertNotNull(nextActivity);
		//open third activity by options menu
		ActivityMonitor am = getInstrumentation().addMonitor(ClaimantAddClaimActivity.class.getName(), null, false);
 
		// Click the menu option
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
		getInstrumentation().invokeMenuActionSync(nextActivity,ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 1);
		Activity a =   getInstrumentation().waitForMonitorWithTimeout(am, 10000);

		//ClaimantAddClaimActivity a = (ClaimantAddClaimActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		assertNotNull(a);
		
		input_name =  (TextView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameTextView);
		input_start = (TextView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateTextView);
		input_end = (TextView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndingDateTextView);
	
		//test text view: createClaimNameEditText
		final View decorView = a.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, input_name);
		assertNotNull(input_name.getVisibility());
		// test text view: createClaimStartingDateEditText
		ViewAsserts.assertOnScreen(decorView, input_start);
		assertNotNull(input_start.getVisibility());
   
		// test text view: createClaimEndDateEditText
		ViewAsserts.assertOnScreen(decorView, input_end);
		assertNotNull(input_end.getVisibility());
		/*
		 * test US01.01.01 Basic Flow 8
		 */
		// fill blank 
		
		String claimantName = "a";
		String claimantStartingDate = "b";
		String itemEndingDate = "c";
		claimant_name = ((EditText) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameEditText));
		claimant_starting_date = ((EditText) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateEditText));
		claimant_ending_date = ((EditText) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndDateEditText));
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);	
		/*
		 * test US01.01.01 Basic Flow 9
		 */
	
		// test Finish button layout

		final View decorView1 = a.getWindow().getDecorView();
		FinishButton = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);

		ViewAsserts.assertOnScreen(decorView1, FinishButton);
		
		final ViewGroup.LayoutParams layoutParams =
				FinishButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
		assertEquals("Incorrect label of the button", "Finish", view.getText());
   


		/*
		 * test US01.01.01 Basic Flow 10
		 * 
		 */
		//	
		//US01.01.01 test finish button activity
		//	public void testOpenNextActivity() {
		// register next activity that need to be monitored.
		//ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		// open current activity.
		// ClaimantAddClaimActivity a = (ClaimantAddClaimActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
	 final Button button = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
	 a.runOnUiThread(new Runnable() {
		 @Override
		 public void run() {
			 // click button and open next activity.
			 button.performClick();
		 }
	 });
 
	 // next activity is opened and captured.
	 assertNotNull(nextActivity);
	 nextActivity.finish();
	}
}