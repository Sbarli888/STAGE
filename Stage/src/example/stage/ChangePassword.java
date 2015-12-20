package example.stage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity implements FetchDataListener
{
	
	EditText passwd;
	String mob,pasword;
	Context cc;
	
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password);
		passwd = (EditText)findViewById(R.id.editText1);
		
		
		
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext(); 
		mob  = globalVariable.getMob();
		
		cc = this;
		
	}

	public void chang(View view)
	{
		pasword = passwd.getEditableText().toString();
		
		Toast.makeText(cc, pasword, Toast.LENGTH_SHORT).show();
		FetchDataTask changer = new FetchDataTask(this);
		changer.execute("http://www.stage000.comyr.com/change.php?mobu="+mob+"&pwd="+pasword); 
		
	
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
		Toast.makeText(cc, "Password Updated", Toast.LENGTH_SHORT).show();
		
		ChangePassword.this.finish();
		
	}
}
