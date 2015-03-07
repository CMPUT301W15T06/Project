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
	
	public void addDestination(Destination destination) {
		destinationList.add(destination);
		notifyListeners();
	}
	
	public void removeDestination(Destination destination) {
		destinationList.remove(destination);
		notifyListeners();
	}
	
	public ArrayList<Destination> getDestinationList() {
		return destinationList;
	}
	
	public String toString(){
		return name+'\n'+beginDate+'\n';
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
	
	
}
