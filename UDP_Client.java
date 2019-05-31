import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class UDP_Client{

	public static void main(String args[]) throws Exception{
		
		DatagramSocket dsocket = new DatagramSocket();
		Scanner input = new Scanner(System.in);
		String mensagemSair = "Cliente Saindo...";
		byte[] msgVai = new byte [1024];
		byte[] endD = new byte [1024];
		byte[] portArg = new byte [1024];
		String mensagemVai = null;
		String endDestino = null;
		InetAddress endDst = null;
		String endString = null;
		String portArgInt = null;
		
		
		if(args.length == 0){ 
			
			mensagemVai = "ping";
			msgVai = mensagemVai.getBytes();
			
		} else if(args.length >=1){ 
			msgVai = args[0].getBytes();
			
		}
		
		
		if(args.length <=1){ 
			
			endDestino = "localhost";
			endDst = InetAddress.getByName(endDestino);
	
		} else if(args.length >1){
			endD = args[1].getBytes();
			endString = new String(endD);
			endDst = InetAddress.getByName(endString);
		}
			
			
		int portaDst = 0;
		if(args.length <=2){

			portaDst = 6789; 
			
			
		}else if(args.length >2){
			portArg = args[2].getBytes();
			portArgInt = new String(portArg);
			portaDst = Integer.parseInt(portArgInt);
			
		}
		
		
		try{
		
		
		DatagramPacket pctVai = new DatagramPacket(msgVai, msgVai.length, endDst, portaDst);
		dsocket.send(pctVai);
		
		System.out.println("Enviei solicitacao ao Servidor\n");

		byte[] msgVem = new byte[1024];
		DatagramPacket pctVem = new DatagramPacket(msgVem, msgVem.length);
		dsocket.receive(pctVem);
		System.out.println("Recebi resposta do Servidor:\n");
		System.out.println("Chegou: " + new String(pctVem.getData()));

		Thread.sleep(5000);
		dsocket.close();
		
		}	catch(Exception e){
			
		}
		
	
	}	
	
}