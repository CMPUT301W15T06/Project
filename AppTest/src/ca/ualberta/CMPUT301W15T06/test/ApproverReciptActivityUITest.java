///*
//UA CMPUT 301 Project Group: CMPUT301W15T06
//
//Copyright {2015} {Jingjiao Ni
//
//              Tianqi Xiao
//
//              Jiafeng Wu
//
//              Xinyi Pan 
//
//              Xinyi Wu
//
//              Han Wang}
//Licensed under the Apache License, Version 2.0 (the "License");
//
//you may not use this file except in compliance with the License.
//
//You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
//Unless required by applicable law or agreed to in writing, software distributed under 
//the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
//ANY KIND, either express or implied. See the License for the specific language 
//governing permissions and limitations under the License.
//
// */
//
//package ca.ualberta.CMPUT301W15T06.test;
//
//import java.io.File;
//
//import android.app.Activity;
//import android.app.Instrumentation;
//import android.test.ActivityInstrumentationTestCase2;
//import android.view.View;
//import ca.ualberta.CMPUT301W15T06.ApproverReciptActivity;
//import ca.ualberta.CMPUT301W15T06.Claim;
//import ca.ualberta.CMPUT301W15T06.ClaimList;
//import ca.ualberta.CMPUT301W15T06.Item;
//import ca.ualberta.CMPUT301W15T06.Recipt;
//
//public class ApproverReciptActivityUITest extends ActivityInstrumentationTestCase2<ApproverReciptActivity>{
//
//	Instrumentation instrumentation;
//	Activity activity;
//	
//	public ApproverReciptActivityUITest() {
//		super(ApproverReciptActivity.class);
//		// TODO Auto-generated constructor stub
//	}
//	
//	protected void setUp() throws Exception {
//		super.setUp();
//		// Setting the touch mode to true prevents the UI control from taking focus when you click it programmatically in the test method later 
//		setActivityInitialTouchMode(true);
//
//		instrumentation = getInstrumentation();
//		activity = getActivity();
//	}
//	
//	// test for use case 08.05.01
//	// system gets photographic receipt for the item
//	public void testRecipt() {
//		// build a claim list
//		ClaimList cList = new ClaimList();
//
//		// build new claim
//		Claim test = new Claim("A");
//		
//		// set state as "submitted"
//		test.setStatus("submitted");
//		
//		// add claim
//		cList.addClaim(test);
//		
//		// set new expense item
//		Item new_item = new Item();
//		new_item.setAmount(10);
//		new_item.setCategory("traffic");
//		new_item.setDate("2012-03-27");
//		new_item.setCurrency("CAD");
//		new_item.setDescription("arrived Edmonton");
//		new_item.setRecipt(null);
//
//		// add new item
//		cList.getClaimList().get(0).addItem(new_item);
//		
//		// get receipt
//		Recipt r = cList.getClaimList().get(0).getItemList().get(0).getRecipt();
//		// It's an error now because our function returns null
//		assertTrue("There's no recipt", r.equals(null));
//		
//		// add receipt
//		cList.getClaimList().get(0).getItemList().get(0).setRecipt(new Recipt());
//		r = cList.getClaimList().get(0).getItemList().get(0).getRecipt();
//		assertFalse("There exists one recipt", r.equals(null));
//		
//		// UI test
//		View v = activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverRecieptView);
//		assertTrue("view is not null", v.equals(null));
//	}
//	// There's no button in this activity's layout, so there is no button behavior to be test
//	
//	//US06.04.01
//	public void photoSizeUITest() {
//		File photoFile = null;
//		Item.getPhoto();
//		assertTrue("Compressed photo file too large (" + photoFile.length() + ")", Item.photoSize() < 65536);
//	}
//
//
//}
