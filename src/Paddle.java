import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends GameObject implements Interface
{
	private int speed;
	private Direction dir;
	public Paddle(int x, int y, int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		this.x = x;
		speed = 8;
		this.y = y;
		width = 10;
		height = 100;
		c = Color.cyan;
		dir = Direction.None;

	}

	@Override
	public void move()
	{
		if(dir == Direction.UP)
		{
			y -= speed;
		}
		else if(dir == Direction.DOWN)
		{
			y += speed;
		}
		if(y + height > 700)
		{
			y = 600;
		}
		else if(y < 0)
		{
			y = 0;
		}
	}
	public void setDir(Direction dir)
	{
		this.dir = dir;
	}
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
		move();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getY()
	{
		return y;
	}
}
