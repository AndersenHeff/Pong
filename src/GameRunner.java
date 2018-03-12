

import javax.swing.JFrame;

public class GameRunner 
{
	public static void main(String[] args)
	{
		int width = 1260;
		int height = 720;
		JFrame obj = new JFrame();
		GamePanel panel = new GamePanel(width, height);
		obj.setSize(width, height);
        obj.setTitle("Pong");
        obj.add(panel);
        obj.setLocationRelativeTo(null);
        obj.setResizable(false);
        obj.setVisible(true);
	}
}
