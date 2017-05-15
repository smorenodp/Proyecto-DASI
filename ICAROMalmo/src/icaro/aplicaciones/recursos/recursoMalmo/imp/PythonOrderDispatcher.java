package icaro.aplicaciones.recursos.recursoMalmo.imp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.agentes.agenteAplicacionAgteRecolectorCognitivo.tareas.ConseguirInformacionDelEntorno;

public class PythonOrderDispatcher implements OrderDispatcher 
{
	private ServerSocket serversocket, serversocketAck;
	private Socket inSocket, inSocketAck, outSocket;
	BufferedReader inputData, inputDataAck;
	protected DataOutputStream outputData;
	protected Process pythonDispatcherThread;
	protected ArrayList<String> commandAcks;
	public static Hashtable<String,Coordinate> agentes = new Hashtable<String,Coordinate>();
	public static Hashtable<String,Integer> evaluaciones = new Hashtable<String,Integer>();

	public PythonOrderDispatcher(String pythonPath, String pythonScript, int port) throws IOException
	{
		try 
		{
			//Iniciamos el proceso de inicializacion de la parte de python
			String[] command = {pythonPath, pythonScript};
			pythonDispatcherThread = Runtime.getRuntime().exec(command);
			//damos tiempo para que se inicie tranquilamente
			Thread.sleep(10000);
			//creamos el outSocket para comunicarnos con la interfaz de python
			outSocket = new Socket("localhost", port);
			//creamos el inSocket para recibir los mensajes de python
			serversocket = new ServerSocket(port + 1);
			inSocket = new Socket();
			inSocket = serversocket.accept();
			//creamos la clase con la que recibimos los mensajes asociados a inSocket
			inputData = new BufferedReader(new InputStreamReader(inSocket.getInputStream()));
			
			new Thread(){
				public void run(){
					receiveCommand();
				}
			}.start();
			//creamos el inSocket para recibir los mensajes de python
			serversocketAck = new ServerSocket(port + 2);
			inSocketAck = new Socket();
			inSocketAck = serversocketAck.accept();
			//creamos la clase con la que recibimos los mensajes asociados a inSocket
			inputDataAck = new BufferedReader(new InputStreamReader(inSocketAck.getInputStream()));
			//Flujo de datos hacia la interfaz de python
			outputData = new DataOutputStream(outSocket.getOutputStream());
			//iniciamos la estructura de almacenamiento de acks de comandos finalizados
			commandAcks = new ArrayList<>();
		} 
		catch (UnknownHostException e)
		{
			System.err.println("Capturada la excepci�n al crear el socket ");
		}
		catch (IOException e)
		{
			System.err.println("Capturada la excepci�n al crear el socket ");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(pythonDispatcherThread.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(pythonDispatcherThread.getErrorStream()));

			// read the output from the command
			//System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		}
		catch (InterruptedException e) 
		{
			System.err.println("Capturada la excepci�n al esperar ");
		}
	}

	public synchronized void sendCommand(String order) 
	{
		try
		{            
			//Se manda la orden a la interfaz de python
			outputData.writeUTF(order);
		}
		catch (Exception e)
		{
			System.err.println("Capturada la excepci�n al mandar la orden a la interfaz python");
		}
	}

	public ArrayList<String> getAcks()
	{
		return commandAcks;
	}

	public void updateAcks()
	{
		try 
		{
			while(inputDataAck.ready())
			{
				commandAcks.add(inputDataAck.readLine());
			}
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeDispatcher()
	{
		try
		{
			//mandamos el mensaje de cierre de conexion
			outputData.writeUTF("end");
			//cerramos el outSocket para finalizar la comunicacion
			outSocket.close();
			inSocket.close();
			//damos tiempo para que se cierre tranquilamente
			Thread.sleep(200);
			//eliminamos los subprocesos si se queda con los ojos para los lados
			//if(pythonDispatcherThread.isAlive())
			//pythonDispatcherThread.destroyForcibly();
		}
		catch (IOException e) 
		{
			System.err.println("Capturada la excepci�n al cerrar el socket ");
		}
		catch (InterruptedException e) 
		{
			System.err.println("Capturada la excepci�n al esperar ");
		}
	}

	@Override
	public void receiveCommand() {
		// TODO Auto-generated method stub
		while(true){
			try {
				/*while(!inputData.ready()){
					System.out.println("I'm not ready!!");
				}*/
				String msg = inputData.readLine();
				if(msg == null){
					JOptionPane.showMessageDialog(null, "Fallos en la comunicaci�n...");
				}
				else{
					new Parser(msg).start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class Parser extends Thread{
		private String msg;
		public Parser(String msg){
			this.msg = msg;
		}
		public void run(){
			String[] lines = msg.split("_");
			if(lines[0].equalsIgnoreCase("ap")){
				ArrayList<String> manzanas = new ArrayList<String>();
				ClaseGeneradoraRecursoMalmo.buildInformation(lines[1], manzanas);
				ConseguirInformacionDelEntorno.manzanas = ClaseGeneradoraRecursoMalmo.parseManzanas(manzanas);
			}
			else if(lines[0].equalsIgnoreCase("ag")){
				String [] parts = msg.split("\\[");
				parts = parts[1].split(",");
				agentes.put(lines[1], 
						new Coordinate(
								Double.parseDouble(parts[0]),
								Double.parseDouble(parts[2].split("\\]")[0]),
								Double.parseDouble(parts[1])));
			}
			else if(lines[0].equalsIgnoreCase("eval")){
				if(lines[1].equals("Failed")){
					
				}
				else{
					while(evaluaciones.containsKey(lines[1])){}
					evaluaciones.put(lines[1], Integer.parseInt(lines[2]));						
				}
			}
		}
	}
	
	
}
