package ca.ualberta.CMPUT301W15T06.test;

import java.lang.reflect.Array;

import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.R;
import ca.ualberta.CMPUT301W15T06.ShowLocationActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
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
public class US01_07_01_Test<Final> extends
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


	public US01_07_01_Test() {
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

	public void testUS010701() {
		
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
		
	
		ListView ilv = (ListView) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, ilv);
				

		// Click the menu option;
		ActivityMonitor aamm = getInstrumentation().addMonitor(ClaimantClaimDetailActivity.class.getName(), null, false);
		// Click the menu option
		getInstrumentation().invokeMenuActionSync(a,ca.ualberta.CMPUT301W15T06.R.id.detail, 1);
		Activity b = getInstrumentation().waitForMonitorWithTimeout(aamm,10000);
		assertNotNull(b);
		
		/*
		 * Test Case for US01.07.01 Basic Flow 1
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
		 * Test for US 01.07.01 Basic Flow 2
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
		 * test US01.07.01 Basic Flow 3,4
		 */
		// test interface
		EditText claimant_des = ((EditText) c.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
		EditText claimant_reason = ((EditText) c.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
	
		final View dv1 = c.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(dv1, claimant_des);
		assertNotNull(claimant_des);
	
		ViewAsserts.assertOnScreen(dv1, claimant_reason);
		assertNotNull(claimant_reason);

		/*
		 * Test Case for US01.07.01 Basic Flow 5
		 */
		final ListView detailListView = (ListView) c.findViewById(ca.ualberta.CMPUT301W15T06.R.id.detailListView);
		c.runOnUiThread(new Runnable() {
			//test add button works
			@Override
			public void run() {
						
			/*
			 * test US01.07.01 Basic Flow 6
			 */		
			final ListView geoArray = (ListView) detailListView.findViewById(ca.ualberta.CMPUT301W15T06.R.array.dest_dialog_array);
			geoArray.getChildAt(0).performClick();

						
			/*
			 * test US01.07.01 Basic Flow 7
			 */
			ActivityMonitor amamam = getInstrumentation().addMonitor(ShowLocationActivity.class.getName(), null, false);
			
			/*
			 * test US01.07.01 Basic Flow 8
			 */
			ShowLocationActivity aaa = (ShowLocationActivity) getInstrumentation().waitForMonitorWithTimeout(amamam, 10000);
			assertNotNull(aaa);
						
			/*
			 * test US01.07.01 Basic Flow 9
			 */
			ImageView image=(ImageView)aaa.findViewById(R.drawable.worldmap);
			assertNotNull(image);
						
			TextView tv=(TextView)aaa.findViewById(R.id.llTextView);
			assertNotNull(tv);
						
						
			/*
			 * test US01.07.01 Basic Flow 10
			 */
			boolean clickMap = image.performClick();
			assertTrue(clickMap);
						
			/*
			 * test US01.07.01 Basic Flow 11
			 */
			Location location=AppSingleton.getInstance().getLocation();tv.setText("Lat: " + location.getLatitude() + "\nLong: " + location.getLongitude());
			
			}
		});
	}
}
