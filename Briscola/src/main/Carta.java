package main;

public class Carta {
	private int numero;
	private int valore;
	private String seme;
	private Player player; //Serve per ricordarsi chi ha lanciato la carta una volta sul tavolo
	
	
	public Carta(int numero, String seme) { //Costruttore iniziale
		this.numero = numero;
		this.seme = seme;
		if(numero <= 7 && numero != 1 && numero != 3)
			this.valore = 0;
		else {
			switch(numero) {
				case 1: this.valore = 11;
						break;
				case 3: this.valore = 10;
						break;
				case 10: this.valore = 4;
						break;
				case 9: this.valore = 3;
						break;
				case 8: this.valore = 2;
						break;
				default:
						System.out.println("SEI UN FOTTUTO PIDDOSCIO");
						break;
			}
		}
		this.player = null;
	}
	public int getValore() {
		return this.valore;
	}
	public String toString() {
		return this.seme + " " + String.valueOf(numero);
	}

	public int compareTo(Carta carta, String seme, String briscola){ //Serve per vedere chi prende le carte al turno
		if((this.seme.equals(briscola))&&(!carta.seme.equals(briscola)))
			return 1;
		if(this.seme.equals(seme)&&(!carta.seme.equals(briscola)&&!(carta.seme.equals(seme))))
			return 1;
		if((this.seme.equals(briscola)&&(carta.seme.equals(briscola))||(this.seme.equals(seme)&&carta.seme.equals(seme)))){
			if(this.valore==carta.valore)
				if(this.numero>carta.numero)
					return 1;
				else 
					return -1;
			else
				if(this.valore>carta.valore)
					return 1;
				else
					return -1;
		}
		return -1;
	}

	public Player getPlayer(){
		return this.player;
	}

	public String getSeme(){
		return this.seme;
	}

}
