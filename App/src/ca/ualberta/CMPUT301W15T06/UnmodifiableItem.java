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

import android.location.Location;

/**
 * This is a subclass of Item. It will check if the Item is edible.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.Date
 * @see android.location.Location
 */
public class UnmodifiableItem extends Item {

	/**
	 * Switch sub-class.
	 * 
	 * @param item  a Item object
	 */
	public UnmodifiableItem(Item item) {
		// TODO Auto-generated constructor stub
		super(item);
		receipt=new UnmodifiableReceipt(item.getRecipt());
	}

	/**
	 * Set a new date of the item. Check for warnings to prevent crush.
	 * 
	 * @param itemDate  a Date object indicate the date of the expense item (like "15-Mar-2015")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setDate(Date itemDate) throws StatusException{
		throw new StatusException();					
	}

	/**
	 * Set a new category of the item. Check for warnings to prevent crush.
	 * 
	 * @param category  a String variable indicate the category of the expense item (like "air fare")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setCategory(String category) throws StatusException{
		throw new StatusException();					
	}

	/**
	 * Set a new description of the item. Check for warnings to prevent crush.
	 * 
	 * @param description  a String variable indicate the description of the expense item (like "office use")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setDescription(String description) throws StatusException{
		throw new StatusException();					
	}

	/**
	 * Set a new amount of the item. Check for warnings to prevent crush.
	 * 
	 * @param amount  a Double variable indicate the amount of the expense item (like "150.23")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setAmount(Double amount) throws StatusException{
		throw new StatusException();					
	}

	/**
	 * Set a new currency of the item. Check for warnings to prevent crush.
	 * 
	 * @param currency  a String variable indicate the currency of the expense item (like "CAD")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setCurrency(String currency) throws StatusException{
		throw new StatusException();					
	}


	/**
	 * Set a new location of the destination. Check for warnings to prevent crush.
	 * 
	 * @param b  a boolean variable ("True" or "False")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setFlag(boolean b) throws StatusException{
		throw new StatusException();					
	}
	
	/**
	 * Set a new location of the destination. Check for warnings to prevent crush.
	 * 
	 * @param location  the location of the destination (like "Edmonton")
	 * @throws NetWorkException
	 * @throws StatusException
	 */
	public void setLocation(Location location) throws StatusException {
		throw new StatusException();
	}

}
