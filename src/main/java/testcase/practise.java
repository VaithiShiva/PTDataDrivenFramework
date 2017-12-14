
package testcase;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import page.CommonObjects;
import page.homepage;

public class practise {
	
	@Test
	public void testing()
	{
	CommonObjects f = new CommonObjects();
	
	List<Object> ticketDetails1 = new ArrayList<Object>();
	ticketDetails1.add(f);
	ticketDetails1.add(new homepage());
	
	
	Object a = ticketDetails1.get(0);
	
	Object[] n = a.getClass().getDeclaredMethods();
	System.out.println(n[0].toString());
	CommonObjects ab1 = (CommonObjects)a;
	
	
	Object bb = ticketDetails1.get(1);
	}
}
