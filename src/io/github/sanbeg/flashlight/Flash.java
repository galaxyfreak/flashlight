package io.github.sanbeg.flashlight;

import java.io.DataOutputStream;
import java.io.IOException;

public class Flash {
	// basic enable / disable flashlight via sysfs
	
    String sysfs = "/sys/devices/01-qcom,leds-d300/leds/led:flash_torch/brightness";
    
    int min = 0;
    int max = 200;
    
    public void setFlash(boolean isOn) {
    	java.lang.Process p;   
    
    	try {   
    		p = Runtime.getRuntime().exec("su");   
    		DataOutputStream os = new DataOutputStream(p.getOutputStream());  
    		if (isOn == true)
    		{
    			os.writeBytes("echo " + max + " > " + sysfs + "\n");
    		} else {
    			os.writeBytes("echo " + min + " > " + sysfs + "\n");   
    		}
       
    		os.writeBytes("exit\n");   
    		os.flush();   
    		
    		try {   
    			p.waitFor();     
    			} catch (InterruptedException e) {    
    				
    			}   
    		} catch (IOException e) {   
    			
    		}
    	}

    public void on() {
    	setFlash(true);
    }

    public void off() {
    	setFlash(false);
    }

}
