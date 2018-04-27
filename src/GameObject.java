import java.awt.Color;
//UPDATED
public class GameObject 
{
	protected int x;
	protected int y;
	protected int width,height;
	protected Color c;
	protected int gWidth,gHeight;
	public GameObject(int gWidth, int gHeight)
	{
		this.gWidth = gWidth;
		this.gHeight = gHeight;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	
	
	
	
	
	
	
	
	
	
}