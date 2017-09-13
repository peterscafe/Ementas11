package Clslista;

import java.util.Hashtable;

import General.General;


public class Clsitem {

	public int coluna=1;
	public Object Keyobject=null;
	
	Hashtable<Object, Object> subitem=new Hashtable<Object, Object>();
	@SuppressWarnings("rawtypes")
	Hashtable Indice=new Hashtable();


	@SuppressWarnings("unchecked")
	public void Add(Object ...args)
	{

		for (Object i : args) 
		{
			if(Keyobject==null)
			{
				subitem.put(coluna,i);
				Indice.put(coluna, coluna);

			}
			else
			{
				subitem.put(Keyobject, i);
				Indice.put(coluna, Keyobject);
			}
			coluna++;
		}

	}


	public void PutsubItem(int Index, Object Context)
	{
		

		subitem.put(Index, Context);  
		

	}

	public Object get_column(Object Index)
	{
		return subitem.get(Index);
	}
	
	public int count()
	{
		return subitem.size();
	}


	public Object Get_item(Object index)
	{
		Object aux;

		try{
			if(!General.IsDigit(index.toString()))
			{
				return subitem.get(index.toString());
			}
			else
			{
				
				aux =  Indice.get(Integer.parseInt(index.toString()));
						
				if(!General.IsDigit(aux.toString()))
				{
					return subitem.get(aux.toString());
				}
				else
					return subitem.get(Integer.parseInt(aux.toString()));

			}
		}catch (NullPointerException e)
		{
			return null;
		}


	}

	public void Remove(Object index)
	{
		try
		{
			String aux;
			if(!(General.IsDigit(index.toString())))
			{
				subitem.remove(index);	
			}
			else
			{
				aux = Indice.get(index).toString();
				subitem.remove(Integer.parseInt(aux));
				Indice.remove(index);
			}
		}
		catch(Exception e){ 
			
		}
	}
	
	
	public void Clear()
	{
		subitem.clear();
	}




}
