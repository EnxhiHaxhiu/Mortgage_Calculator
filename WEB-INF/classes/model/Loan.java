package model;

public class Loan 
{
	public Loan(){}
	
	public double computePayment(String p, String a, String i , String g , String gp , String fi) throws Exception
	{
		double principle= Double.parseDouble(p);		
		double gracePeriod = Double.parseDouble(gp);
		double period = Double.parseDouble(a);
		double interest = Double.parseDouble(i);
		
		double fixedInterest = Double.parseDouble(fi);
		double totalInterest = interest + fixedInterest;
		totalInterest /= 100;
		double graceInterest = 0.0;
		
		if(g!= null && g.equals("on"))
		{
			graceInterest = this.computeGraceInterest(p, gp, i, fi);
		}
		
        double pow = Math.pow((1+ (totalInterest/12)), - period);
        double monthlypayment = ((principle + graceInterest) * (totalInterest/12) ) / (1-pow);		
        return monthlypayment;
        
	}
	public double computeGraceInterest(String p, String gp, String i , String fi) throws Exception 
	{	
		double principle=Double.parseDouble(p);
		double gracePeriod = Double.parseDouble(gp);
		double interest=Double.parseDouble(i);
		double fixedInterest = Double.parseDouble(fi);
	
		double totalInterest = interest + fixedInterest;
		totalInterest /= 100;
		double graceInterest = principle * (totalInterest/12) * gracePeriod;
		return graceInterest;
	}
}