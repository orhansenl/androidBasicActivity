package com.example.myapplication;

public class Ajan {
    int id;
    String ad;
    String aciklama;

    public Ajan() {
    }

    public Ajan(String ad, String aciklama) {
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
