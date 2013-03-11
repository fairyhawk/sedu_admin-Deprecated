package com.shangde.common.util;

import java.io.UnsupportedEncodingException;   
import java.net.URLEncoder;   

public class URLUtil {   
    public static String encodeURL(String url,String encode) throws UnsupportedEncodingException {   
        StringBuilder sb = new StringBuilder();   
        StringBuilder noAsciiPart = new StringBuilder();   
        for (int i = 0; i < url.length(); i++) {   
            char c = url.charAt(i);   
            if (c > 255) {   
                noAsciiPart.append(c);   
            } else {   
                if (noAsciiPart.length() != 0) {   
                    sb.append(URLEncoder.encode(noAsciiPart.toString(),encode));   
                    noAsciiPart.delete(0, noAsciiPart.length());   
                }   
                sb.append(c);   
            }   
        }   
        return sb.toString();   
    }   
}  