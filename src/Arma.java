
public class Arma {
	private int idArma;
	private String nombre;
	private int daño;

	public Arma (int idArma, String nombre, int daño) {
		this.idArma = idArma;
		this.nombre = nombre;
		this.daño = daño;
	}
	
	public Arma (String nombre, int daño) {
		this.nombre = nombre;
		this.daño = daño;
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
		return daño;
	}
	
	public void setDanyo(int daño) {
		this.daño = daño;
	}
	
	public String toString () {
		return "Nombre: " + nombre + " Daño: " + daño;
	}
}
