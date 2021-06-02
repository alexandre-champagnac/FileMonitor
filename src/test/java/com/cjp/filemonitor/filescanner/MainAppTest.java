package com.cjp.filemonitor.filescanner;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

import static org.junit.jupiter.api.Assertions.fail;

public class MainAppTest {

    @Test
    public void shouldBeLower(){
        long var1 = 102303030;
        long var2 = 255455455;

        boolean testvar = MainApp.isTimestampLowerThan(var1,var2);


        if(testvar == false){
            fail();
        }
    }

    @Test
    public void shouldnotBeLower(){
        long var1 = 255455455;
        long var2 = 102303030;

        boolean testvar = MainApp.isTimestampLowerThan(var1,var2);


        if(testvar == true){
            fail();
        }
    }


    @Test
    public void shouldGetConfigFromIni() throws IOException {

       Path path = Files.createTempFile(null, ".ini",new FileAttribute[]{});
       Files.writeString(path, "[global]\nintervalInMinute = 12", StandardOpenOption.WRITE);

       long test = MainApp.getIntervalFromConfig(path.toString());

       if(test != 12){
           fail("This result may be 12");
       }


    }

    @Test
    public void shouldGetStringFromIni() throws IOException {

        Path path = Files.createTempFile(null, ".ini",new FileAttribute[]{});
        Files.writeString(path, "[email]\nto = email@email.com", StandardOpenOption.WRITE);

        String test = MainApp.getStringFromConfig(path.toString(),"email","to");

        if(!test.equals("email@email.com")){
            fail("This result may be email@email.com");
        }




    }


}
