package com.example.logindemo.Entity;

import java.util.List;

public class Translator {
    Integer translatorId;
    String translator;
    boolean status;

    public Translator(Integer translatorId, String translator, boolean status) {
        this.translatorId = translatorId;
        this.translator = translator;
        this.status = status;
    }

    public Translator() {
    }

    public Integer getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(Integer translatorId) {
        this.translatorId = translatorId;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
