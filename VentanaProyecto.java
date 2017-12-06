//Proyecto Final POO 
//Georgina González Enríquez 
//Juan Andrés Montero Espina 

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class VentanaProyecto extends JFrame{
	
	public VentanaProyecto() {
		super("Proyecto");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelInstrucciones pi = new PanelInstrucciones(this);
		this.add(pi);
		
		this.pack();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		VentanaProyecto ventana = new VentanaProyecto();
	}
}
