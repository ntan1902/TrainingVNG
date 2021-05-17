package com.vng.demo.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vng.demo.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Card name, card holder name for identify bank name
 *
 * uid for identify user
 */
public class Payment {
    private int id;

    private String bankName;
    private String cardNumber;
    private String cardHolderName;

    // CMND = 0
    // CCCD = 1
    // Passport = 2
    private int typeIdentity;
    private String uid;

    public Payment(@JsonProperty("bankName") String bankName,
                   @JsonProperty("cardNumber") String cardNumber,
                   @JsonProperty("cardHolderName") String cardHolderName,
                   @JsonProperty("typeIdentity") int typeIdentity,
                   @JsonProperty("uid") String uid) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.typeIdentity = typeIdentity;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getTypeIdentity() {
        return typeIdentity;
    }

    public void setTypeIdentity(int typeIdentity) {
        this.typeIdentity = typeIdentity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", typeIdentity=" + typeIdentity +
                ", uid='" + uid + '\'' +
                '}';
    }

    public int isValid() {
        if(!isValidBankName()) return 1;
        if(!isValidCardNumber()) return 2;

        return 0;
    }

    public boolean isValidBankName() {
        return !this.bankName.isEmpty();
    }

    public boolean isValidCardNumber() {
        switch (bankName) {
            case "VCB" : Regex.checkRegex(this.cardNumber, "(^970436)(\\d{13}$)");
            case "SCB" : Regex.checkRegex(this.cardNumber, "(^\\d{9})(678$)");
            case "OCB" : Regex.checkRegex(this.cardNumber, "^((?!012))\\d{9}[13579]$");
            default:
                return false;
        }
    }
}
