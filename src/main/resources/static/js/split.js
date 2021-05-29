function split_submit(element) {
    var page = $(element).attr("page");
    $("#form1").prop("action",$("#form1").prop("action") + "?page=" + page).submit();
}