package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public static Snake snake;
	public JFrame jframe;
	public RenderPanel renderPanel;
	public Timer timer = new Timer(20, this);
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public int ticks = 0, direction = DOWN, score, tailLength = 10, time,
			speedVariable = 2;
	public Point head, cherry;
	public Random random;
	public boolean over = false, paused;
	public Dimension dim;
	
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height
				/ 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		String menuText = "<html><body width=175, align=center><h3>Welcome to Snake!</h3>"
				+ "<p>Try to eat as many cherries as you can, but <br></p>"
				+ "<p>beware of hitting the walls or biting your tail!</p>"
				+ "<h3>Controls</h3>"
				+ "<p>Use the W, A, S and D keys to turn the snake.<br> "
				+ "Press and hold the Shift key to slither around faster.<br> "
				+ "Pause the game by pressing Space, or in case "
				+ "you fail, <br>press Space to start a new game.</p><br>"
				+ "<p>Good luck!</p></body></html>";
		JOptionPane.showMessageDialog(jframe, menuText, "Snake",
				JOptionPane.PLAIN_MESSAGE);
		startGame();
	}

	public void startGame() {

		over = false;
		paused = false;
		score = 0;
		tailLength = 5;
		speedVariable = 2;
		ticks = 0;
		time = 0;
		direction = DOWN;
		head = new Point(0, -1);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(79), random.nextInt(66));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();
		ticks++;
		String lostText = "<html><body width=175, align=center><h2>You lost!</h2>"
				+ "<p>Score: " + score
				+ "<p>Length: " + (tailLength - 5)
				+ "<p>Time: " + time / 50 + "<br><br>"
				+ "<p>Press Space in the gameview to try again.</body></html>";
		if (!paused && !over)
		time++;

		if (ticks % speedVariable == 0 && head != null && !over && !paused) {

			snakeParts.add(new Point(head.x, head.y));

			if (direction == UP) {

				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					over = true;
					JOptionPane.showMessageDialog(jframe, lostText, "Snake",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
			if (direction == DOWN) {
				if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
					head = new Point(head.x, head.y + 1);
				} else {
					over = true;
					JOptionPane.showMessageDialog(jframe, lostText, "Snake",
							JOptionPane.PLAIN_MESSAGE);
				}
			}

			if (direction == LEFT) {
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else {
					over = true;
					JOptionPane.showMessageDialog(jframe, lostText, "Snake",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
					head = new Point(head.x + 1, head.y);
				} else {
					over = true;
					JOptionPane.showMessageDialog(jframe, lostText, "Snake",
							JOptionPane.PLAIN_MESSAGE);
				}
			}

			if (snakeParts.size() > tailLength)
				snakeParts.remove(0);

			if (cherry != null) {
				if (head.equals(cherry)) {
					score += 10;
					tailLength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));

				}

			}
		}

	}

	public boolean noTailAt(int x, int y) {

		for (Point point : snakeParts) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		snake = new Snake();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_A && direction != RIGHT)
			direction = LEFT;
		if (i == KeyEvent.VK_D && direction != LEFT)
			direction = RIGHT;
		if (i == KeyEvent.VK_W && direction != DOWN)
			direction = UP;
		if (i == KeyEvent.VK_S && direction != UP)
			direction = DOWN;
		if (i == KeyEvent.VK_SHIFT)
			speedVariable = 1;
		if (i == KeyEvent.VK_SPACE)
			if (over)
				startGame();
			else
				paused = !paused;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_SHIFT)
			speedVariable = 2;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
