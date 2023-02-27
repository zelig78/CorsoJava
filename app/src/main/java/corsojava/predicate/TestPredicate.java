package corsojava.predicate;

import java.util.function.Predicate;

public class TestPredicate implements Predicate<Utente> {
    @Override
    public boolean test(Utente utente) {
        return utente.getNome().equals("Davide") && utente.getCognome().equals("Tedesco");
    }
}
