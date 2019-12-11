$(function(){
	getJson();
});
//json 내용을 읽어오는 함수
function getJson(){
	//getJSON() 통해서 json 데이터를 받아온다.
	$.getJSON("json/bike.json",function(data){ 
		$.each(data,function(key,val){
			//key: 2개, DESCRIPTION , DATA
			if(key=="DESCRIPTION"){
				$("table").attr("border","1");
				
				$("thead").append(
						"<tr>"+
						"<th>"+val.ADDR_GU+"</th>"+
						"<th>"+val.CONTENT_ID+"</th>"+
						"<th>"+val.CONTENT_NM+"</th>"+
						"<th>"+val.NEW_ADDR+"</th>"+
						"<th>"+val.CRADLE_COUNT+"</th>"+
						"<th>"+val.LONGITUDE+"</th>"+
						"<th>"+val.LATITUDE+"</th>"+
						"</tr>"
				);
			}else {
				var list = val; //list:배열
				for(var i=0; i<list.length; i++){
					var str = list[i]; //str: 배열 안에 있는 하나의 json
					$("tbody").append(
							"<tr>"+
							"<td>"+str.addr_gu+"</td>"+
							"<td>"+str.content_id+"</td>"+
							"<td>"+str.content_nm+"</td>"+
							"<td>"+str.new_addr+"</td>"+
							"<td>"+str.cradle_count+"</td>"+
							"<td>"+str.longitude+"</td>"+
							"<td>"+str.latitude+"</td>"+
							"<input type='hidden' name='bike' value='"+
							str.addr_gu+"/"+str.content_id+"/"+
							str.content_nm+"/"+str.new_addr+"/" +
							str.cradle_count+"/"+str.longitude+"/"+
							str.latitude+"'/>"+
							"</tr>"
					
					);
				}
				
				
				
			}
			
			
		});
		
		
	});
	
	
}












