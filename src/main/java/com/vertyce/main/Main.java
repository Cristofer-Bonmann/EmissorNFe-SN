package com.vertyce.main;

import com.vertyce.nfe.EmissorNfe;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            EmissorNfe emissorNfe = new EmissorNfe();
            emissorNfe.emitirNfe();

            System.out.println("Encerrando...");
            System.exit(0);
        });
    }
}
