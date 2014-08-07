<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Express Checkout Details</title>
</head>
<body>
<form name="TestShop" action="doExpressCheckout.do" method="post">
<input type="hidden" name="actionName" value="doExpressCheckout.do"/>	
	<fieldset>
		<table>
			
			<tr>
				<td>
					Do express checkout
				</td>
				<td>
					<img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" align="left" style="margin-right:7px;" onclick="javascript:document.TestShop.submit()">
				</td>
				
			</tr>
			<tr>
				<td>
					Items Total : $1 
					Shipping    : $0
					Sub Total	: $1
				</td>
				<td>
				<img src="https://www.paypal.com/en_US/i/logo/PayPal_mark_37x23.gif" align="left" style="margin-right:7px;"><span style="font-size:11px; font-family: Arial, Verdana;">The safer, easier way to pay.</span>
				</td>
			</tr>						
		</table>
	</fieldset>
</form>
</body>
</html>
