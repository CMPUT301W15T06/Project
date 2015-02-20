package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityUITest extends
		ActivityInstrumentationTestCase2<MainActivity> {


	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	
	public MainActivityUITest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	//test button layout
	 public void testLayout() {
		 assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		 assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
	}

	
	 //test button activity
	public void activityTest(){
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		TouchUtils.clickView(this, view1);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton)).performClick();
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		TouchUtils.clickView(this, view2);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton)).performClick();
	}
	
}
