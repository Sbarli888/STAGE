package example.stage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainAppScreen extends Activity implements FetchDataListener
{

//	private Button bookticket;
	private Button bookinghistory;
	private Button checkbalance;
	private Button changeoassword;
	private Button ticket;
	private Button logoff;
	
	Intent intentnew;
	
	String tickqr,reteriv;
	String mob;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainappscreen);

		//bookticket = (Button) findViewById(R.id.button1);
		bookinghistory = (Button) findViewById(R.id.button2);
		checkbalance = (Button) findViewById(R.id.button3);
		changeoassword = (Button) findViewById(R.id.button4);
		ticket = (Button) findViewById(R.id.button5);
		logoff = (Button) findViewById(R.id.button6);
		
		//Intent intentTicket = getIntent();
		//tickqr = intentTicket.getStringExtra("ticke");
		
		

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext(); 
		mob  = globalVariable.getMob();
		
		
		

		bookinghistory.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent().setClass(getApplicationContext(),BookingHistory.class);
				
				startActivity(intent);
			}
		});

		

		checkbalance.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent().setClass(getApplicationContext(),Balance.class);
				
				startActivity(intent);
			}
		});

		changeoassword.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent().setClass(getApplicationContext(),ChangePassword.class);
				
				startActivity(intent);
			}
		});
		
		
		ticket.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent().setClass(getApplicationContext(),qrDisplay.class);
				
				
				startActivity(intent);	
				
			}
		});
		
		
		logoff.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent().setClass(getApplicationContext(),MainActivity.class);
				
				startActivity(intent);
				//Toast.makeText(this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
	}
	
	public void ticketboook(View view) 
	{
		
		FetchDataTask balance = new FetchDataTask(this);
		balance.execute("http://www.stage000.comyr.com/balance.php?mobu="+mob);
		
		
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
		reteriv = string.trim();
		if(reteriv.length()>=2)
		{
			Toast.makeText(MainAppScreen.this, reteriv,Toast.LENGTH_SHORT).show();
			intentnew = new Intent().setClass(getApplicationContext(),BookTicket.class);
			intentnew.putExtra("bal", reteriv);
			startActivity(intentnew);
		}
		else
			Toast.makeText(MainAppScreen.this, "Insufficient Balance",Toast.LENGTH_SHORT).show();
		
	}
	@Override
	public void onBackPressed()
	{
		MainAppScreen.this.finish();
	}

}
