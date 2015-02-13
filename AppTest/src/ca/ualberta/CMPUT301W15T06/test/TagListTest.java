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
