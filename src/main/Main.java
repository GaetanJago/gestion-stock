package main;
import javafx.application.Application;
import model.Article;
import model.Leader;
import model.Manager;
import model.Role;
import model.Section;
import model.Store;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import controller.ArticleDAO;
import controller.LeaderDAO;
import controller.ManagerDAO;
import controller.RoleDAO;
import controller.SectionDAO;
import controller.StoreDAO;
import view.Interface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Launch the application
 *
 */
public class Main {

	//private static String batName = "cher.bat";

	private final static Logger logger = Logger.getLogger(Main.class);

	/**
	 * Use for persistence in hibernate
	 */
	public static EntityManagerFactory emf;
	/**
	 * Use for persistence in hibernate
	 */
	public static EntityManager em;

	/**
	 * Main of the application
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
		em = emf.createEntityManager();

		generateExample();

		Application.launch(Interface.class,args);
	}

	/**
	 * generate an example in the data Base
	 */
	public static void generateExample() {
		StoreDAO storeDAO = new StoreDAO(em);
		Store store = new Store("64 avenue Jean Portalis Tours");
		storeDAO.create(store);

		SectionDAO sectionDAO = new SectionDAO(em);
		Section section = new Section("Chaussure");
		Section section_b = new Section("Raquette");
		section.setStore(store);
		section_b.setStore(store);
		sectionDAO.create(section);
		sectionDAO.create(section_b);

		ArticleDAO articleDAO = new ArticleDAO(em);
		Article article = new Article("Chaussure de foot", "Nike", 50, 1);
		Article article2 = new Article("Raquette de tennis", "Babolat", 40, 5);
		article.setSection(section);
		article2.setSection(section_b);
		articleDAO.create(article);
		articleDAO.create(article2);

		RoleDAO rd = new RoleDAO(em);
		Role r_a = new Role("Admin");
		Role r_m = new Role("Manager");
		rd.create(r_a);
		rd.create(r_m);

		LeaderDAO ld = new LeaderDAO(em);
		Leader l = new Leader("chef", "du mag", "admin", "admin");
		Leader bl = new Leader("bras droit", "du magasin", "admin2", "admin2");
		l.setRole(r_a);
		bl.setRole(r_a);
		l.setStore(store);
		ld.create(l);
		ld.create(bl);

		ManagerDAO md = new ManagerDAO(Main.em);
		Manager m_a = new Manager("paul", "martin", "pmartin", "azeqsd");
		m_a.setSection(section);
		m_a.setRole(r_m);
		md.create(m_a);
		Manager m_b = new Manager("Bastien", "marsault", "bmarsault", "123456");
		m_b.setSection(section_b);
		m_b.setRole(r_m);
		md.create(m_b);
	}
}