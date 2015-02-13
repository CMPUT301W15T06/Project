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
