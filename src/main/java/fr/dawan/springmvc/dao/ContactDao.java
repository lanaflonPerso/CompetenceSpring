package fr.dawan.springmvc.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import fr.dawan.springmvc.beans.Contact;

public class ContactDao {

	//liste statique d'objets Contact
	private static List<Contact> lc;
	private static int nb;
	
	//bloc statique : sert à initialiser les variables statiques
	static {
		lc = new ArrayList<>();
		lc.add(new Contact(1, "toto"));
		lc.add(new Contact(2, "tata"));
		lc.add(new Contact(3, "titi"));
		nb=3;
	}
	
	//méthode statique findAll
	public static List<Contact> findAll(){
		return lc;
	}
	
	//méthode statique add
	public static void add(Contact c) {
		nb++;
		c.setId(nb);
		lc.add(c);
	}
	
	//méthode statique remove
	public static void remove(int id) throws Exception {
		int pos = lc.indexOf(new Contact(id,null));
		if(pos!=-1)
			lc.remove(pos);
		else
			throw new Exception("Error : id not valid !");
	}
	
	//méthode statique update
	public static void update(Contact c) throws Exception {
		int pos = lc.indexOf(c);
		if(pos!=-1) {
			lc.set(pos, c);
		}else
			throw new Exception("Error : id not valid !");
	}
	
	//méthode statique findById
	public static Contact findById(int id) {
		int pos = lc.indexOf(new Contact(id, null));
		if(pos!=-1) 
			return lc.get(pos);
		else
			return null;
	}

	public static List<Contact> importCsv(File filePath) throws Exception {
		List<Contact> lc = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		br.readLine(); //ignorer la ligne d'entête
		String line = null;
		while((line=br.readLine())!=null) {
			String[] tab = line.split(";");
			try {
				lc.add(new Contact(Integer.parseInt(tab[0]), tab[1]));	
			} catch (Exception e) {
				//TODO log ignored line
			}
		}
		br.close();
		return lc;
	}
	
	
	
	
}













