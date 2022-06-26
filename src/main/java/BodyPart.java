import java.awt.*;

public class BodyPart extends Rectangle {
    public BodyPart(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public BodyPart(BodyPart b) {
        super(b);
    }

    public BodyPart clone() {
        return new BodyPart(this);
    }
}