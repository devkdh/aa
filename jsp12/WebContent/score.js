function getParameterValues(){
			var name = "name="+ $("#name").val();
			//var name = "name="+ encodeURIComponent($("#name").val());
			var kor = "kor="+ $("#kor").val();
			var eng = "eng="+ $("#eng").val();
			var math = "math="+ $("#math").val();
			
			return "?"+name+"&"+kor+"&"+eng+"&"+math;
}

$(function(){ 
	$("#process").click(function(){
		alert("asdf");
		load();
	});
});

function load(){
	var url = "CalScore"+getParameterValues();
			// CalScore?name=asdf&kor=ask ....
	
	httpRequest = new XMLHttpRequest();   //서버와 통신
	httpRequest.onreadystatechange = callback; //처리할 함수,
	//서버로 보낸 요청에 대한 응답을 받았을때 어떤 동작을 할지 지정 -> 어떤 함수가 동작을 수행할지
	httpRequest.open("GET",url,true);	//http 요청에 대한 속성을 지정,
	//get방식, 해당 url으로 요청, true:비동기
	httpRequest.send();	//open에서 지정한 속성을 이용해 요청을 전송
}

function callback(){
	alert("status: "+ httpRequest.readyState);
	
}

/*
 * -readyState: onreadystatechange에서 사용할 수 있는 http 요청상태의 코드
 * 0:실행되지 않음, open 메소드가 호출되기 이전
 * 1:로드 중, http 요청 준비된 상태
 * 2:로드 됨, http 요청 보내서 처리되는 중
 * 3:통신 됨, 데이터를 받는중
 * 4:통신 완료, 완전히 데이터를 받은 상태
 */


