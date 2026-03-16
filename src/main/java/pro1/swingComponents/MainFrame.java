package pro1.swingComponents;

import pro1.drawingModel.*;
import pro1.drawingModel.Rectangle;
import pro1.utils.ColorUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MainFrame extends JFrame {
    DisplayPanel displayPanel;
    JCheckBox checkBox;
    JSlider slider;
    JButton resetBtn;

    private Point2D lastClick;

    public MainFrame() {
        this.setTitle("PRO1 Drawing");
        this.setVisible(true);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.displayPanel = new DisplayPanel();
        this.add(this.displayPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Thickness Slider
        leftPanel.add(new JLabel("Thickness:"));
        slider = new JSlider(1, 10, 3);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            displayPanel.changeThickness(slider.getValue()); // globaly change the thickness of all drawables
        });
        leftPanel.add(slider);

        // Checkbox
        checkBox = new JCheckBox("Hide Text");
        checkBox.setSelected(true);
        checkBox.addActionListener(e -> {
           displayPanel.toggleHideText();
        });
        leftPanel.add(checkBox);

        // Reset Button
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> {
            displayPanel.clearDrawables();
            lastClick = null;
        });
        leftPanel.add(resetBtn);

        this.add(leftPanel, BorderLayout.WEST);

        this.displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                HandleClick(e.getPoint());
            }
        });
    }

    private void HandleClick (Point p)
    {
        if (lastClick == null) {
            lastClick = p;
            return;
        }

        int thickness = slider.getValue();

        var l = new Line((int)lastClick.getX(), (int)lastClick.getY(), (int)p.getX(), (int)p.getY(), thickness,
                ColorUtils.randomColor());

        l.textVisible = checkBox.isSelected();

        displayPanel.addDrawable(l);
        lastClick = p;
    }

}