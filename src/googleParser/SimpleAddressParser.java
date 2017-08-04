package googleParser;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SimpleAddressParser 
{
    public static void main(String[] args) throws Exception 
    {
    	JFrame frame = new JFrame("Boolean text input.");
	    String stmt = JOptionPane.showInputDialog(frame, "Please enter your desired address.");
	    
	    StringBuilder builder = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/xml");
	    builder.append("?address=" + stmt.replaceAll(" ", "+") + "&sensor=false");
	    
	    System.out.println(builder);
	    
        URL result = new URL(builder.toString());
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(result.openStream()));

        String readLine;
        
        int x = 0;
        String fa = new String();
        String lat = new String();
        String lng = new String();
        while ((readLine = reader.readLine()) != null && x < 3)
        {
        	System.out.println(readLine);
        	if(readLine.contains("<formatted_address>"))
        	{
        		fa = readLine.substring(readLine.indexOf(">")+1, readLine.lastIndexOf("<"));
        		x++;
        	}
        	
        	if(readLine.contains("<lat>"))
        	{
        		lat = readLine.substring(readLine.indexOf(">")+1, readLine.lastIndexOf("<"));
        		x++;
        	}
        	
        	if(readLine.contains("<lng>"))
        	{
        		lng = readLine.substring(readLine.indexOf(">")+1, readLine.lastIndexOf("<"));
        		x++;
        	}
        }
        
//        System.out.println(fa + ", " + lat + ", " + lng);
        
        reader.close();
        
        if(fa.length() > 0 && lat.length() > 0 && lng.length() > 0)
        	JOptionPane.showMessageDialog(frame, "Formatted address: " + fa + "\nLatitude: " + lat + "\nLongitude: " + lng);
        else
        	JOptionPane.showMessageDialog(frame, "I'm sorry, this address could not be found.");
        
    	System.exit(0);
    }
    
//    public String getAddress(String xml)
}
