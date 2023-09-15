package main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
	private String nome;
	private int punteggio;
	private ArrayList<Carta> mazzo; 
	private LinkedList<Carta> carteDisponibili = new LinkedList<>();

	public Player(String nome, ArrayList<Carta> mazzo) {
		this.nome = nome;
		this.punteggio = 0;
		this.mazzo = new ArrayList<>(mazzo);
		int i = 0;
		while( i <= 2) {
			this.carteDisponibili.addLast(mazzo.get(i));
			i++;
		}
	}

	public LinkedList<Carta> getCarteDisponibili() {
		return carteDisponibili;
	}
	public String getNome() {
		return this.nome;
	}
	public int getPunteggio() {
		return this.punteggio;
	}

	public ArrayList<Carta> getMazzo() {
		return this.mazzo;
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
}
