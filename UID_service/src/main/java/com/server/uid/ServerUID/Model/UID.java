package com.server.uid.ServerUID.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UID {
    @Id
    private int id=1;
    private String symbol;
    private String apha;
    private String num;
    private boolean isSymbolFull;
    private boolean isAlphFull;
    private boolean isNumFull;

    public UID() {
    }

    public UID(String symbol, String apha, String num, boolean isSymbolFull, boolean isAlphFull, boolean isNumFull) {
        this.symbol = symbol;
        this.apha = apha;
        this.num = num;
        this.isSymbolFull = isSymbolFull;
        this.isAlphFull = isAlphFull;
        this.isNumFull = isNumFull;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getApha() {
        return apha;
    }

    public void setApha(String apha) {
        this.apha = apha;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isSymbolFull() {
        return isSymbolFull;
    }

    public void setSymbolFull(boolean symbolFull) {
        isSymbolFull = symbolFull;
    }

    public boolean isAlphFull() {
        return isAlphFull;
    }

    public void setAlphFull(boolean alphFull) {
        isAlphFull = alphFull;
    }

    public boolean isNumFull() {
        return isNumFull;
    }

    public void setNumFull(boolean numFull) {
        isNumFull = numFull;
    }

    public int getId() {
        return id;
    }
}
