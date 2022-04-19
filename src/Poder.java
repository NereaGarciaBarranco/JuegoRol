
public class Poder {
	private int idPoder;
	private String nombre;
	private int dano_defensa;
	
	public Poder (int idPoder, String nombre, int dano_defensa) {
		this.idPoder = idPoder;
		this.nombre = nombre;
		this.dano_defensa = dano_defensa;
	}
	
	public Poder (String nombre, int dano_defensa) {
		this.nombre = nombre;
		this.dano_defensa = dano_defensa;
	}
	
	public int getIdPoder() {
		return idPoder;
	}
	
	public void setIdPoder(int idPoder) {
		this.idPoder = idPoder;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getDano_defensa() {
		return dano_defensa;
	}
	
	public void setDaño_defensa(int dano_defensa) {
		this.dano_defensa = dano_defensa;
	}
	
	public String toString() {
		return "Nombre: " + nombre + " Daño/defensa: " + dano_defensa;
	}
}
