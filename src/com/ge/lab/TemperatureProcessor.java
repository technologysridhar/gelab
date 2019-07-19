package com.ge.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class TemperatureProcessor   implements  Runnable { 	 
		TemparatureSensorData sensordata;
		List<String> processedresult=new ArrayList<String>();
	 
		TemperatureProcessor(TemparatureSensorData sensordata){		
			this.sensordata=sensordata;
		} 
		
		public String process(int index){
				if(this.sensordata.tempdata.get(index) <0)
					return "Cold";
				else if(this.sensordata.tempdata.get(index) <0)
					return "Warm"; 
				return "";
		}
		
		public float computeAverageTemparature(int startindex,int endindex){
			 float sum=0.0F;
			 
			 for(int i=startindex;i<endindex;i++){
				 sum+=i;
			 }
			 
			 return sum/(endindex-startindex);
		}

		int startpoint=0;
		int endpoint=0;
		public void run() {
			for(;;){
				startpoint=endpoint;
				
				cleanupPreviousResults();
			    try {
					Thread.sleep(300);	//3,600,000
					int endpoint=sensordata.tempdata.size();
					 
					for(int idxpoints=startpoint;idxpoints<endpoint;idxpoints++){
						String result=process(idxpoints);
						processedresult.add(result);
					}
					
					float avg=computeAverageTemparature(startpoint,endpoint);
					StoreIntoDB(avg);
				} catch (InterruptedException e) {			 
					e.printStackTrace();
				} catch (Exception e) { 
					e.printStackTrace();
				}  
			} 
		}
		
		public  void StoreIntoDB(float avg)throws Exception{
			//Processed Result
			//Input Data
			
			//processedresult
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			PreparedStatement psInsert=null;
			 
					 psInsert=con.prepareStatement("insert into SampleTable2 values(?)");  
		            //date hr average
					 
					 //Date date = new Date();   // given date
					 
					 
					psInsert.setString(1,"avg");   
			      
		 
			  
					psInsert.executeUpdate();   
			con.close();  
			
		}
		
		
		
		
		public  void StoreIntoDB()throws Exception{
			//Processed Result
			//Input Data
			
			//processedresult
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			PreparedStatement psInsert=null;
			for(int i=0;i<processedresult.size();i++){
					 psInsert=con.prepareStatement("insert into SampleTable values(?)");  
		 
					psInsert.setString(1,"Ratan");   
			        psInsert.addBatch();
	            
			} 
			  
			psInsert.executeUpdate();   
			con.close();  
			
		}
		
		public void cleanupPreviousResults(){
			processedresult.clear();
		}
}