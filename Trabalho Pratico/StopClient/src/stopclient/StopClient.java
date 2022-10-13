package stopclient;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

public class StopClient {

    private JFrame frame = new JFrame("Stop");
    private JTextField messageLabel = new JTextField("...");
    TitledBorder title;
    JLabel principal, happen, tempoRestante, pontosLabel;
    boolean apertei=false, stopped=false;
    Cronometro cronometro;
    Thread t;
    JTextField Nome, Objeto, Cor, Animal, Cep, Comida, pontosField;
    JButton stop, pontosButton;
    
    Border blackline = BorderFactory.createLineBorder(Color.black);

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public StopClient(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 60000);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        
		frame.setLayout (new GridBagLayout());
		GridBagConstraints p = new GridBagConstraints();
		p.fill = GridBagConstraints.BOTH; 

        //-----------------PAINEL DA ESQUERDA----------------------------
		
		JPanel pEsq = new JPanel();
		pEsq.setBackground(Color.LIGHT_GRAY);
		pEsq.setLayout(new GridBagLayout());
		GridBagConstraints propEsq = new GridBagConstraints();
		pEsq.setVisible(true);
		propEsq.fill = GridBagConstraints.BOTH; 
	       
	    p.weightx = 0.3; 
		p.weighty = 1;
		p.gridx = 0; 
		p.gridy = 0;
		frame.add(pEsq, p);

        //-----------------PAINEL DO MEIO----------------------------
		
		JPanel pMeio = new JPanel();
        pMeio.setBackground(Color.GRAY);
        pMeio.setVisible(true);
        pMeio.setLayout(new GridBagLayout());
        GridBagConstraints propriedades = new GridBagConstraints();
        
		        JPanel pMeioTitulo = new JPanel();
				pMeioTitulo.setBackground(Color.LIGHT_GRAY);
				pMeioTitulo.setVisible(true);
				
			        principal = new JLabel("STOP");
			        principal.setFont( new Font("Tahoma", Font.BOLD, 32));
			        principal.setForeground(new Color(25, 25, 150));
			        principal.setVisible(true);
					pMeioTitulo.add(principal);
					
				propriedades.fill = GridBagConstraints.BOTH; 
			    propriedades.weightx = 1; 
			    propriedades.weighty = 0.03;
			    propriedades.gridx = 0; 
			    propriedades.gridy = 0;				
			    pMeio.add(pMeioTitulo, propriedades);
			    
				JPanel pMeioCima = new JPanel();
				pMeioCima.setBackground(Color.LIGHT_GRAY);
				pMeioCima.setVisible(true);
				
					title = BorderFactory.createTitledBorder(blackline, "Letra: [?]");
					title.setTitleJustification(TitledBorder.CENTER);
					title.setTitleFont(new Font("Tahoma", Font.BOLD, 30));
					pMeioCima.setBorder(title);
					
					pMeioCima.setLayout(new GridBagLayout());
					GridBagConstraints pMC = new GridBagConstraints();
					
						JPanel pMeioCima1 = new JPanel();
						pMeioCima1.setBackground(Color.LIGHT_GRAY);
						pMeioCima1.setVisible(true);
						
						JLabel AX = new JLabel("Nome ");
						AX.setFont( new Font("Tahoma", Font.BOLD, 25));
				        AX.setForeground(new Color(25, 25, 150));
				        AX.setVisible(true);
						pMeioCima1.add(AX);
						
						Nome = new JTextField("");
						Nome.setPreferredSize(new Dimension(300,35));
						pMeioCima1.add(Nome);
						
						pMC.weightx = 1; 
						pMC.weighty = 0.1;
						pMC.gridx = 0; 
						pMC.gridy = 0;
						pMeioCima.add(pMeioCima1, pMC);
						
						JPanel pMeioCima2 = new JPanel();
						pMeioCima2.setBackground(Color.LIGHT_GRAY);
						pMeioCima2.setVisible(true);
						
						JLabel DX = new JLabel("Objeto ");
						DX.setFont( new Font("Tahoma", Font.BOLD, 25));
				        DX.setForeground(new Color(25, 25, 150));
				        DX.setVisible(true);
						pMeioCima2.add(DX);
						
						Objeto = new JTextField("");
						Objeto.setPreferredSize(new Dimension(300,35));
						pMeioCima2.add(Objeto);
						
