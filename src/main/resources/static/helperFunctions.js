function getName () {
    $.get("/user/name",function (name,status){
        $("#navbardrop").html(name);
    });
};