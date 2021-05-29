var blogName = $("#blogName");
var blogAddress = $("#blogAddress");

var blogName_flag = false;
var blogAddress_flag = false;

function blogAddress_Validate() {
    if(!ge_one_letters_regex.test(blogAddress.val())){
        $("#msg_box").show();
        $("#msg").html("友链链接必须大于一个字符")
    }else{
        $.ajax({
            type: "post",
            url: "/blog/admin/link/isExistByBlogAddress",
            data: {blogAddress:blogAddress.val()},
            success: function(isExist){
                if(isExist){
                    $("#msg").html("该友链链接已存在！");
                }else{
                    blogAddress_flag = true;
                    $("#msg_box").hide();
                    $("#br_show").show();
                }
            }
        });
    }
}

function blogName_Validate() {
    if(!ge_one_letters_regex.test(blogName.val())){
        $("#msg_box").show();
        $("#msg").html("友链名必须大于一个字符");
    }else {
        blogName_flag = true;
        $("#msg_box").hide();
        $("#msg").html("");
    }
}

$(blogAddress).blur(function () {
    blogAddress_Validate();
});

$(blogName).blur(function () {
    blogName_Validate();
});

$("#btn_submit").click(function () {

    blogAddress_Validate();
    blogName_Validate();

    if(blogAddress_flag && blogName_flag)
        $("#form1").submit();
});