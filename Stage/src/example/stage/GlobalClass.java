package example.stage;

import android.app.Application;

public class GlobalClass extends Application
{

	private String mob;
	
	public String getMob() {
        
        return mob;
    } 
      
    public void setMob(String aMob) {
         
       mob = aMob;
          
    } 
}
