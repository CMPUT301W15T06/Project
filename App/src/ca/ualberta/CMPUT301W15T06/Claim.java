
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
	private boolean editable = false;

	
	public Claim(String claimName) {
		
	}
	
	public static void setName(String string){
		
	}
	
	public String getName() {
		return null;
	}
	
	public static void setBeginDate(String beginDate){

	}
	
	public String getBeginDate() {
		return null;
	}
	
	public static void setEndDate(String endDate){

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
		return editable;
	}
	
	
}
