var API_BASE_URL = "http://localhost:8080/better-api";
 
 
$("#button_get_sting").click(function(e){
	e.preventDefault();
	getSting(85);
});
 
$("#button_delete_sting").click(function(e){
	e.preventDefault();
	deleteSting(85);
});
 
$("#button_post_sting").click(function(e){
	e.preventDefault();
	var sting ='{"content": "Muchos años después, frente al pelotón de fusilamiento...", "subject": "Cien Años de Soledad", "username": "alicia"}';
	createSting(sting);
});
 
 
function getSting(stingid) {
	//var url = "/stings/"+stingid;
	var url = "http://localhost:8080/better-api/stings/85";
 
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
			//"Access-Control-Allow-Origin" : "*"
		},
		beforeSend: function (request)
	    {
	        request.withCredentials = true;
	        request.setRequestHeader("Authorization", "Basic "+ btoa('alicia:alicia'));
	    },
	})
	.done(function (data, status, jqxhr) {
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
 
}
 
function deleteSting(stingid) {
	var url = API_BASE_URL + '/stings/'+stingid;
 
	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		username : 'username',
		password : 'password',
	})
    .done(function (data, status, jqxhr) {
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
		
}
 
 
function createSting(sting) {
	var url = API_BASE_URL + '/stings';
 
	$.ajax({
        dataType: "jsonp",
        url: "http://jsfiddle.net/echo/jsonp/",
		// url : url,
		type : 'POST',
		crossDomain : true,
		data : sting,
		//dataType : 'json',
		username : 'username',
		password : 'password',
	})
	.done(function (data, status, jqxhr) {
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
}