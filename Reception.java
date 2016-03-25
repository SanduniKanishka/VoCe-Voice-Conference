/*
This is to recieve packet and send it to player class 

*/
import java.net.* ;

public class Reception extends Thread{
    MulticastSocket socket;
    InetAddress host;
    int packetsize;
    InitiateComponents ic;
	
    public Reception(DatagramSocket socket,InetAddress host,int packetsize,InitiateComponents ic){
    	this.socket=socket;
	this.host=host;
	this.ic=ic;
	this.packetsize=packetsize;
		
    }
		
    @Override
    public void run(){
    	MulticastSocket socket = null;
	Playback play=new Playback(ic);
        while(true){
            DatagramPacket packet = new DatagramPacket( new byte[packetsize], packetsize ) ;
            try{
            	socket = new MulticastSocket(20000);
		//InetAddress address = InetAddress.getByName("224.2.2.3");
		socket.joinGroup(host);
           // Receive a packet (blocking)
               socket.receive( packet );
               play.captureAudio(packet.getData());//send thiS to the Playback class
            }
            catch(Exception ee){
               System.out.println(ee);
            }
        }
    }
	
	
}
