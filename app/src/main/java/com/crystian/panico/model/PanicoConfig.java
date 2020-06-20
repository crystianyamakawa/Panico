package com.crystian.panico.model;

import com.orm.SugarRecord;

import java.math.BigDecimal;

public class PanicoConfig extends SugarRecord<PanicoConfig> {

    private String telefone1;
    private Boolean telefone1_ligar;
    private Boolean telefone1_sms;

    private String telefone2;
    private Boolean telefone2_ligar;
    private Boolean telefone2_sms;

    private String telefone3;
    private Boolean telefone3_ligar;
    private Boolean telefone3_sms;

    private String textoMensagem;
    private String tempoReenvio;

    private String longitude;
    private String latitude;


    public PanicoConfig(String telefone1, Boolean telefone1_ligar,
                        Boolean telefone1_sms, String telefone2,
                        Boolean telefone2_ligar, Boolean telefone2_sms,
                        String telefone3, Boolean telefone3_ligar,
                        Boolean telefone3_sms, String textoMensagem, String tempoReenvio) {
        this.telefone1 = telefone1;
        this.telefone1_ligar = telefone1_ligar;
        this.telefone1_sms = telefone1_sms;
        this.telefone2 = telefone2;
        this.telefone2_ligar = telefone2_ligar;
        this.telefone2_sms = telefone2_sms;
        this.telefone3 = telefone3;
        this.telefone3_ligar = telefone3_ligar;
        this.telefone3_sms = telefone3_sms;
        this.textoMensagem = textoMensagem;
        this.tempoReenvio = tempoReenvio;
    }

    public PanicoConfig() {

    }

    public PanicoConfig(String longitude, String latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public Boolean getTelefone1_ligar() {
        return telefone1_ligar;
    }

    public void setTelefone1_ligar(Boolean telefone1_ligar) {
        this.telefone1_ligar = telefone1_ligar;
    }

    public Boolean getTelefone1_sms() {
        return telefone1_sms;
    }

    public void setTelefone1_sms(Boolean telefone1_sms) {
        this.telefone1_sms = telefone1_sms;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Boolean getTelefone2_ligar() {
        return telefone2_ligar;
    }

    public void setTelefone2_ligar(Boolean telefone2_ligar) {
        this.telefone2_ligar = telefone2_ligar;
    }

    public Boolean getTelefone2_sms() {
        return telefone2_sms;
    }

    public void setTelefone2_sms(Boolean telefone2_sms) {
        this.telefone2_sms = telefone2_sms;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Boolean getTelefone3_ligar() {
        return telefone3_ligar;
    }

    public void setTelefone3_ligar(Boolean telefone3_ligar) {
        this.telefone3_ligar = telefone3_ligar;
    }

    public Boolean getTelefone3_sms() {
        return telefone3_sms;
    }

    public void setTelefone3_sms(Boolean telefone3_sms) {
        this.telefone3_sms = telefone3_sms;
    }

    public String getTextoMensagem() {
        return textoMensagem;
    }

    public void setTextoMensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
    }

    public String getTempoReenvio() {
        return tempoReenvio;
    }

    public void setTempoReenvio(String tempoReenvio) {
        this.tempoReenvio = tempoReenvio;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
