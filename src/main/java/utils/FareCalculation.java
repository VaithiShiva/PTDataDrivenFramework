package utils;

import java.util.ArrayList;
import java.util.List;

public class FareCalculation {

	public static float primetripDiscount;
	public static float GSTPercentage;
	public static float GST;
	public static float convenienceFee;
	public static float baseFare;
	public static float totalFare;

	public List<Float> fareCalculation(float noOfSeat, float baseFare, float primetripDiscount, float convenienceFee, float GST, float paidfromWallet, float amounttobepaid)
	{
		List<Float> fareList = new ArrayList<Float>();
		try
		{
			System.out.println("baseFare: "+baseFare+"primetripDiscount: "+primetripDiscount+"convenienceFee: "+convenienceFee+ "GST: "+GST+"totalFare: "+paidfromWallet);
			
			this.primetripDiscount = Math.round(baseFare*primetripDiscount/100)*noOfSeat;
			
			this.GSTPercentage = Math.round((GST/noOfSeat)/baseFare*100);
			
			this.GST = Math.round((baseFare * GSTPercentage)/100)*noOfSeat;
			
			this.convenienceFee = baseFare*convenienceFee*100;
			
			this.baseFare = baseFare*noOfSeat;

			this.totalFare = this.baseFare - this.primetripDiscount + this.GST + this.convenienceFee;

			System.out.println("baseFare: "+baseFare+"primetripDiscount: "+primetripDiscount+"convenienceFee: "+convenienceFee+"GSTPercentage: "+GSTPercentage+"GST: "+GST+"paidfromWallet: "+paidfromWallet);

			fareList.add(this.primetripDiscount);
			fareList.add(GSTPercentage);
			fareList.add(this.GST);
			fareList.add(this.convenienceFee);
			fareList.add(this.baseFare);
			fareList.add(this.totalFare);
			//fareList.add(paidfromWallet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return fareList;
	}
	public float getPrimetripDiscount()
	{
		return this.primetripDiscount;
	}
	
	public float getGSTPercentage()
	{
		return this.GSTPercentage;
	}

	public float getGST()
	{
		return this.GST;
	}
	
	public float getConvenienceFee()
	{
		return this.convenienceFee;
	}
	
	public float getBaseFare()
	{
		return this.baseFare;
	}
	
	public float gettotalFare()
	{
		return this.totalFare;
	}


}
