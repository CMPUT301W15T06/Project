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

public class Claim {

	private String name;
	private String beginDate;
	private String endDate;
	private Item currentItem;
	private String status="In progress";
	private ArrayList<Item> itemList;
	private ArrayList<Destination> destinationList;
	private String approver;
	private String comment;	
	private ArrayList<Tag> tagList;

	
	public Claim(String claimName) {
		
	}
	
	public void setName(String string){
		
	}
	
	public String getName() {
		return null;
	}
	
	public void setBeginDate(String beginDate){

	}
	
	public String getBeginDate() {
		return null;
	}
	
	public void setEndDate(String endDate){

	}
	
	public String getEndDate() {
		return null;
	}
	
	
	
	public void setCurrentItem(Item item) {

	}
	
	public Item getCurrentItem() {
		return null;	
	}
	
	
	public void setStatus(String status){

	}
	
	public String getStatus(){
		return null;	
	}

	public void addItem(Item item) {

	}
	
	public void removeItem(Item item) {

		
	}
	
	
	public ArrayList<Item> getItemList() {
		return null;
	}
	
	public void addDestination(Destination destination) {

	}
	
	public void removeDestination(Destination destination) {

		
	}
	
	public ArrayList<Item> getDestinationList() {
		return null;
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
