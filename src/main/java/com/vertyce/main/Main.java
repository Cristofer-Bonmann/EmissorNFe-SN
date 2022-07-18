package com.vertyce.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            System.out.println("Encerrando...");
            System.exit(0);
        });
    }
}
