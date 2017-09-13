package General;





public class General {

	public static long idutilizador = -1;
	Boolean Bnlok=false;
	public static String Nmaquina="-1";
	public static String Ncaixa="-1";


	public  Boolean Asquestion_Msgbox(String Msg)
	{

		return Bnlok;

	}



	@SuppressWarnings("deprecation")
	public static void MessageBox(String Msg)
	{


	}




	public static int GetSubStringCount(String str, String Separador, int index)
    {
        int instart = 0;
        int count = 0;

        if (index < 0)
            return 0;
        else
        {
            instart = 1;
            for (int i = 0; i <= (index - 1); i++)
            {
                instart = str.indexOf(Separador, instart);
                if (instart <= 0)
                    return count;
                else
                    count++;
            }
            return count;
        }

    }
	

	
	
	public static String GetSubStringRigth( String str1,String str2,int index)
	{
		int pos=0;
		pos=str1.indexOf(str2, index);
		if (pos!=-1)
			return str1.substring( (pos+str2.length()), ((str1.length()) ) );
		else
			return "";
	}

	public static String GetSubString (String str, String Separador, int index)
	{
		int intStart = 0, intEnd = 0;
		if(index < 0)
			return "";
		else
		{
			intEnd = str.indexOf(Separador, intStart);
			if(intEnd == -1)
				return "";
			else
				return str.toString().substring(intStart, intEnd);
		}
	}




	public static boolean IsDigit(String str)
	{
		boolean res = false;

		for (int i = 0; i < str.length(); i++) 
		{
			if((!Character.isDigit(str.charAt(i))) && str.charAt(i) != '.')
			{
				res = false;
				break;
			}
			else
				res = true;
		}
		return res;

	}




}
