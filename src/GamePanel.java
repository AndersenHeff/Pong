import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
//UPDATED
public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private Paddle paddle;
	private Paddle paddle2;
	private Ball ball;
	private Score score;
	private Score score2;
	private Powerup powerup;
	private Barrier barrier;
	private ArrayList<GameObject> parts;
	private boolean start;
	private boolean barrierSpawn, powerupSpawn, winSpawn;
	public GamePanel(int gWidth, int gHeight)
	{
		barrier = new Barrier(gWidth/2 - 20, 200, Color.GREEN, gWidth, gHeight);
		powerup = new Powerup(gWidth, gHeight);
		paddle = new Paddle(15, 310, Color.cyan, gWidth, gHeight);
		paddle2 = new Paddle(gWidth - 30, 310, Color.red, gWidth, gHeight);
		ball = new Ball(gWidth, gHeight);
		score = new Score(50 , 30, gWidth, gHeight);
		score2 = new Score(1125 , 30, gWidth, gHeight);
		setBackground(Color.black);
		start = true;
		barrierSpawn = false;
		powerupSpawn = false;
		winSpawn = true;
		thread = new Thread(this);
		thread.start();
		parts = new ArrayList<GameObject>();
		parts.add(paddle);
		parts.add(paddle2);
		parts.add(ball);
		parts.add(score);
		parts.add(score2);
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

		//barrier and powerup
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0, false), "barrier", barrierS);
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "powerup", powerupS);

		while(true)
		{
			try {
				//FPS
				Thread.sleep(10);
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
	//barrier and powerup
	private Action barrierS = new AbstractAction("barrier") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			barrierSpawn = true;
		}
	};
	private Action powerupS = new AbstractAction("powerup") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			powerupSpawn = true;
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
					score.setScore(0);
					score2.setScore(0);
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
		if(start && (score.getScore() % 3 != 0 || score.getScore() == 0) 
				&& (score2.getScore() % 3 != 0 || score2.getScore() == 0)
				&& !barrierSpawn && !powerupSpawn)
		{
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Begin", 450, 150);
			g.setColor(Color.ORANGE);
			g.drawString("Press P for Powerups or B for Barriers", 300, 300);
			g.setColor(Color.ORANGE);
			g.drawString("Press Space to Play Without Either", 325, 500);
			powerup.setCount(0);
		}
		//Makes Barrier Erase on Win
		if(!start && !winSpawn)
		{
			winSpawn = true;
		}
		
		
		
		
		
		//Draws All Objects
		for(GameObject part : parts)
		{
			part.draw(g);
			if(part instanceof Interface)
			{
				((Interface) part).move();
			}
		}
		
		
		
		//Barrier and Powerup Spawn
		if(barrierSpawn && winSpawn)
		{
			barrier.draw(g);
		}
		if(powerupSpawn && powerup.getCount() == 1500 && winSpawn)
		{
			powerup.draw(g);
		}
		//Draws Ball Bounce
		bounce();

		//Left Paddle Win Message
		if(score.getScore() == 3 && score.getScore() != 0 && start)
		{
			g.setColor(Color.cyan);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Left Paddle Wins!", 510, 300);

			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Keep Playing", 450, 500);
			powerup.setCount(0);
			winSpawn = false;
		}

		//Right Paddle Win Message
		if(score2.getScore() == 3 && score2.getScore() != 0 && start)
		{
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Right Paddle Wins!", 510, 300);

			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Space to Keep Playing", 450, 500);
			powerup.setCount(0);
			winSpawn = false;
		}

		//Resets Ball After Goal And Grants A Score
		if(ball.getX() < 0)
		{
			score2.setScore(score2.getScore() + 1);
			start = true;
			ball.reset();
			powerup.setCount(0);
		    paddle.reset();
		    paddle2.reset();
		}
		if(ball.getX() > 1260)
		{
			score.setScore(score.getScore() + 1);
			start = true;
			ball.reset();
			powerup.setCount(0);
			paddle.reset();
			paddle2.reset();
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
			ball.rightBounce(paddle2);
			ball.setC(Color.cyan);
		}
		//Makes Ball Bounce on Left Paddle
		if(ball.getX() <= paddle.getX() && (ball.getY() > paddle.getY() 
				&& ball.getY() < paddle.getY() + paddle.getHeight())) 
		{
			ball.setX(paddle.getX() + paddle.getWidth());
			ball.leftBounce(paddle);	
			ball.setC(Color.red);

		}
		//Makes Ball Bounce on Left Side Barrier
		if(barrierSpawn) 
		{
			if(ball.getX() >= barrier.getX() && ball.getX() <= barrier.getX() + ball.getWidth()
			&& ball.getY() > barrier.getY() && ball.getY() < barrier.getY() + barrier.getHeight()
			&& ball.getVX() > 0)
			{
				ball.setX(barrier.getX() - ball.getWidth());
				ball.barrierBounce();
			}
			//Makes Ball Bounce on Right Side Barrier
			else if(ball.getX() <= barrier.getX() && ball.getX() >= barrier.getX() - ball.getWidth()
			&& ball.getY() > barrier.getY() && ball.getY() < barrier.getY() + barrier.getHeight()
			&& ball.getVX() < 0)
			{
				ball.setX(barrier.getX() + ball.getWidth());
				ball.barrierBounce();
			}

		}

	}


}
