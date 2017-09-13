package com.example.ementas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import Comunications.AdoConectionPhp;
import General.Globals;
import Menus.menuHelper;
import Startup.Startup;


public class MainActivity extends Activity {

	String resultementa="";
	
	AdoConectionPhp ementa;
	TextView textv;

	Globals g=Globals.getInstance();
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//textv=(TextView) findViewById(R.id.txt1);
		
		//ementa=new AdoConectionPhp("");
		//resultementa=ementa.OpenEmentas();


		//Log.v("teste","ola");
		Startup.init(this);

//		try {
			//StringBuffer myData=new StringBuffer();
			//ArrayList<menuFolder> mainFolders=Startup.get_menuFolders();

			//for (menuFolder f: mainFolders) {
				//myData.append(f.get_titulo("2") + " " + f.get_titulo("3")+ f.get_titulo("4") + " -> ");
			//}


//			for (int i=0;i<mainFolders.length();i++){
//				JSONObject finalObj=mainFolders.getJSONObject(i);
//				String folderName=finalObj.getString("@name");
//
//
//
//				myData.append(folderName + " ");
//			}
			//resultementa=myData.toString();

		ListView lvCountries=(ListView)findViewById(R.id.lvCountries);
		ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, menuHelper.GetFoldersForListView(Startup.get_menuFolders()));

		lvCountries.setAdapter(adapter);

//		}catch (JSONException e) {
//			myDebug.mylog(e.getMessage(), this);
//		}

		//resultementa=Startup.get_ementasdb();

//		JSONArray j=new JSONArray(resultementa);
//		JsonFactory d;



//		JSONParser jParser = new JSONParser();
//		JSONArray  recordjson;
//
//		JSONArray record = json.getJSONArray(resultementa);





		//textv.setText(resultementa);

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
		switch (item.getItemId()){
			case R.id.setlanguage1:
				g.set_Language("1");
				break;
			case R.id.setlanguage2:
				g.set_Language("2");
				break;
			case R.id.setlanguage3:
				g.set_Language("3");
				break;
			case R.id.setlanguage4:
				g.set_Language("4");
				break;
			case R.id.setlanguage5:
				g.set_Language("5");
				break;
			case R.id.setlanguage6:
				g.set_Language("6");
				break;
		}

		ListView lvCountries=(ListView)findViewById(R.id.lvCountries);
		ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, menuHelper.GetFoldersForListView(Startup.get_menuFolders()));

		lvCountries.setAdapter(adapter);

		//

//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}


}
