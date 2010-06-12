package sg.ruqqq.ActivityThumbnailPrototype;

import static android.util.Log.d;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	public static String LOG_TAG = "ActivityThumbnailPrototype";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ActivityManager am = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        // Faruq: Get 6 running tasks
        List<ActivityManager.RunningTaskInfo> lru = am.getRunningTasks(6);
        
        for (ActivityManager.RunningTaskInfo rti : lru) {
        	// Faruq: This will print out the last 6 tasks Class Names
        	d(LOG_TAG, "LRU: "+rti.topActivity.getClassName());
        	
        	// Faruq: The code that requires magic modifications
        	Bitmap b = rti.thumbnail;
        	if (b != null) {
        		d(LOG_TAG, "	Bitmap found: "+b);
        		// Faruq: Create ImageView object and set the content to the bitmap
	        	ImageView iv = new ImageView(this);
	        	iv.setImageBitmap(b);
	        	
	        	// Faruq: Add the iv to the Layout we have
	        	((LinearLayout) findViewById(R.id.llImage_test)).addView(iv);
        	} else {
        		d(LOG_TAG, "	Bitmap is null");
        	}
        }
    }
}