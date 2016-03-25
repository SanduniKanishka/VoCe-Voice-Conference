/*
*This is to send the packet to the destination IP address
*/

import java.net.* ;

public class Transmition{
    InetAddress host;
    DatagramSocket socket;
    int portClient2;
	
    public Transmition(DatagramSocket socket,InetAddress host,int portClient2){
	this.host=host;
	this.portClient2=portClient2;
	this.socket=socket;
    }
	
    public void transmit(byte [] audio) throws SocketException{
    	
        try{
            DatagramPacket packet11 = new DatagramPacket(audio,audio.length,host,portClient2 );
    		
            socket.send( packet11 ) ;
    		
    	}
    	catch(Exception ee){
            System.out.println(ee );
    	}
    	
    }
	
}