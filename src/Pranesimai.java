public enum Pranesimai {
    BLOGA_IVESTIS("Netinkama įvestis."),
    SUMA("Įveskite sumą."),
    KATEGORIJA("Įveskite kategoriją."),
    PAPILDOMA_INFO("Įveskite papildomos informacijos"),
    AR_I_BANKA("Ar pajamos pervedamos į banką? (true/false)"),
    PAJAMU_TIPAS("Kokio tipo pajamos?"),
    ISLAIDU_TIPAS("Kokio tipo išlaidos?"),
    TRINTI("Kurią operaciją norite pašalinti?"),
    REDAGUOTI("Pasirinkite operaciją, kuria norite redaguoti."),
    NETINKAMAS_ID("Operacija su tokiu id nerasta."),
    FORMATAS("| %-20s | %-20s | %-20s |"),
    PAJAMU_FORMATAS("| %-5d | %-10.2f | %-10s | %-15s | %-15s "),
    ISLAIDU_FORMATAS("| %-5d | %-10.2f | %-15s | %-15s | %-15s "),
    FAILO_KLAIDA("Failo kalida. Patikrinkite direktoriją/pavadinimą."),
    FAILAS("""
                Priimamas failo fromatas:
                1 stulpelis: P - pajamos , I - islaidos
                2 stulpelis: int Id
                3 stulpelis: double Suma
                4 stulpelis: boolean Ar bankas? (TRUE/FALSE)
                5 stulpelis: String Komentaras
                6 stulpelis: String Įrašo tipas
                7 stulpelis: String data (pajamų įrašo formatas: yyyy-MM-dd; išlaidų įrašo formatas: yyyy-MM-dd HH:mm:ss)
                8 stulpelis: String Kategorija
                9 stulpelis: String Tipas
                Stulpeliai atskiriami ","
                """);;


    public final String pranesimas;

    Pranesimai(String pranesimas) {
        this.pranesimas = pranesimas;
    }
}
