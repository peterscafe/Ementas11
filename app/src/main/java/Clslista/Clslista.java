package Clslista;

import java.util.Enumeration;
import java.util.Hashtable;

public class Clslista 
{
	Hashtable<Object, Clsitem> proximo;
	int linha;
	int linhaindex;
	public Object chave;
	Hashtable<Integer, Object> Indices;

	
	
	public Clslista()
	{
		linha = 0;
		linhaindex = 0;
		proximo = new Hashtable<Object, Clsitem>();
		Indices = new Hashtable<Integer, Object>();
		chave=null;		
	}

	public int RowCount(){
		if(proximo != null)
			return proximo.size();
		else
			return 0;
	}

	public int ColCount(Object index)
	{
		Object pos;

		pos = Indices.get(index);
		Clsitem col = (Clsitem) proximo.get(pos);
		return col.count();
	}

	public void Clear()
	{
		if(proximo != null)
		{
			proximo.clear();
			Indices.clear();
		}

		linha = 0;
		linhaindex = 0;
	}

	public void AddRow(Object... args)
	{
		Clsitem subitem = new Clsitem();

		for (Object i : args) 
		{
			subitem.Add(i);
		}
		
		linha ++;
		linhaindex++;
		if(chave == null)
		{
			proximo.put(linhaindex, subitem);
			Indices.put(linha, linhaindex);
		}
		else
		{
			proximo.put(chave, subitem);
			Indices.put(linha, chave);
		}
	}
	
	
	

	public Clsitem GetRow(Object index)
	{
		
		//index.toString().charAt(0);
		
		
		try
		{
			
				return (Clsitem)proximo.get(index);
				
		}
		catch(Exception e)
		{
			return null;
		}
	}

	
	
	public void PutValue(int linhaTemp, int coluna, Object Context)
	{
		Clsitem subaux = new Clsitem();

		Object pos;

		pos = Indices.get(linhaTemp);

		subaux = (Clsitem) proximo.get(pos);
		proximo.remove(pos);

		subaux.PutsubItem(coluna, Context);
		proximo.put(pos, subaux);
		
		subaux = null;
	}
	
	public void RemoveRow(Object index)
	{
		
		Clsitem subaux;
		int j = 1;
		
		Object pos=null;
		
		linha--;
        
		
		
//		if (General.IsDigit(index.toString()))  
//		{
//			
//			pos=Indices.get(index);
//		
//			subaux = (Clsitem) proximo.get(pos);
//			subaux.Clear();
//			proximo.remove(pos);
//		}
		//else
		//{
			subaux = (Clsitem)proximo.get(index);
			subaux.Clear();
			proximo.remove(index);
		//}
		
		
		Indices.clear();
		Enumeration <Object> key = proximo.keys(); 
		j = proximo.size();
		
		while(key.hasMoreElements()){
			Indices.put(j, key.nextElement());
			j--;
		}
		
		
	}
	
	
	
	public Object GetValue(int linha, int coluna)
	{
		
		Clsitem subaux;
		Object pos;
		
		pos = Indices.get(linha);
		subaux = (Clsitem)proximo.get(pos);
		
		return subaux.get_column(coluna);
		
	}
	
	
	public Hashtable<Object, Clsitem> GetCollection()
	{
		return proximo;
	}
	
	

}


