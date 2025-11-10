package View;

import Model.Aparcamiento;

public class PrincipalParking {

    public static void main(String[] args) {

        Aparcamiento aparcamiento = new Aparcamiento(3);
        for (int i = 1; i <= 7; i++) {
            Thread coche = new Thread(new Model.Coche("Coche " + i, aparcamiento));
            coche.start();
            try {
                //Escalonamos el arranque para determinar el orden de llegada
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
