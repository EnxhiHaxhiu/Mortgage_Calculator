package ctrl;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Loan;
/**
 * Servlet implementation class Start
 */
@WebServlet(
		urlPatterns = {"/Start", "/Startup/*"}
)
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	String target;
	Loan loan;
	String error;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException
	{
			loan = new Loan();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		
		HttpSession session = request.getSession(true);
	
		String interest = request.getParameter("interest");
		String principle = request.getParameter("principle");
		String period = request.getParameter("period");
		String grace = request.getParameter("grace");
		String fixedInterest = getServletContext().getInitParameter("fixedInterest");
		String submit = request.getParameter("submit");
			
		if(submit != null && submit.equals("submit"))
		{
			if(interest.length() == 0 || period.length() == 0 || principle.length() == 0)
			{
				if(interest.length() == 0)
				{
					target = "/UI.jspx";
					error = "Please enter the interest!";
				}
				else if(period.length() == 0)
				{
					target ="/UI.jspx";
					error = "Please enter the period";
				}
				else if(principle.length() == 0)
				{
					target = "/UI.jspx";
					error = "Please enter the principle";
				}
				request.setAttribute("principle", principle);
				request.setAttribute("period", period);
				request.setAttribute("interest", interest);
				request.setAttribute("error", error);
		}
		else if (interest.length() != 0 || period.length() != 0 || principle.length() != 0)
		{
			double interestDouble = 0;
			double periodDouble= 0;
			double principleDouble= 0;
			try
			{
				interestDouble = Double.parseDouble(interest);
				periodDouble = Double.parseDouble(period);
				principleDouble = Double.parseDouble(principle);
			
				if(interestDouble < 0 || principleDouble <0 || periodDouble <0)
				{
					if(interestDouble < 0)
					{
						target = "/UI.jspx";
						error = "Please enter a positive value for interest";
					}
					
					else if(principleDouble < 0)
					{
						target = "/UI.jspx";
						error = "Please enter a positive value for principle";
					}
					else if(periodDouble < 0)
					{
						target = "/UI.jspx";
						error = "Please enter a positive value for period";
					}
				}
				else
				{
					double graceInterest = 0;
					
					String gracePeriod = getServletContext().getInitParameter("gracePeriod");
					
					if(grace != null && grace.equals("on"))
					{
						try
						{
							graceInterest = loan.computeGraceInterest(principle,gracePeriod,interest,fixedInterest);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(grace != null)
					{
						grace = "off";
					}
					try 
					{
						request.setAttribute("monthlyPayment", loan.computePayment(principle, period, interest, grace, gracePeriod, fixedInterest));
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					try
					{
						request.setAttribute("graceInterest", graceInterest);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					session.setAttribute("principle", principle);
					target = "/Result.jspx";
				}
			}
			
			catch(NumberFormatException nfe)
			{
				try {
					Double.parseDouble(period);
				} catch (NumberFormatException nf) {
					error = "Please enter numbers for the period!";
				}
				
				try
				{
					Double.parseDouble(interest);
				}
				catch(NumberFormatException nf)
				{
					error = "Please enter numbers for the interest!";
				}
				
				try {
					Double.parseDouble(principle);
				} catch (NumberFormatException nf) {
					error = "Please enter numbers for the principle!";
				}
				
				request.setAttribute("error", error);
				
			}
			
			request.setAttribute("error", error);
			if(error != null)
			{
				request.setAttribute("principle", principle);
				request.setAttribute("interest", interest);
				request.setAttribute("period", period);
				request.setAttribute("grace", grace);
			}
		}
	}
		
		else
		{
			target ="/UI.jspx";
		}
		
	request.getRequestDispatcher(target).forward(request, response);
}
	
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			doGet(request, response);
	}

}