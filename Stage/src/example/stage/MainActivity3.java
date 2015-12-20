package example.stage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends Activity implements FetchDataListener 
{

	public String username, mobileno, password, confpassword;
	Context cc;

	private EditText uname;
	private EditText mob;
	private EditText passwd;
	private EditText cnfpasswd;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);

		uname = (EditText) findViewById(R.id.editText1);
		mob = (EditText) findViewById(R.id.editText2);
		passwd = (EditText) findViewById(R.id.editText3);
		cnfpasswd = (EditText) findViewById(R.id.editText4);
		// edit7= (EditText)findViewById(R.id.ET7);

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

	public void sendd(View view) 
	{
		String username = uname.getEditableText().toString();
		String mobileno = mob.getEditableText().toString();
		String password = passwd.getEditableText().toString();
		String confpassword = cnfpasswd.getEditableText().toString();

		if (username.equals("") || mobileno.equals("") || password.equals("")
				|| confpassword.equals("")) {
			Toast.makeText(MainActivity3.this, "Field are blank",
					Toast.LENGTH_SHORT).show();
		} 
		else 
		{
			if (password.equals(confpassword))
			{

				if (mobileno.length() < 10 || mobileno.length() >= 11) 
				{
					Toast.makeText(MainActivity3.this, "Mobile No Is Invalid",
							Toast.LENGTH_SHORT).show();

					/*
					 * Intent intent = new Intent(context,MainActivity3.class);
					 * startActivity(intent);
					 */
				}
				else
				{
					// Toast.makeText(MainActivity3.this,
					// "Registered Successfully",Toast.LENGTH_SHORT).show();

					FetchDataTask search = new FetchDataTask(this);
					search.execute("http://www.stage000.comyr.com/insert.php?name="+username+"&mobno="+mobileno+"&passwd="+password+"&confpasswd="+confpassword);
					Intent intent = new Intent().setClass(getApplicationContext(),Payment.class);
					startActivity(intent);

				}
			}
			else 
			{
				Toast.makeText(MainActivity3.this, "Invalid password",Toast.LENGTH_SHORT).show();
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
	public void onFetchFailure(String msg)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onFetchComplete() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onFetchComplete(String string) 
	{
		// TODO Auto-generated method stub
		if (string.equals("done"))
		{
			Toast.makeText(cc, "Registered Successfully", Toast.LENGTH_SHORT)
					.show();
			
		}

	}
}
