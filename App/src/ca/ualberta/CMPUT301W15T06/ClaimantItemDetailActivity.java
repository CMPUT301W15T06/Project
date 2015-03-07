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
		
		TextView dateView=(TextView) findViewById(R.id.itemDateVTextView);
		TextView categoryView=(TextView) findViewById(R.id.itemCategoryVTextView);
		TextView descriptionView=(TextView) findViewById(R.id.itemDescriptionVTextView);
		TextView amountView=(TextView) findViewById(R.id.itemAmountVTextView);
		TextView currencyView=(TextView) findViewById(R.id.itemCurrencyVTextView);
		dateView.setText(AppSingleton.getInstance().getCurrentItem().getDate());
		categoryView.setText(AppSingleton.getInstance().getCurrentItem().getCategory());
		descriptionView.setText(AppSingleton.getInstance().getCurrentItem().getDescription());
		amountView.setText(String.valueOf(AppSingleton.getInstance().getCurrentItem().getAmount()));
		currencyView.setText(AppSingleton.getInstance().getCurrentItem().getCurrency());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_item_detail, menu);
		return true;
	}

	
	public void deleteItem(View v){
		cdic.removeItem(AppSingleton.getInstance().getCurrentItem());
		finish();
	}
	
	public void editItem(View v){
		Intent intent =new Intent(ClaimantItemDetailActivity.this,ClaimantEditItemActivity.class);
		startActivity(intent);		
	}
}
