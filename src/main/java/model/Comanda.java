package model;
/**
 * clasa care reprezinta un obiect de tipul comanda alaturi
 * de particularitatile necesare (id,pret total,adresa)
 */
public class Comanda {
    private int id;
    private String adresa;
    private int idClient;
    private int idProdus;

    /**
     * constructor gol
     */
    public Comanda(){

    }

    public Comanda(int id, String adress,int idClient,int idProdus) {
        this.id=id;
        this.adresa=adress;
        this.idClient=idClient;
        this.idProdus=idProdus;
    }

    /**
     * getter ptr id comenzii
     * @return id
     */
    public int getId() {
        return id;
    }



    /**
     * getter ptr adresa comezii
     * @return adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * setter ptr id comezii
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter ptr adresa comenzii
     * @param adresa
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }
}
