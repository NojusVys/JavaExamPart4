import java.util.ArrayList;
import java.util.Scanner;

public class Biudzetas {
    ArrayList<Irasas> irasas = new ArrayList<>();
    public static int id = 1;

    public void sukurtiIrPrideti(Scanner sc){
        Irasas irasas1 = sukurtiIrasa(sc, id);
        pridetiIrasa(irasas1);
    }

    private void pridetiIrasa(Irasas irasas1){
        irasas.add(irasas1);
    }
    
    private Irasas sukurtiIrasa(Scanner sc, int id){
        int newId = id++;
        return gautiNaujaIrasa(sc, newId);
    }

    private Irasas gautiNaujaIrasa(Scanner sc, int newId) {
        System.out.println("Pasirinkite");
        String type = pasirinkimas(sc, "[1] - Pajamų įrašas", "[2] - Išlaidų įrašas");
        System.out.println(Pranesimai.SUMA.pranesimas);
        double suma = validacijaDouble(sc);
        System.out.println(Pranesimai.KATEGORIJA.pranesimas);
        String kategorija = validacijaString(sc);
        System.out.println(Pranesimai.AR_I_BANKA.pranesimas);
        boolean atsiskaitymoBudasBankas = Boolean.parseBoolean(sc.nextLine());
        System.out.println(Pranesimai.PAPILDOMA_INFO.pranesimas);
        String papildomaInfo = validacijaString(sc);
        if (type.equals("1")) {
            String irasoTipas = "pajamos";
            System.out.println(Pranesimai.PAJAMU_TIPAS.pranesimas);
            String pajamuTipas = validacijaString(sc);
            return new PajamuIrasas(id, suma, kategorija, atsiskaitymoBudasBankas, papildomaInfo, pajamuTipas, irasoTipas);
        } else if (type.equals("2")) {
            String irasoTipas = "islaidos";
            System.out.println(Pranesimai.ISLAIDU_TIPAS.pranesimas);
            String islaiduTipas = validacijaString(sc);
            return new IslaiduIrasas(id, suma, kategorija, atsiskaitymoBudasBankas, papildomaInfo, islaiduTipas, irasoTipas);
        } else {
            return null;
        }
    }

    private String pasirinkimas(Scanner sc, String... choices) {
        String result = "";
        boolean runLoop = true;
        while (runLoop) {
            for (String choice : choices) {
                System.out.println(choice);
            }
            try {
                if (Integer.parseInt(sc.nextLine()) > 0 && Integer.parseInt(sc.nextLine()) <= choices.length) {
                    result = sc.nextLine();
                    runLoop = false;
                } else {
                        System.out.println(Pranesimai.BLOGA_IVESTIS.pranesimas);
                }
            } catch (NumberFormatException e) {
                System.out.println(Pranesimai.BLOGA_IVESTIS.pranesimas);
            }
        }
        return result;
    }

    public double gautiVisasPajamas() {
        int suma = 0;
        for (Irasas irasa : irasas) {
            if (irasa instanceof PajamuIrasas) {
                suma += irasa.getSuma();
            }
        }
        return suma;
    }

    public double gautiVisasIslaidas() {
        int suma = 0;
        for (Irasas irasa : irasas) {
            if (irasa instanceof IslaiduIrasas) {
                suma += irasa.getSuma();
            }
        }
        return suma;
    }


    public void redaguoti(Scanner sc){
        System.out.println("Pasirinkite veiksmą:");
        String action = pasirinkimas(sc, "[1] - Pakeisti įrašą", "[2] - Ištrinti įrašą");
        if (action.equals("1")) {
            pakeistiIrasa(sc);
        }
        if (action.equals("2")) {
            pasalintiIrasa(sc);
        }
    }

