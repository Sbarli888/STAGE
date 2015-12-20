package example.stage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class balanceUpload extends Activity implements FetchDataListener

{
	private TextView balanceField;
	//private Button btn;
	
	Intent inte;
	
	String mob,faire,bal;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.balance_uploade);
		
		balanceField = (TextView) findViewById(R.id.textView2);
		//btn = (Button)findViewById(R.id.button1);
		
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext(); 
		mob  = globalVariable.getMob();
		
		Intent intent = getIntent();
		
		faire = intent.getStringExtra("fair");
		bal = intent.getStringExtra("balance");
		
		
		Toast.makeText(this, bal,Toast.LENGTH_SHORT).show();

		
		balanceField.setText(faire);
		
		FetchDataTask upload = new FetchDataTask(this);
		upload.execute("http://www.stage000.comyr.com/bananceupl.php?mobu="+mob+"&blance="+bal);
		
		//FetchDataTask balupl = new FetchDataTask(this);
		//balupl.execute("http://www.stage000.comyr.com/bananceupl.php?mobu="+mob+"&blance="+bal); 
		
	}
	
	public void backe(View view)
	{
		//Toast.makeText(balanceUpload.this, "Uploaded",Toast.LENGTH_SHORT).show();
		inte = new Intent().setClass(getApplicationContext(),MainAppScreen.class);
		
		startActivity(inte);
			
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
	public void onFetchComplete(String string) {
		
		// TODO Auto-generated method stub
		if(string.equals("done"))
		{
			Toast.makeText(balanceUpload.this, "Uploaded",Toast.LENGTH_SHORT).show();
		}	
		
	}
	
}
