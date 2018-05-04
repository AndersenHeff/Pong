import java.awt.Color;
import java.awt.Graphics;
//UPDATED
public class Powerup extends GameObject
{
	private int count;
	public Powerup(int gWidth, int gHeight) 
	{
		super(gWidth, gHeight);
		c = Color.GREEN;
		width = 20;
		height = gHeight;
		x = 620;
		y = 0;
	}
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
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
