package ca.ualberta.CMPUT301W15T06;

public class ClaimantTagListController {

	private ClaimList cl=null;
	
	public ClaimantTagListController(ClaimList claimList) {
		// TODO Auto-generated constructor stub
		cl=claimList;
	}

	public void addTag(String tagName) {
		// TODO Auto-generated method stub
		Tag tag=new Tag(tagName);
		cl.getTagList().add(tag);
		tag.addModelListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				cl.notifyListeners();
			}
		});
		cl.notifyListeners();
	}

	public void delete(Tag tag) {
		// TODO Auto-generated method stub
		for (Claim claim:cl.getClaimList()){
			claim.getTagIDList().remove(tag.getID());
		}
		cl.getTagList().remove(tag);
		cl.notifyListeners();
	}

	public void edit(Tag tag, String string) {
		// TODO Auto-generated method stub
		tag.setName(string);
	}

}
