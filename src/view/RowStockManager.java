package view;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class RowStockManager {
	private int maxAdd = 999;
	
	private String product;
	private String section;
	private int stock;
	private Spinner<Integer> modify;
	
	private SpinnerValueFactory<Integer> factory;
	
	public RowStockManager(String product, String section, int stock, Spinner<Integer> modify) {
		super();
		this.product = product;
		this.section = section;
		this.stock = stock;
		this.modify = modify;
		
		factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-(stock),maxAdd,0);
		this.modify.setValueFactory(factory);
		this.modify.setEditable(true);
		this.modify.setPrefWidth(Control.USE_COMPUTED_SIZE);
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSection() {
		return section;
	}

	public void setSectionName(String section) {
		this.section = section;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Spinner<Integer> getModify() {
		return modify;
	}

	public void setModify(Spinner<Integer> modify) {
		this.modify = modify;
	}

	public SpinnerValueFactory<Integer> getFactory() {
		return factory;
	}

	public void setFactory(SpinnerValueFactory<Integer> factory) {
		this.factory = factory;
	}
	
	
	
	
}
