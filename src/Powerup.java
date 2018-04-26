import java.awt.Color;
import java.awt.Graphics;
//Update
public class Powerup extends GameObject
{

	public Powerup(int gWidth, int gHeight) 
	{
		super(gWidth, gHeight);
		c = Color.magenta;
		width = 20;
		height = gHeight;
		x = 620;
		y = 0;
	}
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	
	//if x direction is right an ball x is greater than or equal to powerup x

}
