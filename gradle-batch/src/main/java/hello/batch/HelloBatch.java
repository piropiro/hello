/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello.batch;

import hello.common.HelloMessager;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rara
 */
public class HelloBatch {
    private HelloMessager helloMessager;
    
    public HelloBatch() {
        helloMessager = new HelloMessager();
    }
    public void sayHello() {
        System.out.println(StringUtils.join(helloMessager.getMessage("taro"), "!"));
    }
}
