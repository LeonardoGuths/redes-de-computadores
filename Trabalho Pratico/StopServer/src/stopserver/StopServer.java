package stopserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.Random;


public class StopServer {


    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(60000)) {
            System.out.println("Servidor do STOP rodando...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(listener.accept(), '1'));
                pool.execute(game.new Player(listener.accept(), '2'));
            }
        }
    }
}

class Game {

    Player currentPlayer;
    Random random = new Random();
    char letter = (char)(random.nextInt(26) + 'A');
    
    int p1points=-1, p2points=-1;
    char winner;

    class Player implements Runnable {
        char name;
        Player opponent;
        Socket socket;
        Scanner input;
        PrintWriter output;
        

        public Player(Socket socket, char name) {
            this.socket = socket;
            this.name = name;
        }

        @Override
        public void run() {
            try {
            	
                setup();
                processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (opponent != null && opponent.output != null) {
                    opponent.output.println("OTHER_PLAYER_LEFT");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
                
            }
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + name);
            
            if (this.name == '1') {
                currentPlayer = this;
                output.println("MESSAGE Aguardando um oponente conectar...");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
                output.println("START " + letter + " 50" );
                opponent.output.println("START " + letter + " 50" );
            }
        }

      
        
        private void processCommands() {
            while (input.hasNextLine()) {
                var command = input.nextLine();
               
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("STOP")) {
                	try {
                    	opponent.output.println("OPPONENT_WORDS " + command.substring(5));
                    } catch (IllegalStateException e) {
                        output.println("MESSAGE " + e.getMessage());
                    }
                	
                }else if(command.startsWith("POINTS")) {
                	if(this.name=='1') {
                		p2points=Integer.parseInt(command.substring(7));
                		
                		if(p1points>=0) {
                			if(p1points>p2points)
                			{
                				output.println("WINNER 1");
                				opponent.output.println("WINNER 1");
                			}
                			if(p1points<p2points)
                			{
                				output.println("WINNER 2");
                    			opponent.output.println("WINNER 2");
                			}
                			else {
                				output.println("WINNER 0");
                    			opponent.output.println("WINNER 0");
                			}
                		} 
                	}
                	else {
                		p1points=Integer.parseInt(command.substring(7));
                		if(p2points>=0) {
                			if(p1points>p2points)
                			{
                				output.println("WINNER 1");
                				opponent.output.println("WINNER 1");
                			}
                			if(p1points<p2points)
                			{
                				output.println("WINNER 2");
                    			opponent.output.println("WINNER 2");
                			}
                			else {
                				output.println("WINNER 0");
                    			opponent.output.println("WINNER 0");
                			}
                		} 
                	}
                }
            }
        } 
    }
}