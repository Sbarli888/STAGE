package example.stage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class qrDisplay extends Activity implements FetchDataListener
{
	String mob;
	String tickqr = "";
	String[] array1;
	String[] array2;
	int loc;
	
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qr_generated);
		
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext(); 
		mob  = globalVariable.getMob();
		
		FetchDataTask reteriver = new FetchDataTask(this);
		reteriver.execute("http://www.stage000.comyr.com/reterive.php?mobe="+mob);
		
		//Intent intent = getIntent();
		//final String tickqr = intent.getStringExtra("ticke");
		
		
		
		//qrgenerator(tickqr);

	}

	private void qrgenerator(String tick2)
	{
		// TODO Auto-generated method stub
		// Log.v(LOG_TAG, qrInputText);

		// Find screen size
		WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		int width = point.x;
		int height = point.y;
		int smallerDimension = width < height ? width : height;
		smallerDimension = smallerDimension * 3 / 4;

		// Encode with a QR Code image
		QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(tick2, null,
				Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(),
				smallerDimension);
		try 
		{
			Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
			ImageView myImage = (ImageView) findViewById(R.id.imageView1);
			myImage.setImageBitmap(bitmap);

		} 
		catch (WriterException e) 
		{
			e.printStackTrace();
		}

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
		//Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
		try
		{
			tickqr = string;
		// TODO Auto-generated method stub
			array1 = string.split(";");
			loc = (array1.length)-1;
			tickqr = array1[loc].toString();
			//Toast.makeText(this, tickqr, Toast.LENGTH_SHORT).show();

			//array2 = array1[loc].split(",");
			
			//for(int i = 0;i<array2.length;i++)
			//{
			//	tickqr = tickqr.concat(array2[i]);
			//}
		}
		catch(Exception e)
		{
			
		}
		qrgenerator(tickqr); 
	}

	
	
}
