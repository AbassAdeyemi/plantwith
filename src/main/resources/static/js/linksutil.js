$(document).on('click','#bookPackage',function(event){
	event.preventDefault();
	var crop = $(this).attr('href');
	$.post('profile/package',{crop:crop},function(result){
		if(result === "00")
		alert('Succesfully booked');
		else
			alert('Cannot book this package');
	});
});