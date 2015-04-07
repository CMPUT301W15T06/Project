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
package ca.ualberta.CMPUT301W15T06;

/**
 * This is a subclass of Receipt. It will check if the Receipt is edible.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.Date
 */
public class ModifiableReceipt extends Receipt {

	/**
	 * This method will call the super class to modify the Receipt.
	 * 
	 * @param receipt a Receipt object
	 */
	public ModifiableReceipt(Receipt receipt){
		super(receipt);
	}
	
	/**
	 * This method will call the super class to modify the Receipt.
	 */
	public ModifiableReceipt(){
		super();
	}

	/**
	 * This method will set the photo receipt to the item.
	 * 
	 * @param photo a string object
	 * @throws NetWorkException 
	 */
	public void setPhotoStr(String photo) throws NetWorkException {
		photoStr=photo;
		notifyListeners();
	}
}
