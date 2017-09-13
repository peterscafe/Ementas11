package Comunications;


import org.ksoap2.serialization.SoapObject;

import Clslista.Clslista;


public class Caller  extends Thread  
{
    public CallSoap cs;
    Clslista param; 
   
    
 
    OnResultservice response;
	public String OPERATION_NAME="";
    String SOAP_ADDRESS; 
    
    public Caller(Clslista parametros,OnResultservice resp){
    	this.response=resp;
    	this.param=parametros;
         	
    }
    
    
    
    public void run(){
        try{
        	
        	
        	//if (super.isAlive()) super.interrupt();
        	
        	
            cs=new CallSoap();
            cs.OPERATION_NAME=OPERATION_NAME ;          
            cs.SOAP_ADDRESS=this.SOAP_ADDRESS;
            
            SoapObject resp=cs.Call(this.param);
            
            this.response.onresultservice(resp);
            
            
        }catch(Exception ex)
        {
        	this.response.onresultservice(null);
        	}    
    }
    
    
    public interface OnResultservice{
    	public void onresultservice(SoapObject response);
    }
    
    
    
}

