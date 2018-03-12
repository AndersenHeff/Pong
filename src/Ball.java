import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject implements Interface
{
	private int speed;
	private int vx, vy;
	public Ball(int gWidth, int gHeight) 
	{
		super(gWidth, gHeight);
		c = Color.yellow;
		x = 630;
		y = 360;
		speed = 10;
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
	
	public void ballGone()
	{
		vx = 0;
		vy = 0;
		c = Color.black;
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
		vx = (int)(Math.cos(angle) * speed);
		vy = -(int)(Math.sin(angle) * speed);
	}
	
	public void bounce()
	{
		vx = -vx;
	}
	
	public int getVX()
	{
		return vx;
	}
	
	public void setVX(int vx)
	{
		this.vx = vx;
	}
	
	public int getX()
	{
		return x;
	}
}
