var catName = $("#catName");

var catName_flag = false;

function catName_Validate() {
    if(!ge_one_letters_regex.test(catName.val())){
        $("#msg_box").show();
        $("#br_show").hide();
        $("#msg").html("分类名必须大于一个字符")
    }else{
        $.ajax({
            type: "post",
            url: "/blog/admin/category/isExistByCatName",
            data: {catName:catName.val()},
            success: function(isExist){
                if(isExist){
                    $("#msg").html("该分类名已存在！");
                }else{
                    catName_flag = true;
                    $("#msg_box").hide();
                    $("#br_show").show();
                }
            }
        });
    }
}

$(catName).blur(function () {
    catName_Validate();
});

$("#btn_submit").click(function () {

    catName_Validate();

    if(catName_flag)
        $("#form1").submit();
});