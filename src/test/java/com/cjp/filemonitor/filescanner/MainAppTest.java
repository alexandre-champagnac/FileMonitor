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
