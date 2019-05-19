import java.io.IOException;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;


public class  UDP_Server{
	
	public static void main(String args[]) throws Exception {
		
		//DatagramSocket ds = new DatagramSocket(6789);
		DatagramSocket dsocket = null;
		Scanner input = new Scanner(System.in);
		int porta =0;
		System.out.print("Deseja informar porta? (S/N)");
		String respostaDaPorta = input.nextLine();
		
		try {
			if(respostaDaPorta.equalsIgnoreCase("s")) {
			System.out.print("Digite o numero para a porta: ");
			porta = input.nextInt();
			dsocket = new DatagramSocket(porta);
		  }
			  else{
				dsocket = new DatagramSocket();
		  }
		
		
		System.out.printf("Aguardando Cliente na porta %d...\n",dsocket.getLocalPort());
		
		int clientes = 0;
		byte[] msg = new byte[1024];
		
		while(true){
		DatagramPacket pctVeio = new DatagramPacket(msg, msg.length);
		dsocket.receive(pctVeio);
		
		System.out.println("Recebi solicitacao do Cliente...\n");
		
		DatagramPacket pctVai = new DatagramPacket(pctVeio.getData(),pctVeio.getLength(),pctVeio.getAddress(),pctVeio.getPort());
		dsocket.send(pctVai);
		
		System.out.println("Enviei resposta ao Cliente\n");
		Thread.sleep(2000);
		new UDPServerThread(pctVeio).start();
		
		//clientes ++;
		//System.out.printf("Estou atendendo %d ",clientes);
		
		
		
		
		}
		}catch(Exception e){
		}
		//ds.close();
	
	}	
	
}