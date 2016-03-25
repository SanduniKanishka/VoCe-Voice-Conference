/*
This is to recieve packet and send it to player class 

*/
import java.net.* ;

public class Reception extends Thread{
    DatagramSocket socket;
    int packetsize;
    InitiateComponents ic;
	
    public Reception(DatagramSocket socket,int packetsize,InitiateComponents ic){
    	this.socket=socket;
	
	this.ic=ic;
	this.packetsize=packetsize;
		
    }
		
    @Override
    public void run(){
	Playback play=new Playback(ic);
        while(true){
            DatagramPacket packet = new DatagramPacket( new byte[packetsize], packetsize ) ;
            try{
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