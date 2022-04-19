public class Ranking implements Comparable <Ranking>{
	private int idRanking;
	private Character character;
	private int rondas;
	
	public Ranking(int idRanking, Character character, int rondas) {
		this.idRanking = idRanking;
		this.character = character;
		this.rondas = rondas;
	}
	
	// Metodos get y set
	public int getIdRanking() {
		return idRanking;
	}

	public void setIdRanking(int idRanking) {
		this.idRanking = idRanking;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public int getRondas() {
		return rondas;
	}

	public void setRondas(int rondas) {
		this.rondas = rondas;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + character.getNombre() + ", rondas: " + rondas; 
	}
	
	@Override
	public int compareTo(Ranking r) {
		if (this.rondas > r.getRondas()) {
			return -1;
		} else if (this.rondas < r.getRondas()) {
			return 1;
		} else {
			// Si son iguales
			return 0;
		}	
	}
}
