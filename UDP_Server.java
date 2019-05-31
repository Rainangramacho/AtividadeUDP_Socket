import java.io.IOException;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;
import java.util.ArrayList;


public class  UDP_Server{
	
	public static void main(String args[]) throws Exception {
		
		
		DatagramSocket dsocket = null;
		Scanner input = new Scanner(System.in);
		int porta =0;
		String portServerString = "";
		byte[] portServerArg = new byte [1024];
		
		if(args.length == 0){ 
			porta = 6789;
		
		} else if(args.length >=1){
			portServerArg = args[0].getBytes();
			portServerString = new String(portServerArg);
			porta = Integer.parseInt(portServerString);
		}
		
		try {
			
			dsocket = new DatagramSocket(porta);
			  
		
		
		System.out.printf("Aguardando Cliente na porta %d...\n",dsocket.getLocalPort());
		
		
		byte[] msg = new byte[1024];
		
		ArrayList<UDPServerThread> threads = new ArrayList<>();
		
      new Thread(new Runnable() {
        @Override
        public void run() {
			
          while (true) {
            int clientes = 0;
            try {
             for (UDPServerThread thread : threads) {
              if (thread.isAlive()) {
                  clientes++;
                }
              }
              System.out.println("Conexões ativas: " + clientes);
              Thread.sleep(2000);
            } catch (InterruptedException e) {
              
            }
          }
        }
      }).start();

		
		while(true){
		DatagramPacket pctVeio = new DatagramPacket(msg, msg.length);
		dsocket.receive(pctVeio);
		
		System.out.println("Recebi solicitacao do Cliente:" +pctVeio.getAddress());
		
		UDPServerThread thread = new UDPServerThread(pctVeio);
        threads.add(thread);
        thread.start();
		
		System.out.println("Enviei resposta ao Cliente\n");
		
		
		
		
		
		}
		}catch(Exception e){
		}
		
	
	}	
	
}