function btn_get_submit(element) {
    var submit_url = $(element).attr("submit_url");
    var parameter = $(element).attr("id");
    location.href = submit_url + parameter;
}

function btn_get_remove_submit(element) {
    if(confirm("确认要删除此篇文章吗？")){
        var submit_url = $(element).attr("submit_url");
        var parameter = $(element).attr("id");
        location.href = submit_url + parameter;
    }
}

function btn_get_remove_picture_submit(element) {
    if (confirm("确认要删除此图片吗？")) {
        var submit_url = $(element).attr("submit_url");
        var parameter = $(element).attr("id");
        location.href = submit_url + parameter;
    }
}

function btn_get_remove_link_submit(element) {
    if(confirm("确认要删除此友链吗？")){
        var submit_url = $(element).attr("submit_url");
        var parameter = $(element).attr("id");
        location.href = submit_url + parameter;
    }
}

function ajax_remove_submit(element) {
    var id = $(element).attr("id");
    var model_path = $("#form1").attr("model_path");
    if (confirm("确认要删除此条记录吗？")) {
        $.ajax({
            type: "get",
            url: model_path + "/isUsedByBlog",
            data: {id: id},
            success: function (isUsed) {
                if (isUsed)
                    alert("该记录与其他记录存在关联，不能删除！");
                else
                    location.href = model_path + "/remove?id=" + id;
            }
        });
    }
}