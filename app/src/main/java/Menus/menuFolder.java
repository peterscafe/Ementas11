package Menus;

import java.util.HashMap;
import java.util.Map;

public class menuFolder {
	private int _numero;
	private String _nome,_tipoLista,_iconCategoria,_logoTopo,_labelPreco1,_labelPreco2,_labelPreco3;

	Map<String, String> _titulos = new HashMap();
	boolean _listaArtigos,_idiomaUnico,_infoIva;

	public menuFolder(){
		this._numero=0;
		this._nome="";
		this._tipoLista="detalhes";
		this._iconCategoria="";
		this._logoTopo="";
		this._labelPreco1="";
		this._labelPreco2="";
		this._labelPreco3="";
		this._listaArtigos=true;
		this._idiomaUnico=false;
		this._infoIva=false;
	}

	public int get_numero(){
		return _numero;
	}
	public void set_numero(int value){
		_numero=value;
	}

	public String get_nome(){
		return _nome;
	}
	public void set_nome(String value){
		_nome=value;
	}

	public String get_tipoLista(){
		return _tipoLista;
	}
	public void set_tipoLista(String value){
		_tipoLista=value;
	}

	public String get_iconCat(){
		return _iconCategoria;
	}
	public void set_iconCat(String value){
		_iconCategoria=value;
	}

	public String get_logoTopo(){
		return _logoTopo;
	}
	public void set_logoTopo(String value){
		_logoTopo=value;
	}

	public String get_labelPreco1(){
		return _labelPreco1;
	}
	public void set_labelPreco1(String value){
		_labelPreco1=value;
	}

	public String get_labelPreco2(){
		return _labelPreco2;
	}
	public void set_labelPreco2(String value){
		_labelPreco2=value;
	}

	public String get_labelPreco3(){
		return _labelPreco3;
	}
	public void set_labelPreco3(String value){
		_labelPreco3=value;
	}

	public Boolean get_listaArtigos(){
		return _listaArtigos;
	}
	public void set_listaArtigos(Boolean value){
		_listaArtigos=value;
	}

	public Boolean get_idiomaUnico(){
		return _idiomaUnico;
	}
	public void set_idiomaUnico(Boolean value){
		_idiomaUnico=value;
	}

	public Boolean get_infoIva(){
		return _infoIva;
	}
	public void set_infoIva(Boolean value){
		_infoIva=value;
	}

	public Map<String, String> get_titulos() {return _titulos;}
	public void set_titulos(String lang,String value){
		_titulos.put(lang, value);
	}

	public String get_titulo(String lang){
		return _titulos.get(lang);
	}
}
