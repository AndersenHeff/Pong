import java.awt.Color;
import java.awt.Graphics;

public class Barrier extends GameObject
{
	public Barrier(int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		c = Color.orange;
	}
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(gWidth/2 - 20, 200, 20, 300);
	}
}
