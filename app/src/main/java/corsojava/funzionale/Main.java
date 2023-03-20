package corsojava.funzionale;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> persone = Arrays.asList("Pippo Pippone", "Topolino Mouse", "Mario Rossi");

        for (String persona:persone) {
            System.out.println(persona);
        }

        persone.forEach(System.out::println);

        Function<String,String> estraiIniziali = (String s) -> {
            String[] nomeCognome = s.split(" ");
            return new StringBuilder().append(nomeCognome[0].toUpperCase().charAt(0))
                    .append(nomeCognome[1].toUpperCase().charAt(0)).toString();
        };

        System.out.println(estraiIniziali.apply(persone.get(0)));

        persone.forEach(persona -> System.out.println(estraiIniziali.apply(persona)));

        UnaryOperator<Integer> quadrato = n -> n*n;
        System.out.println(quadrato.apply(10));

        UnaryOperator<Integer> triplo = n -> 3*n;
        System.out.println(triplo.apply(10));

        Function<Integer, Integer> f = quadrato.andThen(triplo);
        System.out.println(f.apply(10));

        Predicate<Integer> pari = n -> n%2 == 0;
        System.out.println(pari.test(3));

        List<Integer> numeri = Arrays.asList(1, 4, 6, 2, 3, 8, 6);
        System.out.println(numeri.stream().map(quadrato).filter(pari).collect(Collectors.toList()));
        System.out.println(numeri.stream().map(quadrato).filter(pari.negate()).collect(Collectors.toList()));
    }

}
