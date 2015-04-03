package ca.ualberta.CMPUT301W15T06;

public class UnmodifiableReceipt extends Receipt {

	public UnmodifiableReceipt(Receipt receipt) {
		super(receipt);
		// TODO Auto-generated constructor stub
	}

	public void setPhotoStr(String photo) throws StatusException{
		throw new StatusException();					
	}

}
