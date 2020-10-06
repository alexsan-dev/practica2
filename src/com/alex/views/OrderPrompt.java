package com.alex.views;

import com.alex.components.*;
import com.alex.controllers.Order;
import com.alex.data.Item;
import com.alex.models.AlgoType;
import com.alex.models.DirType;
import com.alex.models.SpeedType;
import org.jfree.chart.ChartUtils;

import java.awt.*;
import java.io.File;

public class OrderPrompt extends XFrame {
    private boolean confirm;
    private Item[] items;
    private String title;
    private String xLabel;
    private String yLabel;

    /**
     * @param items
     * @param title
     * @param xLabel
     * @param yLabel
     * @return
     */
    public OrderPrompt(Item[] items, String title, String xLabel, String yLabel) {
        setFrame("Opciones", 300, 325);
        this.items = items;
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    @Override
    public void renderWithin() {
        XLabel typeTitle = new XLabel("Tipo de ordenamiento");
        XRadio asc = new XRadio("Ascendente");
        XRadio des = new XRadio("Descendente");
        XRadioGroup direction = new XRadioGroup(new XRadio[] { asc, des });

        XLabel speedTitle = new XLabel("Velocidad de ordenamiento");
        XRadio slow = new XRadio("Lenta");
        XRadio med = new XRadio("Media");
        XRadio high = new XRadio("Rápida");
        XRadioGroup speed = new XRadioGroup(new XRadio[] { slow, med, high });

        XLabel algoTitle = new XLabel("Algoritmo de ordenamiento");
        XRadio bubble = new XRadio("Bubble");
        XRadio quick = new XRadio("Quick");
        XRadio shell = new XRadio("Shell");
        XRadioGroup algo = new XRadioGroup(new XRadio[] { bubble, quick, shell });

        XButton cancel = new XButton("Cancelar", new Color(0, 0, 0, 0), new Color(80, 80, 80));
        XButton confirm = new XButton("Aceptar");

        // EVENTOS
        confirm.onClick(e -> {
            // INDICES
            int dirIndex = direction.getIndex();
            int speedIndex = speed.getIndex();
            int algoIndex = algo.getIndex();

            // OPTIONS
            DirType dir = dirIndex == 0 ? DirType.ASCENDENTE : DirType.DESCENDENTE;
            SpeedType spd = speedIndex == 0 ? SpeedType.LENTO : speedIndex == 1 ? SpeedType.MEDIO : SpeedType.RAPIDO;
            AlgoType alg = algoIndex == 0 ? AlgoType.BUBBLE : algoIndex == 1 ? AlgoType.QUICK : AlgoType.SHELL;

            // CONTROLADOR
            Order orderOptions = new Order(dir, spd, alg);
            new OrderChart(orderOptions, items, title, xLabel, yLabel);
        });

        cancel.onClick(e -> {
            dispose();
        });

        // POSICIONES
        typeTitle.setBounds(25, 15, 170, 40);
        asc.setBounds(25, 45, 100, 40);
        des.setBounds(135, 45, 130, 40);

        speedTitle.setBounds(25, 75, 200, 40);
        slow.setBounds(25, 105, 90, 40);
        med.setBounds(105, 105, 90, 40);
        high.setBounds(190, 105, 90, 40);

        algoTitle.setBounds(25, 135, 200, 40);
        bubble.setBounds(25, 165, 90, 40);
        quick.setBounds(105, 165, 90, 40);
        shell.setBounds(190, 165, 90, 40);

        cancel.setBounds(70, 220, 100, 50);
        confirm.setBounds(170, 220, 100, 50);

        // AGREGAR
        addComp(typeTitle);
        addComp(asc);
        addComp(des);

        addComp(speedTitle);
        addComp(slow);
        addComp(med);
        addComp(high);

        addComp(algoTitle);
        addComp(bubble);
        addComp(quick);
        addComp(shell);

        addComp(cancel);
        addComp(confirm);
    }
}
