/*
UA CMPUT 301 Project Group: CMPUT301W15T06

Copyright {2015} {Jingjiao Ni

              Tianqi Xiao

              Jiafeng Wu

              Xinyi Pan 

              Xinyi Wu

              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.

 */

package ca.ualberta.CMPUT301W15T06.test;


import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.CMPUT301W15T06.ClaimantTagListActivity;

public class ClaimantTagListActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantTagListActivity> {
	
	

	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	public ClaimantTagListActivityUITest() {
		super(ClaimantTagListActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		textInput = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTag));
	}
	

	//fill in blank and click "add" activity under test
	public void AddButton(String tag){
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTagButton));
		textInput.setText(tag);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.AddTagButton)).performClick();
	}



}
