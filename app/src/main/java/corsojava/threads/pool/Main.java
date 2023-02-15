package corsojava.threads.pool;

public class Main {
    public static void main(String[] args) {
        BanconeSalumiFormaggi bancone = new BanconeSalumiFormaggi();

        System.out.println("Nel supermercato ci sono " + bancone.getCLIENTI_DA_SERVIRE() + " clienti che stanno andando al bancone");

        bancone.arrivoClientiAlBancone();
        bancone.servizioClienti();
    }
}
