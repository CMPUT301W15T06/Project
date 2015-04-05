package ca.ualberta.CMPUT301W15T06.test;

import java.util.Date;

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
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
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US02_02_01_Test<Final> extends
		ActivityInstrumentationTestCase2<MainActivity> {

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

	public US02_02_01_Test() {
		super(MainActivity.class);
	}

	// set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(false);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		UserButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),MainActivity.class);
		u = new User("temp");
		cclc = new ClaimantClaimListController(u);
	}

	/*
	 * Test for US01.01.01 Basic Flow 1
	 */
	// test button exists
	public void testLayout() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton));

		// test "Approver" button layout
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, ApproverButton);
		final ViewGroup.LayoutParams layoutParams = ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height,WindowManager.LayoutParams.WRAP_CONTENT);
		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver",view.getText());

		// test "Claimant" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);
		final ViewGroup.LayoutParams layoutParams1 = ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height,WindowManager.LayoutParams.WRAP_CONTENT);
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant",view1.getText());

		// test "Change User" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);
		final ViewGroup.LayoutParams layoutParams2 = UserButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams2.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams2.height,WindowManager.LayoutParams.WRAP_CONTENT);
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		assertEquals("Incorrect label of the button", "Change User",view2.getText());

	/*
	 * Test for US 01.01.01 Basic Flow 2 Test for US 01.01.01 Basic Flow 3
	 * Test for US 01.01.01 Basic Flow 4 Test for US 01.01.01 Basic Flow 5
	 */
		// User click "Change User"

		 activity.runOnUiThread(new Runnable(){
		
		 @Override
		 public void run() {
			 //open the dialog
			 UserButton.performClick();
			 
			 }
		 });

	/*
	 * Test for US 01.01.01 Basic Flow 6
	 */
		// click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		// open current activity
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
	 * Test Case for US01.01.01 Basic Flow 7
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
		assertEquals(layoutParams11.height,WindowManager.LayoutParams.WRAP_CONTENT);
		
		// test if the list is ordered
		// 4 new dates
//		String d1 = "2013-10-10";
//		String d2 = "2012-03-27";
//		String d3 = "2014-05-12";
//		String d4 = "2013-12-01";
//
//		// add new claim, date is d1
//		Claim claim = new Claim();
//		claim.setBeginDate(d1);
//		cclc.addClaim();
//		// add new claim, date is d2
//		cacc.addClaim("B", d2, d2);
//		// add new claim, date is d3
//		cacc.addClaim("C", d3, d3);
//		// add new claim, date is d4
//		cacc.addClaim("D", d4, d4);
//		// get length of claim list
//		int length = cList.getClaimList().size();
//		// initialize date
//		Date last_date = cList.getClaimList().get(0).getBeginDate();
//		Date new_date = last_date;
//		// date should be sorted from the oldest one to the nearest one
//		int i = 0;
//		while (i < length){
//		new_date = cList.getClaimList().get(i).getBeginDate();
//		// It's an error now because our function returns null
//		assertTrue("new date is larger than or equal to old date", new_date.compareTo(last_date)>=0);
//		i++;
//		}

	}
}
