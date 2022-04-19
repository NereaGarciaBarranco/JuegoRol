
public class Arma {
	private int idArma;
	private String nombre;
	private int da�o;

	public Arma (int idArma, String nombre, int da�o) {
		this.idArma = idArma;
		this.nombre = nombre;
		this.da�o = da�o;
	}
	
	public Arma (String nombre, int da�o) {
		this.nombre = nombre;
		this.da�o = da�o;
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
		return da�o;
	}
	
	public void setDanyo(int da�o) {
		this.da�o = da�o;
	}
	
	public String toString () {
		return "Nombre: " + nombre + " Da�o: " + da�o;
	}
}
