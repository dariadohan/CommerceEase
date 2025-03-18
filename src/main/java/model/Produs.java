package model;
/**
 * clasa care reprezinta un obiect de tipul produs alaturi
 * de particularitatile necesare (id,denumire,pret,nr_buc)
 */
public class Produs {
    private int id;
    private String denumire;
    private double pret;
    private int nr_buc;

    /**constructor gol
     *
     */
    public Produs(){

    }

    public Produs(int id, String denumire, double pret, int nrBuc) {
        this.id=id;
        this.denumire=denumire;
        this.pret=pret;
        this.nr_buc=nrBuc;
    }

    /**getter ptr id-ul produsului
     *
     * @return id-ul
     */
    public int getId() {
        return id;
    }

    /**getter ptr denumirea produsului
     *
     * @return denumire
     */
    public String getDenumire() {
        return denumire;
    }

    /**
     * gettter pentru pretul produsului
     * @return pret
     */
    public double getPret() {
        return pret;
    }

    /**getter ptr nr bucati rpodus
     *
     * @return nr_buc
     */
    public int getNr_buc() {
        return nr_buc;
    }

    /**
     * setter ptr id-ul produsului
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter ptr denumirea produsului
     * @param denumire
     */
    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    /**
     * setter pentru pretul produsului
     * @param pret
     */
    public void setPret(double pret) {
        this.pret = pret;
    }

    /**setter ptr nr buc. ale produsului
     *
     * @param nr_buc
     */
    public void setNr_buc(int nr_buc) {
        this.nr_buc = nr_buc;
    }
}
