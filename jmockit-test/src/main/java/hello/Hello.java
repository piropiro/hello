/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import java.io.IOException;

/**
 *
 * @author rara
 */
public class Hello {
    
    private HelloMessager helloMessager = null;
    
    public Hello() {
        helloMessager = new HelloMessager();
    }
    public String sayHello() {
        return helloMessager.getMessage("taro");
    }
    
    public String sayNow() {
        return helloMessager.getNow();
    }
    
    public void writeHello() throws IOException {
        helloMessager.writeMessage();
    }
    
    public String sayStatic() {
        return HelloMessager.getStaticMessage("taro");
    }
    
    public String sayPrivate() {
        return helloMessager.getPublicMessage("taro");
    }
    
    public String ifHello(boolean flag) {
        String result = "";
        
        if (flag) {
            result = "hello";
        }
        
        return result;
    }
    
    public String switchHello(int flag) {
        String result = "";
        switch (flag) {
            case 1:
                result = "hello";
        }
        
        return result;
    }
}
