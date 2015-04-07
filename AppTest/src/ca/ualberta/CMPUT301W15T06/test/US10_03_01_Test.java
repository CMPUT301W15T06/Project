package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditClaimActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.ShowLocationActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US10_03_01_Test extends
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


	public US10_03_01_Test() {
		super(MainActivity.class);
	}

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


	public void testUS100301() {

		//User click "Change User"
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				
				// click button to start another activity
				assertTrue(UserButton.performClick());	
			}	
		});

		//click "Claimant" button and create next activity
		final Button button = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				// click button and open next activity.
				button.performClick();
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
				ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// next activity is opened and captured.
				assertNotNull(nextActivity);
				

				// view which is expected to be present on the screen
				// test claim list layout
				final View decorView1 = nextActivity.getWindow().getDecorView();

				listView = (ListView) nextActivity
						.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, listView);
				// check whether the Button object's width and height attributes
				// match the expected values
				final ViewGroup.LayoutParams layoutParams11 = listView
						.getLayoutParams();
				/* assertNotNull(layoutParams); */
				assertEquals(layoutParams11.width,
						WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams11.height,
						WindowManager.LayoutParams.WRAP_CONTENT);
					
					
				/*
				* Test for US10.03.01 Basic Flow 1,2
				*/
				// after claimant request to add new claim, a new claim should be added into list
				int count1 = u.getClaimList().size();
				assertEquals(count1, 0);
				// Click the menu option
				// open third activity by options menu
				ActivityMonitor am = getInstrumentation().addMonitor(
						ClaimantEditClaimActivity.class.getName(), null, false);
				
				// Click the menu option
				// getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
				getInstrumentation().invokeMenuActionSync(nextActivity,
						ca.ualberta.CMPUT301W15T06.R.id.show_home_location, 1);
				ShowLocationActivity aaa = (ShowLocationActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(aaa);

				ImageView image=(ImageView)aaa.findViewById(ca.ualberta.CMPUT301W15T06.R.drawable.worldmap);
				assertNotNull(image);
				// get space for recording location
				TextView tv=(TextView)aaa.findViewById(ca.ualberta.CMPUT301W15T06.R.id.llTextView);
				assertNotNull(tv);
				
				getInstrumentation().invokeMenuActionSync(nextActivity,
						ca.ualberta.CMPUT301W15T06.R.id.set_home_location, 1);
				ShowLocationActivity qqq = (ShowLocationActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(qqq);

				ImageView ii=(ImageView)aaa.findViewById(ca.ualberta.CMPUT301W15T06.R.drawable.worldmap);
				assertNotNull(ii);
				// get space for recording location
				TextView tt=(TextView)aaa.findViewById(ca.ualberta.CMPUT301W15T06.R.id.llTextView);
				assertNotNull(tt);

				qqq.finish();
				aaa.finish();
				nextActivity.finish();
			}
		});
		activity.finish();
		
	}
}
