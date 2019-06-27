
function addTopic(){
    var title=$("#mission-name").val().toString();
    var content=$("#mission-brief").val().toString();

    if (title != null && content != null) {
        axios.post('/addTopic', {
            title: title,
            description:content
        }).then(response => {
            window.reload();
        })
    }
}