package General;

/**
 * Created by pedrobernardo on 13/09/2017.
 */

public class Globals {
    private static Globals instance;

    private static String currentLanguage="1";

    private Globals(){};

    public void set_Language(String value){
        Globals.currentLanguage=value;
    }
    public String  get_Language(){
        return Globals.currentLanguage;
    }

    public static synchronized Globals getInstance(){
        if(instance==null) instance=new Globals();
        return instance;
    }
}
