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
import java.util.Date;

/**
 * The <code>User</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a ArrayList named User which contains Claim.
 * The User can also be pushed online and pulled online, sort and search 
 * Claim. This class is associated with <code>Claim</code> for Claim information, 
 * <code>Tag</code> and <code>TagList</code> for searching by Tag names.
 * 
 * @author CMPUT301W15T06
 * @version 03/07/2015
 * @see java.util.ArrayList
 */
public class User extends AppModel{

	/**
	 * Set a ArrayList named claimList which records the information
	 * of all the Claim. 
	 * 
	 * @see java.util.ArrayList
	 */
	private ArrayList<Claim> claimList;

	private ArrayList<Tag> tagList;
	
	private ArrayList<Long> filterTagIDList;
	
	private String userName;
	
	private Date lastModify;
	
	private boolean filter;
	
	private boolean needSyn;
	
//	/**
//	 * General construction. This method Set up the claimList.
//	 * 
//	 * @see java.util.ArrayList
//	 */

			
	public User(String name) {
		// TODO Auto-generated constructor stub
		super();
		claimList=new ArrayList<Claim>();
		tagList=new ArrayList<Tag>();
		filterTagIDList=new ArrayList<Long>();
		userName=name;
		filter=false;
		needSyn=false;
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
	 * Push the claimList to the Internet. Do nothing
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
	 * and return a Claim object with default value null.. T
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
	 * This method allows user to add a Listener to the 
	 * ArrayList listeners and also add listener to each claim
	 * in this claimList
	 * @param l  a Listener type
	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
		for (Claim claim:claimList){
			claim.addListener(l);
		}
		for (Tag tag:tagList){
			tag.addListener(l);
		}
		
	}
	/**
	 * Sort the claimList. The method does nothing here, but may
	 * have further use.
	 */
	public void sort() {
		// TODO Auto-generated method stub
		
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public ArrayList<Tag> getTagList() {
		return tagList;
	}

	public CharSequence[] toTagList() {
		// TODO Auto-generated method stub
		ArrayList<CharSequence> csal = new ArrayList<CharSequence>();
		for (Tag tag:tagList){
			csal.add(tag.toString());
		}
		CharSequence[] result=new CharSequence[csal.size()];
		return csal.toArray(result);
	}


	public Tag getTagByID(Long l) {
		// TODO Auto-generated method stub
		for(Tag tag:tagList){
			if(l.equals(tag.getID())){
				return tag;
			}
		}
		return null;
	}


	

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return false;
	}


	public ArrayList<Long> getFilterTagIDList() {
		return filterTagIDList;
	}


	public boolean[] toCheckArray() {
		// TODO Auto-generated method stub
		
		boolean[] result = new boolean[tagList.size()];
		int i=0;
		for (Tag tag:tagList){
			if(filterTagIDList.contains(tag.getID())){
				result[i]=true;
			}else{
				result[i]=false;
			}
			i++;
		}
		return result;
	}


	public boolean isFilter() {
		return filter;
	}


	public void setFilter(boolean filter) {
		this.filter = filter;
		notifyListeners();
	}


	public boolean isNeedSyn() {
		return needSyn;
	}


	public void setNeedSyn(boolean needSyn) {
		if(needSyn!=this.needSyn){
			this.needSyn = needSyn;
			ClaimListManager.getInstance().saveLocal(this);
			
		}
	}
	public void setNeedSynSimple(boolean needSyn) {

		this.needSyn = needSyn;
			
	}

}
