import java.awt.Color;
import java.awt.Graphics;

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
	
	@Override
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
	}
	
	
	public void fire(double angle) 
	{
		vx = (int)(Math.cos(angle + 30) * speed);
		vy = -(int)(Math.sin(angle + 30) * speed);
	}
	
	public void barrierBounce()
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
	public void leftBounce(Paddle paddle)
	{	
		double relY = y + height / 2 - paddle.getY();
		double angle = ((-2 * Math.PI / 3) * relY / paddle.getHeight() 
				+ 11 * Math.PI / 12);
		x = paddle.getX() + paddle.getWidth();
		fire(angle);
	}
	
	public void rightBounce(Paddle paddle2)
	{
		double relY = y + height - paddle2.getY();
		//fix angle
		double angle = ((-2 * Math.PI / 4) * relY / paddle2.getHeight() + 3 * Math.PI / 2);
		x = paddle2.getX() - width;
		fire(angle);
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
}
