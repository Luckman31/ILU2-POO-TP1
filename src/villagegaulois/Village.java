package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtal) {
			this.etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal >= 0 && indiceEtal < etals.length) {
				if (!etals[indiceEtal].isEtalOccupe()) {
					etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
				}
			}

		}

		private int trouverEtalLibre() {
			int libre = -1;
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					libre = i;
				}
			}
			return libre;
		}

		private Etal[] trouverEtals(String produit) {
			int nombre = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nombre++;
				}
			}
			Etal[] etaltrouve = new Etal[nombre];
			for (int i = 0, j = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etaltrouve[j] = etals[i];
					j++;
				}
			}
			return etaltrouve;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			Etal etalvendeur = new Etal();
			boolean reponse = false;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].getVendeur().equals(gaulois)) {
					etalvendeur = etals[i];
					reponse = true;
				}
			}
			if (reponse) {
				return etalvendeur;
			} else {
				return null;
			}

		}

		private String afficherMarche() {
			StringBuilder afficher = new StringBuilder();
			int compteurvide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					afficher.append(etals[i].afficherEtal());
				} else {
					compteurvide++;
				}
			}
			afficher.append("Il reste " + compteurvide + " étals non utilisés dans le marché.\n");
			return afficher.toString();
		}
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int libre = marche.trouverEtalLibre();

		marche.utiliserEtal(libre, vendeur, produit, nbProduit);
		StringBuilder afficher = new StringBuilder(
				vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		if (libre != -1) {
			afficher.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°"
					+ marche.trouverVendeur(vendeur) + "\n");
		}
		return afficher.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		Etal[] etal = marche.trouverEtals(produit);
		StringBuilder afficher = new StringBuilder();
		if (etal.length == 0) {
			afficher.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché\n");
		} else if (etal.length == 1) {
			afficher.append(
					"Seul le vendeur " + etal[0].getVendeur().getNom() + " propose des " + produit + " au marché\n");
		} else {
			afficher.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			for (int i = 0; i < etal.length; i++) {
				afficher.append("- " + etal[i].getVendeur().getNom() + "\n");
			}
		}
		return afficher.toString();
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}