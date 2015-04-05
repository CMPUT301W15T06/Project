package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
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
public class US03_01_01_Test<Final> extends
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
	Object test_tag;
	Object update_list;

	public US03_01_01_Test() {
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
	test_tag = null;
	}


	public void test030101() {

		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
		//User click "Change User"
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				assertTrue(UserButton.performClick());	

				//test opening a dialog
		    	// access the alert dialog using the getDialog() method created in the activity
				AlertDialog d = (AlertDialog) activity.getDialog();
				
			}	
		});
		

		//click "Claimant" button and create next activity
		//open current activity			
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ActivityMonitor activityMonitor00 = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
				// click button and open next activity.
				button.performClick();
				final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor00, 10000);
				// next activity is opened and captured.
				assertNotNull(nextActivity);
				
				/*
				 * Test for US03.01.01 Basic Flow 1
				 */	
				// view which is expected to be present on the screen			
				final View decorView1 = nextActivity.getWindow().getDecorView();
				// layout of claim list
				listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, listView);
				// check whether the Button object's width and height attributes match the expected values
				final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
				/*assertNotNull(layoutParams);*/
				assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	 

				/*
				 * Test for US03.01.01 Basic Flow 2
				 */	
				final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
					
				// get next activity
				nextActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						update_list = null;
						// click the list open next activity.
						ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
						claimList.getChildAt(0).performClick();
						ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
						assertNotNull(thirdActivity);
						
						/*
						 * Test for US03.01.01 Basic Flow 3
						 */
						final View decorView2 = nextActivity.getWindow().getDecorView();
						// layout of item list
						ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
						// check if it is on screen
						ViewAsserts.assertOnScreen(decorView2, ilv);

						/*
						 * Test for US03.01.01 Basic Flow 4 & 5 & 6
						 */
						// Click the menu option;
						getInstrumentation().invokeMenuActionSync(thirdActivity,ca.ualberta.CMPUT301W15T06.R.id.change_tag, 1);
						
						/*
						 * Test for US03.01.01 Basic Flow 7
						 */
						// open the tag list
						AlertDialog d = (AlertDialog) thirdActivity.getDialog();
						assertNotNull(d);
						
						/*
						 * Test for US 03.01.01 Basic Flow 8
						 */	
						assertEquals(d.getContext(), test_tag);
						View diaReturn = d.getCurrentFocus();
						assertEquals(diaReturn, update_list );
						
						//finish activity
						thirdActivity.finish();
						
						/*
						 * Test for US03.01.01 Basic Flow 9
						 */	
						// view which is expected to be present on the screen			
						final View decorView3 = nextActivity.getWindow().getDecorView();
						// layout of claim list
						listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
						// check if it is on screen
						ViewAsserts.assertOnScreen(decorView3, listView);
						}
					});
				
				nextActivity.finish();
				

			}
		});
		activity.finish();
	}
}

