package com.alex.controllers;

import com.alex.components.*;
import com.alex.data.Item;
import com.alex.models.AlgoType;
import com.alex.models.DirType;
import com.alex.models.OrderOptions;
import com.alex.models.SpeedType;
import org.jfree.chart.ChartUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Order implements OrderOptions {
    private final SpeedType speed;
    private final DirType direction;
    private final AlgoType algo;
    static int seconds = 0;
    static int millis = 0;

    XBarChart chartX;
    XLabel steps;
    int time;
    int quickCounter;

    /**
     * @param direction
     * @param speed
     * @param algo
     * @return
     */
    public Order(DirType direction, SpeedType speed, AlgoType algo) {
        this.direction = direction;
        this.speed = speed;
        this.algo = algo;
    }

    /**
     * @return int
     */
    private int getTime() {
        switch (speed) {
        case LENTO:
            return 1500;
        case MEDIO:
            return 1000;
        case RAPIDO:
            return 500;
        default:
            return 499;
        }
    }

    /**
     * @return String[]
     */
    public String[] getOptionString() {
        String[] out = new String[3];
        out[0] = direction.name();
        out[1] = speed.name();
        out[2] = algo.name();
        return out;
    }

    /**
     * @param a
     * @param b
     * @return boolean
     */
    private boolean getDirection(int a, int b) {
        switch (direction) {
        case ASCENDENTE:
            return a > b;
        case DESCENDENTE:
            return a < b;
        default:
            return false;
        }
    }

    /**
     * @param arr
     * @param low
     * @param high
     * @return int
     */
    private int partition(Item[] arr, int low, int high) {
        int pivot = arr[high].count;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (getDirection(pivot, arr[j].count)) {
                i++;
                Item temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Item temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * @param arr
     * @param low
     * @param high
     */
    private void sort(Item[] arr, int low, int high) {
        // ACTUALIZAR
        steps.setText(Integer.toString(++quickCounter));
        updateChart(this.time, arr, this.chartX);

        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    /**
     * @param time
     * @param vals
     * @param chartX
     */
    public void updateChart(int time, Item[] vals, XBarChart chartX) {
        // CREAR GRAFICA
        chartX.clearDts();

        // ORDENAR
        for (Item line : vals)
            chartX.setValue(line.name, line.count);

        // TIMER
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param vals
     * @param chartX
     * @param steps
     * @param timeLabel
     * @param xLabel
     * @param yLabel
     */
    public void execute(Item[] vals, XBarChart chartX, XLabel steps, XLabel timeLabel, String xLabel, String yLabel) {
        // GLOBALES
        Item[] valCopy = Arrays.copyOf(vals, vals.length);
        int n = vals.length;
        int time = getTime();
        millis = 0;
        seconds = 0;

        Timer timer = new Timer();

        // ACTUALIZAR TIEMPO
        TimerTask updateClock = new TimerTask() {
            @Override
            public void run() {
                millis++;
                if (millis >= 100) {
                    seconds++;
                    millis = 0;
                }
                timeLabel.setText(Integer.toString(seconds) + ":" + Integer.toString(millis));
            }
        };

        timer.schedule(updateClock, 30, 10);
        switch (algo) {
        case BUBBLE:
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (getDirection(vals[j].count, vals[j + 1].count)) {
                        Item temp = vals[j];
                        vals[j] = vals[j + 1];
                        vals[j + 1] = temp;
                    }
                }

                // ACTUALIZAR
                steps.setText(Integer.toString(i));
                updateChart(time, vals, chartX);
            }
            break;
        case QUICK:
            this.chartX = chartX;
            this.time = time;
            this.steps = steps;
            quickCounter = 0;
            sort(vals, 0, n - 1);
            break;
        case SHELL:
            int count = 0;
            int increment = n / 2;
            while (increment > 0) {
                for (int i = increment; i < n; i++) {
                    int j = i;
                    Item temp = vals[i];
                    while (j >= increment && getDirection(vals[j - increment].count, temp.count)) {
                        vals[j] = vals[j - increment];
                        j = j - increment;
                    }
                    vals[j] = temp;
                }
                if (increment == 2)
                    increment = 1;
                else
                    increment *= (5.0 / 11);

                // ACTUALIZAR
                steps.setText(Integer.toString(++count));
                updateChart(time, vals, chartX);
            }
            break;
        }

        timer.cancel();
        try {
            ChartUtils.saveChartAsJPEG(new File("./src/reports/final.jpeg"), chartX.jBarChart, 640, 450);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XTimer.timeOut(() -> {
            new XReport(valCopy, vals, xLabel, yLabel, direction, speed, algo, seconds, millis,
                    Integer.parseInt(steps.getText()));
            XAlert.showAlert("Ordenamiento terminado", "Se genero un reporte sobre el ordenamiento.");
        }, 1000);
    }
}
