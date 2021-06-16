package com.cjp.filemonitor.inputs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class GlobalMonitorTest {
    @Test
    public void shouldBeLower(){
        long var1 = 102303030;
        long var2 = 255455455;
        GlobalMonitor test = new TestMonitor();

        boolean testvar = test.isTimestampLowerThan(var1,var2);


        if(!testvar){
            fail();
        }
    }

    @Test
    public void shouldNotBeLower(){
        long var1 = 255455455;
        long var2 = 102303030;
        GlobalMonitor test = new TestMonitor();

        boolean testvar = test.isTimestampLowerThan(var1,var2);




        if(testvar){
            fail();
        }
    }

    @Test
    public void shouldNotBeLowerIfEqual(){
        long var1 = 255455455;
        long var2 = 255455455;

        GlobalMonitor test = new TestMonitor();

        boolean testvar = test.isTimestampLowerThan(var1,var2);

        if(testvar){
            fail("Equals values should not be considered lower");
        }
    }
    private static class TestMonitor extends GlobalMonitor{

        @Override
        public void initialize() throws Exception {

        }

        @Override
        public MonitoringReport analyse(long deadline) throws Exception {
            return null;
        }

        @Override
        public void close() throws Exception {

        }
    }
}