/**
 * 
 */

$(document).ready(function() {
$("#uploadBtn").click(function () {

    var fileInput = $("#fileInput")[0];

    if (fileInput.files.length === 0) {
        alert("Please select a file");
        return;
    }

    var formData = new FormData();
    formData.append("file", fileInput.files[0]);

    $.ajax({
        url: "/upload",   // your backend API
        type: "POST",
        data: formData,
        processData: false,   // IMPORTANT
        contentType: false,   // IMPORTANT
        success: function (response) {
            console.log("Upload success:", response);
            alert("File uploaded successfully");
        },
        error: function (err) {
            console.error("Upload failed:", err);
            alert("Error uploading file");
        }
    });

});

});