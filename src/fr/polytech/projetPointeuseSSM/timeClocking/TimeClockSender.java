package fr.polytech.projetPointeuseSSM.timeClocking;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.polytech.projetPointeuseSSM.application.Serialization;
/**
 * 
 * a class used to send Tickets to the remote application.
 * clientSocket is Socket object which is used to send ticket to the remote application, the socket isn't directly set to the TimeClock launching
 * outputObjectStream is an ObjectOutputStream which is used to write Ticket in the socket output channel, storage. Both of these attributes are static attributes
 *
 */
public class TimeClockSender {

    private static Socket clientSocket;
    private static ObjectOutputStream outputObjectStream;


    public TimeClockSender(InetSocketAddress socketServerAddress) throws IOException
    {
            clientSocket = null;
            outputObjectStream = null;

    }
    /**
     * @param t the ticket sent to the remote application, this ticket is serialized before the sending to keep an eye on it if the timeclock or the remote application 
     * is closed unexpectedly.
     */
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
    /**
     * @param socket which will be used to initialize clientSocket, At the end of this method, the socket is usable
     * @throws IOException in case where the remote address or/port of the socket param is not accessible.
     */
    public void setClientSocket(Socket socket) throws IOException
    {
    	clientSocket = socket;
    	outputObjectStream = new ObjectOutputStream(socket.getOutputStream());
    }
    /**
     * @param addr ip address of the remote machine which will receive the Ticket objects later
     * @param port tcp port of the remote machine which will receive the Ticket objects later
     * @throws IOException in case where the remote port or address is not accessible, the exception are managed in a windows for the user understand the cause of the problem
     */
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
    /**
     * @param addrSock a socket Adress object which could be saved later in the parameters serialized and stored in a file
     * @throws IOException in case where the specified InetSocketAddress is not usable to send data, one more time, the exception throwed aren't directly managed here
     */
    public void setClientSocket(InetSocketAddress addrSock) throws IOException
    {
    	if(addrSock != null) 
    	{

    		clientSocket = new Socket(addrSock.getAddress(),addrSock.getPort());
    		outputObjectStream = new ObjectOutputStream(clientSocket.getOutputStream());

    	}
    }
    /**
     * @return the client socket which can be a null object in some cases, so the value returned should be tested. 
     */
    public Socket getClientSocket() 
    {
        return clientSocket;
    }
}