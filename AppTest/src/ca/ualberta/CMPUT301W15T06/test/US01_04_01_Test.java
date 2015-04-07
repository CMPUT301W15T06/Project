package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("CutPasteId")
public class US01_04_01_Test extends

ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button UserButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	MainActivity activity;
	EditText textInput;
	Intent intent;
	Button AddDestination;
	TextView nameView;
	TextView beginView;
	TextView endView;
	ListView listView;

	public US01_04_01_Test() {
		super(MainActivity.class);
	}

	// set up

	protected void setUp() throws Exception {
		super.setUp();

		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		AddDestination = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		UserButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),MainActivity.class);
	}


	public void testUS010401() {
		
		//click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		//open current activity			
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
				}
		});

		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		
		/*
		 * Test for US01.04.01 Basic Flow 1
		 */
		// view which is expected to be present on the screen
		final View decorView1 = nextActivity.getWindow().getDecorView();

		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, listView);
		// check whether the Button object's width and height attributes match
		// the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
		/* assertNotNull(layoutParams); */
		assertEquals(layoutParams11.width,WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);
		
		/*
		 * Test for US01.04.01 Basic Flow 2
		 */
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
		//get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click the list open next activity.
				claimList.performItemClick(claimList, 0, 0);
			}
		});
		ClaimantItemListActivity a = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
		assertNotNull(a);
		
		/*
		 * Test for US01.04.01 Basic Flow 3
		 */	
		ListView ilv = (ListView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, ilv);
						
		/*
		 * Test for US01.04.01 Basic Flow 4,5,6
		 */	
		// Click the menu option;
		ActivityMonitor ma = getInstrumentation().addMonitor(ClaimantEditClaimActivity.class.getName(), null, false);
		// Click the menu option
		getInstrumentation().invokeMenuActionSync(a,ca.ualberta.CMPUT301W15T06.R.id.edit, 1);
		Activity b = getInstrumentation().waitForMonitorWithTimeout(ma,10000);
		assertNotNull(b);

		/*
		 * Test for US01.04.01 Basic Flow 7
		 */
		EditText claimant_starting_date = ((EditText) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateEditText));
		EditText claimant_ending_date = ((EditText) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndDateEditText));
		TextView input_start = (TextView) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateTextView);
		TextView input_end = (TextView) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndingDateTextView);
		

		// test text view: createClaimNameEditText
		final View decorView = b.getWindow().getDecorView();;
		// test text view: createClaimStartingDateEditText
		ViewAsserts.assertOnScreen(decorView, input_start);
		assertNotNull(claimant_starting_date);
		// test text view: createClaimEndDateEditText
		ViewAsserts.assertOnScreen(decorView, input_end);
		assertNotNull(claimant_ending_date);

		/*
		 * Test for US01.04.01 Basic Flow 8
		 */
		//String claimantStartingDate = "2015-03-04";
		//String itemEndingDate = "2015-03-05";
		//claimant_starting_date.setText(claimantStartingDate);
		//claimant_ending_date.setText(itemEndingDate);

		/*
		 * Test for US01.04.01 Basic Flow 9
		 */
		b.finish();
		ListView ilv_up = (ListView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, ilv_up);
		// finish activity

		a.finish();
		nextActivity.finish();
		activity.finish();		
	}
}
