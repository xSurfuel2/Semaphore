package Model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;

public class Aparcamiento {

    //Atributos
    private final int capacidad;
    private int plazasOcupadas;
    private final Semaphore semaphore;

    // Latch que libera el log de espera cuando los 3 primeros ya han entrado
    private final CountDownLatch primerosEnLog;

    //Constructor
    public Aparcamiento(int capacidad) {
        this.capacidad = capacidad;
        this.plazasOcupadas = 0;
        this.semaphore = new Semaphore(capacidad, true);
        this.primerosEnLog = new CountDownLatch(capacidad); //Esto sirve para que los 3 primeros coches que entren puedan hacerlo sin esperar
    }

    public void entrar(String nombre) {
        try {
            //Intento de adquirir un permiso
            boolean entroSinEsperar = semaphore.tryAcquire();

            if (entroSinEsperar) { //if para el caso de que haya conseguido entrar sin esperar
                synchronized (this) { //Lo sincronizamos para que no haya problemas al modificar plazasOcupadas
                    plazasOcupadas++;
                    System.out.printf("%s ha entrado. Plazas ocupadas: %d%n", nombre, plazasOcupadas);
                }
                //Decrementa el contador del latch, donde el máximo es 3
                primerosEnLog.countDown();
            } else {
                //En el caso de que no haya podido entrar sin esperar, espera a que los 3 primeros se impriman antes de mostra "esperando..."
                primerosEnLog.await(); //El await bloquea hasta que el contador del latch llega a 0
                System.out.printf("%s está esperando...%n", nombre);

                //Bloquea hasta que se le pueda asignar una plaza
                semaphore.acquire();

                //Log de entrada normal
                synchronized (this) {
                    plazasOcupadas++;
                    System.out.printf("%s ha entrado. Plazas ocupadas: %d%n", nombre, plazasOcupadas);
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("%s fue interrumpido%n", nombre);
        } catch (Exception e) {
            System.out.println("Error al entrar al aparcamiento: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void salir(String nombre) {
        try {
            synchronized (this) {
                plazasOcupadas--;
                System.out.printf("%s ha salido. Plazas ocupadas: %d%n", nombre, plazasOcupadas);
            }
            semaphore.release();
        } catch (Exception e) {
            System.out.println("Error al salir del aparcamiento: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
