//Proyecto Final POO 
//Georgina González Enríquez 
//Juan Andrés Montero Espina 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;



public class Calificacion {
	private int  x,
				 y,
				 valor;
	private Random ran = new Random();
	private Rectangle2D rec = new Rectangle2D.Double();
	
	public Calificacion() {
		this.valor = this.ran.nextInt(60) + 40;
		this.y = 12;
		this.x = 0;
	} 
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public Rectangle2D getRec() {
		return this.rec;
	}
	
	public void pintaCali(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.cyan);
		g.setFont(new Font("default", Font.BOLD, 12));

		//g.fillRect(this.x, this.y - 12, 20, 20);
		rec.setFrame(this.x, this.y - 12, 20, 20);
		g2d.fill(rec);
		g.setColor(Color.BLACK);
		g.drawString(this.valor + "", this.x, this.y + 2);
		
	}
	
	public void incY(){
		this.y ++;
	}

	public boolean verificarCalif() {
		if(this.valor >= 70) {
			return true;
		}//cierra if
		return false;
	}

}

