function updateStuInfo() {
    var id = $("#user-id").val();
    var password = $("#user-password").val();
    var academic = $("#user-school").val();
    var tel = $("#user-tel").val();
    var name = $("#user-name").val();

    if (password != null && tel != null) {
        axios.post('editStudent', {
            userid: id,
            password: password,
            academic: academic,
            tel: tel,
            name: name
        })
            .then(res => {
                window.location.reload();
            })
            .catch(err => {
                console.error(err);
            })
    }
}

function IfHasTopic(has) {
    if (has) {
        $('.hasnotopic').hide();
        $('.hastopic').show();
    } else {
        $('.hasnotopic').show();
        $('.hastopic').hide();
    }
}

$("#topic-content").click(function () {
    var data = document.getElementById("topic-content").getAttribute('topic_data')
    SetShowTopicBar(JSON.parse(data));
});
// $('.topic-more').click(function(){
//     console.log($(this).attr('topic_data'));
//     //SetShowTopicBar( JSON.parse( $(this).attr('topic-data') ) );
// })

// $('.topic-add').click(function(){
//     console.log($(this).attr('topic_data'));
//     //SetChooseTopicBar(JSON.parse( $(this).attr('topic-data') ))
// })

function ChooseTopic() {
    //var name = $('#topic-choose-name').val();
    var id = $('#topic-choose-id').val();


    console.log(id);

    if (id != null) {
        axios.post('/selectTopic', {
            topicid: parseInt(id)
        })
            .then(res => {
                window.location.reload();
            })
            .catch(err => {
                console.error(err);
            })
    }
}

function IfHasFile(has) {
    if (has) {
        $(".hasfile").show();
        $('.hasnofile').hide();
    } else {
        $(".hasfile").hide();
        $('.hasnofile').show();
    }
}

$("#upload-article").click(function () {
    console.log("click me")
    var inputObj = document.createElement('input')
    inputObj.setAttribute('id', '_ef');
    inputObj.setAttribute('type', 'file');
    inputObj.setAttribute("style", 'visibility:hidden');
    document.body.appendChild(inputObj);
    inputObj.onchange = function (evt) {
        console.log(inputObj.files);
        var file = inputObj.files[0];
        console.log(file);

        let fm = new FormData();
        fm.append('file', file);
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };

        axios.post('/uploadfile',fm,config)
            .then(res => {
                console.log("send success");
            })
            .catch(err => {
                console.error(err);
            })
    }
    inputObj.click();
});