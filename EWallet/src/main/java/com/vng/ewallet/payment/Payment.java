//package com.vng.ewallet.payment;
//
//import com.vng.ewallet.regex.Regex;
//
//import javax.persistence.*;
//
///**
// * Card name, card holder name for identify bank name
// *
// * uid for identify user
// */
//
//@Entity
//@Table(name = "payment")
//public class Payment{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String bankName;
//    private String cardNumber;
//    private String cardHolderName;
//
//    // CMND = 0
//    // CCCD = 1
//    // Passport = 2
//    private int typeIdentity;
//    private String uid;
//
//    public Payment( String bankName,
//                    String cardNumber,
//                    String cardHolderName,
//                    int typeIdentity,
//                    String uid) {
//        this.bankName = bankName;
//        this.cardNumber = cardNumber;
//        this.cardHolderName = cardHolderName;
//        this.typeIdentity = typeIdentity;
//        this.uid = uid;
//    }
//
//    public Payment() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getBankName() {
//        return bankName;
//    }
//
//    public void setBankName(String bankName) {
//        this.bankName = bankName;
//    }
//
//    public String getCardNumber() {
//        return cardNumber;
//    }
//
//    public void setCardNumber(String cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
//    public String getCardHolderName() {
//        return cardHolderName;
//    }
//
//    public void setCardHolderName(String cardHolderName) {
//        this.cardHolderName = cardHolderName;
//    }
//
//    public int getTypeIdentity() {
//        return typeIdentity;
//    }
//
//    public void setTypeIdentity(int typeIdentity) {
//        this.typeIdentity = typeIdentity;
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    @Override
//    public String toString() {
//        return "Payment{" +
//                "id=" + id +
//                ", bankName='" + bankName + '\'' +
//                ", cardNumber='" + cardNumber + '\'' +
//                ", cardHolderName='" + cardHolderName + '\'' +
//                ", typeIdentity=" + typeIdentity +
//                ", uid='" + uid + '\'' +
//                '}';
//    }
//
//    public ValidPayment isValid() {
//        if(!isValidBankName()) return ValidPayment.INVALID_BANK_NAME;
//        if(!isValidCardNumber()) return ValidPayment.INVALID_CARD_NUMBER;
//        if(!isValidCardHolderName()) return ValidPayment.INVALID_CARD_HOLDER_NAME;
//        if(!isValidTypeIdentity()) return ValidPayment.INVALID_TYPE_IDENTITY;
//        if(!isValidUid()) return ValidPayment.INVALID_UID;
//        return ValidPayment.SUCCESS;
//    }
//
//    private boolean isValidBankName() {
//        return Regex.checkRegex(this.bankName, "[a-zA-Z ]+");
//    }
//
//    private boolean isValidCardNumber() {
//        switch (this.bankName) {
//            case "VCB" :
//                return Regex.checkRegex(this.cardNumber, "(^970436)(\\d{13}$)");
//            case "SCB" :
//                return Regex.checkRegex(this.cardNumber, "(^\\d{9})(678$)");
//            case "OCB" :
//                return Regex.checkRegex(this.cardNumber, "^((?!012))\\d{9}[13579]$");
//            default:
//                return false;
//        }
//    }
//
//    private boolean isValidCardHolderName(){
//        return Regex.checkRegex(this.cardHolderName, "^([A-Z ]+)$");
//    }
//
//    private boolean isValidTypeIdentity() {
//        return Regex.checkRegex(String.valueOf(this.typeIdentity), "^[012]$");
//    }
//
//    private boolean isValidUid() {
//        switch (this.typeIdentity) {
//            case 0 :
//                return Regex.checkRegex(this.uid, "^\\d{9}$");
//            case 1 :
//                return Regex.checkRegex(this.uid, "^\\d{12}$");
//            case 2:
//                return Regex.checkRegex(this.uid, "^[A-Z]\\d{7}$");
//            default:
//                return false;
//        }
//    }
//}
