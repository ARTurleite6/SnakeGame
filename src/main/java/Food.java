import java.awt.*;
import java.util.Random;

public class Food extends Rectangle {

    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    public static final Color COLOR = Color.RED;

    public Food(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    private int closestNumber(int n, int m)
    {
        // find the quotient
        int q = n / m;

        // 1st possible closest number
        int n1 = m * q;

        // 2nd possible closest number
        int n2 = (n * m) > 0 ? (m * (q + 1)) : (m * (q - 1));

        // if true, then n1 is the required closest number
        if (Math.abs(n - n1) < Math.abs(n - n2))
            return n1;

        // else n2 is the required closest number
        return n2;
    }

    public void spawnRandom(int GAMEWIDTH, int GAMEHEIGHT) {
        Random r = new Random();
        int x, y;
        do {
            x = r.nextInt(GAMEWIDTH - Food.WIDTH);
            x = closestNumber(x, Food.WIDTH);
        } while (x > GAMEWIDTH - Food.WIDTH);
        do {
            y = r.nextInt(GAMEHEIGHT - Food.HEIGHT);
            y = closestNumber(y, Food.HEIGHT);
        } while(y > GAMEHEIGHT - Food.HEIGHT);

        this.x = x;
        this.y = y;
    }

}
