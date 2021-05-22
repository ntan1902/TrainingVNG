//package com.vng.ewallet.payment;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PaymentTest {
//    @Test
//    void bankNameShouldNotBeEmpty() {
//        // Setup
//        Payment payment = new Payment(
//                "",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_BANK_NAME, valid, "Bank name payment is valid");
//    }
//
//    @Test
//    void cardHolderNameShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "Card holder name is valid");
//    }
//
//    @Test
//    void cardHolderNameShouldBeCapitalLetters() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH aN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_CARD_HOLDER_NAME, valid, "Card holder name is not valid (contain non-capital letter)");
//    }
//
//    @Test
//    void typeIdentityShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "Type identity is valid");
//    }
//
//    @Test
//    void typeIdentityShouldBeAround012() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                3,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_TYPE_IDENTITY, valid, "Type identity is  not valid");
//    }
//
//
//    @Test
//    void uidCMNDShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "CMND is valid");
//    }
//
//    @Test
//    void uidCMNDShouldNotBeLargerThan9Numbers() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189888"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_UID, valid, "CMND is not valid (> 9 numbers)");
//    }
//
//    @Test
//    void uidCCCDShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                1,
//                "123456789999"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "CCCD is valid");
//    }
//
//    @Test
//    void uidCCCDShouldNotBeLargerThan12Numbers() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                1,
//                "1234567899998"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_UID, valid, "CCCD is not valid (> 12 numbers)");
//    }
//
//    @Test
//    void uidPassportShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                2,
//                "B2790875"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "Passport is valid");
//    }
//
//    @Test
//    void uidPassportShouldHaveCapitalLetterInFirstCharacter() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                2,
//                "b2790875"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_UID, valid, "Passport is not valid (First character is not capital");
//    }
//
//    // VCB
//    @Test
//    void VCBShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "VCB is valid");
//    }
//
//    @Test
//    void VCBCardNumberShouldNotBeLargerThan19Numbers() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "111119704366614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_CARD_NUMBER, valid, "VCB is not valid number (> 19 numbers)");
//    }
//
//    @Test
//    void VCBCardNumberShouldNotStartAt970437() {
//        // Setup
//        Payment payment = new Payment(
//                "VCB",
//                "9704376614626076016",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_CARD_NUMBER, valid, "VCB is not valid number start at 970437");
//    }
//
//
//    // SCB
//    @Test
//    void SCBShouldBeValid() {
//        // Setup
//        Payment payment = new Payment(
//                "SCB",
//                "950436661678",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.SUCCESS, valid, "SCB is valid");
//    }
//
//    @Test
//    void SCBCardNumberShouldNotBeLargerThan12Numbers() {
//        // Setup
//        Payment payment = new Payment(
//                "SCB",
//                "11111950436661678",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_CARD_NUMBER, valid, "SCB is not valid number (> 12 numbers)");
//    }
//
//    @Test
//    void SCBCardNumberShouldNotEndAt678() {
//        // Setup
//        Payment payment = new Payment(
//                "SCB",
//                "950436661679",
//                "NGUYEN TRINH AN",
//                0,
//                "026031189"
//        );
//        // Exercise
//        ValidPayment valid = payment.isValid();
//
//        // Verify
//        assertEquals(ValidPayment.INVALID_CARD_NUMBER, valid, "SCB is not valid number (not end at 678)");
//    }
//}