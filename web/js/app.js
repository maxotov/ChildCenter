$(document).ready(function(){

$("#like").toggle(function(){
    var id = $("#idNews").val();
   $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "incrementlike",
                    id: id},
            dataType: "html",            
            success: function(data) {
                $("#count").html(data);
            }

        });
}, function() {
    var id = $("#idNews").val();
   $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "decrementlike",
                    id: id},
            dataType: "html",            
            success: function(data) {
                $("#count").html(data);
            }

        });
});

$(window).scroll(function(){
if ($(this).scrollTop() > 100) {
$('.scrollup').fadeIn();
} else {
$('.scrollup').fadeOut();
}
});
 
$('.scrollup').click(function(){
$("html, body").animate({ scrollTop: 0 }, 600);
return false;
});
});

function viewCounter(id){              
        $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "viewnews",
                    id: id},
            dataType: "html",            
            success: function(data) {
                console.log(data);
            }

        });
}



