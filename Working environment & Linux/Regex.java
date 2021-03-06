package com.vng.demo;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static String convertCamelNameToNormalName(String camelName) {
        // "VoLamTruyenKy" -> "Vo Lam Truyen Ky "
        String res = camelName.replaceAll("[A-Z][a-z]*", "$0 ");

        // Delete last white space in string -> "Vo Lam Truyen Ky"
        return res.stripTrailing();
    }

    public static void main(String[] args) {
        String camelName = "VoLamTruyenKy";
        String normalname = convertCamelNameToNormalName(camelName);
        System.out.println(normalname);
    }
}
