import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class UDP_Client{

	public static void main(String args[]) throws Exception{
		// c:\> java UDPClient1 <mensagem> <endDst>
		DatagramSocket ds = new DatagramSocket();
		Scanner input = new Scanner(System.in);
		
		
		System.out.print("Digite sua mensagem: ");
		String mensagemVai = input.nextLine();
		if(mensagemVai.isEmpty()){
			mensagemVai = "nulo";
		}
		
		
		System.out.print("Digite o endereço de destino: ");
		String endDestino = input.nextLine();
		if(endDestino.isEmpty()){
			mensagemVai = "localhost";
		}
		
		
		int portaDst = 0;
		System.out.print("Deseja informar porta de destino? (S/N)");
		String respostaDaPorta = input.nextLine();
		
		if(respostaDaPorta.equalsIgnoreCase("s")){
			System.out.print("Digite o numero da porta de destino:");
			portaDst = input.nextInt();
			
		}else{
			portaDst = 6789;
		}
		
		
		try{
		//byte[] msgVai = args[0].getBytes();
		byte[] msgVai = mensagemVai.getBytes(); // tirando a opão de passar mensagem por argumento, agr sera passada pelo scanner
		InetAddress endDst = InetAddress.getByName(endDestino);
		//int portaDst = 6789;
		
		DatagramPacket pctVai = new DatagramPacket(msgVai, msgVai.length, endDst, portaDst);
		ds.send(pctVai);
		
		System.out.println("Enviei solicitacao ao Servidor\n");

		byte[] msgVem = new byte[1024];
		DatagramPacket pctVem = new DatagramPacket(msgVem, msgVem.length);
		ds.receive(pctVem);
		System.out.println("Recebi resposta do Servidor:\n");
		System.out.println("Chegou: " + new String(pctVem.getData()));

		Thread.sleep(5000);
		ds.close();
		
		}	catch(Exception e){
			
		}
		
	
	}	
	
}