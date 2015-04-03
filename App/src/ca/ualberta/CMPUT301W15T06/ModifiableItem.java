package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

public class ModifiableItem extends Item {


	public ModifiableItem(Item item) {
		// TODO Auto-generated constructor stub
		super(item);
		receipt=new ModifiableReceipt(item.getRecipt());
	}

	public ModifiableItem() {
		// TODO Auto-generated constructor stub
		super();	
	}

	public void setDate(Date itemDate) throws NetWorkException {
			date=itemDate;
			notifyListeners();
		}


	public void setCategory(String category) throws NetWorkException {
				this.category=category;
				notifyListeners();
			}


	public void setDescription(String description) throws NetWorkException {
				this.description=description;
				notifyListeners();
			}

	public void setAmount(Double amount) throws NetWorkException {
				this.amount=amount;
				notifyListeners();
			}


	public void setCurrency(String currency) throws NetWorkException {
				this.currency=currency;
				notifyListeners();
			}

	public void setFlag(boolean b) throws NetWorkException {
				flag=b;
				notifyListeners();
			}

}
