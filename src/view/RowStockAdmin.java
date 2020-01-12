package view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import model.Article;
import model.Section;

/**
 * Row to insert in admin stock table
 *
 */
public class RowStockAdmin {
	private int maxAdd = 999;
	
	private TextField product;
	private ComboBox<Section> section;
	private int stock;
	private Spinner<Integer> modify;
	
	private SpinnerValueFactory<Integer> factory;
	
	private Article art;
	
	public RowStockAdmin(TextField product, ComboBox<Section> section, int stock, Spinner<Integer> modify,Article a) {
		super();
		this.product = product;
		this.section = section;
		this.stock = stock;
		this.modify = modify;
		this.art = a;
		
		this.section.setPrefWidth(Double.MAX_VALUE);
		this.modify.setPrefWidth(Double.MAX_VALUE);
		
		factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-(stock),maxAdd,0);
		this.modify.setValueFactory(factory);
		this.modify.setEditable(true);
		
	}

	
	
	public int getMaxAdd() {
		return maxAdd;
	}



	public void setMaxAdd(int maxAdd) {
		this.maxAdd = maxAdd;
	}



	public Article getArt() {
		return art;
	}



	public void setArt(Article art) {
		this.art = art;
	}



	public void setSection(ComboBox<Section> section) {
		this.section = section;
	}



	public TextField getProduct() {
		return product;
	}

	public void setProduct(TextField product) {
		this.product = product;
	}

	public ComboBox<Section> getSection() {
		return section;
	}

	public void setSectionName(ComboBox<Section> section) {
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
