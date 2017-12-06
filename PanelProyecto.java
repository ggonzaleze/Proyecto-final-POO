//Proyecto Final POO 
//Georgina González Enríquez 
//Juan Andrés Montero Espina 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelProyecto extends JPanel implements MouseMotionListener, ComponentListener, Runnable, MouseListener {
	
	private int xBorrego,
				yBorrego,
				largoPantalla = 600,
			    anchoPantalla = 800,
				puntajeVidas, 
				sumaCalifs,
				numCalifs,
				prom, // puntaje de promedio final
				puntajeFinal,
				vidas,
				numBorrados = 0,
				tamanoArreglo = 11,
				tiempo = 20,
				nivel = 0;
	private boolean seguir;
	private Image borrego, fondo;
	private Calificacion[] calif = new Calificacion[11];	
	private Thread hilo = new Thread(this);
	private JFrame frame;
	
	public PanelProyecto(JFrame frame) {
		super();
		this.fondo = new ImageIcon("Borrego-juego.jpeg").getImage();
		this.frame = frame;
		this.xBorrego = 450;
		this.yBorrego = 550;
		this.puntajeVidas = 0;
		this.vidas = 3;
		this.seguir = true;
		this.setPreferredSize(new Dimension(anchoPantalla,largoPantalla));
		this.borrego = new ImageIcon("borrego-juego.png").getImage();
		for(int i = 0; i < this.calif.length; i++) {
			calif[i] = new Calificacion();
		}//cierra for

		this.addMouseMotionListener(this);
		this.addComponentListener(this);
		this.addMouseListener(this);
		hilo.start();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(borrego,this.xBorrego - 50,this.yBorrego - 50,80,70, this);
		for(int i = 0; i < this.tamanoArreglo; i++) {
			if( calif[i] != null) {
				calif[i].setX( ( i * this.getWidth() ) / 11);
				calif[i].pintaCali(g);
			}

		}//cierra for
		Font font = g.getFont().deriveFont( 18.0f );
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Puntaje : "+ this.puntajeFinal, this.anchoPantalla - 145, this.largoPantalla - 30);
		g.drawString("Promedio "+ this.prom, this.anchoPantalla - 145, this.largoPantalla - 48);
		g.drawString("Vidas : " + this.vidas, this.anchoPantalla - 145, this.largoPantalla - 64);
		}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		this.yBorrego = e.getY();
		this.xBorrego = e.getX();
		this.repaint();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.anchoPantalla = (int) this.getWidth();
		this.largoPantalla = (int) this.getHeight();
		this.repaint();
	}
	
	private synchronized void crearArreglo() {
		this.calif = new Calificacion [11];
		for(int l = 0; l < this.calif.length; l++) {
			calif[l] = new Calificacion();
		}
		this.tamanoArreglo = 11;
		this.numBorrados = 0;
		if(this.tiempo > 5) {
			this.tiempo--;
		}
		this.nivel++;
		this.repaint();
	}
	
	private void borrarCalif(int i) {
		if(this.numBorrados < 10) {
			for (int j = i; j < (this.tamanoArreglo - 1); j++) {
				this.calif[j] = this.calif[j + 1];
			}
			this.calif[this.tamanoArreglo - 1] = null;
			this.tamanoArreglo--;
			this.numBorrados++;
			this.repaint();
		}
		else {
			this.crearArreglo();
		}//cierra else
	}
	
	public void perdiste() {
		this.seguir = false;
		PanelPerder pape = new PanelPerder(this.puntajeFinal, this.nivel, this.prom, this.frame);
		this.frame.getContentPane().removeAll();
		this.frame.add(pape);
		this.frame.revalidate();
		
	} 
	
	@Override
	public void run() {
		try {
			do {
				while(this.calif[0].getY() < (this.largoPantalla - 8) ) {
					for(int i = 0; i < this.tamanoArreglo; i++) {
						this.calif[i].incY();
					}//cierra for
					if(vidas <= 0) {
						this.perdiste();
						this.vidas = 0;
						break;
					}
					Thread.sleep(this.tiempo);
					this.repaint();
				} //cierra while

				for(int k = 0; k < this.tamanoArreglo; k++) {
					
					if(this.calif[k].verificarCalif()) {
						this.puntajeVidas -= this.calif[k].getValor();
					}	
				}//cierra for
				if(this.puntajeVidas < 0) {
					this.puntajeVidas = 0;
					this.vidas--;
					this.repaint();
					if(vidas <= 0) {
						this.perdiste();
						this.vidas = 0;
						break;
					}
				}
				this.repaint();
				this.crearArreglo();
				
			}while(this.seguir);
		}//cierra try
		catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < this.tamanoArreglo; i++) {
			try {
				if( this.calif[i].getRec().contains( getMousePosition() ) ) {
					this.sumaCalifs += this.calif[i].getValor();
					this.numCalifs++;
					if(this.calif[i].verificarCalif()) {
						this.puntajeVidas += this.calif[i].getValor();
						this.repaint();
					}//cierra if verificaciÃ³n

					else {
						this.vidas--;
						this.repaint();
					}
					this.prom = this.sumaCalifs / this.numCalifs;
					this.puntajeFinal = this.prom * this.nivel;
					this.borrarCalif(i);

				}//cierra if click
			}catch(NullPointerException error) {}
		}//cierra for
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}//cierra clase



