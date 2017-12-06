//Proyecto Final POO 
//Georgina González Enríquez A01632886
//Juan Andrés Montero Espira A01228817
//LuJu 8:30

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelPerder extends JPanel implements ActionListener{
	private JButton restart;
	private int puntaje, 
			    nivel,
			    prom;
	private JFrame frame;
	private Image fondo;
	
	public PanelPerder(int puntaje,int nivel, int promedio, JFrame frame) {
		super();
		this.fondo = new ImageIcon("borrego-final.jpg").getImage();
		this.frame = frame;
		this.puntaje = puntaje;
		this.prom = promedio;
		this.nivel = nivel;
		this.setPreferredSize(new Dimension(800,600));
		this.restart = new JButton("Reintentar");
		
		restart.setFont(restart.getFont().deriveFont(18.0f));
		this.add(this.restart);
		this.restart.addActionListener(this);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo,0,0,this.getWidth(),this.getHeight(),this);
		Font font = g.getFont().deriveFont( 20.0f );
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Perdiste! Tu promedio fue de: "+ this.prom, 250, 100);
		g.drawString("Tu puntaje fue de : " + this.puntaje, 250, 150);
		g.drawString("Tu nivel fue de: " + this.nivel, 250, 200);
		g.drawString("Presiona para volver a intentar. ", 250, 250);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.getContentPane().removeAll();
		PanelProyecto pp = new PanelProyecto(this.frame);
		this.frame.add(pp);
		this.frame.revalidate();
		
	}

}
