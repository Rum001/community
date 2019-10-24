/**
 * 发起回复
 */
function postComment() {
    var content = $("#content").val();
    var parentId = $("#parentId").val();
    comment(parentId,content,1);
}
function secondComment() {
    var content = $("#secondContent").val();
    var parentId = $("#parentId").val();
    alert(content);
    alert(parentId);
    comment(parentId,content,2);
}
function comment(parentId,content,type) {
    if (content==null ||content==""){
        alert("内容不能为空");
        return;
    }
    $.ajax({
        url: '/comment/',
        type: 'POST',  // 默认为GET
        contentType: "application/json",
        data: JSON.stringify({
            parentId: parentId,
            content: content,
            type: type,
        }),
        dataType: 'json',
        success: function (data) {
            if (data.code == "200") {
                window.location.href="http://localhost:8887/question/"+data.id;
            }else if(data.code=="2005"){
                alert(data.message);
            } else {
                if (data.code == "2003") {
                    var conf = confirm(data.message);
                    if (conf) {
                        window.open("https://github.com/login/oauth/authorize?client_id=91c6cbec3712a2f3de54&redirect_uri=http://localhost:8887/callback&scope=user&=state=1");
                    }
                }
            }
        }
    })
}
/**
 * 显示或隐藏二级评论
 */
function showSecondComment(e) {
    var span = e.getAttribute("data-id");
    var second = $("#comment-"+span);
    var attribute = e.getAttribute("temp");
    if (attribute){
        second.removeClass("in");
        e.classList.remove("active");
        e.removeAttribute("temp");
    }else {
        second.addClass("in");
        e.setAttribute("temp","in");
        e.classList.add("active");
    }

}