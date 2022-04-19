
public class Avatar {
	private int idAvatar;
	private String nombre;
	private int vida;
	
	public Avatar (int idAvatar, String nombre, int vida) {
		this.idAvatar = idAvatar;
		this.nombre = nombre;
		this.vida = vida;
	}
	
	public Avatar (String nombre, int vida) {
		this.nombre = nombre;
		this.vida = vida;
	}
	
	public int getIdAvatar() {
		return idAvatar;
	}
	
	public void setIdAvatar(int idAvatar) {
		this.idAvatar = idAvatar;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + nombre + " Vida: " + vida;
	}
}
