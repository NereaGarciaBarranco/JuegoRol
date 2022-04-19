
public class Arma {
	private int idArma;
	private String nombre;
	private int dano;

	public Arma (int idArma, String nombre, int dano) {
		this.idArma = idArma;
		this.nombre = nombre;
		this.dano = dano;
	}
	
	public Arma (String nombre, int dano) {
		this.nombre = nombre;
		this.dano = dano;
	}
	
	public int getIdArma() {
		return idArma;
	}
	
	public void setIdArma(int idArma) {
		this.idArma = idArma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getDanyo() {
		return dano;
	}
	
	public void setDanyo(int dano) {
		this.dano = dano;
	}
	
	public String toString () {
		return "Nombre: " + nombre + " Daño: " + dano;
	}
}
