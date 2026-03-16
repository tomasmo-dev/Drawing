package pro1.swingComponents;

import pro1.drawingModel.Drawable;
import pro1.drawingModel.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanel extends JPanel {

    private final ArrayList<Line> drawables = new ArrayList<>();

    public DisplayPanel(){
        this.setBackground(Color.WHITE);
    }

    public void addDrawable(Line drawable) {
        this.drawables.add(drawable);
        this.repaint();
    }

    public void clearDrawables() {
        this.drawables.clear();
        this.repaint();
    }

    public void toggleHideText()
    {
        for (Line l : drawables) {
            l.textVisible = !l.textVisible;
        }
        this.repaint();
    }

    public void changeThickness(int newThickness)
    {
        for (Line l : drawables) {
            l.thickness = newThickness;
        }
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawables.isEmpty()) return;

        for (Drawable drawable : drawables) {
            drawable.draw((Graphics2D) g);
        }
    }
}