package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Tag;

public class TagTest extends TestCase {
	public void testTag(){
		String name = "a tag";
		Tag tagName = new Tag(name);
		assertTrue("tag name is not equal", name.equals(tagName.getname()));		
	}
	


}
