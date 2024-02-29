package villagegaulois;
import personnages.Gaulois;

public class Marche {
	private Etal[] etals;
	public void Marche(int nbetals) {
		etals=new Etal[nbetals];
	}
	public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
		Etal etal=etals[indiceEtal];
		System.out.println("Le Gaulois"+vendeur.getNom()+"s'est installé à l'étal"+indiceEtal+"avec"+nbProduit+" "+produit+".");
	}
	
	public int trouverEtalLibre() {
		for(int i=0;i<etals.length;i++) {
			if (etals[i]==null) {
				return i;
			}
		}
		return -1;
	}
	public Etal[] trouverEtals(String produit) {
		int compteur=0;
		for(int i=0;compteur<etals.length;i++) {
			if (etals[i].contientProduit(produit)) {
				compteur++;
			}
		}
		Etal[] vendproduit=new Etal[compteur];
		int index=0;
		for(int i=0;i<etals.length;i++) {
			if(etals[i].contientProduit(produit)) {
				vendproduit[index]=etals[i];
				index++;
			}
		}
		return vendproduit;
		
	}
	public Etal trouverVendeur(Gaulois gaulois) {
		for (int i=0;i<etals.length;i++) {
			if(etals[i].getVendeur()==gaulois) {
				return etals[i];
			}
		}
		return null;
	}
	public String afficherMarche() {
		String affichage="";
		int nbEtalVide=0;
		for(int i=0;i<etals.length;i++) {
			if(etals[i]!=null) {
				affichage+=etals[i].afficherEtal();
			}
			else {
				nbEtalVide++;
			}
			
		}
		if(nbEtalVide>0) {
			affichage+="Il reste"+nbEtalVide+"etals vides";
		}
		return affichage;
	}
}