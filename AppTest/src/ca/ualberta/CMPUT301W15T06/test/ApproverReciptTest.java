//package ca.ualberta.CMPUT301W15T06.test;
//
//import android.view.View;
//import ca.ualberta.CMPUT301W15T06.Claim;
//import ca.ualberta.CMPUT301W15T06.User;
//import ca.ualberta.CMPUT301W15T06.Item;
//import ca.ualberta.CMPUT301W15T06.Recipt;
//import junit.framework.TestCase;
//
//public class ApproverReciptTest extends TestCase{
//	// test for use case 08.05.01
//	// system gets photographic receipt for the item
//	public void testRecipt() {
//		// build a claim list
//		User cList = new User();
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
//	}
//}
