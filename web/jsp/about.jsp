<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html><head>
        <meta name="keywords" content="Балаларға білім беру орталықтары">
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/javascript.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
$(document).ready(function(){
 
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

$("#filter").click(function(e) {
    var title = $("input#title").val();
    var from = $("input#from").val();
    var to = $("input#to").val();
    var items = $("input#items").val();
    var address = $("input#address").val();
       $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "filter",
                   title: title,
                   from: from,
                   to: to,
                   items: items,
                   address: address
                  },
            dataType: "json",            
            success: function(data) {               
                if (data.success) {
                    $("#ajaxResponse").html("");                                        
                    var htmlContent = "";
                    var imageSrc = "";
                    for (var i = 0; i < data.centers.length; i++) {
                        var r = data.centers[i].rating / data.centers[i].vote;
                                    var rating = Math.floor(r);
                        if(!data.centers[i].encodedImage || 0 === data.centers[i].encodedImage.length){
                           imageSrc = "src='images/no-image120.png'";
                        } else{
                            imageSrc = "src='data:image/jpg;base64,"+data.centers[i].encodedImage+"'";
                        }
                        htmlContent += "<div id='newsBlog' class='newsStyle'>"+
                               "<p align='center' style='margin-top: 2px;'><a id='read_more' onclick='viewCenterCounter("+data.centers[i].id+");' href='${pageContext.servletContext.contextPath}/controller?command=center&id_center="+data.centers[i].id+"'><strong>"+data.centers[i].name+"</strong></a></p>"+
                           "<img title='"+data.centers[i].name+"' width='123px' height='93px' style='float: left; padding-left: 5px; padding-right: 5px;' "+imageSrc+"/>"+
                             "<p>Мекен-жай: "+data.centers[i].address+"</p>"+
                             "<p style='margin-left: 5px;'>Қаралу саны: "+data.centers[i].view+"</p>"+
                             "<p style='margin-left: 5px;'>Рейтинг: <img  src='images/"+rating+".png' /></p>"+
                             "<p style='margin-left: 5px;'>Телефон: "+data.centers[i].phone+"</p>"+
                           "</div>";
                    }
                                                           
                    $("#ajaxResponse").html(htmlContent);
                }
                else {
                    $("#ajaxResponse").html("<div>Бос нәтиже</div>");                    
                }
            }
        });
    });
    
$("#clean").click(function(e) {
        $("#title").val("");
        $("#from").val("");
        $("#to").val("");
        $("#items").val("");
        $("#address").val("");
    });

$('#ajaxResponse').scrollPagination({

		nop     : 3, // Количество запрашиваемых из БД записей
		offset  : 0, // Начальное смещение в количестве запрашиваемых данных
		error   : 'Барлық орталықтар көрсетілді!', // оповещение при отсутствии данных в БД
		delay   : 500, // Задержка перед загрузкой данных
		scroll  : true // Если true то записи будут подгружаться при прокрутке странице, иначе только при нажатии на кнопку 
		
	});
});
function toggleChevron(e) {
    $(e.target)
        .prev('.panel-heading')
        .find("i.indicator")
        .toggleClass('glyphicon-chevron-down glyphicon-chevron-up');
}
$('#accordion').on('hidden.bs.collapse', toggleChevron);
$('#accordion').on('shown.bs.collapse', toggleChevron);

    function viewCenterCounter(id){              
        $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "viewcenter",
                    id: id},
            dataType: "html",            
            success: function(data) {
                console.log(data);
            }

        });
}
    
</script>
        <title><fmt:message key="about"/></title>
    </head>
    <body>
        <%@include  file="/jsp/menu.jsp" %>
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr>
                    <td valign="top">
                        <%@include  file="/jsp/right.jsp" %>
                    </td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div style="width: 692px;" class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            <fmt:message key="filter"/>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        <table border="0">
                                            <tr>
                                                <td><label><fmt:message key="center.name"/></label></td><td><input style="margin-bottom: 10px;" size="15" type="text" id="title" class="form-control"/></td>
                                                <td><label style="margin-left: 10px;"><fmt:message key="center.price"/> </label></td><td colspan="2"><input style="margin-bottom: 10px; float: left; width:39%;" size="5" type="text" id="from" class="form-control"/><label style="float: left; line-height: 25px; margin-right: 5px; margin-left: 5px;">—</label><input style="margin-bottom: 10px; width:38%;" size="5" type="text" id="to" class="form-control"/></td>
                                                <td><input id="clean" style="margin-top: -10px;" class="btn btn-danger" type='button' value='<fmt:message key="filter.clear"/>'/></td>
                                            </tr>
                                            <tr>
                                                <td><label><fmt:message key="center.items"/></label></td><td><input style="margin-bottom: 10px;" size="15" type="text" id="items" class="form-control"/></td>
                                                <td colspan="2"><label style="margin-left: 10px;"><fmt:message key="center.address"/></label></td><td><input style="margin-bottom: 10px; width:90%;" size="15" type="text" id="address" class="form-control"/></td>
                                                <td><input id="filter" style="margin-top: -10px;" class="btn btn-success" type='button' value='<fmt:message key="filter.show"/>'/></td>
                                            </tr>
                                        </table>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div id="ajaxResponse"></div>
                    </td>
                    <td valign="top">
                        <%@include  file="/jsp/left.jsp" %>
                    </td>
                    </tr>
            </table>
            <hr/>
            <%@include  file="/jsp/footer.jsp" %>
        </div>
            <a href="#" class="scrollup">Наверх</a>
    </body></html>
