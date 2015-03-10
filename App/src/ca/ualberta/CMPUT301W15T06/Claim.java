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
 * The <code>Claim</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a claim with the information including claimant's name, 
 * travel begin and end date, travel status, item list, destination list with reason, 
 * approve status, comment and tag list. The class also allows the user to call
 * <code>ClaimantAddClaimController</code> to create a new claim, set up and 
 * edit claim detail(such as name, start date and end date), set status, call 
 * <code>ClaimantAddDestinationController</code> class to add destination and reason, 
 * get item list from <code>ItemList</code> and get tag list from <code>TagList</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/07/2015
 * @see java.util.ArrayList
 */

package ca.ualberta.CMPUT301W15T06;


import java.util.ArrayList;


public class Claim extends AppModel{

	private String name;
	private String beginDate;
	private String endDate;
	private String status="In progress";
	private ArrayList<Item> itemList;
	private ArrayList<Destination> destinationList;
	private String approver;
	private String comment;	
	private ArrayList<Tag> tagList;
	

	
	public Claim(String claimName) {
		super();
		itemList=new ArrayList<Item>();
		destinationList=new ArrayList<Destination>();
		tagList=new ArrayList<Tag>();
		name=claimName;
	}
	
	public void setName(String name){
		this.name=name;
		notifyListeners();
	}
	
	public String getName() {
		return name;
	}
	
	public void setBeginDate(String beginDate){
		this.beginDate=beginDate;
		notifyListeners();
	}
	
	public String getBeginDate() {
		return beginDate;
	}
	
	public void setEndDate(String endDate){
		this.endDate=endDate;
		notifyListeners();
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	
	
	public void setStatus(String status){
		this.status=status;
		notifyListeners();
	}
	
	public String getStatus(){
		return status;	
	}

	
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	

	public ArrayList<Destination> getDestinationList() {
		return destinationList;
	}
	
	public String toString(){
		String dest="";
		for (Destination d:destinationList){
			dest+='\n'+"      "+d.getName();
		}
		String tag="";
		for (Tag t:tagList){
			dest+='\n'+"      "+t.getName();
		}
		return "Starting Date: "+beginDate+'\n'+"Destination(s): "+dest+'\n'+"Status: "+status+'\n'+"Tag(s) : "+tag+'\n'+
				getCM("CAD")+'\n'+getCM("USD")+'\n'+getCM("EUR")+'\n'+getCM("GBP")+'\n'+getCM("CHF")+'\n'+getCM("JPY")+'\n'+getCM("CNY");

	}
	
	public String getCM(String currency){
		Double total = null;

		for (Item item:itemList){
			if(item.getCurrency().equals(currency)&&item.getAmount()!=null){
				if(total==null){
					total=item.getAmount();
				}else{
					total+=item.getAmount();
				}
			}		
		}

		return total==null?(currency+": "):(currency+": "+total.toString());
	}
	
	public ArrayList<Item> getClaimDetail() {
		return null;
		
	}
	
	public void setApprover(String name){
		
	}
	
	public String getApprover(){
		return null;
	}
	
	public void setComment(String comment){
		
	}
	
	public String getComment(){
		return null;
	}
	
	public void addTag(String tagName){
		
	}
	
	public ArrayList<Tag> getTagList(){		
		return null;
	}	

	public boolean getEdiable(){
		return false;
	}

	public Object getItem(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getItemDate(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemCategory(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getItemAmount(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemCurrency(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemDescription(Item expenseTest1) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getItemSize() {
		// TODO Auto-generated method stub
		return itemList.size();
	}
	
	
}
