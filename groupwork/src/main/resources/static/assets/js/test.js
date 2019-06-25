
$("#add-teach-test").click(function () { 
    console.log("add teach")
    AttachChildren(Teacher_List_Render,MakeUpTeach("tnj","kkk","123456","12345678","SC"));
});

$("#add-stud-test").click(function () { 
    console.log("add stud")
    AttachChildren(Student_List_Render,MakeUpStud("gkd","kkk","123456","12345678","SC"));
});