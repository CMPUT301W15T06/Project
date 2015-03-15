package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

public class US04_04_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	Button ApproverButton;
	Button ClaimantButton;
	Intent intent;
	ListView clv;
	
	public US04_04_01_Test () {
		super(MainActivity.class);
	}

}
