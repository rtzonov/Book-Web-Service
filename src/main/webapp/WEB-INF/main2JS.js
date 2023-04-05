$('#dropDownAuthorData').change(function() {
    var item=$(this);
    alert(item.val())
   $('#authorData').val(item.val())
});
/*    document.querySelector('#dropDownAuthorData').change(function() {
        alert(1);
        var item=document.querySelector(this);
        alert(item.value)
        document.querySelector('#authorData').val(item.value)
    });
    function arer(){
        alert(1);
        var item=document.querySelector(document.getElementById('dropDownAuthorData'));
        alert(item.value)
        document.querySelector('#authorData').val(item.value)
    }*/
/*$('#dropDownAuthorData').change(function() {
    alert(1);
    var item=$(this);
    alert(item.val())
    $('#authorData').val(item.val())
});*/