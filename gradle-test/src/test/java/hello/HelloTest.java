/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author rara
 */
@RunWith(PowerMockRunner.class)//PowerMockの利用を宣言
@PrepareForTest({HelloMessager.class})
public class HelloTest {

    @Spy
    HelloMessager helloMessager = new HelloMessager();

    @InjectMocks
    Hello target = new Hello();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, of class Hello.
     */
    @Test
    public void sayHello_001() {
        assertThat(target.sayHello(), is("hello"));
    }

    @Test
    public void sayHello_002() {
        target = Mockito.mock(Hello.class);
        Mockito.when(target.sayHello()).thenReturn("taro");

        assertThat(target.sayHello(), is("taro"));
    }

    @Test
    public void sayNow_001() throws Exception {       
        Date date1 = new SimpleDateFormat("yyyyMMdd").parse("20140101");
        Date date2 = new SimpleDateFormat("yyyyMMdd").parse("20140102");
        Date date3 = new SimpleDateFormat("yyyyMMdd").parse("20140103");

        PowerMockito.whenNew(Date.class).withNoArguments()
                .thenReturn(date1)
                .thenReturn(date2)
                .thenReturn(date3);
        
        assertThat(target.sayNow(), is("20140101"));
        assertThat(target.sayNow(), is("20140102"));
        assertThat(target.sayNow(), is("20140103"));    
    }
    
    /**
     * whenNew -> withAnyArguments -> thenThrow は使えない。
     * @throws Exception 
     */
    @Test(expected = FileNotFoundException.class)
    public void writeHello_001() throws Exception {
        PowerMockito.whenNew(FileOutputStream.class)
                .withParameterTypes(String.class).withArguments(Mockito.anyObject())  // OK
                // .withArguments("hello.txt") // OK
                // .withArguments(Mockito.anyString()) // OK
                // .withAnyArguments() // NG
                .thenThrow(new FileNotFoundException("dummy"));

        target.writeHello();
    }
    
    /**
     * whenNew -> withAnyArguments -> thenReturn は使える。
     * @throws Exception 
     */
    @Test(expected = FileNotFoundException.class)
    public void writeHello_002() throws Exception {
        FileOutputStream fos = PowerMockito.mock(FileOutputStream.class);
        PowerMockito.doThrow(new FileNotFoundException("dummy")).when(fos).write((byte[])Mockito.anyObject());
        PowerMockito.whenNew(FileOutputStream.class)
                .withParameterTypes(String.class).withArguments(Mockito.anyObject()) // OK
                // .withArguments("hello.txt") // OK
                // .withArguments(Mockito.anyString()) // OK
                // .withAnyArguments() // OK
                .thenReturn(fos);

        target.writeHello();
    }
    
    @Test
    public void sayStatic_001() {
        PowerMockito.mockStatic(HelloMessager.class);
        PowerMockito.when(HelloMessager.getStaticMessage()).thenReturn("good");
        
        assertThat(target.sayStatic(), is("good"));
    }
    
    @Test
    public void sayPrivate_001() throws Exception {
        
        PowerMockito.when(helloMessager, "getPrivateMessage").thenReturn("good");
        
        assertThat(target.sayPrivate(), is("good"));
    }
    
}
