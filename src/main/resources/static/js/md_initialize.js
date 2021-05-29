var contentEditor;
$(function() {
    contentEditor = editormd("md-content", {//这里是使用书写内容模块的id
        width   : "100%",
        height  : 640,
        syncScrolling : "single",
        path    : "/blog/lib/editormd/lib/",//这里是editor/lib的目录
        saveHTMLToTextarea : true,//方便表单提交
        // theme: "dark",//工具栏主题
        // previewTheme: "dark",//预览主题
        // editorTheme: "pastel-on-dark",//编辑主题
        tocm: true,//用tocm
        tex: true,//数学公式开启
        flowChart: true,//开启流程图
        sequenceDiagram: true,//时序、序列图开启

        /**上传图片相关配置*/
        imageUpload : true,  //开启图片上传
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],//支持格式
        imageUploadURL : "/blog/img/imgUpload", //上传的路径，就是controller路径
    });
});