package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantReceiptActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class US06_04_01_Test extends ActivityInstrumentationTestCase2<MainActivity> {

	public US06_04_01_Test() {
		super(MainActivity.class);
	}
	
	Button ApproverButton;
	Button ClaimantButton;
	Button UserButton;
	Instrumentation instrumentation;
	MainActivity activity;
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

	// set up
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
		u = new User("t");
		cclc = new ClaimantClaimListController(u);
	}

	@SuppressLint("CutPasteId")
	public void test060401() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click user button
				assertTrue(UserButton.performClick());
			}
		});
		// click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(
				ClaimantClaimListActivity.class.getName(), null, false);
		// get current activity
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
		// start next activity
		final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation()
				.waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		

		// view which is expected to be present on the screen
		final View decorView1 = nextActivity.getWindow().getDecorView();
		// layout of claim list
		listView = (ListView) nextActivity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, listView);
		// check whether the Button object's width and height attributes match
		// the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView
				.getLayoutParams();
		assertEquals(layoutParams11.width,
				WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height,
				WindowManager.LayoutParams.WRAP_CONTENT);
		final ListView claimList = (ListView) nextActivity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);

		// get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@SuppressLint("CutPasteId")
			@Override
			public void run() {
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(
						ClaimantItemListActivity.class.getName(), null, false);
				// perform click on claim
				claimList.getChildAt(0).performClick();
				// start claimant item list activity
				// to view the item list of this claim
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation()
						.waitForMonitorWithTimeout(am, 10000);
				// check if the activity is started
				assertNotNull(thirdActivity);
				
				/*
				 * Test for US 06.03.01 Basic Flow 1
				 */
				// get decorview
				final View decorView2 = nextActivity.getWindow().getDecorView();
				// get item list view
				ListView ilv = (ListView) thirdActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView2, ilv);

				// perform click on one item
				final ListView itemlist = (ListView) thirdActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				thirdActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						itemlist.getChildAt(0).performClick();
					}
				});

				// check alert dialogue
				AlertDialog d = (AlertDialog) thirdActivity.getDialog();
				// check if the dialogue is returned by activity
				assertNotNull(d);
				// get option array list
				ListView choose = (ListView) ilv
						.findViewById(ca.ualberta.CMPUT301W15T06.R.array.item_dialog_array);
				// array list is not null
				assertNotNull(choose);
				
				// perform click on option list
				choose.getChildAt(1).performClick();
				
				// start claimant receipt activity
				// to view the receipt of item
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantReceiptActivity.class.getName(), null, false);
				ClaimantReceiptActivity ccc = (ClaimantReceiptActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				assertNotNull(ccc);
 
				// get space for receipt
				ImageView iv = (ImageView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.photoReciptImageView);
				assertNotNull(iv);
				
				assertNotNull(iv.getAccessibilityNodeProvider());
			}
		});
	}
}
