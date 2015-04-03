package ca.ualberta.CMPUT301W15T06;

public class ModifiableReceipt extends Receipt {

	public ModifiableReceipt(Receipt receipt){
		super(receipt);
	}
	
	public ModifiableReceipt(){
		super();
	}

	public void setPhotoStr(String photo) throws NetWorkException {
		photoStr=photo;
		notifyListeners();
	}
}
