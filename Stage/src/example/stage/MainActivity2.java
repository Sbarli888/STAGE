package example.stage;

//import android.support.v7.app.ActionBarActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends Activity implements FetchDataListener 
{

	private EditText mobileno;
	private EditText passwd;
	
    String password,mobno;
    
    Intent intent;
    
	Context cc;
	Boolean flag = false;
	// private Cursor result;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		mobileno = (EditText) findViewById(R.id.editText1);
		passwd = (EditText) findViewById(R.id.editText2);
		
		

		cc = this;

		if (checkConnection() == false)
			Toast.makeText(this, "No Net", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "Checked InterNet", Toast.LENGTH_SHORT).show();

	}

	public boolean checkConnection()
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
		if (activeInfo == null)
			return false;
		else
			return true;
	}

	public void loggin(View view) 
	{
		mobno = mobileno.getEditableText().toString();
		password = passwd.getEditableText().toString();
		
		//String validate;
		
		if (mobno.equals("") || password.equals(""))
		{
			Toast.makeText(MainActivity2.this, "Field are blank",
					Toast.LENGTH_SHORT).show();
		}
		else 
		{
			if (mobno.length() < 10 || mobno.length() >= 11)
			{
				Toast.makeText(MainActivity2.this, "Mobile No Is Invalid",
						Toast.LENGTH_SHORT).show();
			}
			else
			{
				FetchDataTask search = new FetchDataTask(this);
				search.execute("http://www.stage000.comyr.com/select.php?mob="+mobno);
				onFetchComplete();
				/*if(flag == true)
				{
					Intent intent = new Intent().setClass(getApplicationContext(),Join.class);
					intent.putExtra("mobi", mobno);
					startActivity(intent);	
					
				} */
				
				
			}
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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


	@Override
	public void onFetchComplete()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onFetchFailure(String msg) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onFetchComplete(String string)
	{
		password = passwd.getEditableText().toString();
		
		 //TODO Auto-generated method stub
		if (string.equals(password))
		{
			Toast.makeText(MainActivity2.this, "Welcome Back", Toast.LENGTH_SHORT).show();
			flag = true;
			final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
			globalVariable.setMob(mobno);
			intent = new Intent().setClass(getApplicationContext(),Join.class);
			startActivity(intent);	
			MainActivity2.this.finish();
		}
		else
		{
			Toast.makeText(MainActivity2.this, "Invalid password", Toast.LENGTH_SHORT).show();
			flag = false;
		}

	}
	@Override
	public void onBackPressed()
	{
		MainActivity2.this.finish();
	}

}
