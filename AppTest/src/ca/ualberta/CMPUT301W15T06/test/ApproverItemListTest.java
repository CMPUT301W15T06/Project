//package ca.ualberta.CMPUT301W15T06.test;
//
//import ca.ualberta.CMPUT301W15T06.Claim;
//import ca.ualberta.CMPUT301W15T06.ClaimList;
//import ca.ualberta.CMPUT301W15T06.Item;
//import junit.framework.TestCase;
//
//public class ApproverItemListTest extends TestCase{
//	// test for use case 08.04.01
//	// system gets expense item list of this expense claim
//	public void testViewExpenseItemList() {
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
//		cList.getClaimList().get(0).addItem(new_item);
//		
//		// get expense item from item list
//		Item ei = cList.getClaimList().get(0).getItemList().get(0);
//		// check if it is the item just saved
//		// It's an error now because our function returns null
//		assertTrue("test expense item", ei.equals(new_item));
//		
//	}
//}
