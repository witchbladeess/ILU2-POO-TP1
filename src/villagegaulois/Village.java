package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;


	private static class Marche{
		private Etal[] etals;
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for(int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
			
		}
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if(indiceEtal>=0 && indiceEtal <etals.length && !etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		public int trouverEtalLibre(){
			for(int i = 0; i < etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int counter = 0;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					counter++;					
				}
			}
			Etal[] etalsVendus = new Etal[counter];
			int index = 0;
			for(int i = 0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etalsVendus[index] = etals[i];
					index++;
				}
			}
			return etalsVendus;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i<etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					if(etals[i].getVendeur() == gaulois) {
						return etals[i];
					}
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder marcheinfo = new StringBuilder();
			int count = 0;
			for (int i = 0; i<etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					marcheinfo.append(etals[i].afficherEtal());
					count++;
				} 
				if(count != etals.length) {
					int nbEtalVide = etals.length + count;
					marcheinfo.append("Il reste" + nbEtalVide + "étals non utilisés dans le marché.\n");
					
				}
			}
			return marcheinfo.toString();
		}
		
		public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
			 StringBuilder outp = new StringBuilder();
			 outp.append(vendeur + "cherche un endroit pour vendre" + nbProduit + " " + produit + ".");
			 int numEtal = trouverEtalLibre();
			 if(numEtal != -1) {
			 outp.append("le vendeur" + vendeur + "vend des " + produit + "a l'etal Num" + numEtal);
			 } else {
				 outp.append("Il n'y a plus d'etales libres.");
			 }
		 }

	}
	
	 		
	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
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
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}