package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.Recipt;

public class ExpenseClaimStatus extends TestCase {
	//US 07.01.01
	public void testSubmitApproval(){
		Claim claim = new Claim("123");
		assertTrue("can be submmit",claim.getStatus().toString().equals("in progress"));
		assertTrue("editable?", claim.getEdiable()==false);
	}
	//US 07.02.01
	public void testWarning(){
		Item item = new Item("111");
		Recipt re = new Recipt();
		item.setAmount(100);
		assertTrue("exist?", item.getAmount()==100);
		item.setCategory("aa");
		assertTrue("exist?", item.getCategory()=="aa");
		item.setCurrency("CNY");
		item.setDate("2010-10-10");
		item.setDescription("abcd");
		item.setName("name");
		item.setRecipt(re);
		if (item.getAmount()!=0){
			if (item.getCategory()!=""){
				if (item.getCurrency()!=""){
					if (item.getDate()!="")
						if(item.getDescription()!=""){
							if (item.getName()!=""){
								if (item.getRecipt()!=null){
									//no warning
								}
							}
						}
				}
			}
			
		}else{
			//warning
		}
	}
	//US 07.03.01
	public void testReturned(){
		Claim claim = new Claim("123");
		assertTrue("can be submmit",claim.getStatus().toString().equals("Returned"));
		assertTrue("editable?", claim.getEdiable()==true);
	}
	//US 07.04.01
	public void testApproved(){
		Claim claim = new Claim("123");
		assertTrue("can be submmit",claim.getStatus().toString().equals("Approved"));
		assertTrue("editable?", claim.getEdiable()==false);
	}
	
	//US 07.05.01
	public void testApproverComment() {
		Claim claim = new Claim("Test");
		claim.setApprover("A");
		claim.setComment("abc");
		assertTrue("match?",claim.getApprover().equals("A"));
		assertTrue("match?",claim.getComment().equals("abc"));
	}
	
	
	
	
}
