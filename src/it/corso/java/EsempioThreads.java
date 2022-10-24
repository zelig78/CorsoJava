package it.corso.java;

import it.corso.java.concorrenza.GetSitePageRunnable;
import it.corso.java.concorrenza.GetSitePageThread;
import it.corso.java.concorrenza.GetSitePageCallable;
import it.corso.java.concorrenza.GetSitePageForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class EsempioThreads {

    public void esempioConcorrenzaForkJoin() throws InterruptedException, ExecutionException {
        System.out.println("ConcorrenzaForkJoin");

        ForkJoinPool f = new ForkJoinPool();

        System.out.println("IT");
        System.out.println(f.invoke(new GetSitePageForkJoin("http://www.google.it/")));

        System.out.println("COM");
        System.out.println(f.invoke(new GetSitePageForkJoin("https://www.list-group.com/")));

        f.shutdown();
    }

    public void esempioConcorrenzaCallable() throws InterruptedException, ExecutionException {
        System.out.println("ConcorrenzaCallable");

        List<Callable<String>> siti = new ArrayList<Callable<String>>();

        siti.add(new GetSitePageCallable("http://www.google.it/"));
        siti.add(new GetSitePageCallable("https://www.list-group.com/"));

        ExecutorService ex = Executors.newSingleThreadExecutor();
        List<Future<String>> out = ex.invokeAll(siti);

        for (Future<String> future : out) {
            System.out.println(future.get());
        }

        ex.shutdown();
    }

    public void esempioConcorrenzaThread() throws InterruptedException {
        System.out.println("ConcorrenzaThread");

        GetSitePageThread s1 = new GetSitePageThread("http://www.google.it/");
        GetSitePageThread s2 = new GetSitePageThread("https://www.list-group.com/");

        s1.start();
        s2.start();

        s1.join();
        s2.join();

        System.out.println("IT");
        System.out.println(s1.getContent());

        System.out.println("COM");
        System.out.println(s2.getContent());
    }

    public void esempioConcorrenzaRunnable() {
        System.out.println("ConcorrenzaRunnable");

        GetSitePageRunnable s1 = new GetSitePageRunnable("http://www.google.it/");
        GetSitePageRunnable s2 = new GetSitePageRunnable("https://www.list-group.com/");

        s1.run();
        s2.run();

        System.out.println("IT");
        System.out.println(s1.getContent());

        System.out.println("COM");
        System.out.println(s2.getContent());
    }

}
