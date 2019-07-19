package com.ge.lab;

import java.util.Random;

public class TempProducer  implements Runnable{
	TemparatureSensorData sensordata;
	TempProducer(TemparatureSensorData sensordata){		
		this.sensordata=sensordata;
	}
 
	public void run() {
		//Need to continuously generate 
		while(true){ 
			//for every one second
			try
			{
			    Thread.sleep(3000);  
			    Random rand = new Random();  
		        float temp = rand.nextInt(100); 
		        sensordata.tempdata.add(temp); 
		        System.out.println(sensordata); 
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			} 
		}
	}  
	 
}
