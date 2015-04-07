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

import java.util.ArrayList;

/**
 * This class contain all the User in the application.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.ArrayList
 */
public class UserList {
	private ArrayList<String> userList;

	private boolean needSynList;

	
	public UserList(){
		userList=new ArrayList<String>();
		needSynList=false;
	}
	
	public ArrayList<String> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}

	public boolean isNeedSyn() {
		boolean result=false;
		for (String name:userList){
			if(ClaimListManager.getInstance().load(name).isNeedSyn()){
				result=true;
			}
		}
		return result;
	}


	public boolean isNeedSynList() {
		return needSynList;
	}

	public void setNeedSynList(boolean needSynList) {
		this.needSynList = needSynList;
	}

	
}
