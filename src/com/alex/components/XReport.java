package com.alex.components;

import com.alex.data.Item;
import com.alex.models.AlgoType;
import com.alex.models.DirType;
import com.alex.models.SpeedType;

import java.io.FileWriter;
import java.io.IOException;

public class XReport {

    /**
     * @param initialVals
     * @param finalVals
     * @param xLabel
     * @param yLabel
     * @param direction
     * @param speed
     * @param algo
     * @param seconds
     * @param millis
     * @param steps
     * @return
     */
    public XReport(Item[] initialVals, Item[] finalVals, String xLabel, String yLabel, DirType direction,
            SpeedType speed, AlgoType algo, int seconds, int millis, int steps) {
        Item maxItem = direction == DirType.ASCENDENTE ? finalVals[finalVals.length - 1] : finalVals[0];
        Item minItem = direction == DirType.ASCENDENTE ? finalVals[0] : finalVals[finalVals.length - 1];

        // DATOS INICIALES
        String initialVal = "<div class=\"table\"><div><strong>" + yLabel + "</strong><strong>"+xLabel+"</strong></div><ul>";
        for (int i = 0; i < initialVals.length; i++)
            initialVal += "<li><p>" + initialVals[i].name + "</p><p>" + initialVals[i].count + "</p></li>";
        initialVal += "</ul></div>";

        // DATOS FINALES
        String finalVal = "<div class=\"table\"><div><strong>" + yLabel + "</strong><strong>"+xLabel+"</strong></div><ul>";
        for (int i = 0; i < finalVals.length; i++)
            finalVal += "<li><p>" + finalVals[i].name + "</p><p>" + finalVals[i].count + "</p></li>";
        finalVal += "</ul></div>";

        String htmlOut = "<!DOCTYPE html><html><head><title>Practica 2 - Reporte</title><meta charset=\"utf-8\"/><link rel=\"stylesheet\" href=\"./styles.css\"/></head><body><header><h1>Alex Santos</h1><p>201904117</p></header><div class=\"info\"><ul><li><strong>Algoritmo: </strong>"
                + algo.name() + "</li><li><strong>Tiempo: </strong>" + seconds + ":" + millis
                + "</li><li><strong>Velocidad: </strong>" + speed.name() + "</li><li><strong>Pasos: </strong>" + steps
                + "</li><li><strong>Orden: </strong>" + direction.name()
                + "</li></ul><div><div><strong>Dato Minimo</strong><span>" + minItem.name + "</span><span>"
                + minItem.count + "</span></div><div><strong>Dato Maximo</strong><span>" + maxItem.name
                + "</span><span>" + maxItem.count + "</span></div></div></div><div><h2>Datos iniciales</h2>"
                + initialVal + "<img src=\"./initial.jpeg\"/><h2>Datos finales</h2>" + finalVal
                + "<img src=\"./final.jpeg\"/></body></html>";

        try {
            FileWriter file = new FileWriter("./src/reports/index.html");
            file.write(htmlOut);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
