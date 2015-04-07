package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.ApproverClaimDetailListActivity;
import ca.ualberta.CMPUT301W15T06.ApproverClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ApproverItemListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantReceiptActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;

public class US08_07_01_Test extends ActivityInstrumentationTestCase2<MainActivity>  {

	public US08_07_01_Test() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	Button ApproverButton;
	Button ClaimantButton;
	Button UserButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	Menu menu;
	View View1;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	Button FinishButton;
	ClaimantClaimListController cclc;
	User u;
	ApproverClaimListActivity cl;
	
	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(false);
		ApproverButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		UserButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		u = new User("temp");
		cclc = new ClaimantClaimListController(u);
		cl = new ApproverClaimListActivity();
	}

	public void test080701() {

		//click "Approver" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApproverClaimListActivity.class.getName(), null, false);
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
		
		// approver claim list activity is started
		final ApproverClaimListActivity nextActivity = (ApproverClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		assertNotNull(nextActivity);

		// get claim list in approver page
		final ListView al =  (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// get decorview
		final View decorView1 = nextActivity.getWindow().getDecorView();
		// make sure list is on screen
		ViewAsserts.assertOnScreen(decorView1, al);
		
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				/*
				 * Test for US 08.07.01 Basic Flow 1
				 */
				// click on claim
				al.performClick();

				/*
				 * Test for US 08.07.01 Basic Flow 2
				 */
				// get dialogue
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApproverClaimListActivity.class.getName(), null, false);
				ApproverClaimListActivity nActivity = (ApproverClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				View d = nActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);
				assertNotNull(d);

				/*
				 * Test for US 08.07.01 Basic Flow 3
				 */
				// click option in dialogue
				d.performClick();

				/*
				 * Test for US 08.07.01 Basic Flow 4
				 */
				final ListView al =  (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
				final View decorView1 = nextActivity.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(decorView1, al);
			}
		});
	
	}
}