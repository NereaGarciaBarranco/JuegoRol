
public class Pregunta {
	private int idPregunta;
	private String texto;
	private String textoConsecuencia1;
	private String textoConsecuencia2;
	private int valorConsecuencia1;
	private int valorConsecuencia2;
	
	// Constructor con identificador para leer de la BD
	public Pregunta (int idPregunta, String texto, String textoConsecuencia1, String textoConsecuencia2,
			int valorConsecuencia1, int valorConsecuencia2) {
		this.idPregunta = idPregunta;
		this.texto = texto;
		this.textoConsecuencia1 = textoConsecuencia1;
		this.textoConsecuencia2 = textoConsecuencia2;
		this.valorConsecuencia1 = valorConsecuencia1;
		this.valorConsecuencia2 = valorConsecuencia2;
	}
	
	// Constructor sin identificador para insercion de Preguntas
	public Pregunta (String texto, String textoConsecuencia1, String textoConsecuencia2,
			int valorConsecuencia1, int valorConsecuencia2) {
		this.texto = texto;
		this.textoConsecuencia1 = textoConsecuencia1;
		this.textoConsecuencia2 = textoConsecuencia2;
		this.valorConsecuencia1 = valorConsecuencia1;
		this.valorConsecuencia2 = valorConsecuencia2;
	}
	
	public int getIdPregunta() {
		return idPregunta;
	}
	
	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getTextoConsecuencia1() {
		return textoConsecuencia1;
	}
	
	public void setTextoConsecencia1(String textoConsecuencia1) {
		this.textoConsecuencia1 = textoConsecuencia1;
	}
	
	public String getTextoConsecuencia2() {
		return textoConsecuencia2;
	}
	
	public void setTextoConsecuencia2(String textoConsecuencia2) {
		this.textoConsecuencia2 = textoConsecuencia2;
	}
	
	public int getValorConsecuencia1() {
		return valorConsecuencia1;
	}
	
	public void setValorConsecuencia1(int valorConsecuencia1) {
		this.valorConsecuencia1 = valorConsecuencia1;
	}
	
	public int getValorConsecuencia2() {
		return valorConsecuencia2;
	}
	
	public void setValorConsecuencia2(int valorConsecuencia2) {
		this.valorConsecuencia2 = valorConsecuencia2;
	}
	
}
