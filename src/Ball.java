import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject implements Interface
{
	private int speed;
	private double vx, vy;
	public Ball(int gWidth, int gHeight) 
	{
		super(gWidth, gHeight);
		c = Color.yellow;
		x = 630;
		y = 360;
		speed = 5;
	}

	@Override
	public void move() 
	{
		x += vx;
		y += vy;
		if(y < 0)
		{
			vy = -vy;
		}
		if(y > 675)
		{
			vy = -vy;
		}
	}
	
	public void reset()
	{
		vx = 0;
		vy = 0;
		x = 630;
		y = 360;
	}
	
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillOval(x, y, 20, 20);
		move();
	}
	
	
	public void fire(double angle) 
	{
		vx = (int)(Math.cos(angle + 30) * speed);
		vy = -(int)(Math.sin(angle + 30) * speed);
	}
	
	public void bounce()
	{
			vx = -1.25 * vx;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
}
