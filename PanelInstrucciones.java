//Proyecto Final POO 
//Georgina González Enríquez 
//Juan Andrés Montero Espira 

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

public class PanelInstrucciones extends JPanel implements ActionListener{
	private JButton start;
	private JFrame frame;
	private Image fondoInstrucciones;
	
	public PanelInstrucciones(JFrame frame){
		super();
		this.fondoInstrucciones = new ImageIcon("borrego-background4.jpg").getImage();
		this.setPreferredSize(new Dimension(800,600));
		this.frame = frame;
		this.start = new JButton("Start");
		start.setFont(start.getFont().deriveFont(20.0f));
		this.add(this.start);
		this.start.addActionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.fondoInstrucciones,0,170, this.getWidth(),this.getHeight()-50, this );
		g.setFont(new Font("default", Font.BOLD, 20));
		g.setColor(Color.RED);
		g.drawString("Bienvenido al juego!",300,60);
		g.setFont(new Font("default", Font.BOLD, 15));
		g.setColor(Color.blue);
		g.drawString("Ayuda a borreguito a pasar su semestre!",250,85);
		g.drawString("Atrapa las calificaciones aprobatorias (70 para arriba) dando click en ellas, "
				+ "y deja caer las reprobatorias.", 8, 110);
		g.setColor(Color.red);
		g.drawString("Buena suerte!", 340, 140);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getContentPane().removeAll();
		PanelProyecto pp = new PanelProyecto(this.frame);
		frame.add(pp);
		frame.revalidate();
	}

}
