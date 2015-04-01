package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

public class Tag extends AppModel{

	private String name;
	private long ID;
	
	public Tag(String tagName) {
		// TODO Auto-generated constructor stub
		name=tagName;
		ID=new Date().getTime();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) throws NetWorkException {
		this.name = name;
		notifyListeners();
	}

	public String toString(){
		return name;
	}


	public long getID() {
		return ID;
	}


	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return false;
	}


}
