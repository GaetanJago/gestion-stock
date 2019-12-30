package main;
/*
import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.PrintWriter;
*/
import controller.*;
import javafx.application.Application;
import model.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import view.Interface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	//private static String batName = "cher.bat";

	private final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
		EntityManager em = emf.createEntityManager();

        /*DAO dao = new DAO(em);

        Store store = new Store("11 Launay Plumieux");
        dao.create(store);


        Section section = new Section("chaussure");
        section.setStore(store);
		dao.create(section);
		//dao.delete(section);
		System.out.println(store.getSections());
		store.removeSection(section);
		dao.delete(section);
		System.out.println(store.getSections());*/

		ArticleDAO articleDAO = new ArticleDAO(em);
		StoreDAO storeDAO = new StoreDAO(em);
		SectionDAO sectionDAO = new SectionDAO(em);
		LeaderDAO leaderDAO = new LeaderDAO(em);
		ManagerDAO managerDAO = new ManagerDAO(em);

		Store store = new Store("64 avenue Jean Portalis Tours");
		Section section = new Section("Chaussure");
		Article article = new Article("Chaussure de foot", "Nike", 50, 1);
		Article article2 = new Article("Raquette de tennis", "Babolat", 40, 5);
		Leader leader = new Leader();
		leader.setStore(store);
		Manager manager = new Manager("Martin", "Pierre", "pmartin", "azeqsd");

		manager.setSection(section);
		Manager manager2 = new Manager("Marsault", "Bastien", "bmarsault", "emufac");
		manager2.setSection(section);

		storeDAO.create(store);
		//section.setStore(store);
		//sectionDAO.create(section);
		store.addSection(section);
		storeDAO.save();
		//storeDAO.delete(store);
		articleDAO.create(article);
		article.setSection(section);
		articleDAO.save();
		//sectionDAO.delete(section);
		//store.setLeader(leader);
		//storeDAO.save();
		leaderDAO.create(leader);
		leaderDAO.delete(leader);
		managerDAO.create(manager);

		managerDAO.create(manager2);
		articleDAO.create(article2);
		//sectionDAO.delete(section);

		//managerDAO.delete(manager);
		//storeDAO.save();
		//leaderDAO.delete(leader);
		//storeDAO.delete(store);

		em.close();
		emf.close();
		
		Application.launch(Interface.class,args);
	}
	
	/*
	public static void launch() {
		Console console = System.console();
		if(console != null) {
			File f = new File(batName);
			if(f.exists()) {
				f.delete();
			}
		}
		else if (!GraphicsEnvironment.isHeadless()) {
			String os = System.getProperty("os.name").toLowerCase();
			if(os.contains("indows")) {
				try {
					File jar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
					PrintWriter out  = new PrintWriter(new File(batName));
					out.println("@echo off");
					out.println("title test");
					out.println("java -jar " + jar.getPath());
					out.close();
					Runtime rt = Runtime.getRuntime();
					rt.exec("cmd /c start " + batName);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		}
	}
	*/
}
