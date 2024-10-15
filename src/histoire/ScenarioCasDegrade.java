package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;
public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Village village = new Village("le village des irréductibles", 10, 5);
	    
	    try {
	        System.out.println(village.afficherVillageois());
	    } catch (VillageSansChefException e) {
	    	e.printStackTrace();
	    }
		Etal etal = new Etal();
		try {
	        etal.libererEtal();
	    } catch (IllegalStateException e) {
	    	e.printStackTrace();
	    }
	    System.out.println("Fin du test");
	    Gaulois acheteur = null;
	    
	    try {
	        etal.acheterProduit(10, acheteur);
	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    }

	    try {
	        etal.acheterProduit(-5, new Gaulois("Abraracourcix", 5));
	    } catch (IllegalArgumentException e) {
	    	e.printStackTrace();
	    }

	    try {
	        etal.acheterProduit(10, new Gaulois("Abraracourcix",7));
	    } catch (IllegalStateException e) {
	    	e.printStackTrace();
	    }
	    System.out.println("Fin du test");
	}

}
