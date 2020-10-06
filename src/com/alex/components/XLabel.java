package com.alex.components;

import java.awt.*;
import javax.swing.*;

public class XLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    /**
     * @param text
     * @return
     */
    public XLabel(String text) {
        setText(text);

        // PROPIEDADES
        setForeground(new Color(60, 60, 60));
        setFont(new Font("Lato", Font.BOLD, 16));
    }
}