import java.awt.Color;
import java.awt.Graphics;
//UPDATED
public class Paddle extends GameObject implements Interface
{
	private int speed;
	private Direction dir;
	public Paddle(int x, int y, Color c, int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		this.x = x;
		speed = 6;
		this.y = y;
		width = 10;
		height = 100;
		this.c = c;
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
		if(y + height > gHeight - 20)
		{
			y = gHeight - height - 20;
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
	
}
