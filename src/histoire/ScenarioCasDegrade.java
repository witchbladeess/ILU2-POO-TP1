package histoire;

import villagegaulois.Etal;
import villagegaulois.Village;
import exceptions.VillageSansChefException;
import personnages.Gaulois;


public class ScenarioCasDegrade {
	public static void main(String[] args) {
		try { cas1(); }
		catch (IllegalStateException e) {
			System.out.println("1 OK");
		}
		System.out.println("2a OK");
		
		try { cas2b(); }
		catch (IllegalArgumentException e) {
			System.out.println("2b OK");
		}
		try { cas2c(); }
		catch (IllegalStateException e) {
			System.out.println("2c OK");
		}
		try { cas3(); }
		catch (VillageSansChefException e) {
			System.out.println("3 OK");
		}
	}

	private static void cas1() {
		Etal etal = new Etal();
		etal.libererEtal();
	}

	private static void cas2b() {
		Etal etal = new Etal();
		etal.acheterProduit(0, new Gaulois("gaulois 1", 1));
	}
	private static void cas2c() {
		Etal etal = new Etal();
		etal.acheterProduit(5, new Gaulois("gaulois 1", 1));
	}
	private static void cas3() throws VillageSansChefException {
		Village village = new Village("village 1", 1, 0);
		village.afficherVillageois();
	}
}