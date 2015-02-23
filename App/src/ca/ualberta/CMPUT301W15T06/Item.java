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
	private double amount=0;
	private String currency;
	private Recipt recipt;

	public Item() {	
	}
	

	public void setDate(String itemDate) {
		date=itemDate;
	}

	public String getDate() {
		return date;
	}

	public void setCategory(String category) {
		this.category=category;
	}

	public String getCategory() {
		return category;
	}

	public void setDescription(String description) {
		this.description=description;
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(double amount) {
		this.amount=amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setCurrency(String currency) {
		this.currency=currency;
	}

	public String getCurrency() {
		return currency;
	}
	
	public String toString(){
		return date+'\n'+category+'\n'+description+'\n'+amount+' '+currency;
	}
	public void setRecipt(Recipt recipt) {
		
	}
	
	public Recipt getRecipt() {
		return null;
	}
	
	public boolean takePhoto(){
		return false;
	}
	public static boolean getPhoto(){
		return false;
	}
	public boolean deletePhoto(){
		return false;
	}
	public static int photoSize(){
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

	public void setIncomplete() {
		// TODO Auto-generated method stub
		
	}


	public String getName() {
		return name;
	}
	
	
}
