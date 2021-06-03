package com.vng.ewallet.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean checkRegex(String str, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

}
