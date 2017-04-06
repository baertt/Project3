package guiController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class WaitingExample {
	public boolean testConnection(String site) {
		Socket socket = new Socket();
		InetSocketAddress addr = new InetSocketAddress(site, 80);
		try {
	        socket.connect(addr,10000);
	        return true;
	    } catch (IOException e) {
	        return false;
	    } finally {
	        try {
	        	socket.close();
	        } catch (IOException e) {
	        	
	        }
	    }
	}
}
