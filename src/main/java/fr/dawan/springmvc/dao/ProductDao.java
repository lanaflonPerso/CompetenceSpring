package fr.dawan.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import fr.dawan.springmvc.beans.Contact;
import fr.dawan.springmvc.beans.Product;

public class ProductDao {

	private static List<Product> lc;

	static {
		lc = new ArrayList<>();
		lc.add(new Product(1, "Ordinateur",340));
		lc.add(new Product(2, "Clavier",40));
		lc.add(new Product(3, "Souris",15));

	}
	
	public static List<Product> findAll(){
		return lc;
	}
	
	public static Product findById(int id) {
		int pos = lc.indexOf(new Product(id, null,0));
		if(pos!=-1) 
			return lc.get(pos);
		else
			return null;
	}
	
	
	
	
}
