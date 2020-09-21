package com.alex.views;

import com.alex.components.*;
import com.alex.structures.LinkedList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu extends XFrame {
    public MainMenu(){
        setFrame("Procesamiento de Datos", 640, 450);
    }

    @Override
    public void renderWithin() {
        // COMPONENTES
        XField route = new XField("Ruta del archivo", 200, "", false);
        XButton selectRoute = new XButton("Seleccionar");
        XField title = new XField("Titulo para la grafica", 220);
        XButton confirm = new XButton("Aceptar");
        XBarChart chartX = new XBarChart("", "Tipo", "Cantidad");

        // PLOTS
        final String[] titleText = {""};
        final String[] labels = new String[2];
        LinkedList<String> data = new LinkedList<>();

        // FILECHOOSER
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Datos en CSV", "csv" );
        chooser.setFileFilter(filter);

        selectRoute.onClick(e ->{
            // LEER VALORES
            chooser.showOpenDialog(null);
            File dataFile = chooser.getSelectedFile();

            // LEER Y GUARDAR
            try {
                int firstCount = 0;
                Scanner fileReader = new Scanner(dataFile);

                // ASIGNAR A INPUT
                route.setText(dataFile.getAbsolutePath());

                // LEER
                while(fileReader.hasNextLine()){
                    // LINES
                    String nLine = fileReader.nextLine();
                    String[] dataLine = nLine.split(",");

                    // CONTADOR
                    if(firstCount == 0) {
                        labels[0] = dataLine[0];
                        labels[1] = dataLine[1];
                    } else data.add(nLine);

                    // AVANZAR LINEA
                    firstCount++;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        // ASIGNAR TITULO
        confirm.onClick(e -> {
            // VERIFICAR
            String[] dataLines = data.toArray();
            titleText[0] = title.getData();
            boolean verify = labels[0].length() > 0 && labels[1].length() > 0 && titleText[0].length() > 0 && dataLines.length > 0;

            // CREAR
            if(verify) {
                // CREAR GRAFICA
                chartX.createChart(titleText[0], labels[0], labels[1]);

                // DATOS
                for(String line : dataLines) {
                    String[] vals = line.split(",");
                    chartX.setValue(vals[0], Integer.parseInt(vals[1]));
                }
            }
            else XAlert.showError("Error al crear", "No se encuentra el titulo o los ejes");
        });

        // POSICIONES
        chartX.chartPanel.setBounds(0,125,636,300);
        title.setBounds(310,15,210, 90);
        confirm.setBounds(525, 60, 90,40);
        route.setBounds(0,15,190, 90);
        selectRoute.setBounds(195, 60, 110,40);

        // AGREGAR
        addComp(route);
        addComp(selectRoute);
        addComp(title);
        addComp(confirm);
        addComp(chartX.chartPanel);
    }
}