						pMC.weightx = 1; 
						pMC.weighty = 0.1;
						pMC.gridx = 0; 
						pMC.gridy = 1;
						pMeioCima.add(pMeioCima2, pMC);
						
						JPanel pMeioCima3 = new JPanel();
						pMeioCima3.setBackground(Color.LIGHT_GRAY);
						pMeioCima3.setVisible(true);
						
						JLabel SR = new JLabel("Cor ");
						SR.setFont( new Font("Tahoma", Font.BOLD, 25));
				        SR.setForeground(new Color(25, 25, 150));
				        SR.setVisible(true);
						pMeioCima3.add(SR);
						
						Cor = new JTextField("");
						Cor.setPreferredSize(new Dimension(300,35));
						pMeioCima3.add(Cor);
						
						pMC.weightx = 1; 
						pMC.weighty = 0.1;
						pMC.gridx = 0; 
						pMC.gridy = 2;
						pMeioCima.add(pMeioCima3, pMC);
						
						JPanel pMeioCima4 = new JPanel();
						pMeioCima4.setBackground(Color.LIGHT_GRAY);
						pMeioCima4.setVisible(true);

						JLabel SP = new JLabel("Animal ");
						SP.setFont( new Font("Tahoma", Font.BOLD, 25));
				        SP.setForeground(new Color(25, 25, 150));
				        SP.setVisible(true);
						pMeioCima4.add(SP);
						
						Animal = new JTextField("");
						Animal.setPreferredSize(new Dimension(300,35));
						pMeioCima4.add(Animal);
						
						pMC.weightx = 1; 
						pMC.weighty = 0.1;
						pMC.gridx = 0; 
						pMC.gridy = 3;
						pMeioCima.add(pMeioCima4, pMC);
						
						JPanel pMeioCima5 = new JPanel();
						pMeioCima5.setBackground(Color.LIGHT_GRAY);
						pMeioCima5.setVisible(true);

						JLabel SI = new JLabel("CEP ");
						SI.setFont( new Font("Tahoma", Font.BOLD, 25));
				        SI.setForeground(new Color(25, 25, 150));
				        SI.setVisible(true);
						pMeioCima5.add(SI);
						
						Cep = new JTextField("");
						Cep.setPreferredSize(new Dimension(300,35));
						pMeioCima5.add(Cep);
						
						pMC.weightx = 1; 
						pMC.weighty = 0.1;
						pMC.gridx = 0; 
						pMC.gridy = 4;
						pMeioCima.add(pMeioCima5, pMC);
						
						JPanel pMeioCima6 = new JPanel();
						pMeioCima6.setBackground(Color.LIGHT_GRAY);
						pMeioCima6.setVisible(true);

						JLabel IP = new JLabel("Comida ");
						IP.setFont( new Font("Tahoma", Font.BOLD, 25));
				        IP.setForeground(new Color(25, 25, 150));
				        IP.setVisible(true);
						pMeioCima6.add(IP);
						
						Comida = new JTextField("");
						Comida.setPreferredSize(new Dimension(300,35));
						pMeioCima6.add(Comida);
						
						pMC.weightx = 1; 
						pMC.weighty = 0.1;
						pMC.gridx = 0; 
						pMC.gridy = 5;
						pMeioCima.add(pMeioCima6, pMC);
				
				propriedades.fill = GridBagConstraints.BOTH; 
		        propriedades.weighty = 0.7; 
		        propriedades.gridy = 1;			
		        pMeio.add(pMeioCima, propriedades);
				
				JPanel pMeioMeio = new JPanel();
				pMeioMeio.setBackground(Color.LIGHT_GRAY);
				pMeioMeio.setVisible(true);
				pMeioMeio.setLayout(new GridLayout(2,0));
				
					happen = new JLabel ("Mensagem do servidor:");
					happen.setFont(new Font("Tahoma", Font.BOLD, 25));
			        happen.setVisible(true);
			        pMeioMeio.add(happen);
			        
			        messageLabel = new JTextField("*server message*");
			        messageLabel.setPreferredSize(new Dimension(200, 24));
			        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			        messageLabel.setEditable(false);
			        messageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			        pMeioMeio.add(messageLabel);
				
