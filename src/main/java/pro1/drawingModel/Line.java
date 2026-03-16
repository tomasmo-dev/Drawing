package pro1.drawingModel;

import java.awt.*;

public class Line extends XY {
    private int endX;
    private int endY;
    private int thickness;
    private String color;

    public boolean textVisible;
    private Text lengthText;

    public final int Length = (int) Math.sqrt(Math.pow(endX-x,2)+Math.pow(endY-y,2));

    public Line(int x, int y, int endX, int endY, int thickness, String color) {
        super(x,y);
        this.color = color;
        this.thickness = thickness;
        this.endX = endX;
        this.endY = endY;
        this.textVisible = true;

        // place the label near the last point of the segment
        this.lengthText = new Text(endX + 6, endY - 6, String.valueOf(Length));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(this.thickness));
        g.setColor(Color.decode(this.color));
        g.drawLine(this.x,this.y,this.endX,this.endY);

        if (textVisible) {
            lengthText.draw(g);
        }
    }
}
