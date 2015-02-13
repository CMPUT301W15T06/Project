package ca.ualberta.CMPUT301W15T06.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import ca.ualberta.CMPUT301W15T06.ClaimentTagListActivity;

public class ClaimentTagListActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimentTagListActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	public ClaimentTagListActivityUITest() {
        super(ClaimentTagListActivity.class);
        
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		textInput = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTag));
	}
	

	//fill in blank
	private void AddButton(String tag) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTagButton));
		textInput.setText(tag);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTagButton)).performClick();
	}
	
	//test if add something to list
	public void testClickAddButton(String tag) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTagButton));
		textInput.setText(tag);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTagButton)).performClick();
	    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.TagList));	    
	}


}
