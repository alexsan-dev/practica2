package com.alex;

import com.alex.controllers.Order;
import com.alex.data.Item;
import com.alex.models.AlgoType;
import com.alex.models.DirType;
import com.alex.models.SpeedType;
import com.alex.views.MainMenu;
import com.alex.views.OrderChart;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FlatLaf.install(new FlatLightLaf());
        new MainMenu();
    }
}
