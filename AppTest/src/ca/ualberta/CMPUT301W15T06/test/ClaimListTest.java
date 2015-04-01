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
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.User;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;

public class ClaimListTest extends TestCase {

	// US02.01.01 test the display of claim list
	public void testDisplayList()
	{
		User claimList = new User();
		ClaimantAddClaimController cacc = new ClaimantAddClaimController(claimList);
		cacc.addClaim("test", "2014-12-11", "2015-01-05");
		assertTrue("correctly dispalyed?", claimList.getClaimList()!=null);
	}

	// US02.02.01 test if the list is ordered
	public void testOrderedList()
	{
		User claimlist =new User();
		Claim c1=new Claim("1");
		//c1.setBeginDate("20141005");
		Claim c2=new Claim("2");
		//c1.setBeginDate("20140905");
		Claim c3=new Claim("3");
		//c1.setBeginDate("20131105");
		Claim c4=new Claim("4");
		//c1.setBeginDate("20141004");
		claimlist.sort();
		assertTrue("The first one should be c3", claimlist.getClaimList().get(0)==c3);
		assertTrue("The first one should be c2", claimlist.getClaimList().get(1)==c2);
		assertTrue("The first one should be c4", claimlist.getClaimList().get(2)==c4);
		assertTrue("The first one should be c1", claimlist.getClaimList().get(3)==c1);		
	}
	
}
