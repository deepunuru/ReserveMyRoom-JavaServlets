$(document).ready(function() {	
	var var1 = $("select#roomType").val();
	$.get('http://localhost/EWAProject/Controller/RoomTypeServlet', { typeVal : var1 }, function(response) {
	
	var select1 = $('#roomType');
	select1.find('option').remove();
	$.each(response, function(index, value) {
	$('<option>').val(value).text(value).appendTo(select1);
		});
	});
});