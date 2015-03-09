package ca.ualberta.CMPUT301W15T06.test;

import java.lang.reflect.Proxy;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;

public class US07_01_01_Test_NOTFINISHED extends ActivityInstrumentationTestCase2<MainActivity>{

	public US07_01_01_Test_NOTFINISHED() {
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
	 * Test for US07.01.01 Basic Flow 1
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
	 * Test for US07.01.01 Basic Flow 2
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

		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				assertNotNull(claimList.performLongClick());
			}
		});
		
		/*
		 * Test for US07.03.01 Basic Flow 3
		 * 
		 * WARNING: missing function
		 */

//		final ContextMenu contextMenu = nextActivity.getContextMenu();
//		assertTrue(contextMenu != null);
//		
//

		/*
		 * Test for US07.03.01 Basic Flow 4
		 */
		
//	    getActivity().runOnUiThread(new Runnable() {
//	        public void run() {
//	            contextMenu.performIdentifierAction(ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 0);
//	        }
//	    });
//	    getInstrumentation().waitForIdleSync();
		

		
		/*
		 * Test for US07.01.01 Basic Flow 5
		 */
		
    	// view which is expected to be present on the screen
		final View decorView = nextActivity.getWindow().getDecorView();	  
	    ListView updatedclaimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
	    
	    // check if claim list is on screen
	    ViewAsserts.assertOnScreen(decorView, updatedclaimList);
	    
		// check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = updatedclaimList.getLayoutParams();
	    
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    nextActivity.finish();
	    activity.finish();
		
	}
	
}
