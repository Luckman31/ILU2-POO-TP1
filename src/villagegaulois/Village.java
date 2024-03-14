package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nombreEtalsMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche=new Marche(nombreEtalsMarche);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}
	private class Marche {
		private Etal[] etals;
		public Marche(int nbetals) {
			etals=new Etal[nbetals];
			for(int i=0;i<nbetals;i++) {
				etals[i]=null;
			}
			
		}
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			if(indiceEtal>=0 && indiceEtal<etals.length) {
				if(etals[indiceEtal]==null) {
					Etal etal=new Etal();
					etal.occuperEtal(vendeur, produit, nbProduit);
					etals[indiceEtal]=etal;
					System.out.println("Le Gaulois "+vendeur.getNom()+" s'est installé à l'étal "+(indiceEtal+1)+" avec "+nbProduit+" "+produit+".");
					
				}else {
					System.out.println("L'étal "+(indiceEtal+1)+" est déjà occupé.");
				}
			}else {
				System.out.println("L'indice de l'etal est invalide");
			}
			
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
			for(int i=0;i<etals.length;i++) {
				if (etals[i]!=null && etals[i].contientProduit(produit)) {
					compteur++;
				}
			}
			Etal[] vendproduit=new Etal[compteur];
			int index=0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i]!=null && etals[i].contientProduit(produit)) {
					vendproduit[index]=etals[i];
					index++;
				}
			}
			return vendproduit;
			
		}
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0;i<etals.length;i++) {
				if(etals[i]!=null && etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		public String afficherMarche() {
			StringBuilder affichage=new StringBuilder("Le marché du village \""+getNom()+"\"possède plusieurs étals :\n");
			int nbEtalVide=0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i]!=null) {
					affichage.append(etals[i].afficherEtal());
				}
				else {
					nbEtalVide++;
				}
				
			}
			if(nbEtalVide>0) {
				affichage.append("Il reste "+nbEtalVide+" etals vides");
			}
			return affichage.toString();
		}
	}
	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}
	public String installerVendeur(Gaulois nom,String produit,int nbProduit) {
		StringBuilder chaine=new StringBuilder();
		chaine.append(nom.getNom()+" cherche un endroit pour vendre "+nbProduit+" "+produit+". ");
		int indiceEtal=marche.trouverEtalLibre();
		if(indiceEtal!=-1) {
			Etal etal=new Etal();
			etal.occuperEtal(nom, produit, nbProduit);
			marche.utiliserEtal(indiceEtal, nom, produit, nbProduit);
			chaine.append( nom.getNom()+" vend des "+produit+" à l'étal n°"+(indiceEtal+1)+".\n");
			
		}
		else {
			chaine.append("Aucun étal n'est disponible");
		}
		return chaine.toString();
	}
	public String rechercherVendeursProduit(String produit) {
		Etal[] etals=marche.trouverEtals(produit);
		StringBuilder chaine=new StringBuilder();
		if(etals.length>0) {
			if(etals.length==1) {
				chaine.append("Le vendeur " +etals[0].getVendeur().getNom()+" propose des "+produit+" au marché.");
			}else {
				chaine.append("Les vendeurs qui proposent des ").append(produit).append(" sont :\n");
				for (int i=0;i<etals.length;i++) {
					chaine.append("- ").append(etals[i].getVendeur().getNom()).append("\n");
				}
			}	
		}else if(etals.length<=0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " ).append(produit).append(" au marché.\n");
		}
		return chaine.toString();
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	public String partirVendeur(Gaulois vendeur) {
		Etal etal=marche.trouverVendeur(vendeur);
		if(etal!=null) {
			return etal.libererEtal();
			
		}else {
			return "Le vendeur "+vendeur.getNom()+" n'est pas installé à un étal.\n";
		}
	}
	public String afficherMarche() {
		return marche.afficherMarche();
	}
}