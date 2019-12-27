package view;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class RowStockAdmin {
	private int maxAdd = 999;
	
	private TextField product;
	private TextField section;
	private int stock;
	private Spinner<Integer> modify;
	
	private SpinnerValueFactory<Integer> factory;
	
	public RowStockAdmin(TextField product, TextField section, int stock, Spinner<Integer> modify) {
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

	public TextField getProduct() {
		return product;
	}

	public void setProduct(TextField product) {
		this.product = product;
	}

	public TextField getSection() {
		return section;
	}

	public void setSectionName(TextField section) {
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
