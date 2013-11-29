var API_BASE_URL = "http://localhost:8080/better-api";

$().ready(function() {
	$('#get').validate({
		  rules: {
			  	id: "required"
		  		},
		  messages: {	//si va mal
			    id: "Introduce una ID!"
		  		}
		});
});
 
$("#button_get_sting").click(function(e){
	e.preventDefault();
	var id = $('#id').val();
	
	getSting(id);	
});
 
$("#button_delete_sting").click(function(e){
	e.preventDefault();
	deleteSting(84);
});
 
$("#button_post_sting").click(function(e){
	e.preventDefault();
	var content = $('#content').val();
	var subject = $('#subject').val();
	var username = $('#username').val();
	if ((content != "") && (subject != "") && (username != "")) {
		var sting ='{"content": "'+ content+'", "subject": "'+ subject+'", "username": "'+ username+'"}';
		$('#error_content').html("");
		$('#error_subject').html("");
		$('#error_username').html("");
		createSting(sting);
	} else {
		if (content == "")
			$('#error_content').html("<font color='red'>Falta contenido!</font>");
		if (subject == "")
			$('#error_subject').html("<font color='red'>Falta subject!</font>");
		if (username == "")
			$('#error_username').html("<font color='red'>Falta username!</font>");
	}
});

$("#button_put_sting").click(function(e){
	e.preventDefault();
	var sting ='{"content": "Update AJAX", "subject": "AJAX", "id": "104"}';
	var stingid = 104;
	updateSting(sting, stingid);
});

$("#button_get_list_sting").click(function(e){
	e.preventDefault();
	var offset = 0;
	var length = 10;
	getListSting(offset, length);
}); 
 
function getSting(stingid) {
	//var url = "/stings/"+stingid;
	var url = "http://localhost:8080/better-api/stings/"+stingid;
 
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
		var htmlString = "GET STING<br>";
		htmlString += 'ID: '+data.stingId+'<br>';
		htmlString += 'Autor: '+data.author+'<br>';
		htmlString += 'Subject: '+data.subject+'<br>';
		htmlString += 'Content: '+data.content+'<br>';
		htmlString += 'Tiemstamp: '+data.creationTimestamp;
		$('#res_get_sting').html(htmlString);
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
    	var htmlString = "DELETE STING: "+status;
		$('#res_delete_sting').html(htmlString);
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
    	var htmlString = "DELETE STING: "+textStatus;
		$('#res_delete_sting').html(htmlString);
		console.log(textStatus);
	});
		
}
 
 
function createSting(sting) {
	var url = API_BASE_URL + '/stings';
 
	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : sting,
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
			"Content-Type" : "application/vnd.beeter.api.sting+json",
			//"Access-Control-Allow-Origin" : "*"
		},
		beforeSend: function (request)
	    {
	        request.withCredentials = true;
	        request.setRequestHeader("Authorization", "Basic "+ btoa('alicia:alicia'));
	    },
	})
	.done(function (data, status, jqxhr) {
		var htmlString = "POST STING: "+status;
		$('#res_post_sting').html(htmlString);
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
    	var htmlString = "POST STING: "+textStatus+" <br>"+sting;;
		$('#res_post_sting').html(htmlString);
		console.log(textStatus);
	});
}


function updateSting(sting, stingid) {
	var url = API_BASE_URL + '/stings/' + stingid;
 
	$.ajax({
		url : url,
		type : 'PUT',
		crossDomain : true,
		dataType : 'json',
		data : sting,
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
			"Content-Type" : "application/vnd.beeter.api.sting+json",
			//"Access-Control-Allow-Origin" : "*"
		},
		beforeSend: function (request)
	    {
	        request.withCredentials = true;
	        request.setRequestHeader("Authorization", "Basic "+ btoa('alicia:alicia'));
	    },
	}) //http://richardshepherd.com/how-to-use-jquery-with-a-json-flickr-feed-to-display-photos/
	.done(function (data, status, jqxhr) {
		var htmlString = "PUT STING:<br>";
		htmlString += 'ID: '+data.stingId+'<br>';
		htmlString += 'Autor: '+data.author+'<br>';
		htmlString += 'Subject: '+data.subject+'<br>';
		htmlString += 'Content: '+data.content+'<br>';
		htmlString += 'Tiemstamp: '+data.creationTimestamp+'<br>';
		$('#res_put_sting').html(htmlString);
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
    	var htmlString = "PUT STING: "+textStatus;
    	$('#res_put_sting').text(htmlString);
		console.log(textStatus);
	});
}


function getListSting(offset, length) {
	//var url = "/stings/"+stingid;
	var url = "http://localhost:8080/better-api/stings?offset="+offset+"&length="+length+"";
 
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting.collection+json",
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
		var htmlString = "GET LIST STING<br>";
		 // Start putting together the HTML string
	    
	    // Now start cycling through our array of Flickr photo details
	    $.each(data.stings, function(i,s){	        
	        // Here's where we piece together the HTML
	        htmlString += 'ID: '+s.stingId+'<br>';
			htmlString += 'Autor: '+s.author+'<br>';
			htmlString += 'Subject: '+s.subject+'<br>';
			htmlString += 'Content: '+s.content+'<br>';
			htmlString += 'Tiemstamp: '+s.creationTimestamp+'<br><br>';
	    
	    });
		$('#res_get_list_sting').html(htmlString);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
 
}