package villagegaulois;

import exceptions.VillageSansChefException;
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

    public String afficherVillageois() throws VillageSansChefException {
        StringBuilder chaine = new StringBuilder();
        if (chef == null) {
        	throw new VillageSansChefException("Le village n'a pas de chef");
        }
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
    
    
    
   private static class Marche{
	   private Etal[] etals;
	   // Constructor
	   private Marche(int nbEtals) {
		   etals = new Etal[nbEtals];
		   for(int i = 0; i < nbEtals; i++) {
			   etals[i] = new Etal();
		   }
	   }
	   private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		   if(indiceEtal >= 0 && indiceEtal <etals.length && !etals[indiceEtal].isEtalOccupe()) {
			   etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	
		   }
	   }
	   int trouverEtalLibre() {
		   for(int i = 0; i < etals.length; i++){
			   if(!etals[i].isEtalOccupe()) {
				   return i;
			   }
		   }
		   return -1;
	   }
	   public Etal[] trouverEtals(String produit) {
		   int counter = 0;
		   for(int i = 0; i<etals.length; i++) {
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
		   for(int i=0; i < etals.length; i++) {
			   if(etals[i].isEtalOccupe() && etals[i].getVendeur().equals(gaulois)) {
					   return etals[i];
				   }
			   }
		   return null;
	   }
	   	   	   
}

   public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
       int etalLibre = marche.trouverEtalLibre();
       if (etalLibre != -1) {
           marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
           System.out.println(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit +" " + produit);
           return "Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l’étal n°" + (etalLibre + 1) + ".\n";
       } else {
           return "Pas d’étal libre pour " + vendeur.getNom();
       }
   }

  public String rechercherVendeursProduit(String produit) {
	   Etal[] etalsProduits = marche.trouverEtals(produit);
       if (etalsProduits.length == 0) {
           return "Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n ";
       } else {
           StringBuilder resultat = new StringBuilder("Les vendeurs qui proposent des " + produit + " sont :\n");
          

           for (int i=0; i < etalsProduits.length; i++) {
        	   if(etalsProduits.length == 1) {
        		   return "Seul " + etalsProduits[i].getVendeur().getNom() + " propose des " + produit + " au marché.";
        	   }

               resultat.append("- ").append(etalsProduits[i].getVendeur().getNom()).append("\n");
           }
           return resultat.toString();
       }
  }
  public Etal rechercherEtal(Gaulois vendeur) {
	   return marche.trouverVendeur(vendeur);
  }
  public String partirVendeur(Gaulois vendeur) {
	   Etal etal = marche.trouverVendeur(vendeur);
       if (etal != null) {
           return etal.libererEtal();
       }
       return vendeur.getNom() + " n'a pas d'étal.";

 }
  
  public String afficherMarche() {
	    StringBuilder info = new StringBuilder();
	    info.append("Le marché du village \"").append(nom).append("\" possède plusieurs étals :\n");

	    int etalsOccupes = 0;
	    for (int i = 0; i < marche.etals.length; i++) { 
	    	if (marche.etals[i].isEtalOccupe()) { 
	    		info.append(marche.etals[i].afficherEtal());
	        	etalsOccupes++;
	    	}
	    }
	  
	    int nbEtalsVides = marche.etals.length - etalsOccupes ;
	    if (nbEtalsVides > 0) {
	        info.append("Il reste ").append(nbEtalsVides).append(" étals non utilisés dans le marché.\n");
	    }

	    return info.toString();	  
  }


}