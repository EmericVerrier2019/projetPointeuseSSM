package projetPointeuseSSM;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeClockSender {
	private Socket clientSocket;
	private ObjectOutputStream outputObjectStream;

	
	public TimeClockSender(InetSocketAddress socketServerAddress) throws IOException
	{
			clientSocket = new Socket(socketServerAddress.getAddress(),socketServerAddress.getPort());
			outputObjectStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
	}
	public void sentTicket(Ticket t) 
	{
		try {
			outputObjectStream.writeObject(t);
			Serialization.writeTicket(clientSocket.getOutputStream(), t);
			InputStream is = new FileInputStream(Serialization.getFILE_TICKETS());
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader buffer = new BufferedReader(isr);
		        
		    String line = buffer.readLine();
		    StringBuilder builder = new StringBuilder();
		        
		    while(line != null){
		       builder.append(line).append("\n");
		       line = buffer.readLine();
		    }    
		    String str = builder.toString();
		    System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Socket getClientSocket() 
	{
		return clientSocket;
	}
}
