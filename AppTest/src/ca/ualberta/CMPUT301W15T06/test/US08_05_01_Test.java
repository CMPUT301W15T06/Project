package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.ApproverClaimDetailListActivity;
import ca.ualberta.CMPUT301W15T06.ApproverClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ApproverItemListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantReceiptActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;

public class US08_05_01_Test extends ActivityInstrumentationTestCase2<MainActivity>  {

	public US08_05_01_Test() {
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
	User u;

	
	//set up
	protected void setUp() throws Exception {
		super.setUp();
		AppSingleton.getInstance().setTest(true);
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
		u = AppSingleton.getInstance().getCurrentUser();

	}

	public void test080501() {

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
		ApproverClaimListActivity nextActivity = (ApproverClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);

		// get claim list view
		final ListView al =  (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// get decorview
		final View decorView1 = nextActivity.getWindow().getDecorView();
		// make sure list is on screen
		ViewAsserts.assertOnScreen(decorView1, al);

		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click claim
				al.performClick();

				// get dialogue
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApproverClaimListActivity.class.getName(), null, false);
				ApproverClaimListActivity nActivity = (ApproverClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				View d = nActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);
				assertNotNull(d);

				// click option in dialogue
				d.performClick();

				ActivityMonitor activityMonitor2 = getInstrumentation().addMonitor(ApproverClaimListActivity.class.getName(), null, false);
				ApproverClaimDetailListActivity dActivity = (ApproverClaimDetailListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor2, 10000);
				assertNotNull(dActivity);
				
				ActivityMonitor activityMonitor3 = getInstrumentation().addMonitor(ApproverItemListActivity.class.getName(), null, false);
				ApproverItemListActivity aila = (ApproverItemListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor3, 10000);
				
				/*
				 * Test for US 08.05.01 Basic Flow 1
				 */
				// get list in approver page
				final View ail = aila.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverItemListView);
				View decorView = aila.getWindow().getDecorView();
				// make sure list is on screen
				ViewAsserts.assertOnScreen(decorView, ail);
				
				/*
				 * Test for US 08.05.01 Basic Flow 2
				 */
				aila.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ail.performClick();
						
						/*
						 * Test for US 08.05.01 Basic Flow 3
						 */
						// get dialogue
						ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApproverItemListActivity.class.getName(), null, false);
						ApproverItemListActivity nActivity = (ApproverItemListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
						View d = nActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.approver_item_dialog_array);
						assertNotNull(d);
						View decorView = nActivity.getWindow().getDecorView();
						ViewAsserts.assertOnScreen(decorView, d);
						
						/*
						 * Test for US 08.05.01 Basic Flow 4
						 */
						// click option in dialogue
						d.performClick();
						
						/*
						 * Test for US 08.05.01 Basic Flow 5
						 */
						ActivityMonitor activityMonitor2 = getInstrumentation().addMonitor(ClaimantReceiptActivity.class.getName(), null, false);
						ClaimantReceiptActivity dActivity = (ClaimantReceiptActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor2, 10000);
						assertNotNull(dActivity);
					}
				});
			}
		});
	
	}
}