<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Paypal Express Checkout Demo</title>
</head>
<body>
<center><h1>Paypal Express Checkout Demo (java/classic nvp)</h1></center>

<form name="TestShop" action="setexpresscheckout.do" method="post">	
	<input type="hidden" name="actionName" value="setexpresscheckout.do"/>
	<fieldset>
		<table>
			<tr>
				<td>
					Item(s) in cart
				</td>
				<td>
					<input type="text" readonly="readonly" value="1" name="items" value="item1"/>
				</td>
			</tr>
			<tr>
				<td>
					Quantity
				</td>
				<td>
					<input type="text" readonly="readonly" value="1" name="qty" value="1"/>
				</td>
				
			</tr>
			<tr>
				<td>
					Price
				</td>
				<td>
					<input type="text" readonly="readonly" value="1" name="price" value="1"/>
				</td>				
			</tr>
			<tr>
				<td>
					Paypal Express Checkout
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
