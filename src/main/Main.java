package main;

import javafx.application.Application;
import view.Interface;

public class Main {

	public static void main(String[] args) {
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit 
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();*/
		Application.launch(Interface.class,args);
	}
}
