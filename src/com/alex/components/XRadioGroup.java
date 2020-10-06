package com.alex.components;

import javax.swing.*;

public class XRadioGroup extends ButtonGroup {
    private final XRadio[] radios;

    /**
     * @param radios
     * @return
     */
    public XRadioGroup(XRadio[] radios) {
        this.radios = radios;
        for (XRadio radio : radios)
            this.add(radio);
    }

    /**
     * @return int
     */
    public int getIndex() {
        int selIndex = -1;
        for (int index = 0; index < radios.length; index++)
            if (radios[index].isSelected()) {
                selIndex = index;
                break;
            }
        return selIndex;
    }
}