    private void pakeistiIrasa(Scanner sc) {
        String modificationLevel = pasirinkimas(sc, "[1] - Dalinis įrašo keitimas", "[2] - Pilnas įrašo keitimas");
        visiIrasai();
        System.out.println(Pranesimai.REDAGUOTI.pranesimas);
        int id = validacijaInt(sc);
        if (irasas.stream().anyMatch(o -> id == o.getId())) {
            if (modificationLevel.equals("1")) {
                Irasas irasas1 = gautiIrasa(id);
                assert irasas1 != null;
                double suma = irasas1.getSuma();
                boolean arBankas = irasas1.isArIBanka();
                String komentaras = irasas1.getPapildomaInfo();
                String kategorija = "";
                String tipas = "";
                if (irasas1 instanceof IslaiduIrasas) {
                    kategorija = ((IslaiduIrasas) irasas1).getIslaiduKategorija();
                    tipas = ((IslaiduIrasas) irasas1).getIslaiduTipas();
                }
                if (irasas1 instanceof PajamuIrasas) {
                    kategorija = ((PajamuIrasas) irasas1).getPajamuKategorija();
                    tipas = ((PajamuIrasas) irasas1).getPajamuTipas();
                }
                boolean isOver = keitimas(sc, irasas1, "Suma");
                if (!isOver) {
                    isOver = keitimas(sc, irasas1, "Ar bankas?");
                }
                if (!isOver) {
                    isOver = keitimas(sc, irasas1, "Komentaras");
                }
                if (!isOver) {
                    isOver = keitimas(sc, irasas1, "Kategorija");
                }
                if (!isOver) {
                    keitimas(sc, irasas1, "Tipas");
                }
                String kategorijaPoPakeitimo = "";
                String tipasPoPakeitimo = "";
                if (irasas1 instanceof IslaiduIrasas) {
                    kategorijaPoPakeitimo = ((IslaiduIrasas) irasas1).getIslaiduKategorija();
                    tipasPoPakeitimo = ((IslaiduIrasas) irasas1).getIslaiduTipas();
                }
                if (irasas1 instanceof PajamuIrasas) {
                    kategorijaPoPakeitimo = ((PajamuIrasas) irasas1).getPajamuKategorija();
                    tipasPoPakeitimo = ((PajamuIrasas) irasas1).getPajamuTipas();
                }
                if (suma != irasas1.getSuma()
                        || arBankas != irasas1.isArIBanka()
                        || !komentaras.equals(irasas1.getPapildomaInfo())
                        || !kategorija.equals(kategorijaPoPakeitimo)
                        || !tipas.equals(tipasPoPakeitimo)) {
                    if (irasas1 instanceof IslaiduIrasas) {
                        ((IslaiduIrasas) irasas1).setDataIrLaikas();
                    }
                    if (irasas1 instanceof PajamuIrasas) {
                        ((PajamuIrasas) irasas1).setData();
                    }
                    System.out.println("Pakeitimai įvykdyti.");
                } else {
                    System.out.println("Niekas nepakeista.");
                }
                visiIrasai();
            }
            if (modificationLevel.equals("2")) {
                atnaujintiIrasa(sukurtiIrasa(sc, id));
                System.out.println("Pakeitimai įvykdyti.");
                visiIrasai();
            }
        } else {
            System.out.printf(Pranesimai.NETINKAMAS_ID.pranesimas);
        }
    }

    private void atnaujintiIrasa(Irasas irasas1) {
        for (Irasas irasa : irasas) {
            if (irasa.equals(irasas1)) {
                int index = irasas.indexOf(irasa);
                irasas.set(index, irasas1);
            }
        }
    }

    private void pasalintiIrasa(Scanner sc) {
        visiIrasai();
        System.out.println(Pranesimai.TRINTI.pranesimas);
        int id = Integer.parseInt(sc.nextLine());
        if (irasas.stream().anyMatch(o -> id == o.getId())) {
            for (Irasas irasa : irasas) {
                if (irasa.getId() == id) {
                    irasas.remove(irasa);
                    break;
                }
            }
        } else {
            System.out.printf(Pranesimai.NETINKAMAS_ID.pranesimas);
        }
        visiIrasai();
    }

    private Irasas gautiIrasa(int id) {
        for (Irasas irasa : irasas) {
            if (irasa.getId() == id) {
                return irasa;
            }
        }
        return null;
    }

    public void atspausdintiPajamuIrasus() {
        for (PajamuIrasas irasa : gautiPajamuIrasa()) {
            System.out.println(irasa);
        }
    }

    public void atspausdintiIslaiduIrasus() {
        for (IslaiduIrasas irasa : gautiIslaiduIrasa()) {
            System.out.println(irasa);
        }
    }

    public ArrayList<PajamuIrasas> gautiPajamuIrasa(){
        ArrayList<PajamuIrasas> pajamos = new ArrayList<>();
        for (Irasas irasa : irasas) {
            if (irasa instanceof PajamuIrasas) {
                pajamos.add((PajamuIrasas) irasa);
            }
        }
        return pajamos;
    }

    public ArrayList<IslaiduIrasas> gautiIslaiduIrasa(){
        ArrayList<IslaiduIrasas> islaidos = new ArrayList<>();
        for (Irasas irasa : irasas) {
            if (irasa instanceof IslaiduIrasas) {
                islaidos.add((IslaiduIrasas) irasa);
            }
        }
        return islaidos;
    }

    public void visiIrasai() {
        for (Irasas irasa : getIrasai()) {
            System.out.println(irasa);
        }
    }

    public ArrayList<Irasas> getIrasai() {
        return irasas;
    }

