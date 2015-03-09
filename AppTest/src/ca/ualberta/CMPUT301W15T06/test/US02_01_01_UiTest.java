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
import android.widget.ListView;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;

public class US02_01_01_UiTest extends ActivityInstrumentationTestCase2<MainActivity>{

	public US02_01_01_UiTest() {
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
	
	public void testClaimantButtonlayout() {
		
		/*
		 * Test for US02.01.01 Basic Flow 1
		 */
		
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, ClaimantButton);

	    final ViewGroup.LayoutParams layoutParams =
	           ClaimantButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    assertEquals("Incorrect label of the button", "Claimant", ClaimantButton.getText());
		
		/*
		 * Test for US02.01.01 Basic Flow 2
		 */
	    
		// test if the button can create next activity
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
		
		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		
		
		/*
		 * Test for US02.01.01 Basic Flow 3
		 */
		
    	// view which is expected to be present on the screen
		final View decorView2 = nextActivity.getWindow().getDecorView();	  
	    ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
	    
	    // check if claim list is on screen
	    ViewAsserts.assertOnScreen(decorView2, claimList);
	    
		// check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams2 = claimList.getLayoutParams();
	    
	    assertNotNull(layoutParams2);
	    assertEquals(layoutParams2.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams2.height, WindowManager.LayoutParams.WRAP_CONTENT);
		
		nextActivity.finish();
	}
	

}
