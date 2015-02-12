package ca.ualberta.CMPUT301W15T06.test;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.Tag;
import ca.ualberta.CMPUT301W15T06.TagList;


public class TagInClaimTest extends TestCase {
	
	//US03.01.01  check if add tag to a claim
	public void testAddTagToClaim(){
		//add tag to tagList
		TagList tagList = new TagList();
		String name = "A tag";
		Tag testTag = new Tag(name);
		tagList.addTags(testTag);
		Collection<Tag> tags = tagList.getTags();
		assertTrue("Tag list added incorrect", name.equals(tags));
		//add tag to a claim
		String cname = "Travel";
		Claim claim = new Claim(cname);
		claim.addTag(name);
		ArrayList<Tag> claimTagList = claim.getTagList();
		assertTrue("should add a tag to a claim",name.equals(claimTagList));
	}
	
	
	//US03.03.01
	public void testSearchByTag(){
		TagList tagList = new TagList();
		String name = "A tag";
		Tag testTag = new Tag(name);
		tagList.addTags(testTag);
		String cname = "Travel";
		Claim claim = new Claim(cname);
		claim.addTag(name);
		ClaimList claimList = new ClaimList();
		Claim searchResult = claimList.searchByTag(name);
		assertTrue("Search by tags does not work", cname.equals(searchResult));		
	}

	//US09.01.01 check if push online successfully
	public void testPushOnline(){
		String name = "Travel";
		Claim claim = new Claim(name);
		ClaimList claimList = new ClaimList();
		claimList.addClaim(claim);
		claimList.pushOnline();
		assertTrue("Push Online unsuccessfully",claim.equals(claimList.pullOnline()));
	}

}
