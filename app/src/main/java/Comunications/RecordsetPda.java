package Comunications;

import Clslista.Clsitem;
import Clslista.Clslista;


public class RecordsetPda {

	Clslista lista;
	int indice;
	
	
	public static   class dados{
		public String fields;
		public  String values;
		public String type;
	}
	
	
	
	public RecordsetPda()
	{
		lista = new Clslista();
		indice = 0;
	}
	
	public void Close()
	{
		indice = 0;
		lista = null;
	}
	
	public void Movefirst()
	{
		indice = 1;
	}
	
	public int RecordCountPda()
	{
		if (lista==null)
			return 0;
		else
			return lista.RowCount();
	}
	
	public void MoveLast()
	{
		indice = RecordCountPda();
	}
	
	public boolean Eof()
	{
		boolean res = false;
		if((lista == null) || (indice == 0))
			res = false;
		else if (indice <= lista.RowCount())
			res = true;
		
		return res;
	}
	
	public void Movenext()
	{
		indice ++;
	}
	
	
	
	public String Fields (String field)
	{
		Clsitem row;
		Clsitem obj;
//		AdoConection.dados f;
		dados f;
		
		try
		{
			row = lista.GetRow(indice);
			obj = (Clsitem) row.Get_item(1);
			f = (dados) obj.Get_item(field.toString().trim());
			return f.values;
		}
		catch(Exception e)
		{
			return "Campo " + field + " � Invalido";
		}
		
	}
	
	
	public Clsitem Columns(){
		Clsitem row;
		Clsitem obj;
		
		
		try
		{
			row = lista.GetRow(indice);
			obj = (Clsitem) row.Get_item(1);
			return obj ;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	
	
	public String Type (String field)
	{
		Clsitem row;
		Clsitem obj;
//		AdoConection.dados f;
		dados f;
		
		try
		{
			row = lista.GetRow(indice);
			obj = (Clsitem) row.Get_item(1);
			f = (dados) obj.Get_item(field.toString().trim());
			return f.type ;
		}
		catch(Exception e)
		{
			return "Campo " + field + " � Invalido";
		}
		
	}
	
	
	
	
	public void Add(Object rr)
	{
		lista.AddRow(rr);
		indice ++;
	}
	
	
	
	
	
}
