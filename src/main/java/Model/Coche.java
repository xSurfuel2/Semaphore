package Model;

import java.util.Random;

public class Coche implements Runnable{

    //Atributos
    private String nombre;
    private Aparcamiento aparcamiento;
    private Random random = new Random();

    //Constructor
    public Coche(String nombre, Aparcamiento aparcamiento) {
        this.nombre = nombre;
        this.aparcamiento = aparcamiento;
    }

    //Método run para el hilo, que simula la llegada, aparcamiento y salida del coche
    @Override
    public void run() {
        try {
            aparcamiento.entrar(nombre);
            long tiempo = 1000 + random.nextInt(4000);
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            System.out.printf("%s fue interrumpido", nombre); //%s devuelve el nombre del coche
        } finally {
            aparcamiento.salir(nombre);
        }
    }




}
