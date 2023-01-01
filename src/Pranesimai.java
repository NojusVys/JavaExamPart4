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
    ISLAIDU_FORMATAS("| %-5d | %-10.2f | %-15s | %-15s | %-15s ");


    public final String pranesimas;

    Pranesimai(String pranesimas) {
        this.pranesimas = pranesimas;
    }
}
