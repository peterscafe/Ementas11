package Startup;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import Comunications.AdoConectionPhp;
import General.myDebug;
import Menus.menuFolder;


public class Startup {

	static String _ementasdb;
	//static JSONArray _mainFolders;
	static ArrayList<menuFolder> _menuFolders;


	public static void init(Context cont)
	{
		myDebug.mylog("To", cont);
		Log.d("teste",mainJSONFileExists(cont)+"");

	}

	public static String get_ementasdb(){
		return _ementasdb;
	}
	//public static JSONArray get_mainFolders(){
		//return _mainFolders;
	//}
	public static ArrayList<menuFolder> get_menuFolders(){return _menuFolders;}




	static boolean mainJSONFileExists(Context cont) {
		//Log.v("dir ext storage",Environment.getExternalStorageDirectory() + "");
		//Log.v("dir ext storage",Environment.getExternalStorageDirectory() + "");

		//Context c;
		//c.getApplicationContext().getFilesDir().toString()+"/database";
		//Log.v("app context folder",cont.getApplicationContext().getFilesDir().toString());
		if(mainDBFolderExists(cont)) {
			myDebug.mylog("Vai Ler o ficheiro", cont);
			File myfile = new File(cont.getApplicationContext().getFilesDir() + "/db/ementasdb.txt");
			myDebug.mylog("Vai verificar se existe", cont);

			//As vezes o Ficheiro é criado vazio
			if(myfile.exists() && myfile.length() > 0) {
				//myDebug.mylog(myfile.getTotalSpace()+" (Espaco)", cont);
				myDebug.mylog("Ficheiro DB Existe", cont);
				_ementasdb=General.myStringHelper.readFromFile(cont,myfile);
			}
			else {
				myDebug.mylog("Ficheiro DB Não Existe", cont);
				//Vai verificar conetividade net
				if(General.myInternetCon.InternetReady(cont)) {
					myDebug.mylog("Internet Ready", cont);
					String resultementa="";
					AdoConectionPhp ementa;
					ementa=new AdoConectionPhp("");
					resultementa=ementa.OpenEmentas();
					myDebug.mylog("Recebeu as Ementas", cont);
					CreateMainDBFile(myfile,resultementa,cont);
					_ementasdb=resultementa;
				}
				else myDebug.mylog("Internet Not Ready", cont);
			}
			_menuFolders=Menus.menuHelper.getMainFolders(_ementasdb);
			//_mainFolders=
		}

		return false;
	}


	static boolean mainDBFolderExists(Context cont) {
		File folder = new File(cont.getApplicationContext().getFilesDir() + "/db");
		boolean folderExists = true;


		if (!folder.exists()) {
			folderExists = folder.mkdir();
			myDebug.mylog("Cria Folder DB Result : " + folderExists, cont);
		}
		else myDebug.mylog("DB Folder Existe", cont);

		if(!folderExists) myDebug.mylog("Erro ao Criar Folder DB", cont);
		return folderExists;
	}

	static void CreateMainDBFile(File myfile,String data,Context cont) {

		// Save your stream, don't forget to flush() it before closing it.

		try
		{
			myfile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(myfile);
			OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
			myOutWriter.append(data);

			myOutWriter.close();

			fOut.flush();
			fOut.close();
			myDebug.mylog("Ficheiro Criado com Sucesso", cont);
		}
		catch (IOException e)
		{
			myDebug.mylog("Falha ao Criar Ficheiro " + e.toString(), cont);
			//Log.e("Exception", "File write failed: " + e.toString());
		}

	}
}
