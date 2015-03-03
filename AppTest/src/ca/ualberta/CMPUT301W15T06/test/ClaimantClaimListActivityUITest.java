package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

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

	
	//test claim list 
	public void testClaim(){
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView)).performClick();
	}
	
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