package projetPointeuseSSM;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeClockSender {
    private static Socket clientSocket;
    private static ObjectOutputStream outputObjectStream;


    public TimeClockSender(InetSocketAddress socketServerAddress) throws IOException
    {
            clientSocket = null;
            outputObjectStream = null;

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
    public void setClientSocket(Socket socket) throws IOException
    {
    	clientSocket = socket;
    	outputObjectStream = new ObjectOutputStream(socket.getOutputStream());
    }
    public void setClientSocket(InetAddress addr, int port) throws IOException
    {
    	if(addr != null) 
    	{
    		try 
    		{
    			clientSocket = new Socket(addr, port);
    			outputObjectStream = new ObjectOutputStream(clientSocket.getOutputStream());
    		}
    		catch(IOException exce) 
    		{
    			exce.printStackTrace();
    		}
    	}
    }
    public void setClientSocket(InetSocketAddress addrSock) throws IOException
    {
    	if(addrSock != null) 
    	{

    		clientSocket = new Socket(addrSock.getAddress(),addrSock.getPort());
    		outputObjectStream = new ObjectOutputStream(clientSocket.getOutputStream());

    	}
    }
    public Socket getClientSocket() 
    {
        return clientSocket;
    }
}