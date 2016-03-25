/*
 * This is to deserialise the packet and play back the sound 
 *
 */


import java.io.ByteArrayOutputStream;


public class Playback {
    InitiateComponents ic;
    long temp=-1;
	
    public Playback(InitiateComponents ic){
	this.ic=ic;
    }




    public void captureAudio(byte [] tempBuffer) {
	boolean stopCapture = false;
	ByteArrayOutputStream byteArrayOutputStream;
	
	
    
        try {
            long lost,now;
      
            stopCapture = false;
        
  
            try {
                now=bytesToLong(tempBuffer);
        	if(now>temp){ //--------------here the packets which has lesser sequence number then the current one will be discarded-------------- 
                    ic.sourceDataLine.write(tempBuffer, 8, tempBuffer.length-8);   //playing audio available in tempBuffer
                    lost=temp;
                    while(now-lost!=1){
                        System.out.println("Packet No "+ ++lost +" is discarded." +now);
                    }
                    temp=bytesToLong(tempBuffer); 
        	}
        		
        			  
        			  
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
      
        }catch (Exception e) {
            System.out.println(e);
        
        }
  
    }
    //get the sequence number from the packet. deserialising
    public long bytesToLong(byte[] tempBuffer) {
		
        long value=0;
            for (int i = 0; i <8; i++)
            {
               value = (value << 8) + (tempBuffer[i] & 0xff);
            }
            return value;
    }
}



