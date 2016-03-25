/*
 *  This is the main class which runs the application
 *
 *  Instructions to run:
 *  Pass the IP address as command line arguments.
 *  Eg: >>javac UDPClient IP address
 */
 
 import java.net.* ;


public class Client extends Thread
{
	
   //Give a standard packet size
   private final static int packetsize = 512;
   private static DatagramSocket socket;
   public static void main( String args[] )
   {
      // Check the whether the arguments are given
      if( args.length != 1 )
      {
         System.out.println( "usage: DatagramServer host" ) ;
         return ;
      }
      
      try
      {
         
         int port = 20000;
        
         // Convert the argument to ensure that is it valid
         InetAddress host = InetAddress.getByName( args[0] ) ;
         
         // Construct the socket
         socket = new DatagramSocket( port ) ;
         
         //Initiating the components
		 InitiateComponents ic = new InitiateComponents();
		 ic.IniComp();
		 
		 //Starting Threads to record and send packets
         Recording recod =new Recording(socket,host,port,ic);
         recod.start();
         
         //Starting thread to recieve packets and play        
         Reception rcp1=new Reception(socket,packetsize,ic);
         rcp1.start();      

      }
      catch(UnknownHostException | SocketException ee){
         System.out.println(ee);
      }
      finally{
    	  //socket.close();
      }
          
     
    
  }
}