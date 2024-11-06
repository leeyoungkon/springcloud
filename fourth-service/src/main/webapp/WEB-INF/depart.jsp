<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	        <script src="/resources/static/assets/js/jquery.min.js"></script>
			<script src="/resources/static/assets/js/browser.min.js"></script>
			<script src="/resources/static/assets/js/breakpoints.min.js"></script>
			<script src="/resources/static/assets/js/main.js"></script>
책 관련 정보입니다. 
<button onclick='procCallAjax("/departList", departList)'> Depart 데이터 가져오기 </button>
							<table>
								<thead>
									<tr>
						                <td>departId</td>
						                <td>Name</td>
						                <td>price</td>
						                <td>saleDate</td>
						            </tr>
								</thead>
								<tbody id="mysqlResult"></tbody>
							</table>
<script type="text/javascript">

var procCallAjax = function(reqUrl, callback) {
    var reqData = "";
    $.ajax({
        url: reqUrl,
        method: "GET",
        dataType: 'json',
        async: false,
        contentType: "application/json",
        success: function(data) {
            callback(data);
        },
        error: function(xhr, status, error) {
            //alert("api error message");
        },
        complete : function(data) {
            // SKIP
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

var departList = function(data) {
	var dataStr = [];
 	$.each(data, function(index, item){
		/* dataStr += JSON.stringify(item) + "\r\n"; */
 		dataStr.push('<tr>' +
 				        '<td>' + item.departId + '</td>' +
 				        '<td>' + item.name + '</td>' +
 				        '<td>' + item.price + '</td>' +
 				        "<td>" + item.saleDate + "</td>" +
 			        '</tr>');

	});
	$('#mysqlResult').html(dataStr);
	//$('#ghost').hide();
}



</script>								
</body>

</html>
