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
 * This <code>Receipt</code> class is design to set a photo receipt and to get photo if 
 * it is necessary.
 * 
 * @author CMPUT301W15T06
 * @version 03/07/2015
 */
public class Receipt extends AppModel {
	
	/**
	 * Set a private field photoStr String as null
	 */
	protected String photoStr=null;
	
	/**
	 * Call super class.
	 */
	public Receipt(){
		super();
		
	}
	
	/**
	 * General constructor.
	 * 
	 * @param receipt  a Receipt object
	 */
	public Receipt(Receipt receipt){
		super(receipt);
		photoStr=receipt.getPhotoStr();
	}
	
	/**
	 * This method will return a photo <code>Receipt</code> for display or 
	 * further use.
	 * 
	 * @return photoStr an String variable
	 */
	public String getPhotoStr(){
		return photoStr;
	}
	
	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return photoStr==null;
	}
	
	/**
	 * This method will set the photo receipt to the item.
	 * 
	 * @param photo a string object
	 * @throws NetWorkException 
	 */
	public void setPhotoStr(String photo) throws StatusException, NetWorkException {
	}

}
