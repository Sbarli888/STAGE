package example.stage;

import example.stage.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class BookTicket extends Activity implements FetchDataListener
{

	// private EditText destination;
	private EditText noofticket;
	private TextView source;
	private Spinner spinner1;
	private RadioGroup ticketType;
	private RadioButton type;

	Context cc;
	Button button1;
	String src, dest, notkt, ttype;
	String mob, cur, exp, date, bala, tktfair;
	String tick;

	int su = 0, de = 0, ded = 0, bal = 0, tkt;
	String getBalance;

	String[] station = { "ChengalpattuJunction", "Paranur", "SingaperumalKoil",
			"MaraimalaiNagar", "Kattangulattur", "Potheri", "Guduvancheri",
			"Urappakkam", "Vandalur", "Perungulatur", "Tambaram",
			"TambaramSanatorium", "Chrompet", "Pallavaram", "Tirusulam",
			"Minambakkam", "Palavanthangal", "StThomasMount", "Guindy",
			"Saidapet", "Mambalam", "Kodambakkam", "Nungambakkam",
			"ChennaiChetpet", "ChennaiEgmore", "ChennaiPark", "ChennaiFort",
			"ChennaiBeach" };

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_ticket);
		cc = this;
		button1 = (Button) findViewById(R.id.button1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		noofticket = (EditText) findViewById(R.id.editText3);
		source = (TextView) findViewById(R.id.textView2);
		ticketType = (RadioGroup) findViewById(R.id.TicketType);

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		mob = globalVariable.getMob();

		Intent intent = getIntent();
		getBalance = intent.getStringExtra("bal");
		bal = Integer.parseInt(getBalance.trim());

		// spinner1 = (Spinner) findViewById(R.id.spinner1);

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

	public void qrscan(View view)
	{
		Intent intent = new Intent().setClass(getApplicationContext(),
				QRScanner.class);
		startActivityForResult(intent, 2);

	}

	public void onActivityResult(int rcode, int rescode, Intent in)
	{
		// super.onActivityResult(rcode, rescode, in);
		if (rcode == 2) 
		{
			String msg = in.getStringExtra("location");
			source.setText(msg);

		}
	}

	public void booktkt(View view)
	{
		dest = spinner1.getSelectedItem().toString();
		src = source.getText().toString();
		notkt = noofticket.getEditableText().toString();

		int selectedId = ticketType.getCheckedRadioButtonId();
		type = (RadioButton) findViewById(selectedId);

		ttype = type.getText().toString().trim();

		// src = "tambaram";

		if (src.equals("") || notkt.equals(""))
		{
			Toast.makeText(BookTicket.this, "Fields are blank",
					Toast.LENGTH_SHORT).show();
		} 
		else if (notkt.length() >= 2) 
		{
			Toast.makeText(BookTicket.this, "Book Upto 9 tickets ",
					Toast.LENGTH_SHORT).show();
		} 
		else if(src.equals(dest))
		{
			Toast.makeText(BookTicket.this, "Choose different Destination",Toast.LENGTH_SHORT).show();
		}
		else 
		{
			for (int i = 0; i < station.length; i++)
			{
				if (src.equals(station[i]))
				{
					su = i;
				}
			}
			for (int i = 0; i < station.length; i++)
			{
				if (dest.equals(station[i]))
				{
					de = i;
				}
			}

			ded = su - de;
			if (ded < 0)
				ded *= -1;

			// bal = Integer.parseInt(getBalance);
			tkt = Integer.parseInt(notkt.trim());
			if (ded <= 11) 
			{
				if(ttype.equalsIgnoreCase("Single"))
				{
					tkt = tkt * 5;
					bal = bal - tkt;
				}
				else
				{
					tkt = (tkt * 5)*2;
					bal = bal - tkt;
				}
			} 
			else
			{
				if(ttype.equalsIgnoreCase("Single"))
				{
					tkt = tkt * 10;
					bal = bal - tkt;
				}
				else
				{
					tkt = (tkt * 10)*2;
					bal = bal - tkt;
				}
			}
		

			bala = Integer.toString(bal);
			tktfair = Integer.toString(tkt);

			Time curTime = new Time(Time.getCurrentTimezone());
			curTime.setToNow();
			cur = curTime.format("%k:%M:%S").toString();
			
			Time expTime = new Time(Time.getCurrentTimezone());
			expTime.setToNow();
			if(ttype.equals("Single"))
			{	
				expTime.hour += 1;
				exp = expTime.format("%k:%M:%S").toString();
			}
			else
				exp = "Midnight";
			
			date = (curTime.monthDay + "/" + curTime.month + "/" + curTime.year).toString();
			
		// Boolean has=false;
			tick = src;
			tick = tick.concat(",");
			tick = tick.concat(dest);
			tick = tick.concat(",");
			tick = tick.concat(notkt);
			tick = tick.concat(",").concat(ttype).concat(",");
			tick = tick.concat(cur).concat(",").concat(date).concat(",")
					.concat(exp);
			tick = tick.concat(";");
			tick.toString();
		// StringBuilder tick = new StringBuilder();
		// tick.append(src).append(":").append(dest).append(":").append(notkt).append(";");
			tick = tick.toString().trim().replaceAll("\\s+", "");

			FetchDataTask booking = new FetchDataTask(this);
			booking.execute("http://www.stage000.comyr.com/ticket.php?mobu=" + mob+"&bookin=" + tick);

			Toast.makeText(cc, tick, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent().setClass(getApplicationContext(),
					balanceUpload.class);

			intent.putExtra("fair", tktfair);
			intent.putExtra("balance", bala);
			
			startActivity(intent);

			BookTicket.this.finish();
		}	

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

		if (string.equals("done"))
		{
			Toast.makeText(cc, "Ticket Booked", Toast.LENGTH_SHORT).show();

		}

	}

}
