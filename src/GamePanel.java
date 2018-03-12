import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = -7302468257756861299L;
	private Thread thread;
	private Paddle paddle;
	private Paddle paddle2;
	private Ball ball;
	private Score score;
	private Score score2;
	private boolean start;
	public GamePanel(int gWidth, int gHeight)
	{
		
		paddle = new Paddle(15, 0, gWidth, gHeight);
		paddle2 = new Paddle(gWidth - 30, 0, gWidth, gHeight);
		ball = new Ball(gWidth, gHeight);
		score = new Score(50 , 30, gWidth, gHeight);
		score2 = new Score(1125 , 30, gWidth, gHeight);
		setBackground(Color.black);
		start = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() 
	{
	  //paddle
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "up", up);
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "stop", stop);
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "down", down);
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "stop", stop);
	  //paddle2
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up2", up2);
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "stop2", stop2);
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down2", down2);
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "stop2", stop2);
	  
	  //start game
	  registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "fire", fire);
	  while(true)
  	{
  		try {
  			Thread.sleep(20);
  		} catch (InterruptedException e) { 
  			System.out.println("Thread stopped");
  			e.printStackTrace();
  			thread.interrupt();
  			return;
  		}
  		repaint();
  	}
	  
	}
	//paddle1
	private Action up = new AbstractAction("up") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			paddle.setDir(Direction.UP);
		}
	};
	private Action down = new AbstractAction("down") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			paddle.setDir(Direction.DOWN);
		}
	};
	private Action stop = new AbstractAction("stop") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			paddle.setDir(Direction.None);
		}
	};
	
	
	//paddle2
	private Action up2 = new AbstractAction("up2") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			paddle2.setDir(Direction.UP);
		}
	};
	private Action down2 = new AbstractAction("down2") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			paddle2.setDir(Direction.DOWN);
		}
	};
	private Action stop2 = new AbstractAction("stop2") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			paddle2.setDir(Direction.None);
		}
	};
	
	
	
	
	private Action fire = new AbstractAction("fire") 
	{
		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			if(start)
			{
				ball.fire(Math.random() * Math.PI / 2 + Math.PI / 4);
				start = false;
			}
			//random angle between 45 & 135 degrees
			
		}
	};
	
	
	
	
	
      private void registerKeyBinding(KeyStroke keyStroke, String name, Action action)
      {
          InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
          ActionMap am = getActionMap();
          im.put(keyStroke, name);
          am.put(name, action);
      }
       
	public void paintComponent(Graphics g)
	{
		//Start Message
		
		super.paintComponent(g);
		if(start)
		{
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Begin", 495, 300);
		}
		paddle.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		score2.draw(g);
		bounce();
		
		
		if(ball.getX() < 0)
		{
			ball.ballGone();
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Right Paddle Wins!", 800, 300);
		}
		if(ball.getX() > 1240)
		{
			ball.ballGone();
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Left Paddle Wins!", 190, 300);
		}
		
	}

	//make it so that if ball goes off the screen the game stops
	public void bounce()
	{
		if(ball.getX() >= paddle2.getX() - 15 && (ball.getY() > paddle2.getY() && ball.getY() < paddle2.getY() + 100)) 
		{
			ball.bounce();
		}
		if(ball.getX() <= paddle.getX() && (ball.getY() > paddle.getY() && ball.getY() < paddle.getY() + 100)) 
		{
			ball.bounce();
		}
	}

	
}
