package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.Claim;
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
		
		//User click "Change User"
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {

				/*
				* Test for US 01.02.01 Basic Flow 2
				*/
				// click button to start another activity
				assertTrue(UserButton.performClick());	
				
				/*
				 * Test for US 01.02.01 Basic Flow 3
				 */
				//test opening a dialog
		    	// access the alert dialog using the getDialog() method created in the activity
				AlertDialog d = (AlertDialog) activity.getDialog();
				
				}	
		});

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
		 * Test Case for US01.02.01 Basic Flow 7	
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
		

		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		//get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
				claimList.getChildAt(0).performClick();
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(thirdActivity);
	
				/*
				 * Test for US 01.07.01 Basic Flow 1
				 */
				ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, ilv);

				// Click the menu option;
				getInstrumentation().invokeMenuActionSync(thirdActivity,ca.ualberta.CMPUT301W15T06.R.id.detail, 1);
				//open the forth activity
				Activity forthActivity =   getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(forthActivity);
				
				/*
				 * Test for US 01.07.01 Basic Flow 2
				 */			
				//test 'Add a Destination' button layout
				Button addDestination = (Button) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
				final View dv = forthActivity.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(dv, addDestination);
				final ViewGroup.LayoutParams lp =addDestination.getLayoutParams();
				assertNotNull(lp);
				assertEquals(lp.width, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals(lp.height, WindowManager.LayoutParams.WRAP_CONTENT);
			
				assertEquals("Incorrect label of the button", "Add a destination", addDestination.getText());
				
				Claim claim = new Claim();
				int count1 = claim.getDestinationList().size();
				
				//test click "Add a Destination" button						
				// open next activity.
				final Button desButton = (Button) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
				forthActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ActivityMonitor activityMonitor1 = getInstrumentation().addMonitor(ClaimantEditDestinationActivity.class.getName(), null, false);
						// click button and open next activity.
						desButton.performClick();
						// next activity is opened and captured.
						ClaimantEditDestinationActivity fifthActivity = (ClaimantEditDestinationActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 1000);
						assertNotNull(fifthActivity);
						
						
						/*
						 * Test for US 01.07.01 Basic Flow 3
						 */
						//test interface
						EditText claimant_des = ((EditText) fifthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
						EditText claimant_reason = ((EditText) fifthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
	
						final View dv1 = fifthActivity.getWindow().getDecorView();
						ViewAsserts.assertOnScreen(dv1, claimant_des);
						assertTrue(View.GONE == claimant_des.getVisibility());
	
						ViewAsserts.assertOnScreen(dv1, claimant_reason);
						assertTrue(View.GONE == claimant_reason.getVisibility());	
						
						/*
						 * Test for US 01.07.01 Basic Flow 4
						 */
						//fill blank
						String claimantDes = "a";
						String claimantReason = "b";
						claimant_des.setText(claimantDes);
						claimant_reason.setText(claimantReason);

						//finish the activity and go back
						fifthActivity.finish();
					}
				});
				
				/*
				 * Test for US 01.07.01 Basic Flow 5
				 */
				int count2 = claim.getDestinationList().size();
				assertEquals(count1,count2-1);

				final ListView detailListView = (ListView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.detailListView);
				forthActivity.runOnUiThread(new Runnable() {
					//test add button works
					@Override
					public void run() {
						
						/*
						 * test US01.07.01 Basic Flow 6
						 */
						ListView geoArray = (ListView) detailListView.findViewById(ca.ualberta.CMPUT301W15T06.R.array.dest_dialog_array);
						assertNotNull(geoArray);
						geoArray.getChildAt(0).performClick();
						
						/*
						 * test US01.07.01 Basic Flow 7
						 */
						ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShowLocationActivity.class.getName(), null, false);
						
						/*
						 * test US01.07.01 Basic Flow 8
						 */
						ShowLocationActivity aaa = (ShowLocationActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
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
						Location location=AppSingleton.getInstance().getLocation();
						tv.setText("Lat: " + location.getLatitude()
								+ "\nLong: " + location.getLongitude());
					}
				});
				
			}
		});

	}
}
