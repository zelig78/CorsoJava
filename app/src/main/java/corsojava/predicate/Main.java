package corsojava.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Utente> elenco = elencoUtenti();
        List<Utente> trovati = cercaUtenti(elenco, utente -> utente.getNome().equals("Paolo"));

        for (Utente utenteTrovato : trovati) {
            System.out.println(utenteTrovato.getNome() + " " + utenteTrovato.getCognome());
        }
    }

    private static List<Utente> elencoUtenti() {
        List<Utente> utenti = new ArrayList<Utente>();

        utenti.add(new Utente("Paolo", "Preite", 39, "Roma", "info@dominio.it", "test"));
        utenti.add(new Utente("Pippo", "Pippone", 39, "Milano", "info@dominio.it", "test"));
        utenti.add(new Utente("Topolino", "Mouse", 39, "Milano", "info@dominio.it", "test"));
        utenti.add(new Utente("Paolo", "Rossi", 39, "Milano", "info@dominio.it", "test"));
        utenti.add(new Utente("Paperino", "Duck", 39, "Milano", "info@dominio.it", "test"));

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
}
