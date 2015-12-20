package example.stage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	
	Button button;
	Button button1; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//Restore Saved State
		//savedInstanceState is a data structure that is used to save 
		//any instances, data from the last time the activity is running
		super.onCreate(savedInstanceState);
		
		//Set Content View
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
		addListenerOnButton1();
	}
	
	
	
	
	public void addListenerOnButton()
	{
		
		
		//Initialize UI elements
		final Context context = this;
		
		//findViewById-->> find the element by ID
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View argo) {
				
				Intent intent = new Intent(context,MainActivity2.class);
				startActivity(intent);
				
			}
		});
	
	}
	
	//addListenerOnButton1 or use the onClickListener Interface for button Click
	//both is an interface interface and has an onClick method
	//onClick method is called every time the button is clicked 	
	public void addListenerOnButton1(){
		
		final Context context = this;
		button1 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View argo) {
				
				Intent intent = new Intent(context,MainActivity3.class);
				startActivity(intent);
				
			}
		});
	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed()
	{
		MainActivity.this.finish();
	}
}
