package projetPointeuseSSM;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Socket getClientSocket() 
	{
		return clientSocket;
	}
}
