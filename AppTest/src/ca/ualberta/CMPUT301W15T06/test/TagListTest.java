package ca.ualberta.CMPUT301W15T06.test;

import java.util.Collection;

import ca.ualberta.CMPUT301W15T06.Tag;
import ca.ualberta.CMPUT301W15T06.TagList;
import junit.framework.TestCase;

public class TagListTest extends TestCase {

	//US03.02.01  add tag to tagList
	public void testAddTagList(){
		TagList tagList = new TagList();
		String name = "A tag";
		Tag testTag = new Tag(name);
		tagList.addTags(testTag);
		Collection<Tag> tags = tagList.getTags();
		assertTrue("should add a tag into tag list", name.equals(tags));
	}

	
	//US03.02.01  remove tag from tagList
	public void testRmoveTagList(){
		TagList tagList = new TagList();
		String name = "New tag";
		Tag testTag = new Tag(name);
		tagList.addTags(testTag);
		Collection<Tag> tags = tagList.getTags();
		assertTrue("should add a tag into tag list", name.equals(tags));
		tagList.removeTags(testTag);
		assertFalse("Tag list still contains that tag", tags.contains(testTag));	
	}
	
	//US03.02.01 rename tagName in tagList
	public void testRenameTag(){
		TagList tagList = new TagList();
		String name = "AA tag";
		Tag testTag = new Tag(name);
		tagList.addTags(testTag);
		Collection<Tag> tags = tagList.getTags();
		assertTrue("should add a tag into tag list", name.equals(tags));
		tagList.renameTags(testTag);
		assertFalse("should rename the tag", name.equals(tags));
		
	}

}
