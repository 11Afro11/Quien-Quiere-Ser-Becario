import java.net.*;
import java.io.*;

public class MinimoClienteTCP {
	// Atributos de la clase:
	static Socket socket_datos;
	static String direccionServidor; // Nombre o dirección IP
	static int puerto;
	static PrintWriter out;
	static BufferedReader in;
	public MinimoClienteTCP() {
	}
	public static void main (String args[]) {
		boolean error=false;
		String mensajeSolicitud;
		String mensajeRespuesta = "";

		// Se piden 3 argumentos: dirección del servidor, puerto y mensaje a enviar.
		if (args.length<3) {
			System.err.println("Sintaxixs: MinimoClienteTCP <direccion-servidor> <puerto> <mensaje a enviar>");
			System.exit(-1);
		}
		// Dirección (IP o nombre) del servidor
		direccionServidor = args[0];
		// Puerto
		puerto = Integer.parseInt(args[1]);
		// Mensaje a enviar
		mensajeSolicitud = args[2];
		// 1 - Se abre el socket y se conecta a la dirección y puerto del servidor.
		try {
			socket_datos = new Socket (direccionServidor, puerto);
			// Se obtienen los flujos de lectura y escritura para recibir y enviar
			// mensajes.
			out = new PrintWriter (socket_datos.getOutputStream(), true);
			in = new BufferedReader (new
			InputStreamReader(socket_datos.getInputStream()));
		} 
		catch (UnknownHostException e){
			System.err.println ("Error: no se pudo encontrar al servidor " + direccionServidor);
			System.exit(-2);
		} 
		catch (IOException e){
			System.err.println("Error: no se pudo establecer la conexión con el servidor");
		}
		// (Continua en la siguiente transparencia ...)
		// (... continuación de MinimoClienteTCP)
		// 2 - Se escribe el mensaje de solicitud y leemos la respuesta:
		out.println(mensajeSolicitud);
		try {
			mensajeRespuesta = in.readLine();
		} 
		catch (IOException e) {
			System.err.println ("Error: no se pudo leer la respuesta.");
		}
		// 3 - Se cierra la conexión.
		try {
			in.close();
			out.close();
			socket_datos.close();
		} 
		catch (IOException e) {
			System.err.println ("Error: no se pudo cerrar la conexión.");
		}
		// Se muestra la respuesta:
		System.out.println("El mensaje enviado fue: " + mensajeSolicitud);
		System.out.println("El mensaje recibido fue: " + mensajeRespuesta);
	}
}
