package Comunications;


import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Clslista.Clsitem;
import Comunications.TouchTimer.OnTimeCompleted;






public class AdoConectionPhp  {

	
	JSONParser jParser = new JSONParser();
	JSONArray  recordjson;
	String Sucess;
	Boolean _erroconection=false;
	
	
	
	 //private static final String url_Proxy = "http://" +   +"/proxy.php";
	 private static String url_Proxy;
	 
	 
	// JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DATASET = "dataset";
    private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_EMENTAOCA="rootexport";
	private static final String YOUTUBE="items";
	private String path;
	private String _Playlistid="";
	private Boolean _youtubeserive;
	
	
	public AdoConectionPhp(String host,Boolean service,String path){
		_youtubeserive=false;
	    this.path=path; 	
		url_Proxy=host;
		//url_Proxy="http://79.125.9.6:81/ocaClientServiceBus.svc/rest/testing/";
		recordjson=null;	
	}
	
	
	public AdoConectionPhp(String host){
		_youtubeserive=false;
		url_Proxy="http://" + host  + "/proxy.php";
		
		//url_Proxy="http://79.125.9.6:81/ocaClientServiceBus.svc/rest/testing/";
		recordjson=null;
		Sucess="-1";
	}
	
	
	public AdoConectionPhp(Boolean YoutubeTitle,String PlaylistID){
		_youtubeserive=YoutubeTitle;
		_Playlistid=PlaylistID;
		
	}
	
	
	
	public String OpenEmentas(){
		
		Sucess="-1";
		
		new PhpProvider().execute("EmentasOca");
		
        while (Sucess=="-1" && _erroconection==false){
			
		}
        
        
        return Sucess;
	}
	
	

	
	public ArrayList<String> GetYoutubeTitles()  {
		
		Sucess="-1";
		recordjson=null;
		
		ArrayList<String> Titles;
		Titles=new ArrayList<String>();
		
		new PhpProvider().execute("youtubetitles");
		
        while (recordjson==null && _erroconection==false){
			
		}
        
        
        
        if (recordjson!=null){	
        	
        	
        	Titles=new ArrayList<String>();
        	
    		for (int i = 0; i < recordjson.length(); i++) {
                JSONObject c;
				try {
					
					c = recordjson.getJSONObject(i);
					c=c.getJSONObject("snippet");
				
				     Titles.add(c.getString("title"));
						 
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					return null;
				}

              
                // Storing each json item in variable
              
    			
                
    		}
    		
    		
    	 }
        else
        	Titles=null;
       
        
        return Titles;
             
	}


	
	public int Execute(String sql){
		
		TouchTimer timeout = null;
		
		_erroconection=false;
		
		
		if (timeout==null){
			timeout=new TouchTimer(5);
			
			timeout.Timeroff(new OnTimeCompleted() {
				
				@Override
				public void TimerCompleted() {
					// TODO Auto-generated method stub
					   _erroconection=true;			  
					}
			});
		
		}
		
		
		
		Sucess="-1";
		new PhpProvider().execute("execute",sql );
		
        while (Sucess.equalsIgnoreCase("-1") && _erroconection==false){
			
		}
		
		
		 //timeout.Close(); 
		 _erroconection=false;
		
		return Integer.parseInt(Sucess);
		
	}
	
	
	
	//@SuppressWarnings("null")
	public RecordsetPda Dataset(String Sql){
		
		Clsitem colunas=null;
		RecordsetPda record = null ;
		RecordsetPda.dados r;
		recordjson=null;
		TouchTimer timeout = null;
		
		try{
		
			
			
			_erroconection=false;
			if (timeout==null){
				timeout=new TouchTimer(5);
				
				timeout.Timeroff(new OnTimeCompleted() {
					
					@Override
					public void TimerCompleted() {
						// TODO Auto-generated method stub
						   _erroconection=true;			  
						}
				});
			
			}	
			
			
		record=new RecordsetPda();			
		new PhpProvider().execute("sql",Sql);
		
		
		while (recordjson==null && _erroconection==false){
			
		}
		
		timeout.Close();  
		
		try {   
			
		
	 if (recordjson!=null){	
		for (int i = 0; i < recordjson.length(); i++) {
            JSONObject c = recordjson.getJSONObject(i);

            colunas=new Clsitem();
            // Storing each json item in variable
           for(int j=0;j<c.length();j++ ){ 
                r=new RecordsetPda.dados();
			    r.fields = c.names().getString(j);
				r.values = c.getString(c.names().getString(j));
			
			colunas.Keyobject = r.fields; 
			colunas.Add(r);
			
           }
			
            record.Add(colunas);
            colunas=null;
		}
	 }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			record=null;
		}
		
		if (record!=null)  record.Movefirst();
		
		
		}
		 catch(Exception e) {}
		 
		return record;
		
		
		
	}
	
	
	
	
	
	
	class PhpProvider extends AsyncTask<String, String, String> {

		
		
		
		@Override
        protected void onPreExecute() {
			/*super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*/
		}
		
		
		
