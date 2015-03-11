package ca.ualberta.CMPUT301W15T06.test;

import java.lang.reflect.Proxy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.ContextMenu;
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

public class US07_02_01_Test_UNFINISHED extends ActivityInstrumentationTestCase2<MainActivity>{

	public US07_02_01_Test_UNFINISHED() {
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
	 * Test for US07.02.01 Basic Flow 1
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
	 * Test for US07.02.01 Basic Flow 2
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

		final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				assertNotNull(claimList.performLongClick());
			}
		});
		
		/*
		 * Test for US07.01.01 Basic Flow 3
		 */
		
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				assertNotNull(claimList.getChildAt(0).performLongClick());
				
				final GridView contextMenu = (GridView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);
				final View decorView = nextActivity.getWindow().getDecorView();	
			    ViewAsserts.assertOnScreen(decorView, contextMenu);
			    
			    final ViewGroup.LayoutParams layoutParams = claimList.getLayoutParams();
			    
			    assertNotNull(layoutParams);
			    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
			    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
			}
		});
		

		/*
		 * Test for US07.01.01 Basic Flow 4
		 */
		
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				assertNotNull(claimList.getChildAt(0).performLongClick());
				
				final GridView contextMenu = (GridView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);

            	assertNotNull(contextMenu.performClick());
            }
        });

		
		/*
		 * Test for US07.02.01 Basic Flow 5
		 */
		
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ClaimantAddClaimController  cacc = new ClaimantAddClaimController(new ClaimList());
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				assertNotNull(claimList.getChildAt(0).performLongClick());
				
				final GridView contextMenu = (GridView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.array.claim_dialog_array);

            	contextMenu.performClick();
            	
            	//??????????????????????????????????? warning dialogue ??
            	
            }
        });
		
//		View mLLAppelerFixe = nextActivity.
//		
//		nextActivity.runOnUiThread(new Runnable() {
//	        @Override
//	        public void run() {
//	            View mLLAppelerFixe;
//				assertTrue(mLLAppelerFixe.performClick());
//
//	            AlertDialog mDialog = nextActivity.getAdAppelerFixe();
//	            assertTrue(mDialog.isShowing());
//
//	            Button okButton = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
//
//	            assertTrue(okButton.performClick());
//	            assertTrue(nextActivity.isNumeroValide());
//	        }
//	    });
//		
		/*
		 * Test for US07.02.01 Basic Flow 6
		 */
		
		//???????????????????????????????????????????? button in warning dialogue?
		
		/*
		 * Test for US07.02.01 Basic Flow 7
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
