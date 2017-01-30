package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MaxPrinciple implements HttpSessionAttributeListener {

	 private double max =-1;
   
	public MaxPrinciple() 
    {
    }

	
    public void attributeRemoved(HttpSessionBindingEvent arg0)  
    { 
    }

    public void attributeAdded(HttpSessionBindingEvent arg0)  
    { 
    	System.out.println(arg0.getName());
    	if(arg0.getName().equals("principle"))
    	{
    		String temp = (String) arg0.getSession().getAttribute("principle");
    		double maxTemp = Double.parseDouble(temp);
    		
    		if(maxTemp > max)
    		{
    			max = maxTemp;
    		}
    		
    	}
    	System.out.println("ADDED: "+ max);
    	    		arg0.getSession().getServletContext().setAttribute("maxPrinciple", max);
    	
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  
    {
    	if (arg0.getSession().getAttribute("principle") != null)
    	{
    		String temp = (String) arg0.getSession().getAttribute("principle");
    		double maxTemp = Double.parseDouble(temp);
    		
    		if (maxTemp > max)
    		{
    			max = maxTemp;
    		}
			System.out.println("REPLACE: " + max);

    	}
    	    			arg0.getSession().getServletContext().setAttribute("maxPrinciple", max);
    	
    }
    
    
	
}