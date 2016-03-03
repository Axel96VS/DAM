package inout;

import java.util.Scanner;
import risk.DamRISK;


public class InOut {
	
	public static int demanarNumeroJugadors() {

		// Java.util.Scanner ens permet llegir de consola les dades dels usuaris
		Scanner scanner = new Scanner(System.in);
		int nJugadors = 0;

		System.out.println("Indica el numero de jugadors (minim " + DamRISK.minJugadors + " i maxim " + DamRISK.maxJugadors + ")?");
		do {
			// Si a l'scanner hi ha un enter
			if (scanner.hasNextInt()) {
				// Llegim l'enter
				nJugadors = scanner.nextInt();
			} else // Avancem la posició de l'scanner per descatar la línia
				scanner.next();
		} while (nJugadors < DamRISK.minJugadors || nJugadors > DamRISK.maxJugadors);
		// Si aquest enter està entre minJugadors i maxJugadors ja tenim
		// un número vàlid de jugadors
		return nJugadors;
	}
	
	public static String demanarNomJugador(int jugador) {
		Scanner scanner = new Scanner(System.in);

		String nom = "";
		System.out.println("Indica el nom del jugador " + (jugador + 1) + ".");

		do {
			nom = (scanner.hasNext() ? scanner.next() : "");
		} while (nom.length() <= 0);

		return nom;
	}
	
	public static void printObjectiuJugador(int jugador, int[][] infoJugadors) {
		System.out.println("L'objectiu es " + DamRISK.objectius[infoJugadors[jugador][0]]);
	}

	public static void printTauler(int[][] tauler, String[] nomsJugadors) {
		for (int i = 0; i < tauler.length; i++) {
			System.out.println(DamRISK.territoris[i] + " (Codi: "+i+") -" + nomsJugadors[tauler[i][0]] + "-" + tauler[i][1]);
		}
	}
	
	public static void printTerritorisJugador(int[][] tauler, int jugador, String[] nomsJugadors) {

		System.out.println("Aquests son els teus territoris...");

		for (int i = 0; i < tauler.length; i++) {
			if (tauler[i][0] == jugador) {
				System.out.println(DamRISK.territoris[i] + " - " + nomsJugadors[jugador] + " - " + tauler[i][1]);
			}
		}
		System.out.println();
	}
	
	public static int demanarExercitsPosicio(int[][] tauler, int i, int maxExercits) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Actualment tens " + tauler[i][1] + " exercits en el territori " + DamRISK.territoris[i]);
		System.out.println("Indica quants exercits vols afegir mes (minim 0 i maxim " + maxExercits + ")");

		int nExercits = 0;
		do {
			nExercits = (scanner.hasNextInt() ? scanner.nextInt() : 0);
		} while (nExercits < 0 || nExercits > maxExercits);

		return nExercits;
	}

	public static void printTerritorisAtacables(int[][] tauler, int torn, String[] nomsJugadors) {
		// TODO
		System.out.println("Posibles atacs des de:");
		for (int i=0; i<tauler.length;i++) {
			if(tauler[i][0]==torn && tauler[i][1]>1){
				System.out.println("- "+DamRISK.territoris[i]+" (Codi: "+i+"). Tens "+tauler[i][1]+" exercits. Pots atacar a:");
				for (int j=0; j<DamRISK.veins[i].length;j++){
					if(tauler[DamRISK.veins[i][j]][0]!=torn){
						System.out.println("      ---(Codi: "+DamRISK.veins[i][j]+") "+DamRISK.territoris[DamRISK.veins[i][j]]+" ( "+nomsJugadors[tauler[DamRISK.veins[i][j]][0]]+"; "+tauler[DamRISK.veins[i][j]][1]+" exercits ).");
					}
				}
			}
		}
	}
	
	public static int demanarOrigenAtac(int[][] tauler, int torn) {
		// Muestra los territorios que tienen más de dos tropas
		Scanner scanner = new Scanner(System.in);
		int paisOrigen=-1;
		do
		{
			System.out.println("Des d'on vols atacar? (Introdueix codi):");
			if(scanner.hasNextInt())
			{
				paisOrigen=scanner.nextInt();
				if(tauler[paisOrigen][0]==torn)
				{
					if(tauler[paisOrigen][1]>1)
					{
						System.out.println("Atac des de "+DamRISK.territoris[paisOrigen]);
					}
					else
					{
						System.out.println("No hi ha exercits suficients");
					}
				}
				else
				{
					System.out.println("Aquest pais no es teu");
				}
			}
		}while(paisOrigen<0 || paisOrigen>41 || tauler[paisOrigen][0]!=torn || tauler[paisOrigen][1]<2);
		return paisOrigen;
	}
	
	public static int demanarDestiAtac(int[][] tauler, int torn) {
		
		Scanner scanner = new Scanner(System.in);
		int paisDesti=-1;
		do
		{
			System.out.println("A on vols atacar? (Introdueix codi):");
			if(scanner.hasNextInt())
			{
				paisDesti=scanner.nextInt();
				if(tauler[paisDesti][0]!=torn)
				{
					System.out.println("Atac a "+DamRISK.territoris[paisDesti]);
				}
				else
				{
					System.out.println("Aquest pais es teu");
				}
			}
		}while(paisDesti<0 || paisDesti>41 || tauler[paisDesti][0]==torn);
		return paisDesti;
	}

	public static int printGuanyador(int[][] tauler) {
		// TODO
		return 0;
	} 
}
