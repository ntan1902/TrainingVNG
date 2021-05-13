package com.vng.demo.payment;

/**
 * Card name, card holder name for identify bank name
 *
 * uid for identify user
 */
public class Payment {

    private String bankName;
    private String cardNumber;
    private String cardHolderName;

    // CMND, CCCD, Passport
    private TypeIdentity typeIdentity;
    private String uid;

    public Payment(String bankName,
                   String cardNumber,
                   String cardHolderName,
                   TypeIdentity typeIdentity,
                   String identityNumber) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.typeIdentity = typeIdentity;
        this.uid = identityNumber;
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

    public TypeIdentity getTypeIdentity() {
        return typeIdentity;
    }

    public void setTypeIdentity(TypeIdentity typeIdentity) {
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
                "bankName='" + bankName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", typeIdentity=" + typeIdentity +
                ", uid='" + uid + '\'' +
                '}';
    }
}
