package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
public class ScenarioDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
        Gaulois acheteur = new Gaulois("eazz",2); // Exemple d'acheteur
        try {
            String resultat = etal.acheterProduit(5, acheteur); // Tentative d'achat sur un étal vide
            System.out.println("Résultat de l'achat : " + resultat);
        } catch (IllegalStateException e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace(); // Afficher la pile d'erreurs sur la sortie d'erreur
        }
        System.out.println("Fin du test");
    }

}
