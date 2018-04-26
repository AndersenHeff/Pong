import java.awt.Color;
import java.awt.Graphics;

public class Barrier extends GameObject
{
	public Barrier(int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		c = Color.orange;
		width = 20;
		height = 300;
		x = gWidth/2 - 20;
		y = 200;
	}
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
}
