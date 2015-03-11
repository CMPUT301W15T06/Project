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

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.*;


public class ExpenseClaimStatus extends TestCase {
	//US 07.01.01
	public void testSubmitApproval() throws StatusException{
		Claim claim = new Claim("123");
		ClaimantAddItemController i1 = new ClaimantAddItemController(claim);
		i1.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		assertTrue("can be submmit",claim.getStatus().toString().equals("in progress"));
		assertTrue("editable?", claim.getEdiable()==false);
	}
	//US 07.02.01
	public void testWarning() throws StatusException{
		Claim claim = new Claim("123");
		ClaimantAddItemController i1 = new ClaimantAddItemController(claim);
		i1.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		Item item = new Item();
		Receipt re = new Receipt();
		assertTrue("exist?", item.getAmount()==100);
		assertTrue("exist?", item.getCategory()=="aa");
		if (item.getAmount()!=0){
			if (item.getCategory()!=""){
				if (item.getCurrency()!=""){
					if (item.getDate()!="")
						if(item.getDescription()!=""){
								if (item.getRecipt()!=null){
									//no warning
								}
							}
						}
				}
			
		}else{
			//warning
		}
	}
	
	//US 07.03.01
	public void testReturned() throws StatusException{
		Claim claim = new Claim("123");
		ClaimantAddItemController i1 = new ClaimantAddItemController(claim);
		i1.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		assertTrue("can be submmit",claim.getStatus().toString().equals("Returned"));
		assertTrue("editable?", claim.getEdiable()==true);
	}
	//US 07.04.01
	public void testApproved() throws StatusException{
		Claim claim = new Claim("123");
		ClaimantAddItemController i1 = new ClaimantAddItemController(claim);
		i1.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		assertTrue("can be submmit",claim.getStatus().toString().equals("Approved"));
		assertTrue("editable?", claim.getEdiable()==false);
	}
	
	//US 07.05.01
	public void testApproverComment() throws StatusException {
		Claim claim = new Claim("Test");
		ClaimantAddItemController i1 = new ClaimantAddItemController(claim);
		i1.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		claim.setApprover("A");
		claim.setComment("abc");
		assertTrue("match?",claim.getApprover().equals("A"));
		assertTrue("match?",claim.getComment().equals("abc"));
	}
	
	
	
	
}
