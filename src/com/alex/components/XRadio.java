package com.alex.components;

import javax.swing.*;
import java.awt.*;

public class XRadio extends JRadioButton {

    /**
     * @param label
     * @return
     */
    public XRadio(String label) {
        super(label);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
