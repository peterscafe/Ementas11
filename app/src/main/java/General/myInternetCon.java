package General;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class myInternetCon {

	public static boolean InternetReady(Context con){
		//return (isNetworkAvailable(con) && hostAvailable("",5000));
		return (isNetworkAvailable(con));
	}

	private static boolean isNetworkAvailable(Context con) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	//Tens q fazer como
	//https://stackoverflow.com/questions/15496278/httpurlconnection-is-throwing-exception
//	private static boolean hostAvailable(String url, int timeout) {
//		try{
//			URL myUrl = new URL("https://spacergif.org/spacer.gif");
//			URLConnection connection = myUrl.openConnection();
//			connection.setConnectTimeout(timeout);
//			connection.connect();
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
}
