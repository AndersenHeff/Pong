import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
//UPDATED
public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = -7302468257756861299L;
	private Thread thread;
	private Paddle paddle;
	private Paddle paddle2;
	private Ball ball;
	private Score score;
	private Score score2;
	private Powerup powerup;
	private Barrier barrier;
	private boolean start;
	public GamePanel(int gWidth, int gHeight)
	{
		barrier = new Barrier(gWidth, gHeight);
		powerup = new Powerup(gWidth, gHeight);
		paddle = new Paddle(15, 310, Color.cyan, gWidth, gHeight);
		paddle2 = new Paddle(gWidth - 30, 310, Color.red, gWidth, gHeight);
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
  			//FPS
  			Thread.sleep(15);
  			powerup.powerUpTimer();
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
	
	
	
	//Ball Fire Angle
	private Action fire = new AbstractAction("fire") 
	{
		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			//Makes Ball Fire Only If Game Isn't Playing
			if(start)
			{
				ball.fire(Math.random() * Math.PI / 2 + Math.PI / 4);
				start = false;
				
				//Makes Score Reset On Win
				if(score.getScore() == 3)
				{
					score.setScore(0);
					score2.setScore(0);
					powerup.setCount(0);
				}
				if(score2.getScore() == 3)
				{
					score2.setScore(0);
					score.setScore(0);
					powerup.setCount(0);
				}
			}
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
		super.paintComponent(g);
		
		//Start Message
		if(start && (score.getScore() % 3 != 0 || score.getScore() == 0))
		{
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Begin", 450, 150);
		}
		
		//Draws All Objects
		paddle.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		score2.draw(g);
		//Write that barriers are only drawn if user hits y key.
		barrier.draw(g);
		
		//Make a method that calls this at a certain time
		
		//Draws Ball Bounce
		bounce();
		
		//Left Paddle Win Message
		if(score.getScore() % 3 == 0 && score.getScore() != 0 && start)
		{
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Left Paddle Wins!", 510, 300);
			
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Keep Playing", 450, 500);
			powerup.setCount(0);
		}
		
		//Right Paddle Win Message
		if(score2.getScore() == 3 && score.getScore() != 0 && start)
		{
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Right Paddle Wins!", 510, 300);
			
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Keep Playing", 450, 500);
			powerup.setCount(0);
		}
		
		//Resets Ball After Win And Grants A Score
		if(ball.getX() < 0)
		{
			score2.setScore(score2.getScore() + 1);
			start = true;
			ball.reset();
		}
		if(ball.getX() > 1260)
		{
			score.setScore(score.getScore() + 1);
			start = true;
			ball.reset();
		}
		Toolkit.getDefaultToolkit().sync();
		
	}

	public void bounce()
	{
		//Makes Ball Bounce on Right Paddle
		if(ball.getX() >= paddle2.getX() - ball.getWidth() 
		   && (ball.getY() > paddle2.getY() && ball.getY() < paddle2.getY() + paddle.getHeight())) 
		{
			ball.setX(paddle2.getX() - ball.getWidth());
			ball.bounce();
			ball.setC(Color.cyan);
		}
		//Makes Ball Bounce on Left Paddle
		if(ball.getX() <= paddle.getX() && (ball.getY() > paddle.getY() 
			&& ball.getY() < paddle.getY() + paddle.getHeight())) 
		{
			ball.setX(paddle.getX() + paddle.getWidth());
			ball.bounce();	
			ball.setC(Color.red);

		}
		
		//Makes Ball Bounce on Left Side Barrier
		if(ball.getX() >= barrier.getX() && ball.getX() <= barrier.getX() + ball.getWidth()
		   && ball.getY() > barrier.getY() && ball.getY() < barrier.getY() + barrier.getHeight()
		   && ball.getVX() > 0)
		{
			ball.setX(barrier.getX() - ball.getWidth());
			ball.bounce();
		}
		
		if(ball.getX() <= barrier.getX() && ball.getX() >= barrier.getX() - ball.getWidth()
		   && ball.getY() > barrier.getY() && ball.getY() < barrier.getY() + barrier.getHeight()
		   && ball.getVX() < 0)
		{
			ball.setX(barrier.getX() + ball.getWidth());
			ball.bounce();
		}
		
	}

	
}
