import java.net.DatagramPacket;
import java.net.DatagramSocket;


class UDPServerThread extends Thread{
	
	private DatagramPacket pctVeio;
	
	
	public UDPServerThread(DatagramPacket pctVeio){
		this.pctVeio = pctVeio;
	}
	
	@Override
	public void run(){
		
		DatagramPacket pctVai = new DatagramPacket(pctVeio.getData(),pctVeio.getLength(),pctVeio.getAddress(),pctVeio.getPort());
		
		try{
			DatagramSocket dst = new DatagramSocket();
			dst.send(pctVai);  
			 Thread.sleep(6000);
			dst.close();
		}catch(Exception e){
			
		}
	}
}

