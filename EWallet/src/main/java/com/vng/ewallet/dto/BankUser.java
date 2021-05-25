package com.vng.ewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankUser {
    private String userName;
    private String bankName;
    private String bankCode;
}