    private boolean keitimas(Scanner sc, Irasas irasas1, String field) {
        switch (field) {
            case "Suma" -> {
                System.out.println("Suma: " + irasas1.getSuma());
                String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                if (answer.equals("1")) {
                    System.out.println(Pranesimai.SUMA.pranesimas);
                    irasas1.setSuma(Double.parseDouble(sc.nextLine()));
                }
                if (answer.equals("3")) {
                    return true;
                }
            }
            case "Ar bankas?" -> {
                System.out.println("Ar bankas?: " + irasas1.isArIBanka());
                String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                if (answer.equals("1")) {
                    System.out.println(Pranesimai.AR_I_BANKA.pranesimas);
                    irasas1.setArIBanka(Boolean.parseBoolean(sc.nextLine()));
                }
                if (answer.equals("3")) {
                    return true;
                }
            }
            case "Komentaras" -> {
                System.out.println("Komentaras: " + irasas1.getPapildomaInfo());
                String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                if (answer.equals("1")) {
                    System.out.println("Įveskite komentarą");
                    irasas1.setPapildomaInfo(sc.nextLine());
                }
                if (answer.equals("3")) {
                    return true;
                }
            }
            case "Kategorija" -> {
                if (irasas1 instanceof IslaiduIrasas) {
                    System.out.println("Kategorija: " + ((IslaiduIrasas) irasas1).getIslaiduKategorija());
                    String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                    if (answer.equals("1")) {
                        System.out.println("Įveskite išlaidų kategoriją.");
                        ((IslaiduIrasas) irasas1).setIslaiduKategorija(sc.nextLine());
                    }
                    if (answer.equals("3")) {
                        return true;
                    }
                }
                if (irasas1 instanceof PajamuIrasas) {
                    System.out.println("Kategorija: " + ((PajamuIrasas) irasas1).getPajamuKategorija());
                    String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                    if (answer.equals("1")) {
                        System.out.println("Įveskite pajamų kategoriją.");
                        ((PajamuIrasas) irasas1).setPajamuKategorija(sc.nextLine());
                    }
                    if (answer.equals("3")) {
                        return true;
                    }
                }
            }
            case "Tipas" -> {
                if (irasas1 instanceof PajamuIrasas) {
                    System.out.println("Tipas: " + ((PajamuIrasas) irasas1).getPajamuTipas());
                    String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                    if (answer.equals("1")) {
                        System.out.println("Įveskite pajamų tipą");
                        ((PajamuIrasas) irasas1).setPajamuTipas(sc.nextLine());
                    }
                    if (answer.equals("3")) {
                        return true;
                    }
                }
                if (irasas1 instanceof IslaiduIrasas) {
                    System.out.println("Tipas: " + ((IslaiduIrasas) irasas1).getIslaiduTipas());
                    String answer = pasirinkimas(sc, "[1] - Redaguoti", "[2] - Toliau", "[3] - Baigti redagavimą");
                    if (answer.equals("1")) {
                        System.out.println("Įveskite išlaidų tipą.");
                        ((IslaiduIrasas) irasas1).setIslaiduTipas(sc.nextLine());
                    }
                    if (answer.equals("3")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private double validacijaDouble(Scanner sc) {
        double suma = 0;
        boolean isNotDouble = true;
        while (isNotDouble) {
            try {
                suma = Double.parseDouble(sc.nextLine());
                isNotDouble = false;
            } catch (NumberFormatException e) {
                System.out.println(Pranesimai.BLOGA_IVESTIS.pranesimas);
            }
        }
        return suma;
    }

    private int validacijaInt(Scanner sc) {
        int id = 0;
        boolean isNotInt = true;
        while (isNotInt) {
            try {
                id = Integer.parseInt(sc.nextLine());
                isNotInt = false;
            } catch (NumberFormatException e) {
                System.out.println(Pranesimai.BLOGA_IVESTIS.pranesimas);
            }
        }
        return id;
    }

    private String validacijaString(Scanner sc) {
        boolean isString = false;
        String input = "";
        while (!isString) {
            input = sc.nextLine();
            if (input.length() > 0) {
                isString = true;
            } else {
                System.out.println(Pranesimai.BLOGA_IVESTIS.pranesimas);
            }
        }
        return input;
    }

    public void pakeistiIrasus(ArrayList<Irasas> irasas) {
        if (irasas != null && irasas.size() > 0) {
            setIrasas(irasas);
        } else {
            System.out.println(Pranesimai.FAILO_KLAIDA.pranesimas);
        }
    }

    public double balansas(){
            return gautiVisasPajamas() - gautiVisasIslaidas();
    }

    public void setIrasas(ArrayList<Irasas> irasas) {
        this.irasas = irasas;
    }
}
