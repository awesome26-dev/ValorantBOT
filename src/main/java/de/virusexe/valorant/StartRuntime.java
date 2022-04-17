package de.virusexe.valorant;

import java.net.MalformedURLException;

public class StartRuntime {

    public static void main(String[] args) {
        ValorantBOT valorantBOT = new ValorantBOT();
        try {
            valorantBOT.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
