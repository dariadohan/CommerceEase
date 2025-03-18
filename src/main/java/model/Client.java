package model;

/**
 * clasa care reprezinta un obiect de tipul client alaturi
 * de particularitatile necesare (id,nume,email,nr_tel)
 */
public class Client {
    private int id;
    private String nume;
    private String email;
    private String nr_tel;

    /**
     * constructor gol
     */
    public Client(){

    }

    /**
     * constructor ptr clasa Client
     * @param id
     * @param nume
     * @param email
     * @param nr_tel
     */
    public Client(int id, String nume, String email, String nr_tel){
        this.id=id;
        this.nume=nume;
        this.email=email;
        this.nr_tel=nr_tel;
    }

    /**
     * getter id client
     * @return id-ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * getter nume client
     * @return numele clientului
     */
    public String getNume() {
        return nume;
    }

    /**
     * getter email client
     * @return email-ul client
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter nr de tel
     * @return nr de telefon
     */
    public String getNr_tel() {
        return nr_tel;
    }

    /**
     * setter id client
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter nume client
     * @param nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * setter email client
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * setter nr tel client
     * @param nr_tel
     */
    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }


}
