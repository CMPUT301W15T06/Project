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
	
//	/**
//	 * Set a private field photo String.
//	 */
	private String photoStr=null;
	
	
//	/**
//	 * This method will set a photo for <code>Receipt</code>.
//	 */
	public void setPhotoStr(String photo) throws StatusException, NetWorkException{
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		photoStr=photo;
		notifyListeners();
	}
//	/**
//	 * This method will return a photo <code>Receipt</code> for display or 
//	 * further use.
//	 * 
//	 * @return 0  an integer variable
//	 */
	public String getPhotoStr(){
		return photoStr;
	}
	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return photoStr==null;
	}


}
