package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.annotation.SuppressLint;
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

@SuppressLint("CutPasteId")
public class US01_06_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public US01_06_01_Test() {

		super(MainActivity.class);

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
	Menu menu;
	View View1;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	Button FinishButton;

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
	 * 
	 * Test for US01.06.01 Basic Flow 1
	 */

	// test button exists

	public void testLayout() {

		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));

		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));

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

		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);

		assertEquals("Incorrect label of the button", "Approver",

		view.getText());

		// test Claimant Button layout

		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams1 = ClaimantButton.getLayoutParams();

		assertNotNull(layoutParams1);

		assertEquals(layoutParams1.width,

		WindowManager.LayoutParams.WRAP_CONTENT);

		assertEquals(layoutParams1.height,

		WindowManager.LayoutParams.WRAP_CONTENT);

		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);

		assertEquals("Incorrect label of the button", "Claimant", view1.getText());

		// test if the button can create next activity

		// register next activity that need to be monitored.

		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		// open current activity.

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

		// view which is expected to be present on the screen

		final View decorView1 = nextActivity.getWindow().getDecorView();

		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);

		// check if it is on screen

		ViewAsserts.assertOnScreen(decorView1, listView);

		// check whether the Button object's width and height attributes match
		// the expected values

		final ViewGroup.LayoutParams layoutParams11 = listView
				.getLayoutParams();

		/* assertNotNull(layoutParams); */

		assertEquals(layoutParams11.width,
				WindowManager.LayoutParams.MATCH_PARENT);

		assertEquals(layoutParams11.height,
				WindowManager.LayoutParams.WRAP_CONTENT);

		// Click the menu option

		// open third activity by options menu

		ActivityMonitor am = getInstrumentation().addMonitor(ClaimantAddClaimActivity.class.getName(), null, false);

		// Click the menu option

		// getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);

		getInstrumentation().invokeMenuActionSync(nextActivity, ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 1);

		Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 10000);

		// ClaimantAddClaimActivity a = (ClaimantAddClaimActivity)
		// getInstrumentation().waitForMonitorWithTimeout(activityMonitor,
		// 10000);

		assertNotNull(a);

		input_name = (TextView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameTextView);

		input_start = (TextView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateTextView);

		input_end = (TextView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndingDateTextView);

		// test text view: createClaimNameEditText

		final View decorView11 = a.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView11, input_name);

		assertNotNull(input_name.getVisibility());

		// test text view: createClaimStartingDateEditText

		ViewAsserts.assertOnScreen(decorView11, input_start);

		assertNotNull(input_start.getVisibility());

		// test text view: createClaimEndDateEditText

		ViewAsserts.assertOnScreen(decorView11, input_end);

		assertNotNull(input_end.getVisibility());

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
		 * 
		 * test US01.06.01 Basic Flow 2
		 * 
		 * test US01.06.01 Basic Flow 3
		 */

		// test Finish button layout

		final View decorView2 = a.getWindow().getDecorView();

		FinishButton = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);

		ViewAsserts.assertOnScreen(decorView2, FinishButton);

		final ViewGroup.LayoutParams layoutParams111 =

		FinishButton.getLayoutParams();

		assertNotNull(layoutParams111);

		assertEquals(layoutParams111.width, WindowManager.LayoutParams.WRAP_CONTENT);

		assertEquals(layoutParams111.height, WindowManager.LayoutParams.WRAP_CONTENT);

		Button view11 = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);

		assertEquals("Incorrect label of the button", "Finish", view11.getText());

		// US01.01.01 test finish button activity

		// public void testOpenNextActivity() {

		// register next activity that need to be monitored.

		// open current activity.

		final Button button1 = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);

		a.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				// click button and open next activity.

				button1.performClick();

			}

		});

		// next activity is opened and captured.

		assertNotNull(nextActivity);

		nextActivity.finish();

	}

}