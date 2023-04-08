public class App {

    public static void main(String[] args) throws Exception {
        Historia historia = new Historia();
        historia.recuperarHistoria();
        historia.salvarHistoria();
        historia.executar();
    }


}
