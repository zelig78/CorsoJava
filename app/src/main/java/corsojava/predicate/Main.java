package corsojava.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Utente> elenco = elencoUtenti();

        List<Utente> trovati = cercaUtenti(elenco, utente -> utente.getNome().equals("Pippo"));
        System.out.println("Utenti trovati con nome Pippo");
        for (Utente utenteTrovato : trovati) {
            System.out.println(utenteTrovato.getNome() + " " + utenteTrovato.getCognome());
        }
        System.out.println("---------------------------------");

        System.out.println("Utenti trovati con mail contenente '.it'");
        List<Utente> trovati2 = cercaUtenti(elenco, utente -> utente.getEmail().contains(".it"));
        for (Utente utenteTrovato : trovati2) {
            System.out.println(utenteTrovato.getNome() + " " + utenteTrovato.getCognome() + " " + utenteTrovato.getEmail());
        }
        System.out.println("---------------------------------");

        System.out.println("Utenti trovati con età maggiore di 40");
        List<Utente> trovati3 = cercaUtenti(elenco, utente -> utente.getEta() > 40);
        for (Utente utenteTrovato : trovati3) {
            System.out.println(utenteTrovato.getNome() + " " + utenteTrovato.getCognome() + " " + utenteTrovato.getEta());
        }
        System.out.println("---------------------------------");

        System.out.println("Utenti con nome Davide e cognome Tedesco");
        List<Utente> trovati4 = cercaUtenti2(elenco);
        for (Utente utenteTrovato : trovati4) {
            System.out.println(utenteTrovato.toString());
        }
    }

    private static List<Utente> elencoUtenti() {
        List<Utente> utenti = new ArrayList<>();

        utenti.add(new Utente("Davide", "Tedesco", 41, "Napoli", "info@dominio.it", "test1"));
        utenti.add(new Utente("Pippo", "Pippone", 30, "Milano", "info@dominio.com", "test2"));
        utenti.add(new Utente("Topolino", "Mouse", 25, "Roma", "info@dominio.org", "test3"));
        utenti.add(new Utente("Pippo", "Rossi", 44, "Lucca", "info@dominio.it", "test4"));
        utenti.add(new Utente("Paperino", "Duck", 45, "Pisa", "info@dominio.com", "test5"));

        return utenti;
    }

    public static List<Utente> cercaUtenti(List<Utente> utenti, Predicate<Utente> predicate) {
        List<Utente> utentiTrovati = new ArrayList<Utente>();

        for (Utente u:utenti) {
            if (predicate.test(u)){
                utentiTrovati.add(u);
            }
        }

        return utentiTrovati;
    }

    public static List<Utente> cercaUtenti2(List<Utente> utenti) {
        List<Utente> utentiTrovati = new ArrayList<Utente>();
        TestPredicate testPredicate = new TestPredicate();

        for (Utente u:utenti) {
            if (testPredicate.test(u)){
                utentiTrovati.add(u);
            }
        }

        return utentiTrovati;
    }
}
