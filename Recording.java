/*
* This class is to record what user says add header to the packet.
* Serializing is also done here.
*/

import java.io.ByteArrayOutputStream;
import java.net.* ;

public class Recording extends Thread{
	
    InetAddress host;
    MulticastSocket socket;
    int portClient2;
    InitiateComponents ic;
    boolean stopCapture = false;
    ByteArrayOutputStream byteArrayOutputStream;
	
    public Recording(MulticastSocket socket,InetAddress host,int portClient2,InitiateComponents ic){
        
	this.host=host;
	this.portClient2=portClient2;
	this.socket=socket;
	this.ic=ic;
    }
		
    @Override
    public void run(){
	try {
            long i=0;
	    stopCapture = false;
	       
	    byte tempBuffer[] = new byte[512];
	    tempBuffer =longToBytes(i,tempBuffer);//adding header
	    
	    try {
	        Transmition trans1 = new Transmition(socket,host,portClient2);
	        int readCount;
	        while (!stopCapture){					
                    readCount = ic.targetDataLine.read(tempBuffer, 8, tempBuffer.length-8);  //capture sound into tempBuffer strting from 8th bit
	                if (readCount > 0) {
                            trans1.transmit(tempBuffer);//send to transmit 
	                }	
	                i++;
	                tempBuffer = longToBytes(i,tempBuffer);//adding header
                }	              
	    } catch (Exception e) {
	        System.err.println("problems: danoja" + e);
	        System.exit(-1);
	    }			
	} catch (Exception e) {
	      System.err.println("Line unavailable: " + e);
	}
    }
	
//Serializing the array. add sequence number to first 8 bytes.(header)
    public byte[] longToBytes(long l,byte [] buffer) {
        for (int i = 7; i >= 0; i--) {
            buffer[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return buffer;
    }

}
