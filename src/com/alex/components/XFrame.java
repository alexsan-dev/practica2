package com.alex.components;

import com.alex.models.XFrameModel;

import java.awt.*;
import javax.swing.*;

public class XFrame extends JFrame implements XFrameModel {
    private static final long serialVersionUID = 1L;
    JPanel content;

    public void setFrame(String name, int width, int height) {
        // AGREGAR ICONO Y COLOR
        Image icon = Toolkit.getDefaultToolkit().getImage("./src/images/icon.png");

        // PANEL PRINCIPAL
        content = new JPanel(null);
        content.setBackground(new Color(220, 220, 220));
        content.setBounds(0,0,width, height);

        setIconImage(icon);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0,0,width, height);

        // AGREGAR DIMENSIONES
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);

        // CONFIGURAR FRAME
        setTitle(name);

        // RENDERIZAR
        renderWithin();
        add(content);
    }

    @Override
    public void renderWithin() {}

    public Component addComp(Component comp) {
        content.add(comp);
        return content;
    }
}