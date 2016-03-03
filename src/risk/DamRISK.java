package risk;

import java.util.Scanner;

import inout.InOut;
import judge.Judge;
import utils.Utils;

public class DamRISK {
	/**
	 * DEFINICIÓ DE CONSTANTS
	 */

	/**
	 * Véctor (array) amb els noms dels continents. La posició del continent
	 * dins del vector l'identifica en les diferents matrius o arrays on es
	 * relaciona. Seria la seva clau primària.
	 */
	public static final String[] continents = { "America Nord", "America Sud", "Africa", "Europa", "Asia", "Oceania" };

	/**
	 * Vector (array) amb els noms dels territoris. Es relacionen amb el seu
	 * continent ja que el nombre de fila correspon a la posició del array
	 * continents.
	 *
	 * territoris[0] -> Correspondrà als territoris del continent[0] Amèrica del
	 * Nord territoris[1] -> Correspondrà als territoris del continent[1]
	 * Amèrica Sud
	 */
	public static final String[] territoris = { "Alaska", "Territorio del nor-oeste", "Groenlandia", "Alberta",
			"Ontario", "Labrador", "Territorio del oeste", "Territorio del este", "America central", "Venezuela",
			"Peru", "Argentina", "Brasil", "Africa del norte", "Egipto", "Africa Oriental", "Congo", "Africa del sur",
			"Magadascar", "Europa Occidental", "Gran Bretaña", "Islandia", "Escandinavia", "Europa del norte",
			"Europa del sur", "Ucrania", "Ural", "Afganistan", "Oriente Medio", "Siberia", "Yakutia", "Chita",
			"Kamchatka", "Mongolia", "Japon", "China", "Siam", "India", "Indonesia", "Nueva Guinea",
			"Australia Occidental", "Australia Oriental" };

	/**
	 * Matriu (array de dues dimensions) que ens permet identificar els païssos
	 * veïns i així poder moure exèrcits entre ells o atacar. Segons moment de
	 * la partida.
	 *
	 * veins[0] ens llista els territoris fronteres amb Alaska.
	 */
	public static final int[][] veins = { { 1, 3, 32 }, { 0, 2, 3, 4 }, { 1, 4, 5, 21 }, { 0, 1, 4, 6 },
			{ 1, 2, 3, 5, 6, 7 }, { 2, 4, 7 }, { 3, 4, 5, 7, 8 }, { 6, 4, 5, 8 }, { 6, 7, 9 }, { 8, 10, 12 },
			{ 9, 11, 12 }, { 10, 12 }, { 9, 10, 11, 13 }, { 12, 14, 15, 16, 19 }, { 13, 15, 24, 28 },
			{ 13, 14, 16, 17, 18 }, { 13, 15, 18 }, { 15, 17 }, { 20, 13, 23, 24 }, { 19, 21, 23, 22 }, { 20, 23, 2 },
			{ 21, 25, 23, 20 }, { 19, 20, 22, 24, 25 }, { 19, 20, 22, 24, 25 }, { 13, 14, 19, 23, 25 },
			{ 22, 23, 24, 26, 27, 28 }, { 25, 27, 28, 29 }, { 25, 26, 28, 37, 35 }, { 27, 37, 14 },
			{ 28, 31, 33, 34, 35 }, { 29, 31, 32 }, { 30, 31, 32, 33, 34, 0 }, { 29, 30, 32, 33 },
			{ 29, 31, 32, 34, 35 }, { 32, 33 }, { 29, 28, 27, 37, 36, 35 }, { 35, 37, 38 }, { 35, 36, 27, 28 },
			{ 40, 39, 36 }, { 38, 40, 41 }, { 38, 39, 41 }, { 40, 39 } };

	/**
	 * Véctor (array) amb els objectius del joc. La posició de l'objectiu dins
	 * del vector l'identifica en les diferents matrius o arrays on es
	 * relaciona. Seria la seva clau primària.
	 */
	public static final String[] objectius = {"Amèrica sur i Àsia","Amèrica sur, Europa i un tercer continent","24 territoris",
			"18 territoris amb dos exèrcits a cadascun com a mínim","Oceania, Europa i un tercer continent",
			"Amèrica del nord i Àfrica","Amèrica del nord i Oceania","Àfrica i Àsia"};

	/**
	 * Véctor (array) amb la quantitat d'exèrcits inicials. En la posició 0
	 * correspon a 3 jugadors i la posició 3 a 6 jugadors.
	 */
	public static final int[] exercitsInicials = { 35, 30, 25, 20 };

