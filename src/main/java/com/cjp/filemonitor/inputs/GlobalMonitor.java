package com.cjp.filemonitor.inputs;

public abstract class GlobalMonitor {

     public abstract  void initialize() throws Exception;
     public abstract  MonitoringReport analyse(long deadline) throws Exception;
     public abstract  void close() throws Exception;

     protected boolean isTimestampLowerThan(long var1, long var2){
          boolean isLower = var1 <= var2 && var1 != var2;

          return isLower;
     }







}
