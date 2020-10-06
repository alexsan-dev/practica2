package com.alex.views;

import com.alex.components.XBarChart;
import com.alex.components.XFrame;
import com.alex.components.XLabel;
import com.alex.components.XTimer;
import com.alex.controllers.Order;
import com.alex.data.Item;

public class OrderChart extends XFrame {
    Order orderOptions;
    Item[] items;
    private final String title;
    private final String xLabel;
    private final String yLabel;

    /**
     * @param orderOptions
     * @param items
     * @param title
     * @param xLabel
     * @param yLabel
     * @return
     */
    public OrderChart(Order orderOptions, Item[] items, String title, String xLabel, String yLabel) {
        this.orderOptions = orderOptions;
        this.items = items;
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        setFrame("Ordenamiento de Datos", 640, 450);
    }

    @Override
    public void renderWithin() {
        // COMPONENTES
        String[] optionString = orderOptions.getOptionString();
        XBarChart chartX = new XBarChart(title, xLabel, yLabel);
        XLabel dirLabel = new XLabel("Orden: " + optionString[0]);
        XLabel speedLabel = new XLabel("Velocidad: " + optionString[1]);
        XLabel algoLabel = new XLabel("Algoritmo: " + optionString[2]);
        XLabel timeLabel = new XLabel("Tiempo: ");
        XLabel timeLVal = new XLabel("00:00");
        XLabel stepsLabel = new XLabel("Pasos: ");
        XLabel stepsLVal = new XLabel("00");

        // PANEL
        chartX.chartPanel.setBounds(0, 125, 636, 300);
        algoLabel.setBounds(20, 25, 150, 20);
        speedLabel.setBounds(20, 45, 150, 40);
        dirLabel.setBounds(20, 65, 200, 40);
        timeLabel.setBounds(505, 65, 70, 40);
        stepsLabel.setBounds(518, 45, 50, 40);
        timeLVal.setBounds(574, 65, 70, 40);
        stepsLVal.setBounds(595, 45, 50, 40);

        // AGREGAR
        addComp(chartX.chartPanel);
        addComp(dirLabel);
        addComp(speedLabel);
        addComp(algoLabel);
        addComp(timeLabel);
        addComp(stepsLabel);
        addComp(timeLVal);
        addComp(stepsLVal);

        XTimer.timeOut(() -> {
            // ORDENAR
            orderOptions.execute(items, chartX, stepsLVal, timeLVal, xLabel, yLabel);
        }, 2000);
    }
}
