import javax.swing.*;
import java.awt.*;

public class Drawer extends JPanel {

    private static final Color SNAKE_COLOR = Color.GREEN;
    private final Game game;
    private boolean menu;

    public Drawer(Game game) {
        super();
        this.game = game;
        this.setPreferredSize(new Dimension(800, 600));
        this.menu = true;
    }

    public boolean getMenu() {
        return this.menu;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        this.setBackground(Color.BLACK);

        if(!menu) {
            Snake snake = this.game.getSnake();
            Food f = this.game.getFood();
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Food.COLOR);
            g2.fillRect(f.x, f.y, f.width, f.height);
            snake.getBodyParts().forEach(b -> {
                g2.setColor(Snake.COLOR);
                g2.fillRect(b.x, b.y, b.width, b.height);
            });
            String score = "Score: " + this.game.getScore();
            Font font = new Font("Arial", Font.BOLD, 30);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(score, 50, 50);
        }
        else {
            Font f = new Font("Arial", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString("Start Game", 270, 100);
            Font f2 = new Font("Arial", Font.BOLD, 10);
            g.setFont(f2);
            g.setColor(Color.WHITE);
            g.drawString("Press any key", 350, 200);
        }
    }

    public void setMenu(boolean state) {
        this.menu = state;
    }
}
