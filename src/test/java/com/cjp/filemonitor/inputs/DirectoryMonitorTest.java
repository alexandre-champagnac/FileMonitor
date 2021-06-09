package com.cjp.filemonitor.inputs;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class DirectoryMonitorTest {

    @Test
    public void shouldReturnsEmptyListIfDirectoryIsEmpty() throws Exception {
        Path dir = Files.createTempDirectory(null, new FileAttribute[]{});
        DirectoryMonitor monitor = new DirectoryMonitor(dir);

        MonitoringReport report = monitor.analyse(0L);

        if(!(report instanceof DirectoryReport)){
            fail("the instance is not the instance excepted");
        }

        DirectoryReport report1 = (DirectoryReport) report;

        if (report1.getCptOldFiles() != 0) {
            fail("returned list should be empty when folder is empty");
        }
    }

    @Test
    public void shouldReturnsEmptyListIfDirectoryContainsRecentFiles() throws Exception {
        Path dir = Files.createTempDirectory(null, new FileAttribute[]{});
        Files.createFile(dir.resolve("file.txt"));

        DirectoryMonitor monitor = new DirectoryMonitor(dir);

        MonitoringReport report = monitor.analyse(0L);

        if(!(report instanceof DirectoryReport)){
            fail("the instance is not the instance excepted");
        }

        DirectoryReport report1 = (DirectoryReport) report;

        if (report1.getCptOldFiles() != 0) {
            fail("returned list should be empty when folder is empty");
        }
    }

    @Test
    public void shouldReturnsFileOlderThanTimestamp() throws Exception {
        Path dir = Files.createTempDirectory(null, new FileAttribute[]{});
        Files.createFile(dir.resolve("file.txt"));
        Files.createFile(dir.resolve("file2.txt"));
        Files.createFile(dir.resolve("file3.txt"));

        DirectoryMonitor monitor = new DirectoryMonitor(dir);

        MonitoringReport report = monitor.analyse(LocalDateTime.now().plusDays(30).toEpochSecond(ZoneOffset.UTC) * 1000);

        if(!(report instanceof DirectoryReport)){
            fail("the instance is not the instance excepted");
        }

        DirectoryReport report1 = (DirectoryReport) report;


        if (report1.getCptOldFiles() != 3) {
            fail("expected to have 3 files, got only " + report1.getCptOldFiles());
        }
    }

    @Test
    public void shouldBeLower(){
        long var1 = 102303030;
        long var2 = 255455455;
        DirectoryMonitor test = new DirectoryMonitor(null);

        boolean testvar = test.isTimestampLowerThan(var1,var2);


        if(testvar == false){
            fail();
        }
    }

    @Test
    public void shouldNotBeLower(){
        long var1 = 255455455;
        long var2 = 102303030;
        DirectoryMonitor test = new DirectoryMonitor(null);

        boolean testvar = test.isTimestampLowerThan(var1,var2);




        if(testvar == true){
            fail();
        }
    }

    @Test
    public void shouldNotBeLowerIfEqual(){
        long var1 = 255455455;
        long var2 = 255455455;

        DirectoryMonitor test = new DirectoryMonitor(null);

        boolean testvar = test.isTimestampLowerThan(var1,var2);

        if(testvar){
            fail("Equals values should not be considered lower");
        }
    }

}
