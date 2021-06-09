package projetPointeuseSSM;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import java.net.SocketOptions;

public class IpSettings extends InetSocketAddress
{

	public IpSettings(InetAddress addr, int port) 
	{
		super(addr, port);
	}
	public IpSettings(int port)
	{
		super(port);		
	}

}
