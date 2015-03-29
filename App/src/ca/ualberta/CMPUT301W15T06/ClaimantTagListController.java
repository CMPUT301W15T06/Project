package ca.ualberta.CMPUT301W15T06;

public class ClaimantTagListController {

	private User user=null;
	
	public ClaimantTagListController(User u) {
		// TODO Auto-generated constructor stub
		user=u;
	}

	public void addTag(String tagName) {
		// TODO Auto-generated method stub
		Tag tag=new Tag(tagName);
		user.getTagList().add(tag);
		tag.addModelListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				user.notifyListeners();
			}
		});
		user.getFilterTagIDList().add(tag.getID());
		
		user.notifyListeners();
	}

	public void delete(Tag tag) {
		// TODO Auto-generated method stub
		for (Claim claim:user.getClaimList()){
			claim.getTagIDList().remove(tag.getID());
		}
		user.getFilterTagIDList().remove(tag.getID());
		user.getTagList().remove(tag);
		user.notifyListeners();
	}

	public void edit(Tag tag, String string) {
		// TODO Auto-generated method stub
		tag.setName(string);
	}

}
