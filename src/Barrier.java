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
		speed = 1;
	}
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
		move();
	}
	public void move()
	{
		//Make barrier move back and forth
		y += speed;
		if(y == 400 || y == 0)
		{
			speed = -speed;
		}
	}
}
