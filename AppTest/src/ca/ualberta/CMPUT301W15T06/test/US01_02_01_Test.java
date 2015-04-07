package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US01_02_01_Test<Final> extends
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
	ClaimantClaimListController cclc;
	User u;


	public US01_02_01_Test() {
		super(MainActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
	instrumentation = getInstrumentation();
	activity = getActivity();
	setActivityInitialTouchMode(false);
	ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
	UserButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
	intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);	
	u = new User("t");
	cclc = new ClaimantClaimListController(u);


	}

	public void testUS010201() {
		// test button layouts
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton));
 
		//test "Approver" button layout
		final View decorView = activity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, ApproverButton);

		final ViewGroup.LayoutParams layoutParams =
				ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver", view.getText());
		
		//test "Claimant" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams1 =
				ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant", view1.getText());
		
		//test "Change User" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams2 =
				UserButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams2.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams2.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		assertEquals("Incorrect label of the button", "Change User", view2.getText());

		/*
		 * Test for US01.02.01 Basic Flow 1
		 */
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
		 * Test Case for US01.02.01 Basic Flow 2	
		 */
		
		// view which is expected to be present on the screen			
		final View decorView1 = nextActivity.getWindow().getDecorView();
	 
		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, listView);
		// check whether the Button object's width and height attributes match the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
		/*assertNotNull(layoutParams);*/
		assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	 
		
		/*
		 * Test for US 01.02.01 Basic Flow 3
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
		 * Test for US 01.02.01 Basic Flow 4
		 */		
		ListView ilv = (ListView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, ilv);
				
		/*
		 * Test for US 01.02.01 Basic Flow 5,6,7
		 */	
		// Click the menu option;
		ActivityMonitor aamm = getInstrumentation().addMonitor(ClaimantClaimDetailActivity.class.getName(), null, false);
		// Click the menu option
		getInstrumentation().invokeMenuActionSync(a,ca.ualberta.CMPUT301W15T06.R.id.detail, 1);
		Activity b = getInstrumentation().waitForMonitorWithTimeout(aamm,10000);
		assertNotNull(b);

		/*
		 * Test for US 01.02.01 Basic Flow 8
		 */		

		//test 'Add a Destination' button layout
		Button addDestination = (Button) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		final View dv = b.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(dv, addDestination);
		final ViewGroup.LayoutParams lp =addDestination.getLayoutParams();
		assertNotNull(lp);
		assertEquals(lp.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(lp.height, WindowManager.LayoutParams.WRAP_CONTENT);
			
		assertEquals("Incorrect label of the button", "Add a destination", addDestination.getText());
		
		/*
		 * Test for US 01.02.01 Basic Flow 9
		 */	
		//test click "Add a Destination" button						
		// open next activity.
		ActivityMonitor amam = getInstrumentation().addMonitor(ClaimantEditDestinationActivity.class.getName(), null, false);
		final Button desButton = (Button) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		b.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				desButton.performClick();
			}
		});
		// next activity is opened and captured.
		ClaimantEditDestinationActivity c = (ClaimantEditDestinationActivity) getInstrumentation().waitForMonitorWithTimeout(amam, 1000);
		assertNotNull(c);

		/*
		 * test US01.02.01 Basic Flow 10,11
		 */
		// test interface
		EditText claimant_des = ((EditText) c.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
		EditText claimant_reason = ((EditText) c.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
	
		final View dv1 = c.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(dv1, claimant_des);
		assertNotNull(claimant_des);
	
		ViewAsserts.assertOnScreen(dv1, claimant_reason);
		assertNotNull(claimant_reason);

		c.finish();

		/*
		 * test US01.02.01 Basic Flow 12
		 */
		//show new list
		ListView ilv1 = (ListView) b.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantDetailListView);
		// check if it is on screen
		assertNotNull(ilv1);
		b.finish();
		a.finish();
		nextActivity.finish();
		activity.finish();
	}
}
