$(document).ready(function() {	
	var var1 = $("select#hotelID").val();
	$.get('http://localhost/EWAProject/Controller/HotelListServlet', { typeVal : var1 }, function(response) {
	
	var select1 = $('#hotelID');
	select1.find('option').remove();
	$.each(response, function(index, value) {
	$('<option>').val(value).text(value).appendTo(select1);
		});
	});
});