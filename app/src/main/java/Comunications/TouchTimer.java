package Comunications;






public class TouchTimer{

	int _touchnumber=0;
	int _tempo=0;
	int _tempoaux=0;
	int _clicknumber=0;
	Thread _thread=null;
	int _timeroff;
	long timeInMilliseconds=0l;
	
	
	OnTimeCompleted _ontimecompleted;
	OnTouchCompleted _ontouchcompleted;
	
	
	
	
	public void Close(){
		
		if (_thread!=null)
		 if (_thread.isAlive())
			_thread.interrupt();
		
		
		_thread=null;
		_tempoaux=0;
		_timeroff=0;
	}
	
	
	
	
	/*public TouchTimer(boolean countdown,int timeroff){
		final Boolean mrun=true;
		final long timerstart=SystemClock.uptimeMillis();
		
		 _timeroff=timeroff*1000;
		 
		 
		 
		 _thread=new Thread(){
			  @Override
		        public void run() {
				  try {
					  
					  while(mrun){
					   timeInMilliseconds = SystemClock.uptimeMillis()-timerstart;
					   
					  }
				  }
			  	   catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			 };
	}*/
	
	
	public TouchTimer(int touchnumber,int tempo){
		
	  _touchnumber=touchnumber;
	  _tempo=tempo;	
	  _tempo=_tempo*1000;	
      _tempoaux=_tempo;
	  
	}
	
	
	
	public TouchTimer(int timeroff){
			
		  _timeroff=timeroff*1000;
		  
		  _thread=new Thread(){
			  @Override
		        public void run() {
				  try {
					sleep(_timeroff);
					tempocompleto();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		  };
		  _thread.start();
		  
		}
	
	
	
	void tempocompleto(){
		
		if(_timeroff!=0 ){
		 _timeroff=0;	
		 _ontimecompleted.TimerCompleted();
		}

		_clicknumber=0;
		Close();
		
		
	}
	
	
    public void SetTouchClick(){
    	
    	_clicknumber++;
    	
    	if (_tempo!=0 && _clicknumber==1){
    		_tempoaux=_tempo;
    		if (_thread==null) {
    			_thread=new Thread(){
    		        @Override
    		        public void run() {
    		            // do something in a new thread if 'called' by super.start()
    		        	try {
							sleep(_tempoaux);
							tempocompleto();
						
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							_tempoaux=0;
						}
    		        	_tempoaux=0;
    		        }
    		    };
    		    _thread.start();
    		    
    		}
    			
    	 }
    	
    	if (_touchnumber==_clicknumber){
    		this._ontouchcompleted.TouchCompleted();
    		_clicknumber=0;
    		_tempoaux=0;
    		_tempoaux=0;
    		_thread=null;	
    		
    	}
    	
    }
	
	
    
	public void Timeroff(OnTimeCompleted ontimercompleted){
		this._ontimecompleted=ontimercompleted;
	}
	
	
	public interface OnTimeCompleted{
		public void TimerCompleted();
	}
	
	

	
	public void TouchCompleted(OnTouchCompleted ontouchcompleted){
	   this._ontouchcompleted=ontouchcompleted;	
	}
	
	public interface OnTouchCompleted{
		public void TouchCompleted();
	}
	
	
	
	
	
	
	
}
