package com.alex.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class XBarChart {
    public DefaultCategoryDataset dts;
    public ChartPanel chartPanel;
    public JFreeChart jBarChart;

    /**
     * @param title
     * @param xLabel
     * @param yLabel
     * @return
     */
    public XBarChart(String title, String xLabel, String yLabel) {
        // DATASET
        dts = new DefaultCategoryDataset();

        // BARRAS
        jBarChart = ChartFactory.createBarChart(title, xLabel, yLabel, dts, PlotOrientation.VERTICAL, true, true,
                false);

        // PANEL
        chartPanel = new ChartPanel(jBarChart);
        chartPanel.setBackground(new Color(220, 220, 220));

    }

    /**
     * @param label
     * @param count
     */
    public void setValue(String label, int count) {
        dts.addValue(count, label, label);
    }

    public void clearDts() {
        dts.clear();
    }

    /**
     * @param title
     * @param xLabel
     * @param yLabel
     */
    public void createChart(String title, String xLabel, String yLabel) {
        // BARRAS
        dts.clear();
        jBarChart = ChartFactory.createBarChart(title, xLabel, yLabel, dts, PlotOrientation.VERTICAL, true, true,
                false);
        chartPanel.setChart(jBarChart);
    }

    /**
     * @return JFreeChart
     */
    public JFreeChart getChart() {
        return jBarChart;
    }
}
