import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Snake {

    private static int WIDTH_PART = 20;
    private static int HEIGHT_PART = 20;
    public static Color COLOR = Color.GREEN;

    private int dx;
    private int dy;

    private List<BodyPart> bodyParts;

    public Snake(int GAME_WIDTH, int GAME_HEIGHT) {
        this.bodyParts = new ArrayList<>();
        int initial_x = (GAME_WIDTH / 2);
        int initial_y = (GAME_HEIGHT/ 2);
        BodyPart head = new BodyPart(initial_x, initial_y, WIDTH_PART, HEIGHT_PART);
        BodyPart body = new BodyPart(initial_x - WIDTH_PART, initial_y, WIDTH_PART, HEIGHT_PART);
        BodyPart tail = new BodyPart(initial_x - (WIDTH_PART * 2), initial_y, WIDTH_PART, HEIGHT_PART);
        this.bodyParts.add(tail);
        this.bodyParts.add(body);
        this.bodyParts.add(head);

        this.dx = 1;
        this.dy = 0;
    }

    public Snake(Snake s) {
        s.bodyParts = s.getBodyParts();
    }

    public static int getWidthPart() {
        return WIDTH_PART;
    }

    public static void setWidthPart(int widthPart) {
        WIDTH_PART = widthPart;
    }

    public static int getHeightPart() {
        return HEIGHT_PART;
    }

    public static void setHeightPart(int heightPart) {
        HEIGHT_PART = heightPart;
    }

    public List<BodyPart> getBodyParts() {
        return bodyParts.stream().map(BodyPart::clone).collect(Collectors.toList());
    }

    public void setBodyParts(List<BodyPart> bodyParts) {
        this.bodyParts = bodyParts.stream().map(BodyPart::clone).collect(Collectors.toList());
    }

    public void move() {
        int last_index = this.bodyParts.size() - 1;
        int headx = this.bodyParts.get(last_index).x;
        int heady = this.bodyParts.get(last_index).y;
        this.bodyParts.get(last_index).x += this.dx * WIDTH_PART;
        this.bodyParts.get(last_index).y += this.dy * HEIGHT_PART;

        for(int i = 0; i < last_index - 1; ++i) {
            this.bodyParts.get(i).x = this.bodyParts.get(i + 1).x;
            this.bodyParts.get(i).y = this.bodyParts.get(i + 1).y;
        }

        this.bodyParts.get(last_index - 1).x = headx;
        this.bodyParts.get(last_index - 1).y = heady;
    }

    public void changeDir(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_DOWN) {
            this.dy = 1;
            this.dx = 0;
        }
        else if(keyCode == KeyEvent.VK_UP) {
            this.dy = -1;
            this.dx = 0;
        }
        else if(keyCode == KeyEvent.VK_LEFT) {
            this.dy = 0;
            this.dx = -1;
        }
        else if(keyCode == KeyEvent.VK_RIGHT) {
            this.dy = 0;
            this.dx = 1;
        }
    }

    public BodyPart getHead() {
        return this.bodyParts.get(this.bodyParts.size() - 1).clone();
    }

    public void grow() {
        int x = this.bodyParts.get(this.bodyParts.size() - 1).x + (this.dx * WIDTH_PART);
        int y = this.bodyParts.get(this.bodyParts.size() - 1).y + (this.dy * HEIGHT_PART);
        this.bodyParts.add(new BodyPart(x, y, WIDTH_PART, HEIGHT_PART));
    }

}