	/**
	 * Véctor (array) amb la quantitat d'exèrcits que guanyes per continent
	 * conquistat. En la posició 0 correspon a Amèrica del Nord i la 5 a
	 * Oceania.
	 */
	public static final int[] continentsComplets = { 5, 2, 3, 5, 7, 2 };

	/**
	 * Nombre que divideix els païssos conquerits per saber les unitats de
	 * reforç.
	 */
	public static final int divisioTerritori = 3;
	/**
	 * Nombre màxims de jugadors que poden jugar al DamRISK.
	 */
	public static final int maxJugadors = 6;
	/**
	 * Nombre mínim de jugadors que poden jugar al DamRISK.
	 */
	public static final int minJugadors = 3;

	/**
	 * Matriu (array de dues dimensions) que ens permet identificar a quin
	 * continent pertany cada païs. Com sempre juguem amb la posició dels arrays
	 * com a clau primària
	 *
	 * paissosContinent[0] són els païssos d'Amèrica del Nord
	 *
	 * paissosContinent[0][0] -> hi ha el territoris[0]
	 */
	public static final int[][] paissosContinent = { { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 9, 10, 11, 12 },
			{ 13, 14, 15, 16, 17, 18 }, { 19, 20, 21, 22, 23, 24, 25 },
			{ 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37 }, { 38, 39, 40, 41 } };

	// paissosContinent[0][0] = territoris[0];
	// paissosContinent[1][0] = territoris[9];
	// paissosContinent[2][0] = territoris[13];
	// paissosContinent[3][0] = territoris[19];
	// paissosContinent[4][0] = territoris[26];
	// paissosContinent[5][0] = territoris[38];

	public static void main(String[] args) {
		/**
		 * Matriu que representa el tauler de joc. Cada posició té un array on
		 * es guarda la informació següent {Id jugador, Número exercits} Cada
		 * posició és un territori.
		 */
		int[][] tauler = new int[territoris.length][2];

		Judge.inicialitzaTauler(tauler);

		/**
		 * Vector per guardar els noms dels jugadors. La posició dins del vector
		 * és l'identificador de jugador.
		 */
		String[] nomsJugadors = null;
		/**
		 * Matriu on guardem la informació del joc per a cada jugador. On
		 * guardem la informació del jugador. Per a cada jugador guardem
		 * nomsJugadors[0] li correspon la infoJugadors {objectiu, cavalleria,
		 * artilleria, cano, comodi }
		 */
		int[][] infoJugadors = null;

		/**
		 * Enter que indicarà el nombre de jugador que li toca tirar.
		 */
		int torn = 0;

		// Codi per demanar el nombre de jugadors
		int nJugadors = InOut.demanarNumeroJugadors();

		// Dimensionar els vectors nomsJugadors i infoJugadors
		nomsJugadors = new String[nJugadors];
		infoJugadors = new int[nJugadors][maxJugadors - 1];

		// Calcular nombre d'exèrcits inicials
		int nExercits = exercitsInicials[nJugadors - 3];
		
		// Tropes jugador
		int nTropes = 0;

		// Demanar les dades als jugadors i preparar-los per poder iniciar el
		// joc.
		for (int i = 0; i < nJugadors; i++) {

			// Demanar el nom i guardar-ho en el vector
			nomsJugadors[i] = InOut.demanarNomJugador(i);

			// Assignar objetiu
			infoJugadors[i][0] = Utils.rnd(objectius.length);

			InOut.printObjectiuJugador(i, infoJugadors);

			// Repartir territoris
			Judge.repartirTerritorisJugador(tauler, nJugadors, i, nomsJugadors);

			nTropes = Utils.calcularTropesJugador(tauler, i);
			
			// Assignar tropes
			Judge.assignarTropesJugador(tauler, i, nExercits - nTropes);

		}
		// Decidir qui comença a jugar i dir-ho per pantalla
		torn = Judge.iniciaTorn(nomsJugadors);
		InOut.printTauler(tauler, nomsJugadors);
		
		boolean guanyador = false;
		int tropesAfegides = 0;
		
		while (!guanyador) {
			
			// Fase de refuerzo
			tropesAfegides = Judge.calcularTropesAfegides(tauler, torn);
			Judge.assignarTropesJugador(tauler, torn, tropesAfegides);
			
			// Fase de ataque
			InOut.printTerritorisAtacables(tauler, torn, nomsJugadors);

			// Ataque
			Judge.atacar(tauler, torn);

			// Redistribución
			Judge.redistribuirTropes(tauler, torn);
			
			// Calcular si hay ganador
			guanyador = Judge.hiHaGuanyador(tauler, infoJugadors); 
			
			Judge.seguentTorn(torn, nJugadors);
		}

		InOut.printGuanyador(tauler);

	}

}