package main;
/*
import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.PrintWriter;
*/
import javafx.application.Application;
import view.Interface;

public class Main {
	
	//private static String batName = "cher.bat";
	
	public static void main(String[] args) {
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit 
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();*/
		
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
