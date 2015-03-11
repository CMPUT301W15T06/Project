package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;

public class US04_01_01_Test_1_un extends ActivityInstrumentationTestCase2<MainActivity>{

	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	ListView gv;
	
	public US04_01_01_Test_1_un() {
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
			
		intent = new Intent(Intent.ACTION_MAIN);//getInstrumentation().getTargetContext(), MainActivity.class);
		//startActivity(intent, null, null);

	}
		

		
	/*
	 * Test for US04.01.01 Basic Flow 2
	 */
		
	//test Claimant Button layout
	public void testClaimantButtonlayout() {
		final View decorView = activity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams = ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
		    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant", view.getText());
	}

	//test button behavior
    public void activityTest(){
    	Button button = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		TouchUtils.clickView(this, button);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton)).performClick();
	}
		
	// test if the button can create next activity
	public void testOpenNextActivity() {
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		// open current activity.
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
	     * Test Case for US01.01.01 Basic Flow 3
	     */

	    // view which is expected to be present on the screen
		final View decorView = nextActivity.getWindow().getDecorView();
				  
		ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView, claimList);
	    // check whether the Button object's width and height attributes match the expected values
		final ViewGroup.LayoutParams layoutParams = claimList.getLayoutParams();
		/*assertNotNull(layoutParams);*/
		assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);		    

	    /*
	     * Test for US04.01.01 Basic Flow 4
		 * Test for US04.01.01 Basic Flow 5
		 */	
		assertNotNull(claimList.performClick());

		//nextActivity.finish();
		
		/*
	     * Test Case for US04.01.01 Basic Flow 5
	     */
		
		// test item list view
		ListView itemList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(claimList, itemList);
		// check whether the Button object's width and height attributes match the expected values
		final ViewGroup.LayoutParams layoutParams2 = itemList.getLayoutParams();
		/*assertNotNull(layoutParams);*/
		assertEquals(layoutParams2.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams2.height, WindowManager.LayoutParams.WRAP_CONTENT);	
		
		assertNotNull(itemList.performClick());
		
	}

}
