package DTO;

public class Virksomhed {

    private int VirksomhedsID;
    private String navn;

    public Virksomhed() {}

    public Virksomhed(int virksomhedsID, String navn) {
        VirksomhedsID = virksomhedsID;
        this.navn = navn;
    }

    public int getVirksomhedsID() {
        return VirksomhedsID;
    }

    public void setVirksomhedsID(int virksomhedsID) {
        VirksomhedsID = virksomhedsID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
