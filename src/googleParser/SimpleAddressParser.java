package googleParser;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** @author Alex
 * 
 * This may not be the most complex or stunning solution,
 * but given the time-frame and requirements,
 * I believe this is the most efficient solution I could come up with.
 *  
 */

public class SimpleAddressParser 
{
    public static void main(String[] args) throws Exception 
    {
    	JFrame frame = new JFrame("Address parser");
	    String stmt = JOptionPane.showInputDialog(frame, "Please enter your desired address.");
	    
	    if(stmt.trim().length() == 0)
	    {
	    	JOptionPane.showMessageDialog(frame, "You must input a location/address.");
	    	System.exit(0);
	    }
	    
	    StringBuilder builder = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/xml");
	    builder.append("?address=" + stmt.replaceAll(" ", "+") + "&sensor=false");
	    
        URL result = new URL(builder.toString());
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(result.openStream()));

        String readLine;
        
        int x = 0;
        String fa = new String();
        String lat = new String();
        String lng = new String();
        //I have not worked so much with XML, please forgive my makeshift parser
        //The XML parsing libraries I know of I don't believe quite fit the "core Java only" so I went with this
        while ((readLine = reader.readLine()) != null && x < 3)
        {
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
        
        reader.close();
        
        if(fa.length() > 0 && lat.length() > 0 && lng.length() > 0)
        	JOptionPane.showMessageDialog(frame, "Formatted address: " + fa + "\nLatitude: " + lat + "\nLongitude: " + lng);
        else
        	JOptionPane.showMessageDialog(frame, "I'm sorry, this address could not be found.");
        
    	System.exit(0);
    }
}
