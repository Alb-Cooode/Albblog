var pictureName = $("#pictureName");
var pictureAddress = $("#pictureAddress");

var pictureName_flag = false;
var pictureAddress_flag = false;

function pictureAddress_Validate() {
    if(!ge_one_letters_regex.test(pictureAddress.val())){
        $("#msg_box").show();
        $("#msg").html("图片地址不规范！")
    }else{
        $.ajax({
            type: "post",
            url: "/blog/admin/picture/isExistByPictureAddress",
            data: {pictureAddress:pictureAddress.val()},
            success: function(isExist){
                if(isExist){
                    $("#msg_box").show();
                    $("#msg").html("该图片已存在！");
                }else{
                    pictureAddress_flag = true;
                    $("#msg_box").hide();
                }
            }
        });
    }
}

function pictureName_Validate() {
    if(!ge_one_letters_regex.test(pictureName.val())){
        $("#msg_box").show();
        $("#msg").html("图片名必须大于一个字符");
    }else {
        pictureName_flag = true;
        $("#msg_box").hide();
        $("#msg").html("");
    }
}

$(pictureAddress).blur(function () {
    pictureAddress_Validate();
});

$(pictureName).blur(function () {
    pictureName_Validate();
});

$("#btn_submit").click(function () {

    pictureAddress_Validate();
    pictureName_Validate();

    if(pictureAddress_flag && pictureName_flag)
        $("#form1").submit();
});