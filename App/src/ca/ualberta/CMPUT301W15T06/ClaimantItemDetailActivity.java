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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_item_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_item_detail, menu);
		return true;
	}

	public void onResume(){
		super.onResume();		
		TextView dateView=(TextView) findViewById(R.id.itemDateVTextView);
		TextView categoryView=(TextView) findViewById(R.id.itemCategoryVTextView);
		TextView descriptionView=(TextView) findViewById(R.id.itemDescriptionVTextView);
		TextView amountView=(TextView) findViewById(R.id.itemAmountVTextView);
		TextView currencyView=(TextView) findViewById(R.id.itemCurrencyVTextView);
		dateView.setText(ClaimListController.getCurrentItem().getDate());
		categoryView.setText(ClaimListController.getCurrentItem().getCategory());
		descriptionView.setText(ClaimListController.getCurrentItem().getDescription());
		amountView.setText(String.valueOf(ClaimListController.getCurrentItem().getAmount()));
		currencyView.setText(ClaimListController.getCurrentItem().getCurrency());
	}
	
	public void deleteItem(View v){
		ClaimListController.removeItem();
		finish();
	}
	
	public void editItem(View v){
		Intent intent =new Intent(ClaimantItemDetailActivity.this,ClaimantEditItemActivity.class);
		startActivity(intent);		
	}
}
