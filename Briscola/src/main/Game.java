package main;

import java.util.*;

public class Game {
	//costanti
	private final String nomePlayer1 = "bianco";
	private final String nomePlayer2 = "grigio";
	

	private ArrayList<Carta> mazzoPlayer1 = new ArrayList<>();
	private ArrayList<Carta> mazzoPlayer2 = new ArrayList<>();
	
	private Player player1; 
	private Player player2;
	private String turnoPlayerAttuale; 
	private boolean isStoppable = false;
	private Carta ultimaCartaGiocata = null;
	private Scanner scanner = new Scanner(System.in);
	
	public Game() {
		Mazzo mazzo = new Mazzo(true); 
		LinkedList<Carta> listaCarte = mazzo.getListaCarte();
		ListIterator<Carta> it1 = listaCarte.listIterator();
		
		//Assegnamento delle carte ai giocatori
		for(int i = 1; i<= 20; i++) 
			mazzoPlayer1.add(it1.next());
		
		for(int i = 21; i<=40; i++) 
			mazzoPlayer2.add(it1.next());

		
		player1 = new Player(nomePlayer1, mazzoPlayer1);
		player2 = new Player(nomePlayer2, mazzoPlayer2);
		this.turnoPlayerAttuale = player1.getNome();
		Carta briscola = mazzo.getListaCarte().getLast();

		int i=2;
		
		while(i<=18){
			
			Carta carta1 = null; //Carta player1
			Carta carta2 = null; //Carta player2
			String semeMossa = null;
			System.out.println("\nLa briscola è: " + briscola);
			if(turnoPlayerAttuale.equals(player1.getNome())) {
				ultimaCartaGiocata = null;
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta1 = mossa(player1,briscola, i);
				ultimaCartaGiocata=carta1;//Serve per il messaggio da console
				semeMossa = carta1.getSeme();
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossa(player2,briscola, i);
				
			}
			else { 
				ultimaCartaGiocata = null;
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossa(player2,briscola, i);
				ultimaCartaGiocata=carta2;//Serve per il messaggio da console
				semeMossa = carta2.getSeme();
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta1 = mossa(player1,briscola, i);
			}
			
			if(carta1.compareTo(carta2,semeMossa,briscola.getSeme())==1){
				turnoPlayerAttuale=player1.getNome();
				player1.setPunteggio(player1.getPunteggio()+carta1.getValore()+carta2.getValore());
			}
			else{
				turnoPlayerAttuale=player2.getNome();
				player2.setPunteggio(player2.getPunteggio()+carta1.getValore()+carta2.getValore());
			}
			i++;
		}

		

		for(int j = 1;j<=3;j++){
			Carta carta1 = null; //Carta player1
			Carta carta2 = null; //Carta player2
			String semeMossa = null;

			if(turnoPlayerAttuale.equals(player1.getNome())) {
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta1 = mossaUltimoTurn(player1);
				semeMossa = carta1.getSeme();
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossaUltimoTurn(player2);
				
			}
			else { 
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossaUltimoTurn(player2);
				semeMossa = carta2.getSeme();
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta1 = mossaUltimoTurn(player1);
			}
			
			if(carta1.compareTo(carta2,semeMossa,briscola.getSeme())==1){
				turnoPlayerAttuale=player1.getNome();
				player1.setPunteggio(player1.getPunteggio()+carta1.getValore()+carta2.getValore());
			}
			else{
				turnoPlayerAttuale=player2.getNome();
				player2.setPunteggio(player2.getPunteggio()+carta1.getValore()+carta2.getValore());
			}
		}

		System.out.println("Punteggio di bianco: " + player1.getPunteggio() + "\n Punteggio di grigio: " + player2.getPunteggio());
		if(player1.getPunteggio()>player2.getPunteggio()) {
			System.out.println("Vince: "+player1.getNome());
			isStoppable = true;

		}
		else if(player2.getPunteggio()>player1.getPunteggio()) {
			System.out.println("Vince: "+player2.getNome());
			isStoppable = true;
		}
		else {
			System.out.println("Pareggio");
			isStoppable = true;
		}
	}
	
	public Carta mossa(Player playerAttuale, Carta briscola, int indiceScorrimento) {
		System.out.println("È il turno di " + playerAttuale.getNome() + "!");
		System.out.println("Scegli una carta dal mazzo! [1], [2], [3] ");
		LinkedList<Carta> carteDisponibili = playerAttuale.getCarteDisponibili();
		System.out.print(carteDisponibili + "  ");
		
		int indiceMossa = prelevaMossa();
		//controllo se l'indice è valido
		while(indiceMossa < 1 || indiceMossa > 3) {
			System.out.println("Mossa non valida, inserire il numero [1], [2] oppure [3]!  ");
			indiceMossa = prelevaMossa();
		}
		indiceMossa--;

		System.out.println(playerAttuale.getNome() + " butta sul tavolo " + carteDisponibili.get(indiceMossa));
		Carta daRestituire = carteDisponibili.get(indiceMossa);

		sostituisciCarta(playerAttuale, indiceScorrimento, indiceMossa);
		System.out.println("Punteggio di " + playerAttuale.getNome() + " = " + playerAttuale.getPunteggio());
		return daRestituire;
	}

	public Carta mossaUltimoTurn(Player playerAttuale){

		//Numero carte Rimaste
		int numeroCarteRimaste = playerAttuale.getCarteDisponibili().size();
		System.out.println("È il turno di " + playerAttuale.getNome() + "!");
		if(numeroCarteRimaste==3)
			System.out.println("Scegli una carta dal mazzo! [1], [2], [3] ");
		if(numeroCarteRimaste==2)
			System.out.println("Scegli una carta dal mazzo! [1], [2]");
		if(numeroCarteRimaste==1)
			System.out.println("Scegli una carta dal mazzo! [1]");

		System.out.println("Numero Carte Rimaste: "+numeroCarteRimaste+" "+playerAttuale.getCarteDisponibili());

		LinkedList<Carta> carteDisponibili = playerAttuale.getCarteDisponibili();
		System.out.print(carteDisponibili + "  ");
		
		int indiceMossa = prelevaMossa();
		//controllo se l'indice è valido
		while(((numeroCarteRimaste==3)&&(indiceMossa < 1 || indiceMossa > 3))||((numeroCarteRimaste==2)&&(indiceMossa<1||indiceMossa>2))||((numeroCarteRimaste==1)&&(indiceMossa!=1))) {
			System.out.println("Mossa non valida, inserire il numero [1], [2] oppure [3]!  ");
			indiceMossa = prelevaMossa();
		}
		indiceMossa--;

		System.out.println("\n" + playerAttuale.getNome() + " butta sul tavolo " + carteDisponibili.get(indiceMossa));
		
		Carta cartaRet = carteDisponibili.get(indiceMossa);
	eliminaCarta(playerAttuale,indiceMossa);
		return cartaRet;
	}

	public int prelevaMossa() {
		
		int i = -1;
		if(scanner.hasNextInt()) {
			i = scanner.nextInt();
		}
		return i;
	}

	public boolean controllaGameOver() {
		return isStoppable;
	} 

	public void sostituisciCarta(Player giocatore, int indiceScorrimento, int indicePosizione) {
 		giocatore.getCarteDisponibili().set(indicePosizione, giocatore.getMazzo().get(indiceScorrimento + 1));
	}

	public void eliminaCarta(Player playerAttuale,int indiceMossa){
		playerAttuale.getCarteDisponibili().remove(indiceMossa);
	}

}
