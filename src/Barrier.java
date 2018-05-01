import java.awt.Color;
import java.awt.Graphics;
//UPDATED
public class Barrier extends Paddle
{
	public Barrier(int x, int y, Color c, int gWidth, int gHeight)
	{
		super(x,y,c,gWidth,gHeight);
		width = 20;
		height = 300;
		this.x = x;
		this.y = y;
		this.c = c;
		speed = 2;
	}
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	public void move()
	{
		speed += y;
		if(y >= 300)
		{
			speed -= y;
		}
		if(y <= 100)
		{
			speed += y;
		}
	}
}
