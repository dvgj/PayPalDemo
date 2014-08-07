package com.paypal.eco.demo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ExpressCheckoutServlet
 */
@WebServlet("/ExpressCheckoutServlet")
public class ExpressCheckoutServlet extends HttpServlet {
	
	public static ThreadLocal td = new ThreadLocal();
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpressCheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
		
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		td.set(request);
		
		String action = request.getParameter("actionName");//custom parameter to handle requests
		String forward = "/jsp/home.jsp"; //Default home 
		
		PayPalFunctions ppf = new PayPalFunctions();
		
		if ("setexpresscheckout.do".equals(action)) {
			
			Map nvp = setExpressCheckout(request, response);
			forward = "/jsp/showRedirectURL.jsp";
			
		} else if ("doRedirect.do".equals(action)) {
			
			doRedirect(request, response);
			forward = null; //got redirected, nothing to do there.
			
		} else if ("getExpressCheckoutDetails.do".equals(action)) {
			
			
			String token = (String) request.getSession(true).getAttribute("token");
			String payerId = request.getParameter("PayerID");
			request.getSession(true).setAttribute("PayerID", payerId);
			
			Map nvp = ppf.GetExpressCheckoutDetails(token);						
			
			forward = "/jsp/getExpressCheckoutDetails.jsp";
		} else if ("doExpressCheckout.do".equals(action)) {
			
			
			String token = (String) request.getSession().getAttribute("token");
			String payerID = (String) request.getSession().getAttribute("PayerID");
			String serverName = request.getServerName();
			ppf.DoExpressCheckoutPayment(token, payerID, "1.00", serverName);
			forward = "/jsp/doExpressCheckoutResponse.jsp";
		}
		if (forward != null) {
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		}
		
	}

/* some static data for demo app */
	String currencyCodeType = "AUD";
	String paymentType = "Sale";

	
	String shipToName 			= "James David";
	String shipToStreet 		= "Dunmore St";
	String shipToStreet2 		= ""; //'Leave it blank if there is no value
	String shipToCity 			= "Sydney";
	String shipToState 			= "NSW";
	String shipToCountryCode 	= "AU"; //' Please refer to the PayPal country codes in the API documentation
	String shipToZip 			= "2145";
	String phoneNum 			= "0414624839";


	private Map setExpressCheckout(HttpServletRequest request,
			HttpServletResponse response) {
		
		//PayPalMethods.setExpressCheckout(request, response);
		PayPalFunctions ppf = new PayPalFunctions();
		StringBuffer url = new StringBuffer();
		url.append("http://");
		url.append(request.getServerName());
		url.append(":");
		url.append(request.getServerPort());
		url.append(request.getContextPath());

		String returnURL = url.toString() + "/eco.do?actionName=getExpressCheckoutDetails.do";
		String cancelURL = url.toString() + "/eco.do?actionName=setexpresscheckout.do";
		
		Map nvp = ppf.SetExpressCheckout("1.00", returnURL, cancelURL, shipToName, shipToStreet, shipToCity, shipToState, shipToCountryCode, shipToZip, shipToStreet2, phoneNum);
		
		request.setAttribute("paypalResponse", nvp.toString());
		
		String strAck = nvp.get("ACK").toString();
		
		request.getSession(true).setAttribute("token", nvp.get("TOKEN"));
			
		

		return nvp;
	}
	

	private void doRedirect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//String redirectUrlOnSuccess = request.getParameter("redirectURL");
		
		//response.sendRedirect(redirectUrlOnSuccess);
		//PayPalMethods.getExpressCheckoutDetails(request, response);
		
		PayPalFunctions ppf = new PayPalFunctions();
		ppf.RedirectURL(response, (String)request.getSession().getAttribute("token"));
		
		//return "/jsp/getExpressCheckoutDetails.jsp";
	}



	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
