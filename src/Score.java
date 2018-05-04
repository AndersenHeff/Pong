import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//UPDATED
public class Score extends GameObject
{
	private int score;
	public Score(int x, int y, int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		this.x = x;
		this.y = y;
		score = 0;
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score: " + score,  x,  y);
	}
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
}
