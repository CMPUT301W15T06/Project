package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantReceiptActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.provider.MediaStore;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US06_01_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {
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
	User u;

	public US06_01_01_Test() {
		super(MainActivity.class);
	}

	// set up
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

	public void test060101() {
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
		// run main activity
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
			@Override
			public void run() {
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(
						ClaimantItemListActivity.class.getName(), null, false);
				// perform click on claim
				claimList.getChildAt(0).performClick();
				// start claimant item list activity
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation()
						.waitForMonitorWithTimeout(am, 10000);
				// check if the activity is started
				assertNotNull(thirdActivity);

				/*
				 * Test for US 06.01.01 Basic Flow 1
				 */
				// get decorview
				final View decorView2 = nextActivity.getWindow().getDecorView();
				// get item list view
				ListView ilv = (ListView) thirdActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView2, ilv);
				
				/*
				 * Test for US 06.01.01 Basic Flow 2
				 */
				final ListView itemlist = (ListView) thirdActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// perform click on item
				thirdActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						itemlist.getChildAt(0).performClick();
					}
				});
				
				/*
				 * Test for US 06.01.01 Basic Flow 3,4
				 */
				// check dialogue
				AlertDialog d = (AlertDialog) thirdActivity.getDialog();
				// check if dialogue is returned by activity
				assertNotNull(d);
				
				// check option list
				ListView choose = (ListView) ilv
						.findViewById(ca.ualberta.CMPUT301W15T06.R.array.item_dialog_array);
				assertNotNull(choose);
				
				// perform click on option in array list
				choose.getChildAt(1).performClick();
				
				// start claimant receipt activity
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantReceiptActivity.class.getName(), null, false);
				ClaimantReceiptActivity ccc = (ClaimantReceiptActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// check if the activity is started
				assertNotNull(ccc);
				
				/*
				 * Test for US 06.01.01 Basic Flow 5
				 */
				ImageView iv = (ImageView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.photoReciptImageView);
				// check if the view is shown on screen
				assertNotNull(iv);
				
				/*
				 * Test for US 06.01.01 Basic Flow 6,7,8,9
				 */
				// take photographic receipt
				ActivityMonitor mmm = getInstrumentation().addMonitor(ClaimantReceiptActivity.class.getName(), null, false);
				// Click the menu option
				getInstrumentation().invokeMenuActionSync(nextActivity,ca.ualberta.CMPUT301W15T06.R.id.takephoto, 1);
				// start activity
				Activity a = getInstrumentation().waitForMonitorWithTimeout(am,10000);
				assertNotNull(a);
				/*
				 * Test for US 06.01.01 Basic Flow 10,11
				 */
				a.finish();
				
				ImageView i1 = (ImageView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.photoReciptImageView);
				assertNotNull(i1);
				ccc.finish();
			}
		});
		nextActivity.finish();
		activity.finish();
	}
}
