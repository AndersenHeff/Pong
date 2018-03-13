import java.awt.Color;
//Updated
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
}