		@Override        
		 protected void onProgressUpdate(String... values) {
			
		}
		
		
		@Override
		protected String doInBackground( String... arg0) {
			// TODO Auto-generated method stub
		    //JSONObject json;	
			Sucess="-1";
			recordjson=null;
			final List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
		  try {
			
			if (arg0[0].equalsIgnoreCase("sql")){
				params.add(new BasicNameValuePair("type","GET"));
				
				params.add(new BasicNameValuePair("query",arg0[1]));
			    //json = jParser.makeHttpRequest(url_Proxy, "POST", params);
				
				Runnable runnable=new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						JSONObject json;
							try {
								
								json = jParser.makeHttpRequest(url_Proxy, "POST", params);
								recordjson = json.getJSONArray(TAG_DATASET);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					  }
				};
			    runnable.run();
		
				
				
				
				/*params.add(new BasicNameValuePair("type","GET"));
				params.add(new BasicNameValuePair("query",arg0[1]));
			    json = jParser.makeHttpRequest(url_Proxy, "POST", params);*/
			    
			    
             // Check your log cat for JSON reponse
            //Log.d("All Products: ", json.toString());
                // Checking for SUCCESS TAG
			   
					//int success = json.getInt(TAG_SUCCESS);		
					//if (success == 1) {
			    /*if (json!=null)
	                    recordjson = json.getJSONArray(TAG_DATASET);*/
	                   // Log.d("All Products: ", json.toString());
	                    
	                  //  }
				/*} catch (JSONException e) {
					// TODO Auto-generated catch block
					recordjson=null;
				}*/
			}    
			else if(arg0[0].equalsIgnoreCase("execute"))
			{
				params.add(new BasicNameValuePair("type","POST"));
				params.add(new BasicNameValuePair("query",arg0[1]));
			    //json = jParser.makeHttpRequest(url_Proxy, "POST", params);
			    
			    
				Runnable runnable=new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						JSONObject json;
						try {
						    //json = jParser.makeHttpRequest(url_Proxy, "POST", params);
							json = jParser.makeHttpRequest(url_Proxy, "POST", params);
						 
						    Sucess=json.getJSONArray(TAG_DATASET).getJSONObject(0).getString("success");
						    
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
			    runnable.run();
			    
			    
			    /*try {
			    	//Sucess= json.getInt("success");
			    	//recordjson = json.getJSONArray(TAG_DATASET);
			    	 //JSONObject c = recordjson.getJSONObject(1);
			    	   
			    	  Sucess=json.getString("success");
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.d("tag", e.getMessage());
					Sucess="1";
				}*/
				
			}
			else if(arg0[0].equalsIgnoreCase("youtubetitles")){
				
				params.add(new BasicNameValuePair("part","snippet"));
				params.add(new BasicNameValuePair("maxResults","50"));
				params.add(new BasicNameValuePair("playlistId",_Playlistid));
				params.add(new BasicNameValuePair("key","AIzaSyCryclnNIEUwDSg9BKSdqccVkQoe_tCvzw"));
				
				Runnable runnable=new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						JSONObject json;
						try {
							
							json=jParser.makeHttpRequest("https://www.googleapis.com/youtube/v3/playlistItems","GET",params);
							
						    //Sucess = jParser.HttpRequest("http://79.125.9.6:81/ocaClientServiceBus.svc/rest/export/ExportOcaStructure", "GET",params);
						   
						    recordjson=json.getJSONArray(YOUTUBE);
						  
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
			    runnable.run();
			    
				
			}
			else if (arg0[0].equalsIgnoreCase("EmentasOca")){
	
				params.add(new BasicNameValuePair("devuid","38E8A9A9-188E-4963-B1B1-316F4928158E"));
				params.add(new BasicNameValuePair("companyuid","9e4363b8-9d53-4c4c-9406-70a9d031041c"));
				params.add(new BasicNameValuePair("ocauid","70b8be87-3ba6-e411-9fb5-22000a53016d"));
				params.add(new BasicNameValuePair("sectionid","1"));
				params.add(new BasicNameValuePair("folderid","7"));
				
			Runnable runnable=new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						JSONObject json;
						try {
							
						    Sucess = jParser.HttpRequest("http://79.125.9.6:81/ocaClientServiceBus.svc/rest/export/ExportOcaStructure2", "GET",params);
						    //jParser.makeHttpRequest("","GET",params).
						   // recordjson=json.getJSONArray(YOUTUBE);
						  
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
			    runnable.run();
				
				
			}
            
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			recordjson=null;
		}
			
		  return null;
			
		}
		
		@Override
		protected void onPostExecute(String file_url) {
			//Log.d("teste", "" + file_url);
		}
	
		
	}
	
	
  /*class PhpProvider extends AsyncTask<String, String, String> {
		
		RecordsetPda.dados r;
        *//**
         * Before starting background thread Show Progress Dialog
         * *//*
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        *//**
         * getting All products from url
         * *//*
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            
            
            
            JSONObject json = jParser.makeHttpRequest(url_Proxy, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	JSONArray record = json.getJSONArray(TAG_DATASET);
                		
                    // looping through All Products
                    for (int i = 0; i < record.length(); i++) {
                        JSONObject c = record.getJSONObject(i);
 
                        // Storing each json item in variable
                         
                        r=new RecordsetPda.dados();
						r.fields = General.GetSubString(colums, ";", 0);
						r.values = General.GetSubStringRigth(colums, ";", 0);
						colunas.Keyobject = r.fields; 
						colunas.Add(r);
                        
						
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);
 
                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                   // Intent i = new Intent(getApplicationContext(),
                     //       NewProductActivity.class);
                    // Closing all previous activities
                   // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   // startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        *//**
         * After completing background task Dismiss the progress dialog
         * **//*
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    *//**
                     * Updating parsed JSON data into ListView
                     * *//*
                    ListAdapter adapter = new SimpleAdapter(
                            AllProductsActivity.this, productsList,
                            R.layout.list_item, new String[] { TAG_PID,
                                    TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
	*/
	
	
  
  }	
	

