package corsojava.threads.pool;

import java.util.Random;

public class Cliente implements Runnable {
    private int numeroTicket;

    public Cliente(int numeroTicket) {
        System.out.println("E' arrivato un nuovo cliente ed ha preso il numero " + numeroTicket);
        this.numeroTicket = numeroTicket;
    }

    @Override
    public void run() {
        richiediProdotti();
    }

    private void richiediProdotti(){
        System.out.println("Viene servito il cliente numero " + numeroTicket);
        Random r = new Random();

        int tempoImpieatoPerAcquisto = (r.nextInt(15)+5)*1000;

        try {
            Thread.sleep(tempoImpieatoPerAcquisto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Il cliente che aveva il numero " + numeroTicket + " ha completato il suo acquisto in " + tempoImpieatoPerAcquisto/1000 + " secondi");
    }
}
