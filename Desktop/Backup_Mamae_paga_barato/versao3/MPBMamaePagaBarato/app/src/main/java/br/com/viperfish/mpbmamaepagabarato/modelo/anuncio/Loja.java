package br.com.viperfish.mpbmamaepagabarato.modelo.anuncio;

import android.net.Uri;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.Serializable;

/**
 * Created by ddark on 28/08/17.
 */

public class Loja implements Serializable {

    private String id;
    private String nome;
    private String endereco;
    private String fone;
    private String websiteUri;
    private Double latitude;
    private Double longitude;

    public Loja() {
    }

    public Loja(String id, String nome, String endereco, String fone, String websiteUri, Double latitude, Double longitude) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.fone = fone;
        this.websiteUri = websiteUri;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Loja(Place place) {

        this.id = place.getId();
        this.nome = place.getName().toString();

        if (place.getAddress() != null) {
            this.endereco = place.getAddress().toString();
        }

        if(place.getPhoneNumber() != null) {
            this.fone = place.getPhoneNumber().toString();
        }

        if (place.getWebsiteUri() != null) {
            this.websiteUri = place.getWebsiteUri().toString();
        }

        this.latitude = place.getLatLng().latitude;
        this.longitude = place.getLatLng().longitude;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
