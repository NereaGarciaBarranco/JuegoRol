import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase presenta un programa que permite jugar a un 
 * sencillo juego de rol en el que en cada turno el usuario
 * tiene que responder a una pregunta. Segun sus decisiones
 * conseguira sobrevivir o no. 
 * 
 * @Author: Nerea Garcia Barranco
 */

public class Main {
	// Creacion de los objetos necesarios para el juego
	static Scanner entrada = new Scanner(System.in);
	static SQLConnection conexion = new SQLConnection();
	// Creacion de los vectores necesarios para el juego
	static ArrayList<Avatar> avatares = new ArrayList<Avatar>();
	static ArrayList<Arma> armas = new ArrayList<Arma>();
	static ArrayList<Poder> poderes = new ArrayList<Poder>();
	static ArrayList<Character> characters = new ArrayList<Character>();
	static ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	static ArrayList<Ranking> ranking = new ArrayList<Ranking>();
	
	/**
	 * Pre: ---
	 * Post: Este metodo principal permite que el usuario elija entre
	 * las cuatro opciones del programa. 
	 * 		[1] Ver el ranking 
	 * 		[2] Guardar nuevas ideas para el juego (avatares, armas, poderes o preguntas)
	 * 		[3] Jugar al juego 
	 * 		[4] Salir del programa.
	 */
	public static void main(String[] args) {
		// Mostramos el mensaje de comienzo de juego
		System.out.println("¡Bienvenido al juego de rol!");
		// Con este bucle permitimos al usuario elegir lo que quiere hacer
		while (true) {
			mostrarMenu();
			obtenerDatosBD();
			// Comprobamos la opcion que ha elegido el usuario
			try {
				int respuesta = entrada.nextInt();
				if (respuesta == 1) {
					mostrarRanking();
				} else if (respuesta == 2) {
					guardarNuevasIdeas();
				} else if (respuesta == 3) {
					jugar();
				} else if (respuesta == 4) {
					break;
				} else {
					System.out.println("Introduce una opcion correcta, ¡por favor!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra al usuario las opciones a elegir.
	 */
	private static void mostrarMenu() {
		System.out.println("\nElige una de las siguientes opciones:");
		System.out.println("Ver ranking [1]");
		System.out.println("Insertar nuevas ideas [2]");
		System.out.println("Jugar [3]");
		System.out.println("Salir del juego [4]\n");
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo obtiene de la BD todos los datos necesarios
	 * para poder jugar o ver el ranking. Se ejecuta cada vez que el
	 * usuario elige una opcion del menu por si ha añadido algun
	 * registro nuevo en la anterior interaccion con el programa, ya 
	 * sea una nueva idea o una nueva posicion en el ranking. 
	 */
	private static void obtenerDatosBD() {		
		try {
			obtenerAvatares();
			obtenerArmas();
			obtenerPoderes();
			obtenerCharacters();
			obtenerPreguntas();
			obtenerRanking();
		} catch (Exception e) {}	
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer los Avatares guardados en la BD, usar
	 * los datos para crear objetos de tipo Avatar y guardarlos en un vector.
	 */
	private static void obtenerAvatares() throws Exception {
		// Borramos para que cada vez que se actualice no se dupliquen datos
		avatares.clear();
		// Conectamos con la BD
		conexion.conectarBD();
		// Obtenemos con un select todos los avatares
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from juegorol.avatar");
		ResultSet rs = preparedStatement.executeQuery();
		// Mientras queden registros por leer
		while (rs.next()) {
			// Almacenamos cada dato en una variable de su tipo correspondiente
			int idAvatar = rs.getInt(1);
			String nombre = rs.getString(2);
			int vida = rs.getInt(3);
			// Creamos un objeto de tipo Avatar con estos datos y lo añadimos a su lista
			avatares.add(new Avatar(idAvatar, nombre, vida));
		}
		// Cerramos la conexion con la BD
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer las Armas guardadas en la BD, usar
	 * los datos para crear objetos de tipo Arma y guardarlos en un vector.
	 */
	private static void obtenerArmas() throws Exception {
		// Borramos para que cada vez que se actualice no se dupliquen datos
		armas.clear();
		// Conectamos con la BD
		conexion.conectarBD();
		// Obtenemos con un select todas las armas
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from juegorol.arma");
		ResultSet rs = preparedStatement.executeQuery();
		// Mientras queden registros por leer
		while (rs.next()) {
			// Almacenamos cada dato en una variable de su tipo correspondiente
			int idArma = rs.getInt(1);
			String nombre = rs.getString(2);
			int dano = rs.getInt(3);
			// Creamos un objeto de tipo Arma con estos datos y lo añadimos a su lista
			armas.add(new Arma(idArma, nombre, dano));
		}
		// Cerramos la conexion con la BD
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer los Poderes guardados en la BD, usar
	 * los datos para crear objetos de tipo Poder y guardarlos en un vector.
	 */
	private static void obtenerPoderes() throws Exception {
		// Borramos para que cada vez que se actualice no se dupliquen datos
		poderes.clear();
		// Conectamos con la BD
		conexion.conectarBD();
		// Obtenemos con un select todas los poderes
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from juegorol.poder");
		ResultSet rs = preparedStatement.executeQuery();
		// Mientras queden registros por leer
		while (rs.next()) {
			// Almacenamos cada dato en una variable de su tipo correspondiente
			int idPoder = rs.getInt(1);
			String nombre = rs.getString(2);
			int danoDefensa = rs.getInt(3);
			// Creamos un objeto de tipo Poder con estos datos y lo añadimos a su lista
			poderes.add(new Poder(idPoder, nombre, danoDefensa));
		}
		// Cerramos la conexion con la BD
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer los Characteres guardados en la BD, usar
	 * los datos para crear objetos de tipo Character y guardarlos en un vector.
	 */
	private static void obtenerCharacters() throws Exception {
		// Borramos para que cada vez que se actualice no se dupliquen datos
		characters.clear();
		// Conectamos con la BD
		conexion.conectarBD();
		// Obtenemos con un select todos los characteres
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from juegorol.character");
		ResultSet rs = preparedStatement.executeQuery();
		// Mientras queden registros por leer
		while (rs.next()) {
			// Almacenamos cada dato en una variable de su tipo correspondiente
			int idCharacter = rs.getInt(1);
			String nombre = rs.getString(2);
			int idAvatar = rs.getInt(3);
			int idArma = rs.getInt(4);
			int idPoder = rs.getInt(5);
			/*
			 * Un objeto de tipo Character esta a su vez formado por un objeto
			 * de tipo Avatar, otro de tipo Arma y otro de tipo Poder. En la BD
			 * se almacenan los identificadores de cada uno. Por ello, hay que
			 * buscar los objetos correspondientes a cada identificador.
			 */
			Avatar avatar = findAvatarById(idAvatar);
			Arma arma = findArmaById(idArma);
			Poder poder = findPoderById(idPoder);
			// Creamos un objeto de tipo Character con estos datos y lo añadimos a su lista
			Character character = new Character(idCharacter, nombre, avatar, arma, poder);
			characters.add(character);		
		}
		// Cerramos la conexion con la BD
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Avatar en un vector
	 * segun su identificador pasado por parametro. 
	 */
	private static Avatar findAvatarById(int idAvatar) {		
		for(int i = 0; i < avatares.size(); i++) {
			if(avatares.get(i).getIdAvatar() == idAvatar) {
				return avatares.get(i);
			}
		}
		return null;		
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Arma en un vector
	 * segun su identificador pasado por parametro. 
	 */
	private static Arma findArmaById(int idArma) {
		for(int i = 0; i < avatares.size(); i++) {
			if(armas.get(i).getIdArma() == idArma) {
				return armas.get(i);
			}
		}	
		return null;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Poder en un vector
	 * segun su identificador pasado por parametro. 
	 */
	private static Poder findPoderById(int idPoder) {
		for(int i = 0; i < poderes.size(); i++) {
			if(poderes.get(i).getIdPoder() == idPoder) {
				return poderes.get(i);
			}
		}	
		return null;	
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer las Preguntas guardadas en la BD, usar
	 * los datos para crear objetos de tipo Pregunta y guardarlos en un vector.
	 */
	private static void obtenerPreguntas() throws Exception {
		// Borramos para que cada vez que se actualice no se dupliquen datos
		preguntas.clear();
		// Conectamos con la BD
		conexion.conectarBD();
		// Obtenemos con un select todas las preguntas
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from juegorol.pregunta");
		ResultSet rs = preparedStatement.executeQuery();
		// Mientras queden registros por leer
		while (rs.next()) {
			// Almacenamos cada dato en una variable de su tipo correspondiente
			int idPregunta = rs.getInt(1);
			String texto = rs.getString(2);
			String textoConsecuencia1 = rs.getString(3);
			String textoConsecuencia2 = rs.getString(4);
			int valorConsecuencia1 = rs.getInt(5);
			int valorConsecuencia2 = rs.getInt(6);
			// Creamos un objeto de tipo Pregunta con estos datos y lo añadimos a su lista
			preguntas.add(new Pregunta(idPregunta, texto, textoConsecuencia1, textoConsecuencia2, 
					valorConsecuencia1, valorConsecuencia2));
		}
		// Cerramos la conexion con la BD
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer el Ranking guardado en la BD, usar
	 * los datos para crear objetos de tipo Ranking y guardarlos en un vector.
	 */
	private static void obtenerRanking() throws Exception {
		// Borramos para que cada vez que se actualice no se dupliquen datos
		ranking.clear();
		// Conectamos con la BD
		conexion.conectarBD();
		// Obtenemos con un select los 10 registros del Ranking con mas rondas
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from juegorol.ranking order by rondas desc limit 10");
		ResultSet rs = preparedStatement.executeQuery();
		// Mientras queden registros por leer
		while (rs.next()) {
			// Almacenamos cada dato en una variable de su tipo correspondiente
			int idRanking = rs.getInt(1);
			int idCharacter = rs.getInt(2);
			int rondas = rs.getInt(3);
			/*
			 * Dentro de cada objeto de tipo Ranking se almacenara el objeto
			 * de tipo Character que haya obtenido dicho record. Para ello
			 * lo buscamos segun su identificador.
			 */
			Character character = findCharacterById(idCharacter);
			ranking.add(new Ranking(idRanking, character, rondas));
		}
		// Cerramos la conexion con la BD
		conexion.cerrarBD();	
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Character en un vector
	 * segun su identificador pasado por parametro. 
	 */
	private static Character findCharacterById(int idCharacter) {
		for(int i = 0; i<characters.size(); i++) {
			if(characters.get(i).getIdCharacter() == idCharacter) {
				return characters.get(i);
			}
		}	
		return null;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra por pantalla como maximo los 10 mejores
	 * registros del ranking del juego. 
	 */
	private static void mostrarRanking() {	
		for(int i = 0; i < ranking.size(); i++) {
			System.out.println("Posicion " + (i + 1) + " - " + ranking.get(i).toString());
		}
	}

	/**
	 * Pre: ---
	 * Post: Este metodo pregunta al usuario que nueva idea quiere introducir
	 * y llama a los metodos correspondientes para recoger los datos y luego
	 * almacenarlos correctamente en la BD. 
	 */
	private static void guardarNuevasIdeas() {
		// Mostramos las nuevas ideas que el usuario puede registrar
		System.out.println("Insertar Avatar [1]");
		System.out.println("Insertar Arma [2]");
		System.out.println("Insertar Poder [3]");
		System.out.println("Insertar Pregunta [4]");
		// Segun la opcion que elija llamamos a los metodos correspondientes
		try {
			int respuesta = entrada.nextInt();
			// Si decide insertar un Avatar
			if (respuesta == 1) {
				Avatar avatar = pedirDatosAvatar();
				insertarAvatar(avatar);
				System.out.println("El avatar " + avatar.getNombre() + " ha sido insertado.");
			// Si decide insertar un Arma
			} else if (respuesta == 2) {
				Arma arma = pedirDatosArma();
				insertarArma(arma);
				System.out.println("El arma " + arma.getNombre() + " ha sido insertada.");
			// Si decide insertar un Poder
			} else if (respuesta == 3) {
				Poder poder = pedirDatosPoder();
				insertarPoder(poder);
				System.out.println("El poder " + poder.getNombre() + " ha sido insertado.");
			// Si decide insertar una Pregunta
			} else if (respuesta == 4) {
				Pregunta pregunta = pedirDatosPregunta();
				insertarPregunta(pregunta);
				System.out.println("La pregunta con texto: '" + pregunta.getTexto() 
					+ "' ha sido insertada.");
			} // Si introduce un numero incorrecto salta una advertencia 
			else {
				System.out.println("¡Introduce una opcion correcta, ¡por favor!");
			}
		// Si no introduce un numero salta una advertencia
		} catch (InputMismatchException e) {
			System.out.println("Introduce un numero correcto, ¡por favor!");
		} catch (Exception e) { e.printStackTrace();}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Avatar.
	 */
	private static Avatar pedirDatosAvatar() {
		entrada.nextLine();
		// Pedimos el nombre del Avatar
		System.out.println("Introduce el nombre del avatar:");
		String nombre = entrada.nextLine();
		System.out.println("Introduce la vida (numero entero) del avatar:");
		// Comprobamos que la vida que ha introducido el usuario es un numero
		while (true) {
			String vida = entrada.nextLine();
			// La vida debe ser un numero positivo
			if (isInteger(vida) && Integer.parseInt (vida) > 0) {
				return new Avatar (nombre, Integer.parseInt(vida));
			} else {
				System.out.println("Introduce un numero correcto, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Avatar y lo almacena en la BD.
	 */
	private static void insertarAvatar(Avatar avatar) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into avatar(nombre, vida) values (?, ?)");
		preparedStatement.setString(1, avatar.getNombre());
		preparedStatement.setInt(2, avatar.getVida());
		// Ejecutamos el insert
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Arma.
	 */
	private static Arma pedirDatosArma() {
		entrada.nextLine();
		// Pedimos el nombre del Arma
		System.out.println("Introduce el nombre del arma:");
		String nombre = entrada.nextLine();
		// Pedimos el da�o y hacemos las comprobaciones necesarias
		System.out.println("Introduce el daño del arma (numero entero entre 0 y 50):");
		while (true) {
			String dano = entrada.nextLine();
			/*
			 * El daño del arma debe ser un numero positivo y ademas no ser
			 * mayor de 50 para que no se rompa el equilibrio del juego.
			 */
			if (isInteger(dano) && Integer.parseInt (dano) > 0 && Integer.parseInt(dano) < 50) {
				return new Arma (nombre,Integer.parseInt(dano));
			} else {
				System.out.println("Introduce un numero correcto, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo comprueba si la palabra pasada como parametro
	 * contiene solo numeros o no.
	 */
	private static boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    } catch(NumberFormatException e){
	        return false;
	    }
	}

	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Arma y lo almacena en la BD.
	 */
	private static void insertarArma(Arma arma) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into arma(nombre, dano) values (?, ?)");
		preparedStatement.setString(1, arma.getNombre());
		preparedStatement.setInt(2, arma.getDanyo());
		// Ejecutamos el insert
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Poder.
	 */
	private static Poder pedirDatosPoder() {
		entrada.nextLine();
		// Pedimos el nombre del Poder
		System.out.println("Introduce el nombre del poder:");
		String nombre = entrada.nextLine();
		System.out.println(
				"Introduce el daño (numero entero negativo entre 0 y 50) " 
						+ "o defensa (numero entero positivo hasta -50) del poder:");
		while (true) {
			String danoDefensa = entrada.nextLine();
			/*
			 * El daño o defensa del poder debe ser un numero positivo y 
			 * ademas no ser mayor de 50 ni menor de  -50 para que no se 
			 * rompa el equilibrio del juego.
			 */
			if (isInteger(danoDefensa) && Integer.parseInt (danoDefensa) > -50
					&& Integer.parseInt(danoDefensa) < 50) {
				return new Poder (nombre, Integer.parseInt(danoDefensa));
			} else {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}

	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Poder y lo almacena en la BD.
	 */
	private static void insertarPoder(Poder poder) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into poder(nombre, danoDefensa) values (?, ?)");
		preparedStatement.setString(1, poder.getNombre());
		preparedStatement.setInt(2, poder.getDano_defensa());
		// Ejecutamos el insert
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Pregunta.
	 */
	private static Pregunta pedirDatosPregunta() {
		entrada.nextLine();
		// Pedimos el texto de la pregunta y de las 2 consecuencias
		System.out.println("Introduce el texto de la pregunta: ");
		String texto = entrada.nextLine();
		System.out.println("Introduce el texto de la consecuencia 1: ");
		String consecuencia1 = entrada.nextLine();
		System.out.println("Introduce el texto de la consecuencia 2: ");
		String consecuencia2 = entrada.nextLine();
		// Pedimos los valores de las 2 consecuencias 
		System.out.println("Introduce el valor de la consecuencia 1: ");
		int valorConsecuencia1 = 0;
		boolean valor1Introducido = false;
		// Comprobamos que el valor de la Consecuencia 1 es un numero
		while (!valor1Introducido) {
			try {
				valorConsecuencia1 = entrada.nextInt();
				valor1Introducido = true;
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
		entrada.nextLine();
		System.out.println("Introduce el valor de la consecuencia 2: ");
		// Comprobamos que el valor de la Consecuencia 2 es un numero
		while (true) {
			try {
				int valorConsecuencia2 = entrada.nextInt();
				// Se forma un objeto de tipo Pregunta y se devuelve
				return new Pregunta(texto, consecuencia1, consecuencia2, 
						valorConsecuencia1, valorConsecuencia2);
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Pregunta y lo almacena en la BD.
	 */
	private static void insertarPregunta(Pregunta pregunta) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into pregunta(" + "texto, " + "textoConsecuencia1, "
						+ "" + "textoConsecuencia2, valorConsecuencia1, " + "valorConsecuencia2) "
							+ "values (?, ?, ?, ?, ?)");
		preparedStatement.setString(1, pregunta.getTexto());
		preparedStatement.setString(2, pregunta.getTextoConsecuencia1());
		preparedStatement.setString(3, pregunta.getTextoConsecuencia2());
		preparedStatement.setInt(4, pregunta.getValorConsecuencia1());
		preparedStatement.setInt(5, pregunta.getValorConsecuencia2());
		// Ejecutamos el insert
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se ejecutado cuando el Usuario quiere jugar y se encarga
	 * de iniciar la partida ya sea con un Character previamente creado o con la
	 * creacion de uno nuevo. 
	 */
	private static void jugar(){
		// Si hay characters guardados en base de datos
		if(!characters.isEmpty()) {
			System.out.println("¿Quieres utilizar un character existente? si / no");
			entrada.nextLine();
			while(true) {	
				String respuesta = entrada.nextLine();
				if(respuesta.equalsIgnoreCase("si")) {
					// Si quiere elegir un Character previo
					iniciarPartida(elegirCharacter());
					return;				
				} else if (respuesta.equalsIgnoreCase("no")) {
					break;
				} else {
					System.out.println("Introduce una respuesta correcta");
				}
			}
		}
		// Si no quiere elegir un Character previo creamos uno
		iniciarPartida(crearCharacter());
	}

	/**
	 * Pre: --- 
	 * Post: Este metodo deja escoger al usuario un Character previamente creado
	 * para jugar su partida.
	 */
	private static Character elegirCharacter() {
		System.out.println("Introduce el numero del character que quieras utilizar");
		for(int i = 0; i < characters.size(); i++) {
			System.out.println("["+i+"] " + characters.get(i).getNombre());
		}
		int respuesta = entrada.nextInt();
		return characters.get(respuesta);
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de crear un nuevo objeto de tipo Character,
	 * guardarlo correctamente en la BD y de devolverlo para poder iniciar la 
	 * partida utilizandolo.  
	 */
	private static Character crearCharacter() {
		// Primero se pide el nombre
		System.out.println("Introduce el nombre de tu nuevo character:");
		String nombre = entrada.nextLine();
		// Despues se elige el Avatar (mediante un numero)
		System.out.println("Elige el avatar de tu nuevo character:");
		for(int i = 0; i < avatares.size(); i++) {
			System.out.println("["+i+"] " + avatares.get(i).toString());
		}
		int respuestaAvatar = entrada.nextInt();
		Avatar avatar = avatares.get(respuestaAvatar);
		// Despues se elige el Arma (mediante un numero)
		System.out.println("Elige el arma de tu nuevo character:");
		for(int i = 0; i < armas.size(); i++) {
			System.out.println("["+i+"] " + armas.get(i).toString());
		}
		int respuestaArma = entrada.nextInt();
		Arma arma = armas.get(respuestaArma);
		// Despues se elige el poder (mediante un numero)
		System.out.println("Elige el poder de tu nuevo character:");
		System.out.println("Si el numero es positivo te cura en cada ronda.");
		System.out.println("Si es negativo te haran menos daño cuando pierdas vida.");
		for(int i = 0; i < poderes.size(); i++) {
			System.out.println("["+i+"] " + poderes.get(i).toString());
		}
		int respuestaPoder = entrada.nextInt();
		Poder poder = poderes.get(respuestaPoder);
		/*
		 *  Finalmente formamos el objeto de tipo Character con su nombre y
		 *  con los tres objetos necesarios para construirlo.
		 */
		Character nuevoCharacter = new Character(nombre, avatar, arma, poder);
		/* 
		 * De esta manera guardamos en el objeto de tipo Character que
		 * acabamos de crear el id que le ha asignado automaticamente la BD.
		 */
		try {
			int idBaseDatos = guardarCharacter(nuevoCharacter);
			nuevoCharacter.setIdCharacter(idBaseDatos);
		} catch (Exception e) {}
		// Este metodo devuelve un Character nuevo para poder jugar con el
		return nuevoCharacter;
	}

	/**
	 * Pre: --- 
	 * Post: Este metodo se encarga de guardar un objeto de tipo Character 
	 * en la BD.
	 */
	private static int guardarCharacter(Character nuevoCharacter) throws Exception {
		// Conectamos con la BD
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into juegorol.character(nombre, idAvatar, "
									+ "idArma, idPoder) values (?, ?, ?, ?)", 
						Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, nuevoCharacter.getNombre());
		preparedStatement.setInt(2, nuevoCharacter.getAvatar().getIdAvatar());
		preparedStatement.setInt(3, nuevoCharacter.getArma().getIdArma());
		preparedStatement.setInt(4, nuevoCharacter.getPoder().getIdPoder());
		// Ejecutamos el insert
		preparedStatement.executeUpdate();
		/*
		 * Con esto obtenemos el id que ha asignado la base de datos para setearlo
		 * al character para luego poder guardarlo en el ranking
		 */
		ResultSet rs = preparedStatement.getGeneratedKeys();
	    rs.next();
	    int id = rs.getInt(1);
		System.out.println("Character guardado");
		// Cerramos la conexion con la BD
		conexion.cerrarBD();
		/*
		 *  Devuelve el ID que se ha generado para poder guardarlo en el objeto
		 *  de tipo Character correspondiente. 
		 */
		return id;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un Character (existente o recien creado) e inicia
	 * una partida con el. 
	 */
	private static void iniciarPartida(Character character) {
		// Desordenamos las preguntas para que cada partida sea aleatoria
		Collections.shuffle(preguntas);
		// Mostramos por pantalla el Character elegido.
		System.out.println(character.toString());
		/*
		 * El arma es defensa, su daño se resta al daño que te hacen
		 * El poder si es positivo te cura cada ronda y si es negativo 
		 * se resta al daño que te hacen.
		 */
		Arma arma = character.getArma();
		Poder poder = character.getPoder();
		int nPregunta = 0;
		while(true) {
			Pregunta pregunta = preguntas.get(nPregunta);
			// Si el poder es curativo siempre se utiliza
			boolean poderCurativo = character.getPoder().getDano_defensa() > 0 ? true : false;
			// Si el poder es defensivo solo se utiliza si pierdes vida
			boolean poderDefensivo = character.getPoder().getDano_defensa() < 0 ? true : false;
			// Mostramos el texto de la pregunta
			System.out.println(pregunta.getTexto());
			int respuesta = entrada.nextInt();
			int valorTotal = 0;
			if (respuesta == 1 || respuesta == 2) {	
				// Dependiendo de la respuesta mostramos una consecuencia u otra
				// y calculamos la vida actual
				valorTotal = calcularValorConsecuencia(respuesta, arma, poder, pregunta, poderDefensivo);
				/*
				 *  Le añadimos al character el valor que se le suma o resta a su vida 
				 *  tras calcularlo con el metodo anterior.
				 */
				character.getAvatar().setVida(character.getAvatar().getVida() + valorTotal);
				nPregunta++;
				// Si ademas el poder es curativo añadimos los puntos de vida correspondientes
				if (poderCurativo) {
					character.getAvatar().setVida(character.getAvatar().getVida() 
							+ poder.getDano_defensa());
					System.out.println("Tu poder curativo te restaura " + poder.getDano_defensa() 
						+ " puntos de vida.");
				}			
				System.out.println("Vida actual: " + character.getAvatar().getVida());
			} else {
				System.out.println("¡Introduce una opcion correcta, ¡por favor!");
			}
			// Si el personaje muere preguntamos si quiere guardar en ranking 
			if(character.getAvatar().getVida() <= 0) {
				System.out.println("HAS PERDIDO");
				gameOver(character, nPregunta);
				break;
			}
			
			// Si se llega al final de las preguntas el jugador ha ganado
			if(nPregunta == preguntas.size()) {
				System.out.println("HAS GANADO");
				gameOver(character, nPregunta);
				break;
			}	
		}	
	}

	/**
	 * Pre: ---
	 * Post: Este metodo calcula el valor real de la consecuencia teniendo
	 * en cuenta la actuacion del arma y del poder que tenga un Character.
	 */
	private static int calcularValorConsecuencia(int respuesta, Arma arma, Poder poder, 
			Pregunta pregunta, boolean poderDefensivo) {
		int valorTotal = 0;
		int valorConsecuencia = 0;	
		if (respuesta == 1) {
			System.out.println("Consecuencia: " + pregunta.getTextoConsecuencia1());
			valorConsecuencia = pregunta.getValorConsecuencia1();
		} else {
			System.out.println("Consecuencia: " + pregunta.getTextoConsecuencia2());
			valorConsecuencia = pregunta.getValorConsecuencia2();
		}
		boolean pierdesVida = valorConsecuencia < 0 ? true : false;	
		/*
		 * Si la consecuencia te resta vida, entra en juego el arma y te quitan 
		 * menos vida. El poder entra en juego tanto si su valor es positivo
		 * como negativo. Si tu poder tiene valor negativo tambien entra en juego
		 * porque te curara un poco en esa ronda.
		 */
		if (pierdesVida) { 
			System.out.println("Esta accion te quita " + Math.abs(valorConsecuencia) 
				+ " puntos de vida.");
			if (poderDefensivo) {
				/*
				 * El arma se suma porque es un numero positivo pero el poder se resta
				 * porque es un numero negativo.
				 */
				valorTotal = valorConsecuencia + arma.getDanyo() - poder.getDano_defensa();
				System.out.println("Gracias a tu arma y tu poder solo te quitan " + Math.abs(valorTotal) 
					+ " puntos de vida.");
			} else {
				valorTotal = valorConsecuencia + arma.getDanyo();
				System.out.println("Gracias a tu arma solo te quitan " + Math.abs(valorTotal) 
					+ " puntos de vida.");
			}
		} 
		// Si la consecuencia te suma vida, el arma no tiene efecto. 
		else {
			valorTotal = valorConsecuencia;
			System.out.println("Esta accion te suma " + valorTotal + " puntos de vida.");
		}
		return valorTotal;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se ejecuta una vez terminada la partida y 
	 * pregunta al usuario si quiere guardar su resultado en el ranking.
	 */
	private static void gameOver(Character character, int rondas) {
		System.out.println("GUARDAR ESTADO EN RANKING\n SI NO");
		entrada.nextLine();
		while(true) {
			String opcion = entrada.nextLine();
			if(opcion.equalsIgnoreCase("si")) {
				try {
					guardarRanking(character, rondas);
				} catch(Exception e) {}
				break;
			} else if (opcion.equalsIgnoreCase("no")) {
				break;
			} else {
				System.out.println("Introduce una opcion correcta, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de guardar en la BD un resultado
	 * de una partida. 
	 */
	private static void guardarRanking(Character character, int rondas) throws Exception {	
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into ranking(idCharacter, rondas) values (?, ?)");
		preparedStatement.setInt(1, character.getIdCharacter());
		preparedStatement.setInt(2, rondas);
		// Ejecutamos el insert
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
		System.out.println("Registro guardado en el ranking");	
	}
}