				propriedades.fill = GridBagConstraints.BOTH;
		        propriedades.weighty = 0.17;
		        propriedades.gridy = 2;
		        pMeio.add(pMeioMeio, propriedades);
		        
				JPanel pMeioBaixo = new JPanel();
				pMeioBaixo.setBackground(Color.LIGHT_GRAY);
				pMeioBaixo.setVisible(true);
				
					pontosLabel = new JLabel ("Avalie quantas são válidas: ");
					pontosLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
					pontosLabel.setVisible(false);
			        pMeioBaixo.add(pontosLabel);
			        
			        pontosField = new JTextField("");
			        pontosField.setPreferredSize(new Dimension(100, 32));
			        pontosField.setHorizontalAlignment(SwingConstants.CENTER);
			        pontosField.setBorder(BorderFactory.createLineBorder(Color.black));
			        pontosField.setVisible(false);
			        pMeioBaixo.add(pontosField);
			        
			        pontosButton = new JButton("Avaliar");
			        pontosButton.setPreferredSize(new Dimension(90, 32));
			        pontosButton.setVisible(false);
			        pontosButton.addMouseListener(new MouseAdapter() {
		                public void mousePressed(MouseEvent e) {
		                	try {
		                	     if (Integer.parseInt(pontosField.getText())>=0 && Integer.parseInt(pontosField.getText())<=6)
		                	     {
			                	     out.println("POINTS " + pontosField.getText());
			                	     pontosButton.setEnabled(false);
		                	     }
		                	     else
		                	    	 JOptionPane.showMessageDialog(frame, "Por favor insira um número válido. (0-6)");
		                	}
		                	catch (NumberFormatException e1) {
		                		JOptionPane.showMessageDialog(frame, "Por favor insira um número válido. (0-6)");
		                	}
		                }
		            });
			        
			        pMeioBaixo.add(pontosButton);
			        
				propriedades.fill = GridBagConstraints.BOTH; 
		        propriedades.weighty = 0.1;
		        propriedades.gridy = 3;			
		        pMeio.add(pMeioBaixo, propriedades);
			
		p.weightx = 0.5; 
		p.weighty = 1;
		p.gridx = 1; 
		p.gridy = 0;
		frame.add(pMeio, p);
        
        //-----------------PAINEL DA DIREITA----------------------------
        
		JPanel pDir = new JPanel();
		
		pDir.setBackground(Color.LIGHT_GRAY);
		pDir.setVisible(true);
		pDir.setLayout(new GridBagLayout());
        
		        JPanel pDirTitulo = new JPanel();
		        pDirTitulo.setBackground(Color.LIGHT_GRAY);
		        pDirTitulo.setVisible(true);
				
			        JLabel terminou = new JLabel ("Terminou?");
			        terminou.setFont(new Font("Tahoma", Font.BOLD, 25));
			        terminou.setVisible(true);
					pDirTitulo.add(terminou);
					
				propriedades.fill = GridBagConstraints.BOTH; 
			    propriedades.weightx = 1; 
			    propriedades.weighty = 0.03;
			    propriedades.gridx = 0; 
			    propriedades.gridy = 0;				
			    pDir.add(pDirTitulo, propriedades);
			    
				JPanel pDirCima = new JPanel();
				pDirCima.setBackground(Color.LIGHT_GRAY);
				pDirCima.setVisible(true);
					
				stop = new JButton("STOP");
				stop.setPreferredSize(new Dimension(300, 50));
				stop.addMouseListener(new MouseAdapter() {
	                public void mousePressed(MouseEvent e) {
	                	out.println("STOP " + Nome.getText() + " | " + Objeto.getText() + " | " + Cor.getText() + " | " + Animal.getText() + " | " + Cep.getText() + " | " + Comida.getText());
	                	apertei=true;
	                }
	            });
				
				pDirCima.add(stop);
				propriedades.fill = GridBagConstraints.BOTH; 
		        propriedades.weighty = 0.3; 
		        propriedades.gridy = 1;			
		        pDir.add(pDirCima, propriedades);
				
				JPanel pDirMeio = new JPanel();
				pDirMeio.setBackground(Color.LIGHT_GRAY);
				pDirMeio.setVisible(true);
				
