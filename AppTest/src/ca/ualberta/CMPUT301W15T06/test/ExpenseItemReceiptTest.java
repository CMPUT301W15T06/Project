package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.Claim;


public class ExpenseItemReceiptTest extends TestCase {
	//US06.01.01
	public void reciptPhoto(){
		Item item = new Item("AA");
		assertTrue("photo taken",item.takePhoto()!=false);
	}
	//US06.02.01
	public void viewReceipt(){
		Item item = new Item("AA");
		assertTrue("photo exist",item.getPhoto()!=false);
	}
	//US06.03.01
	public void deleteReceipt(){
		Claim claim = new Claim("AB");
		assertTrue("editable?",claim.getEdiable()!=false);
		Item item = new Item("AA");
		assertTrue("photo delete",item.deletePhoto()!=false);
		assertTrue("photo not exist",item.getPhoto()==false);
		assertTrue("photo taken",item.takePhoto()!=false);
	}
	//US06.04.01
	public boolean pictureSize(){
		Item item = new Item("AA");
		if (item.photoSize()>65536){
			return false;
		}
		return true;
	}
	

}
