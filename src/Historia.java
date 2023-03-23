import java.util.Scanner;

public class Historia{
    private Capitulo inicio;


    public void executar() throws InterruptedException{
        Capitulo aux = this.inicio;
        escolher(aux);
    }

    private void escolher(Capitulo capitulo) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(capitulo.getTitulo());
        System.out.println(" ");
        System.out.println(capitulo.getContexto());
        System.out.println(" ");

        if(capitulo.getFim() == true){
            return;
        }

        System.out.println(capitulo.getEscolha01().getTitulo());
        System.out.println(capitulo.getEscolha02().getTitulo());

        System.out.println(" ");
        System.out.println("Digite sua opção");

        int escolha = scanner.nextInt();
        
        if (escolha == 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("...");
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Capitulo aux = capitulo.getEscolha01();
            escolher(aux);
        } else if (escolha == 2) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("...");
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Capitulo aux = capitulo.getEscolha02();
            escolher(aux);
        }
    }

    public void setInicio(Capitulo inicio) {
        this.inicio = inicio;
    }

}
