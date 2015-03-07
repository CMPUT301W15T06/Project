package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

public class ClaimantClaimListActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantClaimListActivity> {

	Activity activity;
	Instrumentation instrumentation;
	GridView gv;

	public ClaimantClaimListActivityUITest() {
		super(ClaimantClaimListActivity.class);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		gv = (GridView) getActivity().findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
	}

	/*
	 * Test for US01.01.01 Basic Flow 3
	 * Test for US01.02.01 Basic Flow 3
	 * Test for US01.03.01 Basic Flow 3
	 * test for US01.06.01 Basic Flow 4
	 * Test for US04.01.01 Basic Flow 3
	 * Test for US04.05.01 Basic Flow 3
	 */
/*	public void testCliamList_layout() {
		// view which is expected to be present on the screen
		final View decorView = activity.getWindow().getDecorView();
		
		// check if it is on screen
	    ViewAsserts.assertOnScreen(decorView, gv);

	    // check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = gv.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertTrue(View.GONE == gv.getVisibility());
	    
	} */
	
	/*
	 * Test for US01.01.01 Basic Flow 4 and 5
	 */
	
	//test options menu
	public void testRoute1() {
		ActivityMonitor am = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		/*
		 * Test for US01.01.01 Basic Flow 6
		 */
		// Click the menu option
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
		getInstrumentation().invokeMenuActionSync(activity,ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 0);

		Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 1000);
		assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));
		a.finish();
	}


	/*
	 * Test for US01.02.01 Basic Flow 4
	 * Test for US01.03.01 Basic Flow 4
	 * Test for US04.01.01 Basic Flow 4
	 * Test for US04.05.01 Basic Flow 4
	 */
	public void testLongClickClaimList(){
		ListView claimList = (ListView) (activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		assertTrue(claimList.performLongClick());
	}
	
	
	/*

	 */
	
	public void testClickClaimList(){
		ListView claimList = (ListView) (activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		assertTrue(claimList.performClick());
	}

	
	/*
	 * Test for US01.02.01 Basic Flow 5
	 * Test for US01.03.01 Basic Flow 5
	 * Test for US04.01.01 Basic Flow 5
	 * Test for US04.05.01 Basic Flow 5
	 */
	
	//US01.02.01 & US01.03.01 test ClaimList(ListView) ContextMenu
	public void testContextMenu(){
		activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView).performLongClick();
		
		final ContextMenu contextMenu = (ContextMenu) getActivity().getBaseContext();
		    assertTrue(contextMenu != null);

		    getActivity().runOnUiThread(new Runnable() {
		        public void run() {
		            contextMenu.performIdentifierAction(ca.ualberta.CMPUT301W15T06.R.id.claimListView, 0);
		        }
		    });
		    getInstrumentation().waitForIdleSync();	
	}
	
	/*
	 * Test for US01.02.01 Basic Flow 6
	 * Test for US01.03.01 Basic Flow 6
	 * Test for US04.01.01 Basic Flow 6
	 * Test for US04.05.01 Basic Flow 6
	 */
	
	public void testClickOnContextMenu(){
		activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView).performLongClick();
		
		final ContextMenu contextMenu = (ContextMenu) getActivity().getBaseContext();
		    assertTrue(contextMenu != null);

		    getActivity().runOnUiThread(new Runnable() {
		        public void run() {
		            contextMenu.performIdentifierAction(ca.ualberta.CMPUT301W15T06.R.id.claimListView, 0);
		        }
		    });
		    getInstrumentation().waitForIdleSync();
	}
		
	//US 09.01.01
	public void testPushOnline() {
		// click button runnable -- click push online
		final Button button1 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		activity.runOnUiThread(new Runnable() {
		@Override
		public void run() {
		button1.performClick();
		}
		});
	}


}