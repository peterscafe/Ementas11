package General;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ementas.R;

public class myDebug {

	public static void mylog(String s,Context cont)
	{
		if(cont.getString(R.string.debugMode).equalsIgnoreCase("1")) {
			Toast.makeText(cont,s,Toast.LENGTH_LONG).show();
			Log.v("MYLOG",s);
		}

		//Log.d("teste",mainJSONFileExists(cont)+"");

	}

}
