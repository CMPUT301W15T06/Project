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
//package ca.ualberta.CMPUT301W15T06.test;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import junit.framework.TestCase;
//import ca.ualberta.CMPUT301W15T06.Claim;
//import ca.ualberta.CMPUT301W15T06.ClaimList;
//import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
//import ca.ualberta.CMPUT301W15T06.String;
//import ca.ualberta.CMPUT301W15T06.TagList;
//
//
//public class TagInClaimTest extends TestCase {
//	
//	//US03.01.01  check if add tag to a claim
//	public void testAddTagToClaim(){
//		//add tag to tagList
//		TagList tagList = new TagList();
//		String name = "A tag";
//		String testTag = new String(name);
//		tagList.addTags(testTag);
//		Collection<String> tags = tagList.getTags();
//		assertTrue("Tag list added incorrect", name.equals(tags));
//		//add tag to a claim
//		String cname = "Travel";
//		Claim claim = new Claim(cname);
//		claim.addTag(name);
//		ArrayList<String> claimTagList = claim.getTagList();
//		assertTrue("should add a tag to a claim",name.equals(claimTagList));
//	}
//	
//	
//	//US03.03.01
//	public void testSearchByTag(){
//		TagList tagList = new TagList();
//		String name = "A tag";
//		String testTag = new String(name);
//		tagList.addTags(testTag);
//		String cname = "Travel";
//		Claim claim = new Claim(cname);
//		claim.addTag(name);
//		ClaimList claimList = new ClaimList();
//		Claim searchResult = claimList.searchByTag(name);
//		assertTrue("Search by tags does not work", cname.equals(searchResult));		
//	}
//
//	//US09.01.01 check if push online successfully
//	public void testPushOnline(){
//		String name = "Travel";
//		Claim claim = new Claim(name);
//		ClaimList claimList = new ClaimList();
//		ClaimantAddClaimController ClaimList = new ClaimantAddClaimController(claimList);
//		/*Manager initial*/
//		/*laimList.addClaim(name,null,null);*/
//		claimList.pushOnline();
//		assertTrue("Push Online unsuccessfully",claim.equals(claimList.pullOnline()));
//	}
//
//}
