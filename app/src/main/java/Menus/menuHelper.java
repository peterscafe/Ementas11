package Menus;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


import General.Globals;

public class menuHelper {
	public static ArrayList<menuFolder> getMainFolders(String jsondata)
	{
		ArrayList<menuFolder> colFolders=new ArrayList<menuFolder>();

		try{
			JSONObject jObj=new JSONObject(jsondata);
			JSONArray jFolders=jObj.getJSONObject("rootexport").getJSONArray("folder");
			for (int i=0;i<jFolders.length();i++){
				colFolders.add(i,bindFolderFromJSONObj(jFolders.getJSONObject(i)));
			}
			//colJson=pObject.get("rootexport")("folder");
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return colFolders;
	}

	public static menuFolder bindFolderFromJSONObj(JSONObject jf) {
		Menus.menuFolder m=new menuFolder();
		try {
			m.set_numero(jf.getInt("@number"));
		}catch (JSONException e) {
			Log.d("JSON",e.getMessage());
			m.set_numero(0);
		}
		try {
			m.set_nome(jf.getString("@name"));
		}catch (JSONException e) {
			Log.d("JSON",e.getMessage());
			m.set_nome("");
		}
		//BIND folder Properties
		try {
			JSONArray fProps=jf.getJSONObject("properties").getJSONArray("property");
			for (int i=0;i<fProps.length();i++){
				JSONObject fProp=fProps.getJSONObject(i);
				String propKey=fProp.getString("@key");
				if(propKey.equals("property_titulo")) {
					JSONArray jTraducoes=fProp.getJSONArray("value");
					for (int j=0;j<jTraducoes.length();j++){
						JSONObject jTrad=jTraducoes.getJSONObject(j);
						if(jTrad.has("#text")) m.set_titulos(jTrad.getString("@language"),jTrad.getString("#text"));
					}

				} else if (propKey.equals("property_lista_artigos")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_listaArtigos(fProp.getJSONObject("value").getBoolean("#text"));
				} else if (propKey.equals("property_tipo_de_lista")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_tipoLista(fProp.getJSONObject("value").getString("#text"));
				} else if (propKey.equals("property_icon_categoria_1")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_iconCat(fProp.getJSONObject("value").getString("#text"));
				} else if (propKey.equals("property_logo_topo")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_logoTopo(fProp.getJSONObject("value").getString("#text"));
				} else if (propKey.equals("property_idioma_unico")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_idiomaUnico(fProp.getJSONObject("value").getBoolean("#text"));
				}else if (propKey.equals("property_infoiva")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_infoIva(fProp.getJSONObject("value").getBoolean("#text"));
				}else if (propKey.equals("property_label_preco_1")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_labelPreco1(fProp.getJSONObject("value").getString("#text"));
				}else if (propKey.equals("property_label_preco_2")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_labelPreco2(fProp.getJSONObject("value").getString("#text"));
				}else if (propKey.equals("property_label_preco_3")) {
					if(fProp.getJSONObject("value").has("#text")) m.set_labelPreco3(fProp.getJSONObject("value").getString("#text"));
				}


			}
		}catch (JSONException e) {
			Log.d("JSON",e.getMessage());
		}
		return m;
	}

	public static String[] GetFoldersForListView(ArrayList<menuFolder> menuFolders){
		//Saca as Var Globais
		Globals g=Globals.getInstance();
		int aux=0;
		String[] folders=new String[menuFolders.size()];

		for (menuFolder f: menuFolders) {
			//if(g.get_Language().equals("1")) folders[aux]=f.get_nome();
			//else
			folders[aux]=f.get_titulo(g.get_Language());
			aux++;
		}
		return folders;
	}
}
