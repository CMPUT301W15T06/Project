package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditClaimActivity;
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
import android.widget.TextView;

public class US01_04_01_Test_2 extends ActivityInstrumentationTestCase2<ClaimantEditClaimActivity> {

	public US01_04_01_Test_2() {
		super(ClaimantEditClaimActivity.class);
	}
	
	Instrumentation instrumentation;
	Activity activity;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	Button FinishButton;

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		claimant_name = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimNameEditText));
		claimant_starting_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateEditText));
		claimant_ending_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndDateEditText));
		input_name = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimNameTextView);
		input_start = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateTextView);
		input_end = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndingDateTextView);
		FinishButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimFinishButton);
	}
	
	
	/*
	 * Test for US01.01.01 Basic Flow 7
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
	 * Test for US01.04.01 Basic Flow 8
	 */
	public void testEdit(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton));
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);
		
	}
	
	
	/*
	 * Test for US01.04.01 Basic Flow 9
	 */
	
	//test finish button Layout
	public void testFinishButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, FinishButton);

	    final ViewGroup.LayoutParams layoutParams =
	           FinishButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimFinishButton);
	    assertEquals("Incorrect label of the button", "Finish", view.getText());
	}
	
	//test finish button activity
	public void testOpenNextActivity() {
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		  // open current activity.
		  ClaimantEditClaimActivity myActivity = getActivity();
		  final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
		  myActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		  });

		  ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
		  // next activity is opened and captured.
		  assertNotNull(nextActivity);
		  nextActivity .finish();
	}

}
