package main;
/*
import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.PrintWriter;
*/
import controller.*;
import javafx.application.Application;
import model.Article;
import model.Leader;
import model.Section;
import model.Store;
import view.Interface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	//private static String batName = "cher.bat";

	
	public static void main(String[] args) {
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

		Store store = new Store("64 avenue Jean Portalis Tours");
		Section section = new Section("Chaussure");
		Article article = new Article("Chaussure de foot", "Nike", 50, 1);
		Leader leader = new Leader();

		storeDAO.create(store);
		//section.setStore(store);
		//sectionDAO.create(section);
		store.addSection(section);
		storeDAO.save();
		//storeDAO.delete(store);
		articleDAO.create(article);
		article.setSection(section);
		articleDAO.save();
		sectionDAO.delete(section);
		store.setLeader(leader);
		storeDAO.save();

		//storeDAO.delete(store);


		/*Article a1 = new Article("chaussure", "Nike", 1, 1);
		a1.setSection(section);
		dao.create(a1);
		dao.delete(section);*/


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
