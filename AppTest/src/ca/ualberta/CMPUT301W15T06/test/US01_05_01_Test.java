package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditClaimActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.R;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class US01_05_01_Test extends

ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	Button AddDestination;
	TextView nameView;
	TextView beginView;
	TextView endView;
	ListView listView;

	public US01_05_01_Test() {
		super(MainActivity.class);
	}

	// set up

	protected void setUp() throws Exception {
		super.setUp();

		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		AddDestination = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
	}

	/*
	 * Test for US01.04.01 Basic Flow 1
	 */

	// test button exists

	public void testLayout() {
		assertNotNull(activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		// test Approver button layout
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, ApproverButton);
		final ViewGroup.LayoutParams layoutParams =
		ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width,
				WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height,
				WindowManager.LayoutParams.WRAP_CONTENT);
		Button view = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver",
				view.getText());
		/*
		 * 
		 * Test for US01.04.01 Basic Flow 2
		 */

		// test Claimant Button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);
		final ViewGroup.LayoutParams layoutParams1 =
		ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width,
				WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height,
				WindowManager.LayoutParams.WRAP_CONTENT);
		Button view1 = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant",
				view1.getText());
		// test if the button can create next activity
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(
				ClaimantClaimListActivity.class.getName(), null, false);
		// open current activity.
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
		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation()
				.waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		/*
		 * 
		 * Test for US01.04.01 Basic Flow 3
		 */

		// view which is expected to be present on the screen
		final View decorView2 = nextActivity.getWindow().getDecorView();
		listView = (ListView) nextActivity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView2, listView);
		// check whether the Button object's width and height attributes match
		// the expected values
		final ViewGroup.LayoutParams layoutParams2 = listView.getLayoutParams();
		assertEquals(layoutParams2.width,
				WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams2.height,
				WindowManager.LayoutParams.WRAP_CONTENT);
		/*
		 * 
		 * Test for US01.04.01 Basic Flow 4
		 */
		final ListView claimList = (ListView) nextActivity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		assertNotNull(claimList.performLongClick());
		/*
		 * 
		 * Test for US01.04.01 Basic Flow 5
		 */
		// test ClaimList(ListView) ContextMenu
		final Context contextMenu = (Context) nextActivity.getBaseContext();
		assertTrue(contextMenu != null);
		nextActivity.runOnUiThread(new Runnable() {
			public void run() {
				claimList.getContext();
			}
		});
		getInstrumentation().waitForIdleSync();
		/*
		 * 
		 * Test for US01.04.01 Basic Flow 6
		 */
		// promote info, check button
		// test if the button can create next activity
		// register next activity that need to be monitored
		// open second activity
		assertNotNull(nextActivity);
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// build controller
				ClaimantAddClaimController cacc = new ClaimantAddClaimController(
						new ClaimList());
				// get ClaimantClaimListActivity
				ActivityMonitor activityMonitor = getInstrumentation()
						.addMonitor(ClaimantClaimListActivity.class.getName(),
								null, false);
				final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation()
						.waitForMonitorWithTimeout(activityMonitor, 10000);
				// get claimListView
				final ListView claimList = (ListView) nextActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				// long click new claim
				assertNotNull(claimList.getChildAt(0).performLongClick());
				// get contextMenu
				final GridView contextMenu = (GridView) nextActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);
				// check if user can click this context menu
				assertNotNull(contextMenu.performClick());
				// open new activity
				ActivityMonitor am = getInstrumentation().addMonitor(
						ClaimantClaimDetailActivity.class.getName(), null,
						false);
				nextActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						long row = 4;
						contextMenu.performItemClick(contextMenu, 0, row);
					}
				});
				ClaimantClaimListActivity a = (ClaimantClaimListActivity) getInstrumentation()
						.waitForMonitorWithTimeout(am, 10000);
				assertNotNull(a);
			}
		});
	}
}