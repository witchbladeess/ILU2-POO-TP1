package histoire;

import villagegaulois.Etal;
import villagegaulois.Village;
import personnages.Gaulois;
import villagegaulois.VillageSansChefException;


public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Village village = new Village("le village des irréductibles", 10, 5);
		//Etal etal = new Etal();
		//etal.libererEtal();
		
		//String result = etal.acheterProduit(5, null);
		//System.out.println(result);
		
		//Gaulois vendeur = new Gaulois("Vendeur", 10);
		//village.installerVendeur(vendeur, "fleurs", 10);
		
		//try {
			//String result = etal.acheterProduit(-2, vendeur);
			//System.out.println(result);
		//} catch(IllegalArgumentException e){
			//System.out.println("Erreur : quantité illégale");
		//} catch(IllegalStateException e) {
			//System.out.println("Erreur:  l'étal non occupé");
		//}
		try {
			System.out.println(village.afficherVillageois());
		} catch(VillageSansChefException e){
			System.out.println("erreur: pas de chef");
			
		}

		System.out.println("Fin du test");
	}
}
