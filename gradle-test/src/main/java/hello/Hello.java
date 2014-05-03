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
        return helloMessager.getMessage();
    }
    
    public String sayNow() {
        return helloMessager.getNow();
    }
    
    public void writeHello() throws IOException {
        helloMessager.writeMessage();
    }
    
    public String sayStatic() {
        return HelloMessager.getStaticMessage();
    }
    
    public String sayPrivate() {
        return helloMessager.getPrivateMessage();
    }
}
