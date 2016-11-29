//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

///////////////////////////////
import java.net.DatagramSocket
import java.net.DatagramPacket
import java.newInetAddres

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		byte []buferEnvio ;
		byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		DatagramSocket socket = null;
		DatagramPacket packquet = null;
		DatagramPacket mod = null;
		InetAddres direccion = null;

		String frase_modificada;

		try{
			socket = new DatagramSocket();
		}
		catch(IOException e){
			System.err.println("Error de entrada salida al abrir el socket");
		}

		try{
			direccion = InetAddres.getByNane("localhost");
		}
		catch(UnknownHostException e){
			System.err.println("Error al recuperar la dicerrcion");
		}
		buferEnvio = "Al monte del volcan deberas ir sin demora".getBytes();

		try{
			packquet = new DatagramPacket(buferEnvio,buferEnvio.length,direccion,port);
			socket.send(packquet);

			mod = new DatagramPacket(buferRecepcion,buferRecepcion.length);
			socket.receive(mod);
		}
		catch(IOException e){
			System.err.println("Error de entrada salida al abrir al socket");
		}
	}
}
