public class Irasas {
    private final int id;
    private double suma;

    private boolean arIBanka;
    private String papildomaInfo;

    private String irasoTipas;

    public Irasas(int id, double suma, boolean arIBanka, String papildomaInfo, String recordType) {
        this.id = id;
        this.suma = suma;
        this.arIBanka = arIBanka;
        this.papildomaInfo = papildomaInfo;
        this.irasoTipas = recordType;
    }

    public void setArIBanka(boolean arIBanka) {
        this.arIBanka = arIBanka;
    }

    public void setIrasoTipas(String irasoTipas) {
        this.irasoTipas = irasoTipas;
    }

    public boolean isArIBanka() {
        return arIBanka;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public int getId() {
        return id;
    }

    public String getIrasoTipas() {
        return irasoTipas;
    }

    @Override
    public String toString() {
        if (this.irasoTipas.equals("Pajamos")) {
            return String.format(Pranesimai.PAJAMU_FORMATAS.pranesimas, id, suma, arIBanka, papildomaInfo, irasoTipas);
        } else {
            return String.format(Pranesimai.ISLAIDU_FORMATAS.pranesimas, id, suma, arIBanka, papildomaInfo, irasoTipas);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Irasas irasas)) return false;
        return id == irasas.id;
    }
}
