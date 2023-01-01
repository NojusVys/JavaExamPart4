import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PajamuIrasas extends Irasas {
    private String pajamuTipas;
    private String pajamuKategorija;
    private String data;

    public PajamuIrasas(int id, double suma, String kategorija,
                        boolean arIBanka, String papildomaInfo,
                        String pajamuTipas, String irasoTipas) {
        super(id, suma, arIBanka, papildomaInfo, irasoTipas);
        setData();
        this.pajamuKategorija = pajamuKategorija;
        this.pajamuTipas = pajamuTipas;
    }

    public String getData(){
        return data;
    }

    public void setData(){
        LocalDate t = LocalDate.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.data = t.format(timeFormatter);
    }

    public String getPajamuTipas() {
        return pajamuTipas;
    }

    public String getPajamuKategorija() {
        return pajamuKategorija;
    }

    public void setPajamuTipas(String pajamuTipas) {
        this.pajamuTipas = pajamuTipas;
    }

    public void setPajamuKategorija(String pajamuKategorija) {
        this.pajamuKategorija = pajamuKategorija;
    }

    @Override
    public void setArIBanka(boolean arIBanka) {
        super.setArIBanka(arIBanka);
    }

    @Override
    public void setIrasoTipas(String irasoTipas) {
        super.setIrasoTipas(irasoTipas);
    }

    @Override
    public boolean isArIBanka() {
        return super.isArIBanka();
    }

    @Override
    public double getSuma() {
        return super.getSuma();
    }

    @Override
    public void setSuma(double suma) {
        super.setSuma(suma);
    }

    @Override
    public String getPapildomaInfo() {
        return super.getPapildomaInfo();
    }

    @Override
    public void setPapildomaInfo(String papildomaInfo) {
        super.setPapildomaInfo(papildomaInfo);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getIrasoTipas() {
        return super.getIrasoTipas();
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(Pranesimai.FORMATAS.pranesimas, data, pajamuKategorija, pajamuTipas));
    }
}
