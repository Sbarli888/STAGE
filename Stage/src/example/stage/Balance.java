package example.stage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Balance extends Activity implements FetchDataListener
{
	private TextView balanceeld;
	
	Intent balanceInt;
	
	String mob;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.balance);
		
		balanceeld = (TextView) findViewById(R.id.textView2);
		
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext(); 
		mob  = globalVariable.getMob();
		
		FetchDataTask balance = new FetchDataTask(this);
		balance.execute("http://www.stage000.comyr.com/balance.php?mobu="+mob);
		
		
	}
	public void backer(View view)
	{
		Intent intent = new Intent().setClass(getApplicationContext(),MainAppScreen.class);
		
		startActivity(intent);
		Balance.this.finish();
	}
	
	public void recharge(View view)
	{
		Intent intent = new Intent().setClass(getApplicationContext(),Payment.class);
		
		startActivity(intent);
	}
	
	@Override
	public void onFetchComplete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFetchFailure(String msg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFetchComplete(String string) 
	{
		// TODO Auto-generated method stub
		Toast.makeText(Balance.this, string,Toast.LENGTH_SHORT).show();
		balanceeld.setText(string);
		
	}
	
	@Override
	public void onBackPressed() 
	{
		balanceInt = new Intent().setClass(getApplicationContext(),MainAppScreen.class);
		startActivity(balanceInt);
		Balance.this.finish();
		
	}
}
