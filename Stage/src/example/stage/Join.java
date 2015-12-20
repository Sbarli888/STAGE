package example.stage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Join extends Activity
{
	Button button;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);
		button = (Button) findViewById(R.id.button1);
		

		button.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View argo) 
			{

				Intent intent = new Intent(getApplicationContext(), MainAppScreen.class);
				
				startActivity(intent);
				Join.this.finish();

			}
		});

	}

}
