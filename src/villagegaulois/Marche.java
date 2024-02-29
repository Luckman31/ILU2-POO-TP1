package villagegaulois;
import personnages.Gaulois;
public class Marche {
	private Etal[] etals;
	public CreerEtal(int nbetals) {
		etals=new Etal[nbetals];
	}
	public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
		Etal etal=etals[indiceEtal];
		etal.setvendeur(vendeur);
		etal.setproduit(produit);
		etal.setquantite(nbProduit);
	}
}