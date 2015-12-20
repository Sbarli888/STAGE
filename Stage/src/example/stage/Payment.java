package example.stage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Payment extends Activity
{
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void online(View view)
	{
		Intent intent = new Intent().setClass(getApplicationContext(),PayPal.class);
		startActivity(intent);	
	}
	
	public void cardd(View view)
	{
		Intent intent = new Intent().setClass(getApplicationContext(),Card.class);
		startActivity(intent);	
	}
	
	public void skip(View view)
	{
		Intent intent = new Intent().setClass(getApplicationContext(),MainActivity2.class);
		startActivity(intent);	
		Payment.this.finish();
	}
	
	
	
}
