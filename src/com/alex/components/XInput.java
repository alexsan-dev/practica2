package com.alex.components;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class XInput extends JTextField {
    private Shape shape;

    /**
     * @return
     */
    public XInput() {
        setProperties();
    }

    private void setProperties() {
        // PROPIEDADES
        setBackground(new Color(0, 0, 0, 0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(new Color(80, 80, 80));
        setFont(new Font("Lato", Font.BOLD, 17));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    /**
     * @param g
     */
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g);
    }

    /**
     * @param g
     */
    protected void paintBorder(Graphics g) {
        g.setColor(Color.white);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }

    /**
     * @param x
     * @param y
     * @return boolean
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds()))
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        return shape.contains(x, y);
    }

}
