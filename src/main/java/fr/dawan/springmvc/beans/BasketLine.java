package fr.dawan.springmvc.beans;

public class BasketLine {

	private Product prod;
	
	private int qty;

	public BasketLine(Product prod, int qty) {
		super();
		this.prod = prod;
		this.qty = qty;
	}

	public BasketLine() {
		super();
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod == null) ? 0 : prod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketLine other = (BasketLine) obj;
		if (prod == null) {
			if (other.prod != null)
				return false;
		} else if (!prod.equals(other.prod))
			return false;
		return true;
	}
	
	public double getTotal() {
		return qty*prod.getPrice();
	}
	
	
}
