import java.awt.Color;
import java.awt.Graphics;
//Update
public class Powerup extends GameObject implements Interface
{
	private int count;
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
		x = 620;
		y = 0;
		g.setColor(c);
		g.fillRect(x, y, 20, gHeight);
	}
	
	public void powerUpTimer()
	{
		count++;
	}
	//if x direction is right an ball x is greater than or equal to powerup x

	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	
	public int getX()
	{
		return x;
	}
	
	
}
