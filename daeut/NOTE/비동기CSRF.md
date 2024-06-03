
// 💍 CRSF TOKEN
var csrfToken = "[[${_csrf.token}]]"
let request = new XMLHttpRequest()

// 요청 설정
let url = `/요청경로`
request.open("요청 메소드", url, true)  // 요청 메소드 : GET, POST, PUT, DELETE
// 💍 CSRF 토큰을 요청 헤더에 추가
request.setRequestHeader("X-CSRF-TOKEN", csrfToken) 
request.send()
// 응답 확인
request.onreadystatechange = function() {
  let response = ''

  // 요청 완료 및 응답 성공 시
  if( request.readyState == request.DONE && request.status == 200 ){
      // request.responseText : 응답데이터
      response = request.responseText

      // JSON.parse()  : text --> JSON 변환
      let jsonData = JSON.parse(response)

      // 데이터 없을 때
      if( boardList.length == 0 ) 
          alert('응답된 데이터가 없습니다.')
      // 데이터 있을 때
      else {
          alert(jsonData)
      }
  }
}

