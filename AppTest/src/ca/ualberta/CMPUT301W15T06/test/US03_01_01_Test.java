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
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;

public class US03_01_01_Test extends ActivityInstrumentationTestCase2<MainActivity>{

	public US03_01_01_Test() {
		super(MainActivity.class);
	}
	
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	ListView gv;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		setActivityInitialTouchMode(false);

	}
	
	public void testAddTag() {
		// monitor
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		
		// open current activity.
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
			  // click button and open next activity.
			  ClaimantButton.performClick();
		  }
		});
		
		// next activity is opened and captured.
		final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);

		/*
		 * Test for US03.01.01 Basic Flow 1
		 */
		
    	// view which is expected to be present on the screen
		final View decorView = nextActivity.getWindow().getDecorView();	  
	    final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
	    
	    // check if claim list is on screen
	    ViewAsserts.assertOnScreen(decorView, claimList);
	    
		// check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = claimList.getLayoutParams();
	    
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);

		
		/*
		 * Test for US03.01.01 Basic Flow 2
		 */
		
		// restart ClaimantClaimListActivity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// build controller
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				
				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				Claim claim =  new Claim("test");
				assertTrue("can be submmit",claim.getStatus().toString().equals("in progress"));
				assertTrue("editable?", claim.getEdiable()==false);
				
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
	}

}
