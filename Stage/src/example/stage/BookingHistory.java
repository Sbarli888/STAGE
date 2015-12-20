package example.stage;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BookingHistory extends Activity implements FetchDataListener {
	String mob, reterivedText;
	TextView text;

	String[] array1;
	String[] array2;
	
	

	// LinearLayout ll;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.booking_history);
		/*
		 * ll = new LinearLayout(this); ll =
		 * (LinearLayout)findViewById(R.id.ll);
		 */
		// LinearLayout.LayoutParams params = new
		// LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		// ll.setLayoutParams(params);
		// Changes the height and width to the specified *pixels*

		text = (TextView) findViewById(R.id.textView1);

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext(); 
		mob  = globalVariable.getMob();

		Toast.makeText(this, "Booking History ", Toast.LENGTH_SHORT).show();

		if (checkConnection() == false)
			Toast.makeText(this, "No Net", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "Checked InterNet", Toast.LENGTH_SHORT).show();

		getData();

	}

	public boolean checkConnection() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
		if (activeInfo == null)
			return false;
		else
			return true;
	}

	private void getData() {
		// TODO Auto-generated method stub
		FetchDataTask reterive = new FetchDataTask(this);
		reterive.execute("http://www.stage000.comyr.com/reterive.php?mobe="+mob);

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
		// Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

		try {
			reterivedText = string;
			text.setText(" ");

			array1 = reterivedText.split(";");
			for (int i = 0; i < array1.length; i++) {

				array2 = array1[i].split(",");

				text.setText(text.getText() + "Source: " + array2[0]);
				text.setText(text.getText() + "\n" + "Destination: "
						+ array2[1]);
				text.setText(text.getText() + "\n" + "Number of Ticket: "
						+ array2[2]);
				text.setText(text.getText() + "\n" + "Type: " + array2[3]);
				text.setText(text.getText() + "\n" + "Time: " + array2[4]);
				text.setText(text.getText() + "\n" + "Date: " + array2[5]);
				text.setText(text.getText() + "\n" + "Expire Time: "
						+ array2[6]);

				text.setText(text.getText() + "\n *********************** \n");

				//Toast.makeText(this, "for working", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {

		}
		Toast.makeText(this, "Booking History Activity", Toast.LENGTH_SHORT)
				.show();

	}
	
	@Override
	public void onBackPressed()
	{
		BookingHistory.this.finish();
	}
	
	
}
