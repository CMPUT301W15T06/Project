package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.R;

public class ClaimantItemListUITest extends ActivityInstrumentationTestCase2<ClaimantItemListActivity>{

	Activity activity;
	Instrumentation instrumentation;
	GridView gv;
	Button button;
	
	// constructor
	public ClaimantItemListUITest() {
		super(ClaimantItemListActivity.class);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		gv = (GridView) getActivity().findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
		button = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addItemButton);
	}
	
	/*
	 * test for US04.01.01 Basic Flow 7
	 * Test for US04.05.01 Basic Flow 7
	 */
	
	public void testItemList_layout() {
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
	 * test for US04.01.01 Basic Flow 8
	 */
	
	// test layout of button
	public void testFinishButton_layout() {
		// view which is expected to be present on the screen
		final View decorView = activity.getWindow().getDecorView();
		
		// check if it is on screen
	    ViewAsserts.assertOnScreen(decorView, button);

	    // check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertTrue(View.GONE == button.getVisibility());
	    
	} 

	// test button behavior
	public void testFinishButton_expectInfo() {
		// expected string
	    String expectedInfoText = activity.getString(R.string.title_activity_claimant_add_item);
	    
	    // check if button is visible
	    TouchUtils.clickView(this, button);
	    assertTrue(View.VISIBLE == button.getVisibility());
	    
	    // check if text shown on button equals expected text
	    assertEquals(expectedInfoText.contains(button.getText()), true);
	}
	
	
	
	
	/*
	 * Test for US04.04.01 Basic Flow 8
	 * Test for US05.05.01 Basic Flow 8
	 */
	// click item
	public void testClickItemList(){
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView)).performClick();
	}
	
	/*
	 * Test for US04.04.01 Basic Flow 9 and 10
	 * Test for US04.05.01 Basic Flow 9 and 10
	 */
	public void testClickOnContextMenu(){
		activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView).performLongClick();
		
		final ContextMenu contextMenu = (ContextMenu) getActivity().getBaseContext();
		    assertTrue(contextMenu != null);

		    getActivity().runOnUiThread(new Runnable() {
		        public void run() {
		            contextMenu.performIdentifierAction(ca.ualberta.CMPUT301W15T06.R.id.itemListView, 0);
		        }
		    });
		    getInstrumentation().waitForIdleSync();
	}
}
