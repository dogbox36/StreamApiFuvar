import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fuvar {
    private int id;
    private int Utazasiido;
    private String fizetes;
    private LocalDateTime indulas;
    private double tavolsag;
    private double utar;
    private double borravalo;


    public Fuvar(int id, LocalDateTime indulas, int Utazasiido, double tavolsag, double utar, double borravalo, String fizetes) {
        this.id = id;
        this.indulas = indulas;
        this.Utazasiido = Utazasiido;
        this.tavolsag = tavolsag;
        this.utar = utar;
        this.borravalo = borravalo;
        this.fizetes = fizetes;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getIndulas() {
        return indulas;
    }

    public int getUtazasiido() {
        return Utazasiido;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public double getUtar() {
        return utar;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public String getFizetes() {
        return fizetes;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = String.format(
                "Taxi szám: %d\n" +
                        "Indulási idő: %s\n" +
                        "Utazás időtartalma: %d másodperc\n" +
                        "Megtett távolság: %.2f mérföld\n" +
                        "Viteldíj: %.2f$\n" +
                        "Borravaló: %.2f$\n" +
                        "Fizetés módja: %s\n",
                this.id, this.indulas.format(formatter),this.Utazasiido,
                this.tavolsag, this.utar, this.borravalo, this.fizetes
        );
        return s;
    }
}