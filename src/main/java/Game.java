import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class Game extends JFrame implements KeyListener {

    private static final float FPS = 20;
    private Snake snake;
    private final Food food;
    private final Drawer panel;
    private int score;

    public Game() {
        super();
        this.setResizable(false);

        this.food = new Food(50, 50);
        this.food.spawnRandom(800, 600);
        this.snake = new Snake(800, 600);

        this.addKeyListener(this);

        this.setTitle("Snake Game");
        this.panel = new Drawer(this);
        this.add(panel);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.score = 0;
    }

    private boolean checkEatFood() {
        BodyPart head = this.snake.getHead();
        return head.x == this.food.x && head.y == this.food.y;
    }

    private boolean checkColisionBody() {
        List<BodyPart> body = this.snake.getBodyParts();
        BodyPart head = body.get(body.size() - 1);
        for(int i = 0; i < body.size() - 1; ++i) {
            if(head.x == body.get(i).x && head.y == body.get(i).y){
                return true;
            }
        }
        return false;
    }

    private boolean checkBounds() {
        return (this.snake.getHead().x < 0 || this.snake.getHead().x + Snake.getWidthPart() > 800) || (this.snake.getHead().y < 0 || this.snake.getHead().y + Snake.getHeightPart() > 600);
    }

    private void render() {
        this.panel.repaint();
    }

    private void update() {
        this.snake.move();
        if(this.checkEatFood()){
            this.food.spawnRandom(800, 600);
            this.snake.grow();
            ++this.score;
        }
        if(this.checkColisionBody() || this.checkBounds()) {
            System.out.println("Game Over");
            this.reset();
        }
    }

    private void reset() {
        this.snake = new Snake(800, 600);
        this.score = 0;
        this.panel.setMenu(true);
        this.food.spawnRandom(800, 600);
    }

    public void run() {
        boolean running = true;
        while(running) {
            long before = System.currentTimeMillis();
            this.render();
            if(!this.panel.getMenu()) {
                this.update();
            }
            long after = System.currentTimeMillis();
            long actual_frame = after - before;
            long time_frame = (long)(1000 / FPS);
            if(actual_frame < time_frame) {
                try {
                    TimeUnit.MILLISECONDS.sleep(time_frame - actual_frame);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!this.panel.getMenu()) this.snake.changeDir(e);
        else{
            this.panel.setMenu(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }
}
