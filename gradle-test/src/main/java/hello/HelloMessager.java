/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rara
 */
public class HelloMessager {
    
    public String getMessage() {
        return "hello";
    }
    
    public String getNow() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
    
    public void writeMessage() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("hello.txt");) {
            fos.write("hello".getBytes());
        }
    }
    
    public static String getStaticMessage() {
        return "static";
    }
    
    public String getPrivateMessage() {
        return getPrivateMessageInternal();
    }
    
    private String getPrivateMessageInternal() {
        return "private";
    }
}
