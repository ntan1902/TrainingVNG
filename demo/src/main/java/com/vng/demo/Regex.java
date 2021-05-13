package com.vng.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean checkRegex(String str, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static void main(String[] args) {
        System.out.println(checkRegex("9704366614626076016", "(^970436)(\\d{13}$)"));

        System.out.println(checkRegex("950436661678", "(^\\d{9})(678$)"));
    }
}
