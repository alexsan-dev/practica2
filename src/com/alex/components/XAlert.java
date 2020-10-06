package com.alex.components;

import javax.swing.*;

public class XAlert {

    /**
     * @param title
     * @param text
     */
    public static void showError(String title, String text) {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @param title
     * @return String
     */
    public static String showPrompt(String title) {
        return JOptionPane.showInputDialog(null, title, "");
    }

    /**
     * @param title
     * @param text
     */
    public static void showAlert(String title, String text) {
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @param text
     * @return int
     */
    public static int showConfirm(String text) {
        return JOptionPane.showConfirmDialog(null, text);
    }
}
