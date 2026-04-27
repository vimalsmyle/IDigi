/**
 * 
 */

$(document).ready(function() {


	$("#uploadBtn").click(function() {

		var fileInput = $("#fileInput")[0];

		if (fileInput.files.length === 0) {
			alert("Please select a file");
			return;
		}

		var formData = new FormData();
		formData.append("file", fileInput.files[0]);

		$.ajax({
			url: "./customer/exceladd/"
				+ sessionStorage.getItem("roleID") + "/"
				+ sessionStorage.getItem("createdByID") + "/"
				+ sessionStorage.getItem("ID"),
			type: "POST",
			data: formData,
			processData: false,
			contentType: false,

			// ⭐ IMPORTANT FIX
			xhrFields: {
				responseType: 'blob'
			},

			success: function(data, status, xhr) {

				// Get filename from header
				var filename = "download.xlsx";
				var disposition = xhr.getResponseHeader('Content-Disposition');
				if (disposition && disposition.indexOf('filename=') !== -1) {
					filename = disposition.split('filename=')[1];
				}

				// Create download
				var blob = new Blob([data]);
				var link = document.createElement('a');
				link.href = window.URL.createObjectURL(blob);
				link.download = filename;
				link.click();

				alert("File processed & downloaded successfully");
			},

			error: function(err) {
				console.error("Upload failed:", err);
				console.error("Status:", err.status);
				console.error("Response:", err.responseText);
				alert("Error uploading file");
			}
		});

	});

});