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
//import junit.framework.TestCase;
//import ca.ualberta.CMPUT301W15T06.Claim;
//import ca.ualberta.CMPUT301W15T06.ClaimList;
//import ca.ualberta.CMPUT301W15T06.Destination;
//
//public class ClaimTest extends TestCase {
//
//	// US01.01.01 test if the system records the claimant information correctly
//	public void testRecordInfo()
//	{
//		// add name
//		String name = "Name";
//		Claim claim = new Claim(name);
//		
//		claim.setName(name);
//		assertNotSame("The Name is not equal", name, claim.getName());
//		// add starting date
//		String beginDate = "2015-02-18";
//		claim.setBeginDate(beginDate);
//		assertEquals("The begin date is", beginDate, claim.getBeginDate());
//		// add ending date
//		String endDate = "2015-03-08";
//		claim.setEndDate(endDate);
//		assertEquals("The begin date is", endDate, claim.getBeginDate());
//	}
//	
//	// US01.02.01 test if the destination and reason added correctly
//	public void testDestinationReason()
//	{
//		Destination destination = new Destination("Edmonton");
//		assertEquals("correct destination?", "Edmonton", destination.getName());
//		destination.setReason("Travel");
//		assertEquals("add reason?", "Travel", destination.getReason());
//		Claim claim = new Claim("claim");
//		claim.addDestination(destination);
//		assertTrue("correctly added?", claim.getDestinationList()!=null);
//	}
//	
//	// US01.03.01 test if the detail of a claim can be displayed correctly
//	public void testDetailClaim()
//	{
//		Claim claim = new Claim("new claim");
//		Destination destination = new Destination("Edmonton");
//		destination.setReason("Travel");
//		claim.addDestination(destination);
//		String beginDate = "2015-02-18";
//		claim.setBeginDate(beginDate);
//		String endDate = "2015-03-08";
//		claim.setEndDate(endDate);
//		assertTrue("correct display claim detail?", claim.getClaimDetail()!=null);
//	}
//	
//	// US01.04.01 test if a new claim can be successfully added
//	public void testEditClaim()
//	{
//		Claim claim = new Claim("new claim");
//		ClaimList claimList = new ClaimList();
//		claimList.addClaim(claim);
//		assertTrue("correctly added?", claimList.getClaimList()!=null);
//	}
//	
//	// US01.05.01 test if a claim can be successfully removed
//	public void testDeleteClaim()
//	{
//		Claim claim = new Claim("new claim");
//		ClaimList claimList = new ClaimList();
//		claimList.addClaim(claim);
//		/*claimList.remove(claim);*/
//		assertTrue("correctly removed?", claimList.getClaimList()==null);		
//	}
//	
//	// US01.06.01 test if the claim can be saved and push online
//	public void testSavePushOnline(){
//		String name = "Travel";
//		Claim claim = new Claim(name);
//		ClaimList claimList = new ClaimList();
//		claimList.addClaim(claim);
//		claimList.pushOnline();
//		assertTrue("Push Online unsuccessfully",claim.equals(claimList.pullOnline()));
//	}
//
//}
