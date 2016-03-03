package utils;

import java.util.Random;

import risk.DamRISK;

public class Utils {

	
	public static int rnd(int max) {
		Random rnd = new Random();
		return rnd.nextInt(max);
	}
	
	public static int trobaPosicioLliure(int[][] tauler) {

		// Prenem una posició a l'atzar
		int t = rnd(tauler.length - 1);

		// Iterem fins que trobem una posició lliure
		while (tauler[t][0] != -1) {
			// Incrementem t i comprovem que no es surti del rang del vector
			t = t + 1;
			// L'operador % és el módul, retorna la resta de la divisió entera.
			// Ex: t = 42 i tauler.length = 42
			// 42 % 42 = 0 --> Llavors quan arribem al final del taulell
			// tornarem a la primera posició
			t = t % (tauler.length);
		}

		return t;
	}
	
    public static boolean esVei(int territoriActual, int territoriDesconegut) {
        for (int i=0; i < DamRISK.veins[territoriActual].length; i++ ) {
            if (DamRISK.veins[territoriActual][i] == territoriDesconegut) return true;
        }
        return false;
    }

	public static int calcularTropesJugador(int[][] tauler, int i) {
		// TODO 
		int totalTropes=0;
        for ( int j = 0; j < tauler.length; j++) {
            if (tauler[j][0] == i) {
                totalTropes+=tauler[j][1];          
            }
        }
        return totalTropes;
	}
	
	
	public static int calcularTerritorisJugador(int[][] tauler, int torn) {
        // TODO 
        int totalTerritoris=0;
        for ( int i = 0; i < tauler.length; i++) {
            if (tauler[i][0] == torn) {
                totalTerritoris++;            
            }
        }
        return totalTerritoris;
    }
    
    public static int saberTerritorisContinent (int[][] tauler, int torn) {
        int territorisContinent, exercitsExtra=0;
        for (int i = 0; i < DamRISK.paissosContinent.length; i++) {
            territorisContinent=0;
            for (int j=0; j < DamRISK.paissosContinent[i].length; j++) {
                if(tauler[DamRISK.paissosContinent[i][j]][0]==torn) {
                    territorisContinent++;
                }
            }
            if(territorisContinent==DamRISK.paissosContinent[i].length)
            {
                exercitsExtra+=DamRISK.continentsComplets[i];
            }
        }
        return exercitsExtra;
    }
    
    
}
