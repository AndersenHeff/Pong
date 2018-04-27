import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//UPDATED
public class Ball extends GameObject implements Interface
{
	private int speed;
	private double vx, vy;
	public Ball(int gWidth, int gHeight) 
	{
		super(gWidth, gHeight);
		c = Color.red;
		x = 630;
		y = 360;
		speed = 5;
		width = 20;
		height = 20;
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
		if(y > gHeight - height - 25)
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
		g.fillOval(x, y, width, height);
		move();
	}
	
	
	public void fire(double angle) 
	{
		vx = (int)(Math.cos(angle + 30) * speed);
		vy = -(int)(Math.sin(angle + 30) * speed);
	}
	
	public void bounce()
	{	
		if(vx >= 9 || vx <= -9)
		{
			vx = -vx;
		}
		else
		{
			vx = -1.25 * vx;
		}
	}
	
	public double getVX()
	{
		return vx;
	}
	
	public double getVY()
	{
		return vy;
	}
	
	public void setVX(double vx)
	{
		this.vx = vx;
	}
	public void setC(Color c)
	{
		this.c = c;
	}
}
