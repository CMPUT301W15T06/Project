package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class US01_01_01_Test_2 extends
		ActivityInstrumentationTestCase2<ClaimantAddClaimActivity> {

	public US01_01_01_Test_2() {
		super(ClaimantAddClaimActivity.class);
	}
	
	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;

	
	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		input_name = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameTextView);
		input_start = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateTextView);
		input_end = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndingDateTextView);
		intent = new Intent(getInstrumentation().getTargetContext(), ClaimantAddClaimActivity.class);	
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
}
