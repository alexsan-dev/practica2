package com.alex.components;

import javax.swing.*;
import java.awt.*;

public class XField extends JPanel {
    public JTextField field;
    public XLabel label;

    /**
     * @param text
     * @param width
     * @return
     */
    public XField(String text, int width) {
        setBackground(new Color(220, 220, 220));
        setLayout(null);
        setSize(width, 90);

        // COMPONENTES
        field = new XInput();
        label = new XLabel(text);

        // AGREGAR
        setComponents();
    }

    /**
     * @param text
     * @param width
     * @param initialValue
     * @param editable
     * @return
     */
    public XField(String text, int width, String initialValue, boolean editable) {
        setBackground(new Color(220, 220, 220));
        setLayout(null);
        setSize(width, 90);

        // COMPONENTES
        field = new XInput();
        label = new XLabel(text);

        // PROPIEDADES
        field.setText(initialValue);
        field.setEditable(editable);

        // AGREGAR
        setComponents();
    }

    public void setComponents() {
        // PROPIEDADES
        label.setBounds(25, 0, getWidth() - 50, 40);
        field.setBounds(25, 40, getWidth() - 50, 50);

        // AGREGAR
        add(label);
        add(field);
    }

    /**
     * @param text
     */
    public void setText(String text) {
        field.setText(text);
    }

    /**
     * @return String
     */
    public String getData() {
        return field.getText();
    }
}
