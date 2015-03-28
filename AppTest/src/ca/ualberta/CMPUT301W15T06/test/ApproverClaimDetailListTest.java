//package ca.ualberta.CMPUT301W15T06.test;
//
//import ca.ualberta.CMPUT301W15T06.Claim;
//import ca.ualberta.CMPUT301W15T06.User;
//import ca.ualberta.CMPUT301W15T06.Item;
//import junit.framework.TestCase;
//
//public class ApproverClaimDetailListTest extends TestCase {
//	// test for use case 08.03.01
//	// System shows all the details of one expense claim
//	public void testSubmittedClaim() {
//
//		// build claim list
//		User cList = new User();
//		
//		// build new claim
//		Claim test = new Claim("A");
//		
//		// set start date
//		String d1 = "2012-03-27";
//		test.setBeginDate(d1);
//		
//		// set approver name
//		test.setApprover("Approver1");
//		
//		// set state
//		test.setStatus("submitted");
//		
//		// set new expense item
//		Item new_item = new Item();
//		new_item.setAmount(10);
//		new_item.setCategory("traffic");
//		new_item.setDate(d1);
//		new_item.setCurrency("CAD");
//		new_item.setDescription("arrived Edmonton");
//		test.addItem(new_item);
//		
//		// add claim
//		cList.addClaim(test);
//				
//		// get claim from list
//		Claim ec = cList.getClaimList().get(0);
//		
//		// get expense item in this claim
//		Item ei = ec.getItemList().get(0);
//		// check values saved in expense item
//		// It's an error now because our function returns null
//		assertTrue("category should be 'traffic'", ei.getCategory().equals("traffic"));
//		assertTrue("date should be 2012-03-27", ei.getDate().equals(d1));
//		assertTrue("description should be 'arrived Edmonton'", ei.getDescription().equals("arrived Edmonton"));
//		assertTrue("amount of spend should be 10", ei.getAmount()==10);
//		assertTrue("unit of currency should be 'CAD'", ei.getCurrency().equals("CAD"));
//	}
//}
