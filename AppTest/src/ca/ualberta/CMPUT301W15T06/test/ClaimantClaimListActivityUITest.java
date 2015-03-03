package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.ContextMenu;
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
	 * Test for US04.01.01 Basic Flow 3
	 * Test for US04.05.01 Basic Flow 3
	 */
	public void testCliamList_layout() {
		// view which is expected to be present on the screen
		final View decorView = activity.getWindow().getDecorView();
		
		// check if it is on screen
	    ViewAsserts.assertOnScreen(decorView, gv);

	    // check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = gv.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertTrue(View.GONE == gv.getVisibility());
	    
	} 

	
	/*
	 * Test for US04.01.01 Basic Flow 4
	 * Test for US04.05.01 Basic Flow 4
	 */
	
	public void testClaim(){
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView)).performClick();
	}

	
	/*
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