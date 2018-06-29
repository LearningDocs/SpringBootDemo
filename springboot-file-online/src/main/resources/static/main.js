$(document).ready(function() {

    $("#submitButton").click(function(event) {

        // Stop default form Submit.
        event.preventDefault();

        // Call Ajax Submit.

        ajaxSubmitForm();

    });
});

function ajaxSubmitForm() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);


    $("#submitButton").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/uploadMultiFiles",
        data: data,
        xhr: function () { //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
            myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) { //检查upload属性是否存在
                //绑定progress事件的回调函数
                myXhr.upload.addEventListener('progress', progressHandlingFunction, false);
            }
            return myXhr; //xhr对象返回给jQuery使用
        },
        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(data, textStatus, jqXHR) {

            $("#result").html(data);
            console.log("SUCCESS : ", data);
            $("#submitButton").prop("disabled", false);
            $('#fileUploadForm')[0].reset();
        },
        error: function(jqXHR, textStatus, errorThrown) {

            $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#submitButton").prop("disabled", false);

        }
    });

}

//上传进度回调函数：
function progressHandlingFunction(e) {
    if (e.lengthComputable) {
        $('progress').attr({value: e.loaded, max: e.total}); //更新数据到进度条
        var percent = e.loaded / e.total * 100;
        $('#progress').html(e.loaded + "/" + e.total + " bytes. " + percent.toFixed(2) + "%");
    }
}