package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimList;
import android.widget.GridView;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantAddDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.R;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class US01_02_01_Test_2 extends
	ActivityInstrumentationTestCase2<MainActivity> {
	public US01_02_01_Test_2() {
		super(MainActivity.class);
	}


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

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		AddDestination = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);	
		nameView= (TextView) activity.findViewById(R.id.nameValueClaimantClaimDetailTextView);
		beginView=(TextView) activity.findViewById(R.id.startDateValueClaimantClaimDetailTextView);
		endView=(TextView) activity.findViewById(R.id.endingDateValueClaimantClaimDetailTextView);
		intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
	}
	
	/*
	 * Test for US01.02.01 Basic Flow 6
	 */
	// promote info, check button
	public void testContextMenu() {
		//test if the button can create next activity
		//register next activity that need to be monitored
		//open second activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(),null, false);
		//open current activity
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				ClaimantButton.performClick();
			}
		});
		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		assertNotNull(nextActivity);
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// build controller
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				// get ClaimantClaimListActivity
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(),null, false);
				final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// get claimListView
				final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);

				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				// long click new claim
				assertNotNull(claimList.getChildAt(0).performLongClick());
				// get contextMenu
				final GridView contextMenu = (GridView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);

				// check if user can click this context menu
				assertNotNull(contextMenu.performClick());
           
				/* 
				 * Test for US01.02.01 Basic Flow 7
				 */
            	//open new activity
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantClaimDetailActivity.class.getName(),null, false);

				nextActivity.runOnUiThread(new Runnable(){

					@Override
					public void run() {
						contextMenu.performItemClick(contextMenu, 0, claimList.getAdapter().getItemId(0));
					}
				});
       
				ClaimantClaimDetailActivity a = (ClaimantClaimDetailActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(a);
       
				// test 'Add a Destination' button layout
				final View decorView = a.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(decorView, AddDestination);
				final ViewGroup.LayoutParams layoutParams =
						AddDestination.getLayoutParams();
				assertNotNull(layoutParams);
				assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
           
				Button view = (Button) a.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
				assertEquals("Incorrect label of the button", "Add a destination", view.getText());
           
				/*
				 * Test for US01.02.01 Basic Flow 8
				 */
				//US01.02.01 test button activity
				// register next activity that need to be monitored.
				ActivityMonitor activityMonitor1 = getInstrumentation().addMonitor(ClaimantClaimDetailActivity.class.getName(), null, false);
        
				// open current activity.
				ClaimantClaimDetailActivity nextActivity1 = (ClaimantClaimDetailActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 1000);
				final Button button = (Button) nextActivity1.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
				nextActivity1.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// click button and open next activity.
						button.performClick();
					}
				});
       
				// next activity is opened and captured.
				ClaimantAddDestinationActivity nextActivity2 = (ClaimantAddDestinationActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 1000);
           
				assertNotNull(nextActivity2);
       
       
				/*
				 * test US01.02.01 Basic Flow 9
				 */
           
				//This is layout test for DestinationEditText
				EditText claimant_des = ((EditText) nextActivity2.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
				EditText claimant_reason = ((EditText) nextActivity2.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
     
				final View decorView1 = nextActivity2.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(decorView1, claimant_des);
				assertTrue(View.GONE == claimant_des.getVisibility());
	      
				ViewAsserts.assertOnScreen(decorView1, claimant_reason);
				assertTrue(View.GONE == claimant_reason.getVisibility());	
       	
				/*
				 * test US01.02.01 Basic Flow 10 and 11
				 */
				//US01.02.01 fill blank
				String claimantDes = "a";
				String claimantReason = "b";
				claimant_des.setText(claimantDes);
				claimant_reason.setText(claimantReason);
     
     
     
				/*
				 * test US01.02.01 Basic Flow 12
				 */
				//US01.02.01 test finish button layout
       	
				Button finish = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.finishAddDestinationButton);
       	
				ViewAsserts.assertOnScreen(decorView1, finish);
       	
				final ViewGroup.LayoutParams layoutParams1 =
						finish.getLayoutParams();
				assertNotNull(layoutParams1);
				assertEquals(layoutParams1.width, WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams1.height, WindowManager.LayoutParams.WRAP_CONTENT);
         
				Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
				assertEquals("Incorrect label of the button", "Finish", view1.getText());
     
     
     
				//US01.02.01 test finish button activity
				// open current activity.
				final Button button1 = (Button) nextActivity1.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
     
				nextActivity1.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// click button and open next activity.
						button1.performClick();
					}
				});
     
				// next activity is opened and captured.
				assertNotNull(nextActivity2);
				nextActivity2 .finish();
			}
		});
	}
}

