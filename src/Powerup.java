import java.awt.Color;
import java.awt.Graphics;
//Update
public class Powerup extends GameObject implements Interface
{

	public Powerup(int gWidth, int gHeight) 
	{
		super(gWidth, gHeight);
		c = Color.magenta;
	}

	@Override
	public void move() 
	{
		
	}

	@Override
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(620, 0, 20, gHeight);
	}
	
	
	//if x direction is right an ball x is greater than or equal to powerup x

}
