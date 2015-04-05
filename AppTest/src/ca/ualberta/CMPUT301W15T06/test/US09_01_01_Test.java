//package ca.ualberta.CMPUT301W15T06.test;
//
//import android.app.Activity;
//import android.app.Instrumentation;
//import android.app.Instrumentation.ActivityMonitor;
//import android.test.ActivityInstrumentationTestCase2;
//import android.test.ViewAsserts;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//import ca.ualberta.CMPUT301W15T06.AppSingleton;
//import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
//import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
//import ca.ualberta.CMPUT301W15T06.MainActivity;
//
//public class US09_01_01_Test extends ActivityInstrumentationTestCase2<MainActivity>{
//
//	public US09_01_01_Test() {
//		super(MainActivity.class);
//	}
//	
//	Button ClaimantButton;
//	Instrumentation instrumentation;
//	Activity activity;
//	EditText textInput;
//	ListView gv;
//	
//	protected void setUp() throws Exception {
//		
//		super.setUp();
//		instrumentation = getInstrumentation();
//		activity = getActivity();
//		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
//		setActivityInitialTouchMode(false);
//
//	}
//	
//	public void testPushOnline() {
//
//		// monitor
//		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
//		
//		// open current activity.
//		activity.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//			  // click button and open next activity.
//			  ClaimantButton.performClick();
//		  }
//		});
//		
//		// next activity is opened and captured.
//		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
//
//		// add monitor
//		ActivityMonitor am = getInstrumentation().addMonitor(ClaimantAddClaimActivity.class.getName(), null, false);
//		
//		// monitor should not be null
//		assertNotNull("Monitor should not be null", am);
// 
//		// Click the menu option
//		//getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
//		boolean res = getInstrumentation().invokeMenuActionSync(nextActivity,ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 1);
//		
//		// check if item is abled
//		assertTrue("invokeMenuActionSync should return true", res);
//
//		boolean internet = AppSingleton.getInstance().isSuc();
//		assertNotNull("internet should not be null", internet);
//		
//	}
//}
