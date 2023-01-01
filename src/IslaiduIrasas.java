import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IslaiduIrasas extends Irasas {
    private String islaiduKategorija;
    private String dataIrLaikas;
    private String islaiduTipas;

    public IslaiduIrasas(int id, double suma, String islaiduKategorija, boolean arIBanka, String papildomaInfo,
                         String islaiduTipas, String irasoTipas) {
        super(id, suma, arIBanka, papildomaInfo, irasoTipas);
        this.islaiduKategorija = islaiduKategorija;
        this.islaiduTipas = islaiduTipas;
        setDataIrLaikas();
    }

    public IslaiduIrasas(int id) {
        super(id);
    }

    public String getCsvValue(){
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s\n", this.getId(), this.getSuma(), this.isArIBanka(),
                this.getPapildomaInfo(), this.getIslaiduKategorija(), this.getDataIrLaikas(),
                this.getIslaiduKategorija(), this.getIslaiduTipas());
    }

    public String getDataIrLaikas() {
        return dataIrLaikas;
    }

    public void setDataIrLaikas() {
        LocalDateTime t = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dataIrLaikas = t.format(timeFormatter);
    }

    public void setDataIrLaikas(LocalDateTime t) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dataIrLaikas = t.format(timeFormatter);
    }

    public String getIslaiduKategorija() {
        return islaiduKategorija;
    }

    public String getIslaiduTipas() {
        return islaiduTipas;
    }

    public void setIslaiduKategorija(String islaiduKategorija) {
        this.islaiduKategorija = islaiduKategorija;
    }

    public void setIslaiduTipas(String islaiduTipas) {
        this.islaiduTipas = islaiduTipas;
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
        return super.toString().concat(String.format(Pranesimai.FORMATAS.pranesimas, dataIrLaikas, islaiduKategorija, islaiduTipas));
    }
}
