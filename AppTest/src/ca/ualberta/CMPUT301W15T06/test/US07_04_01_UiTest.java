package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;

public class US07_04_01_UiTest extends ActivityInstrumentationTestCase2<MainActivity>{

	public US07_04_01_UiTest() {
		super(MainActivity.class);
	}

	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	ListView gv;

	//set up
	protected void setUp() throws Exception {
		
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		setActivityInitialTouchMode(false);

	}
	
	/*
	 * Test for US07.04.01 Basic Flow 1
	 */
	
	public void testClaimList() {
		
		// test if the button can create next activity
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		// open current activity.
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
			  // click button and open next activity.
			  ClaimantButton.performClick();
		  }
		});

		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		
    	// view which is expected to be present on the screen
		final View decorView = nextActivity.getWindow().getDecorView();	  
	    ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
	    
	    // check if claim list is on screen
	    ViewAsserts.assertOnScreen(decorView, claimList);
	    
		// check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = claimList.getLayoutParams();
	    
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    nextActivity.finish();
	    activity.finish();
	}
	
	/*
	 * Test for US07.04.01 Basic Flow 2
	 */
	
	public void testLongClickClaimList() {
		
		// test if the button can create next activity
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		// open current activity.
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
			  // click button and open next activity.
			  ClaimantButton.performClick();
		  }
		});

		// get ClaimantClaimListActivity
		final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// get claimListView
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		// start ClaimantClaimListActivity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// build controller
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				
				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				
				// long click new claim
				assertNotNull(claimList.getChildAt(0).performLongClick());
			}
		});
		// ClaimantClaimListActivity finishes
		nextActivity.finish();
		
		/*
		 * Test for US07.04.01 Basic Flow 3
		 */
		
		// restart ClaimantClaimListActivity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// build controller
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				
				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				
				// long click new claim
				assertNotNull(claimList.getChildAt(0).performLongClick());
				
				// get contextMenu
				final GridView contextMenu = (GridView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);
				
				// get decorView on ClaimantClaimListActivity
				final View decorView = nextActivity.getWindow().getDecorView();	
				
				// check if the context menu is on screen
			    ViewAsserts.assertOnScreen(decorView, contextMenu);
			    
			    // get layout parameters of the list view
			    final ViewGroup.LayoutParams layoutParams = claimList.getLayoutParams();
			    
			    // check if the paremeter exists
			    assertNotNull(layoutParams);
			    
			    // check if the claim list view is shown directly
			    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
			    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
			}
		});
		// ClaimantClaimListActivity finishes
		nextActivity.finish();

		/*
		 * Test for US07.04.01 Basic Flow 4
		 */
		
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// build controller
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				
				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				
				// long click new claim
				assertNotNull(claimList.getChildAt(0).performLongClick());
				
				// get contextMenu
				final GridView contextMenu = (GridView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);

				// check if user can click this context menu
            	assertNotNull(contextMenu.performClick());
            }
        });

		
		/*
		 * Test for US07.04.01 Basic Flow 5
		 */
		
    	// view which is expected to be present on the screen
		final View decorView2 = nextActivity.getWindow().getDecorView();	  
	    ListView updatedclaimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
	    
	    // check if claim list is on screen
	    ViewAsserts.assertOnScreen(decorView2, updatedclaimList);
	    
		// check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams2 = updatedclaimList.getLayoutParams();
	    
	    assertNotNull(layoutParams2);
	    assertEquals(layoutParams2.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams2.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    nextActivity.finish();
	    activity.finish();
		
	}
}
