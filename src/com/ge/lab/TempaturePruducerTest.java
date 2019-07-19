package com.ge.lab;
  

public class TempaturePruducerTest {
	public static void main(String[] args) {
		
		TemparatureSensorData sensordata=new TemparatureSensorData(); 
		 
		Thread tempProducerThread=new Thread(new TempProducer(sensordata));
		tempProducerThread.start(); 
		 
		Thread tempProcessorThread=new Thread(new TemperatureProcessor(sensordata));
		tempProcessorThread.start(); 
		 
	} 
}
