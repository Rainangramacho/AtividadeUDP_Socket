import java.net.DatagramPacket;
import java.net.DatagramSocket;


class UDPServerThread extends Thread{
	
	private DatagramPacket pctVeio;
	//private DatagramPacket pctVai;
	
	public UDPServerThread(DatagramPacket pctVeio){
		this.pctVeio = pctVeio;
	}
	
	@Override
	public void run(){
		// essa linha veio do UDPServer3
		DatagramPacket pctVai = new DatagramPacket(pctVeio.getData(),pctVeio.getLength(),pctVeio.getAddress(),pctVeio.getPort());
		
		try{
			DatagramSocket dst = new DatagramSocket();
			dst.send(pctVai);  // essa linha veio do UDPServer3
			 Thread.sleep(8000);//esse sleep faz com que dê tempo de mostrar a quantidade de clientes ativos
			dst.close();
		}catch(Exception e){
			
		}
	}
}

