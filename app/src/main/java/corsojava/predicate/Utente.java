package corsojava.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Utente {
    private String nome;
    private String cognome;
    private int eta;
    private String cittaResidenza;
    private String email;
    private String password;

}
