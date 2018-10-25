package fr.dawan.springmvc.beans;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<BasketLine> lines;

	public Basket() {
		lines = new ArrayList<>();
	}

	public List<BasketLine> getLines() {
		return lines;
	}

	public void setLines(List<BasketLine> lines) {
		this.lines = lines;
	}
	
	public int getItemsNb() {
		int nb = 0;
		for (BasketLine bl : lines) {
			nb+= bl.getQty();
		}
		return nb;
	}
	
	public double getTotal() {
		double t = 0;
		for (BasketLine bl : lines) {
			t+= bl.getTotal();
		}
		return t;
	}
	
	
	
	
	
	
}
