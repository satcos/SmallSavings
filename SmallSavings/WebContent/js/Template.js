function generateFooter()	{
	document.write(
'<div id="footer">',
	'<p>Copyright (c) 2012 <a href="www.satcos.in">www.satcos.in</a> All rights reserved. Design by Sathiya Narayanan.</p>',
'</div>');
}
function generateMenu(page)	{
	var index = "";
	var newacc = "";
	var search = "";
	var makepay = "";
	var print = "";
	var contact = "";
	
	if(page == "index")
		index = 'class="current_page_item"';
	else if(page == "newacc")
		newacc = 'class="current_page_item"';
	else if(page == "search")
		search = 'class="current_page_item"';
	else if(page == "makepay")
		makepay = 'class="current_page_item"';
	else if(page == "print")
		print = 'class="current_page_item"';
	else
		contact = 'class="current_page_item"';
	
	document.write(
			'<div id="menu">' ,
				'<ul>' ,
					'<li ' + index +  ' ><a href="index.jsp">Home</a></li>' ,
					'<li ' + newacc + ' ><a href="newacc.jsp">New Account</a></li>' ,
					'<li ' + search + ' ><a href="search.jsp">Search Account</a></li>' ,
					'<li ' + makepay + ' ><a href="makepay.jsp">Make Payment</a></li>' ,
					'<li ' + print + ' ><a href="Payment?action=printDate">Print</a></li>' ,
					'<li ' + contact + ' ><a href="#">Contact</a></li>' ,
				'</ul>' ,
			'</div>');
}
function generateHeader()	{
	document.write(
		'<div id="header">',
			'<div id="logo">',
				'<h1><a href="#">PORD</a></h1>',
				'<p> Make it Fast!!</p>',
			'</div>',
		'</div>');
}