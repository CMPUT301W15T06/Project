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



public class Item extends AppModel{

	private String name;
	private String date;
	private String category;
	private String description;
	private Double amount;
	private String currency;
	private Recipt recipt;
	private boolean flag=false;
	private boolean missValue;

	public Item() {	
	}
	

	public void setDate(String itemDate) {
		date=itemDate;
		notifyListeners();
	}

	public String getDate() {
		return date;
	}

	public void setCategory(String category) {
		this.category=category;
		notifyListeners();
	}

	public String getCategory() {
		return category;
	}

	public void setDescription(String description) {
		this.description=description;
		notifyListeners();
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(Double amount) {
		this.amount=amount;
		notifyListeners();
	}

	public Double getAmount() {
		return amount;
	}

	public void setCurrency(String currency) {
		this.currency=currency;
		notifyListeners();
	}

	public String getCurrency() {
		return currency;
	}
	
	public void setFlag(boolean b) {
		flag=b;
		notifyListeners();
	}

	public boolean getFlag() {
		return flag;
	}
	
	public void setMissValue(boolean b) {
		missValue=b;
		notifyListeners();
	}

	public boolean getMissValue() {
		return missValue;
	}
	
	public String toString(){
		if (amount==null){
			return date+'\n'+category+'\n'+description+'\n'+" "+' '+currency;
		}else{
			return date+'\n'+category+'\n'+description+'\n'+amount+' '+currency;
		}
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
