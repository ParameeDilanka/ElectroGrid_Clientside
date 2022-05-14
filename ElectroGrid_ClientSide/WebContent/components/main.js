$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateInterruptForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidinterruptIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "InterruptApi",
		type : t,
		data : $("#formInterrupt").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onInterruptSaveComplete(response.responseText, status);
		}
	});
}); 

function onInterruptSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidinterruptIDSave").val("");
	$("#formInterrupt")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidinterruptIDSave").val($(this).closest("tr").find('#hidinterruptIDUpdate').val());     
	$("#interruptArea").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#interruptStartTime").val($(this).closest("tr").find('td:eq(1)').text());   
	$("#interruptEndTime").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#interruptHours").val($(this).closest("tr").find('td:eq(3)').text());   
	$("#interruptMessage").val($(this).closest("tr").find('td:eq(4)').text());     
	
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "InterruptApi",
		type : "DELETE",
		data : "interruptID=" + $(this).data("interruptid"),
		dataType : "text",
		complete : function(response, status)
		{
			onInterruptDeletedComplete(response.responseText, status);
		}
	});
});

function onInterruptDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateInterruptForm() {  
	// Area  
	if ($("#interruptArea").val().trim() == "")  {   
		return "Insert interruptArea.";  
		
	} 
	
	 // Start Time 
	if ($("#interruptStartTime").val().trim() == "")  {   
		return "Insert interruptStartTime.";  
	} 
	
	 // End Time 
	if ($("#interruptEndTime").val().trim() == "")  {   
		return "Insert interruptEndTime.";  
	} 
	
	 // Hours
	if ($("#interruptHours").val().trim() == "")  {   
		return "Insert interruptHours.";  
	} 
	
	
	//Message
	if ($("#interruptMessage").val().trim() == "")  {   
		return "Insert interruptMessage."; 
		 
	}
	 
	 return true; 
	 
}
