package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

public class UnmodifiableItem extends Item {

	public UnmodifiableItem(Item item) {
		// TODO Auto-generated constructor stub
		super(item);
		receipt=new UnmodifiableReceipt(item.getRecipt());
	}


	public void setDate(Date itemDate) throws StatusException{
		throw new StatusException();					
	}


	public void setCategory(String category) throws StatusException{
		throw new StatusException();					
	}


	public void setDescription(String description) throws StatusException{
		throw new StatusException();					
	}


	public void setAmount(Double amount) throws StatusException{
		throw new StatusException();					
	}


	public void setCurrency(String currency) throws StatusException{
		throw new StatusException();					
	}


	public void setFlag(boolean b) throws StatusException{
		throw new StatusException();					
	}

}
