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
 
/**
 * The <code>ClaimList</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a ArrayList named ClaimList which contains Claim.
 * The ClaimList can also be pushed online and pulled online, sort and search 
 * Claim. This class is associated with <code>Claim</code> for Claim information, 
 * <code>Tag</code> and <code>TagList</code> for searching by Tag names.
 * 
 * @author CMPUT301W15T06
 * @version 03/07/2015
 * @see java.util.ArrayList
 */
package ca.ualberta.CMPUT301W15T06;


import java.util.ArrayList;

public class ClaimList extends AppModel{

	/**
	 * Set a ArrayList named claimList which records the information
	 * of all the Claim. 
	 * 
	 * @see java.util.ArrayList
	 */
	private ArrayList<Claim> claimList;

	
	/**
	 * General construction. This method Set up the claimList.
	 * 
	 * @see java.util.ArrayList
	 */
	public ClaimList(){
		super();
		claimList=new ArrayList<Claim>();
	}
			
	/**
	 * Return the ArrayList claimList. This method will be used when 
	 * other class need to use or display the claimList. 
	 * 
	 * @return claimList  an ArrayList object
	 */
	public ArrayList<Claim> getClaimList(){
		return claimList;
	}
		
	/**
	 * Push the claimList to the Internaet. Do nothing
	 * right know, but may have further use.
	 */
	public void pushOnline(){	
	}
	
	/**
	 * Return the Claim object as null. This method will be used when 
	 * claimant wants to get the online data of the claimList. 
	 * 
	 * @return null  a Claim object
	 */
	public Claim pullOnline(){
		return null;
		
	}
	
	/**
	 * Use the string variable tagName to do the search method 
	 * and return a Claim object with defualt value null.. T
	 * his method will be used when other class need to use 
	 * or display. 
	 * 
	 * @param tagName  a String variable
	 * @return null  a Claim object
	 */
	public Claim searchByTag(String tagName){
		return null;
	}
	
	/**
	 * Sort the claimList. The method does nothing here, but may
	 * have further use.
	 */
	public void sort() {
		// TODO Auto-generated method stub
		
	}

}
