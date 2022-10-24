package it.corso.java;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        System.out.println("CORSO JAVA");

        EsempioThreads esempioThreads = new EsempioThreads();

        try {
            esempioThreads.esempioConcorrenzaThread();
            esempioThreads.esempioConcorrenzaCallable();
            esempioThreads.esempioConcorrenzaRunnable();
            esempioThreads.esempioConcorrenzaForkJoin();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
