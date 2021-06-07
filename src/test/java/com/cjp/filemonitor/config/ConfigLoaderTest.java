package com.cjp.filemonitor.config;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    private static final String validIni = """
            [global]
            intervalInMinute = 12
            directory = C:\\bonjour
                    
            [email]
            to = Alexandre.CHAMPAGNAC@clermont.unicancer.fr
            """;

    @Test
    public void shouldReadConfigFromIni() throws IOException {
        Path path = Files.createTempFile(null, ".ini", new FileAttribute[]{});
        Files.writeString(path, validIni, StandardOpenOption.WRITE);


        AppConfig given = ConfigLoader.loadConfigFromIniFile(path);

        if (given.getMonitoringLifespan() != 12) {
            fail("wrong monitoring interval");
        }
        if (!given.getMonitoringDirectory().equals("C:\bonjour")) {
            fail("wrong monitoring directory");
        }
        if (!given.getEmailTo().equals("Alexandre.CHAMPAGNAC@clermont.unicancer.fr")) {
            fail("wrong email recipient");
        }
    }
}