package Comunications;




import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import Clslista.Clslista;
import android.util.Log;



public class CallSoap 
{
public  String SOAP_ACTION = "http://tempuri.org";
 
public String OPERATION_NAME="";

public  String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

//public  final String SOAP_ADDRESS = "http://grasshoppernetwork.com/NewFile.asmx";

//public  String SOAP_ADDRESS = "http://79.125.9.6:82/ServiceMXT.asmx";
public  String SOAP_ADDRESS ;

//public CallSoap() 
//{ 
//}


public SoapObject Call(Clslista parametros)
{

	
 SOAP_ACTION=SOAP_ACTION +"/"+ OPERATION_NAME; 	
	
 SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

for(int i=1;i<=parametros.RowCount();i++){
     
    request.addProperty(parametros.GetValue(i, 1).toString(),parametros.GetValue(i, 2));
    
    
}

      
SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
SoapEnvelope.VER11);

envelope.dotNet = true;

//envelope.implicitTypes=false;

envelope.setOutputSoapObject(request);


HttpTransportSE httpTransport = new HttpTransportSE(this.SOAP_ADDRESS);
SoapObject response=null;
try
{
httpTransport.call(SOAP_ACTION, envelope);
//response = (SoapObject) envelope.getResponse();
response = (SoapObject) envelope.bodyIn;

}
catch (Exception exception)
{
  Log.d("tag", exception.toString());	
  response=null;
  
}
  return response;
}
}


