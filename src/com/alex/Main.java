package com.alex;

import com.alex.views.MainMenu;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatLaf.install(new FlatLightLaf());

        new MainMenu();
    }
}
