import java.util.Scanner;

public class Main {
    public static void main (String [] args){
        Biudzetas b1 = new Biudzetas();
        Scanner sc = new Scanner(System.in);
        boolean runProgram = true;

        while(runProgram){
            komandos();
            String command = sc.nextLine();
            switch (command) {
                case "1" -> b1.sukurtiIrPrideti(sc);
                case "2" -> System.out.printf("Bendros pajamos: %d\n", b1.gautiVisasPajamas());
                case "3" -> System.out.printf("Bendros išlaidos: %d\n", b1.gautiVisasIslaidas());
                case "4" -> System.out.printf("Dabartinis balansas: %.2f EUR\n", b1.balansas());
                case "5" -> b1.redaguoti(sc);
                case "6" -> b1.atspausdintiPajamuIrasus();
                case "7" -> b1.atspausdintiIslaiduIrasus();
                case "8" -> b1.visiIrasai();
                case "0" -> {
                    System.out.println("Programa baigiama.");
                    runProgram = false;
                }
                default -> System.out.println(Pranesimai.BLOGA_IVESTIS.pranesimas);
            }
        }
        sc.close();
    }

    static void komandos(){
        System.out.print("""
                1 - sukurti naują įrašą
                2 - bendros pajamos
                3 - bendros išlaidos
                4 - patikrinti balansą
                5 - redaguoti įrašą
                6 - atspausdinti pajamas
                7 - atspausdinti išlaidas
                8 - visi irasai
                0 - nutraukia programos veikimą
                """);
    }
}