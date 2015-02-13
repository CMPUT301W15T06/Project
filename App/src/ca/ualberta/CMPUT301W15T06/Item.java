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



public class Item{

	private String name;
	private String date;
	private String category;
	private String description;
	private int amount=0;
	private String currency;
	private Recipt recipt;

	public Item(String itemName) {	

	}
	
	public void setName(String name) {
		
	}

	public String getName() {
		return null;
	}

	public void setDate(String itemDate) {

	}

	public String getDate() {
		return null;
	}

	public void setCategory(String category) {
	}

	public String getCategory() {
		return null;
	}

	public void setDescription(String description) {
	
	}

	public String getDescription() {
		return null;
	}

	public void setAmount(double d) {
	
	}

	public double getAmount() {
		return 0;
	}

	public void setCurrency(String currency) {

	}

	public String getCurrency() {
		return null;
	}

	public void setRecipt(Recipt recipt) {
		
	}
	
	public Recipt getRecipt() {
		return null;
	}
	
	public boolean takePhoto(){
		return false;
	}
	public boolean getPhoto(){
		return false;
	}
	public boolean deletePhoto(){
		return false;
	}
	public int photoSize(){
		return 0;
	}
	public void savePhoto(){
		
	}

	public void addFlag() {
		// TODO Auto-generated method stub
		
	}
	public boolean getFlag() {
		return false;
		
	}

	public void deleteFlag() {
		// TODO Auto-generated method stub
		
	}

	public boolean infoComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDate() {
		// TODO Auto-generated method stub
		
	}

	public void addCategory() {
		// TODO Auto-generated method stub
		
	}

	public void addCurrency() {
		// TODO Auto-generated method stub
		
	}

	public void addAmount() {
		// TODO Auto-generated method stub
		
	}

	public void addDescription() {
		// TODO Auto-generated method stub
		
	}

	public void setIncomplete() {
		// TODO Auto-generated method stub
		
	}
	
	
}