					JLabel tempoRestantelabel = new JLabel ("Tempo restante: ");
					tempoRestantelabel.setFont(new Font("Tahoma", Font.BOLD, 25));
					tempoRestantelabel.setVisible(true);
			        pDirMeio.add(tempoRestantelabel);
			        
			        tempoRestante = new JLabel("--:--");
			        tempoRestante.setPreferredSize(new Dimension(200, 24));
			        tempoRestante.setFont(new Font("Tahoma", Font.BOLD, 25));
			        tempoRestante.setHorizontalAlignment(SwingConstants.CENTER);
			        tempoRestante.setBorder(BorderFactory.createLineBorder(Color.black));
			        pDirMeio.add(tempoRestante);
				
				propriedades.fill = GridBagConstraints.BOTH;
		        propriedades.weighty = 0.3;
		        propriedades.gridy = 2;
		        pDir.add(pDirMeio, propriedades);
		        
		p.weightx = 0.3; 
		p.weighty = 1;
		p.gridx = 2; 
		p.gridy = 0;
		frame.add(pDir, p);

    }

    public void play() throws Exception {
        try {
            var response = in.nextLine();
            var name = response.charAt(8);
            frame.setTitle("STOP: Player " + name);
            principal.setText("STOP: Player " + name);
            while (in.hasNextLine()) {
                response = in.nextLine();
                
                if (response.startsWith("START")) {
                    messageLabel.setText("O jogo começou!");
                    title.setTitle("Letra: [" + response.charAt(6) + "]");
                    int tempo = (Integer.parseInt(response.substring(8)));
                    
                    cronometro = new Cronometro(tempoRestante,tempo);
                    t = new Thread(cronometro);
        	        t.start();
                    cronometro.setStatus(true);
                    frame.repaint();
                    
                   if(name=='1') {
                    	 
                    	new java.util.Timer().schedule( 
                		new java.util.TimerTask() {
        		            @Override
        		            public void run() {
        		            	if (!stopped)
        		            		out.println("STOP " + Nome.getText() + " | " + Objeto.getText() + " | " + Cor.getText() + " | " + Animal.getText() + " | " + Cep.getText() + " | " + Comida.getText());
        		            	}
        		        }, 
        		        //tempo ate executar o codigo de cima
                		(tempo*1000)
                    );
                    }
                    
                } else if (response.startsWith("OPPONENT_WORDS")) {
                	stop.setText("STOP!!!");
                	stopped=true;
                	tempoRestante.setText("--:--");
                	Nome.setEnabled(false);
                	Objeto.setEnabled(false);
                	Cor.setEnabled(false);
                	Animal.setEnabled(false);
                	Cep.setEnabled(false);
                	Comida.setEnabled(false);
                	stop.setEnabled(false);
                    happen.setText("Palavras do oponente:");
                    messageLabel.setText(response.substring(15));
                    cronometro.setStatus(false);
                    pontosLabel.setVisible(true);
                    pontosField.setVisible(true);
                    pontosButton.setVisible(true);
                    t.join();
                    
                    if (!apertei)
                    	out.println("STOP " + Nome.getText() + " | " + Objeto.getText() + " | " + Cor.getText() + " | " + Animal.getText() + " | " + Cep.getText() + " | " + Comida.getText());

                }  else if (response.startsWith("WINNER")) {
                	if (response.charAt(7) == '0')
                		JOptionPane.showMessageDialog(frame, "EMPATE.");
                	else if (response.charAt(7) == '1')
                	{
                		if (name == '1')
                			JOptionPane.showMessageDialog(frame, "VOCÊ VENCEU!  :)");
                		else
                			JOptionPane.showMessageDialog(frame, "Você perdeu.  :(");
                	}
                	else
                	{
                		if (name == '2')
                			JOptionPane.showMessageDialog(frame, "VOCÊ VENCEU!  :)");
                		else
                			JOptionPane.showMessageDialog(frame, "Você perdeu.  :(");
                	}
                		
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    JOptionPane.showMessageDialog(frame, "Other player left");
                    break;
                }
            }
            out.println("QUIT");
           
            cronometro.setStatus(false);
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
            frame.dispose();
        }
    }


    public static void main(String[] args) throws Exception {
        
        StopClient client = new StopClient("192.168.1.107");
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(1600, 900);
        client.frame.setVisible(true);
        client.frame.setResizable(false);
        client.play();
    }
}