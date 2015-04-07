package ca.ualberta.CMPUT301W15T06.test;

import java.lang.reflect.Array;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantTagListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.DialogInterface;
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

public class US03_03_01_Test extends
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

	public US03_03_01_Test() {
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


	public void test030301() {

		//User click "Change User"
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
		//User click "Change User"
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				assertTrue(UserButton.performClick());	

				// opening a dialog
		    	// access the alert dialog using the getDialog() method created in the activity
				AlertDialog d = (AlertDialog) activity.getDialog();				
			}	
		});
		
		// get current activity			
		MainActivity myActivity = getActivity();
		// click "Claimant" button and start next activity
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ActivityMonitor activityMonitor00 = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
				// click button and open next activity.
				ClaimantButton.performClick();
				ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor00, 10000);
				// next activity is opened and captured.
				assertNotNull(nextActivity);
				
				/*
				 * Test for US03.03.01 Basic Flow 1
				 */
				// view which is expected to be present on the screen			
				final View decorView1 = nextActivity.getWindow().getDecorView();
				// layout of claim list
				listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, listView);
				// check whether the Button object's width and height attributes match the expected values
				final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
				assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	
				
				/*
				 * Test for US03.03.01 Basic Flow 2,3
				 */
				// Click the menu option
				int count_be = u.getClaimList().size();
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
				
				/*
				 * Test for US03.03.01 Basic Flow 4
				 */
				getInstrumentation().invokeMenuActionSync(nextActivity,ca.ualberta.CMPUT301W15T06.R.id.filter, 1);
				
				/*
				 * Test for US03.03.01 Basic Flow 5
				 */		
				// test alert dialog (contains saved tags)
				AlertDialog d = (AlertDialog) nextActivity.getDialog();
				assertNotNull(d);
				
				/*
				 * Test for US03.03.01 Basic Flow 6a
				 */
				//"Filter Model" button
				Button positiveButton = d.getButton(DialogInterface.BUTTON_POSITIVE);
				assertNotNull(positiveButton);
				positiveButton.performClick();

				/*
				 * Test for US03.03.01 Basic Flow 6b
				 */
				//"show all model" button
				Button negativeButton = d.getButton(DialogInterface.BUTTON_NEGATIVE);
				assertNotNull(negativeButton);
				negativeButton.performClick();
				// all claims are shown
				int count_all = u.getClaimList().size();
				assertEquals(count_be, count_all);

				//finish activity
				nextActivity.finish();				
			}
		});
		activity.finish();
	}
	
}
