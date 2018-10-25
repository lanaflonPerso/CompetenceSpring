package fr.dawan.springmvc.formbeans;

public class OrderForm {

	private int selectedProdId;
	
	private int qty;

	public OrderForm(int selectedProdId, int qty) {
		super();
		this.selectedProdId = selectedProdId;
		this.qty = qty;
	}

	public OrderForm() {
		super();
	}

	public int getSelectedProdId() {
		return selectedProdId;
	}

	public void setSelectedProdId(int selectedProdId) {
		this.selectedProdId = selectedProdId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
