package view;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import model.Article;

/**
 * Row to insert in manager stock table
 *
 */
public class RowStockManager {
	private int maxAdd = 999;
	
	private String product;
	private String section;
	private int stock;
	private Spinner<Integer> modify;
	private Article article;
	
	private SpinnerValueFactory<Integer> factory;
	
	public RowStockManager(String product, String section, int stock, Spinner<Integer> modify,Article a) {
		super();
		this.product = product;
		this.section = section;
		this.stock = stock;
		this.modify = modify;
		this.article = a;
		
		if(modify != null) {
			factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-(stock),maxAdd,0);
			this.modify.setValueFactory(factory);
			this.modify.setEditable(true);
			this.modify.setPrefWidth(Control.USE_COMPUTED_SIZE);
		}
		
	}

	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
