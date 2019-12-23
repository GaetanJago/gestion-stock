package main;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit 
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();
	}
}
