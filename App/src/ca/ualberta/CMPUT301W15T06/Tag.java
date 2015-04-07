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

import java.util.Date;

/**
 * This class will create a tag to the item. 
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.Date
 */
public class Tag extends AppModel{

	private String name;
	private long ID;
	
	/**
	 * General constructor.
	 * 
	 * @param tagName
	 */
	public Tag(String tagName) {
		// TODO Auto-generated constructor stub
		name=tagName;
		ID=new Date().getTime();
	}

	
	/**
	 * return the name of the tag
	 * 
	 * @return the name of the tag
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set a name to the tag.
	 * 
	 * @param name
	 * @throws NetWorkException
	 */
	public void setName(String name) throws NetWorkException {
		this.name = name;
		notifyListeners();
	}

	/**
	 * Return the name of the tag.
	 */
	public String toString(){
		return name;
	}

	/**
	 * Return the ID number of the tag. 
	 * 
	 * @return the ID number of the tag
	 */
	public long getID() {
		return ID;
	}


	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return false;
	}


}
