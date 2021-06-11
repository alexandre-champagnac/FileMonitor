package com.cjp.filemonitor.inputs;

import java.io.Closeable;
import java.io.FileNotFoundException;

public interface GlobalMonitor {

     public void initialize() throws Exception;
     MonitoringReport analyse(long deadline) throws Exception;
     public void close() throws Exception;







}
