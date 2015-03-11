package ca.ualberta.CMPUT301W15T06;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimantItemDetailActivity extends Activity {

	private ClaimantDeleteItemController cdic=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_item_detail);
		
		cdic=new ClaimantDeleteItemController(AppSingleton.getInstance().getCurrentClaim());
		
		final TextView dateView=(TextView) findViewById(R.id.itemDateVTextView);
		final TextView categoryView=(TextView) findViewById(R.id.itemCategoryVTextView);
		final TextView descriptionView=(TextView) findViewById(R.id.itemDescriptionVTextView);
		final TextView amountView=(TextView) findViewById(R.id.itemAmountVTextView);
		final TextView currencyView=(TextView) findViewById(R.id.itemCurrencyVTextView);
		dateView.setText(AppSingleton.getDateFormat().format(AppSingleton.getInstance().getCurrentItem().getDate()));
		categoryView.setText(AppSingleton.getInstance().getCurrentItem().getCategory());
		descriptionView.setText(AppSingleton.getInstance().getCurrentItem().getDescription());
		amountView.setText(AppSingleton.getInstance().getCurrentItem().getAmount()==null?"":String.valueOf(AppSingleton.getInstance().getCurrentItem().getAmount()));
		currencyView.setText(AppSingleton.getInstance().getCurrentItem().getCurrency());
		
		AppSingleton.getInstance().getCurrentItem().addListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				dateView.setText(AppSingleton.getDateFormat().format(AppSingleton.getInstance().getCurrentItem().getDate()));
				categoryView.setText(AppSingleton.getInstance().getCurrentItem().getCategory());
				descriptionView.setText(AppSingleton.getInstance().getCurrentItem().getDescription());
				amountView.setText(AppSingleton.getInstance().getCurrentItem().getAmount()==null?"":String.valueOf(AppSingleton.getInstance().getCurrentItem().getAmount()));
				currencyView.setText(AppSingleton.getInstance().getCurrentItem().getCurrency());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_item_detail, menu);
		return true;
	}

	
	public void deleteItem(View v){
		try {
			cdic.removeItem(AppSingleton.getInstance().getCurrentItem());
		} catch (StatusException e) {
			Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}
		finish();
	}
	
	public void editItem(View v){
		Intent intent =new Intent(ClaimantItemDetailActivity.this,ClaimantEditItemActivity.class);
		startActivity(intent);		
	}
}
