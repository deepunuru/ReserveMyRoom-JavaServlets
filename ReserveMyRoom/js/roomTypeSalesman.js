$(document).ready(function() {
	$('#hotelID').change(function(event) {
		var var1 = $("select#hotelID").val();
		$.get('http://localhost/EWAProject/Controller/RoomTypeServlet', { typeVal : var1 }, function(response) {
		
		var select = $('#roomType');
		select.find('option').remove();
		$.each(response, function(index, value) {
		$('<option>').val(value).text(value).appendTo(select);
			});
		});
	});
});