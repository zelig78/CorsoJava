package corsojava.threads.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BanconeSalumiFormaggi {
    private final static int CLIENTI_DA_SERVIRE = 30;
    private final int DIPENDENTI_AL_BANCONE = 3;

    private BlockingQueue<Runnable> codaBancone = new ArrayBlockingQueue<Runnable>(30, true);
    private ExecutorService dipendentiDisponibili = Executors.newFixedThreadPool(DIPENDENTI_AL_BANCONE);

    public void arrivoClientiAlBancone() {
        for (int i = 1; i <= CLIENTI_DA_SERVIRE; i++) {
            try {
                codaBancone.put(new Cliente(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void servizioClienti() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        dipendentiDisponibili.execute(codaBancone.take());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public int getDIPENDENTI_AL_BANCONE() {
        return DIPENDENTI_AL_BANCONE;
    }

    public int getCLIENTI_DA_SERVIRE() {
        return CLIENTI_DA_SERVIRE;
    }
}
