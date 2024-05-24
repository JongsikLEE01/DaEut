// Daum 우편번호찾기 API
function daumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 각 값 저장
            document.getElementById('userPost').value = data.zonecode;
            document.getElementById('userAddressA').value = data.roadAddress;

            // 상세주소 키 입력 이동
            document.getElementById('userAddressDetail').focus();
        }
    }).open();
}


// 결제 스크립트
function cancelPayment() {
    const payment = alert("정말 결제를 취소하시겠습니까? 결제를 취소할 경우 모든 입력값을 잃어버리게 됩니다")
    if (payment) {
        location.href = "/resevation/reservationRead"
    }
}

$("#paymentBtn").on("click", function () {
    // TODO : 연결 필요
    var userId = $("#userId").val()
    var userName = $("#userName").val()
    // var userAddress = $("#userAddress").val()
    var userAddress = $("#userAddressA").val() + $("#userAddressDetail").val()
    var userPhone = $("#userPhone1").val() + $("#userPhone2").val()
    // var productName = $("#productName").val() 
    var productName = '상품이름'
    // var productCost = $("#productCost").val() 
    var productCost = 300
    var merchant_uid = "O" + new Date().getTime() // 고유 주문번호 생성 

    var IMP = window.IMP
    IMP.init('imp52301113') // 고객사 식별코드 입력 
    // request_pay 결제를 요청하는 함수
    IMP.request_pay({
        pg: "html5_inicis", // 등록된 pg사 (적용된 pg사는 KG이니시스)
        pay_method: "card", // 결제방식: card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(소액결제)
        merchant_uid: merchant_uid, // 주문번호
        name: productName, // 상품명
        amount: productCost, // 금액
        buyer_name: userName, // 주문자
        buyer_tel: userPhone, // 전화번호 (필수입력)
        buyer_addr: userAddress, // 주소
        buyer_postcode: userPost // 우편번호
    }, function (res) {
        if (res.success) {
            axios({
                method: "post",
                url: `/payment/validation/${rsp.imp_uid}`
            })
            // 응답 데이터의 정보들
            alert("결제 성공!")
            console.log("Payment ID : " + res.imp_uid)
            console.log("Order ID : " + res.merchant_uid)
            console.log("Payment Amount : " + res.paid_amount)
        } else
            alert("결제 실패..." + response.error_msg)
    })
})

// Example starter JavaScript for disabling form submissions if there are invalid fields
// (() => {
//     'use strict'

//     // Fetch all the forms we want to apply custom Bootstrap validation styles to
//     const forms = document.querySelectorAll('.needs-validation')

//     // Loop over them and prevent submission
//     Array.from(forms).forEach(form => {
//         form.addEventListener('submit', event => {
//         if (!form.checkValidity()) {
//             event.preventDefault()
//             event.stopPropagation()
//         }

//         form.classList.add('was-validated')
//         }, false)
//     })
// })()

// 문의 하기
function moveToChat() {
    location.href = "/resevation/chat"
}
// 예약화면 다시가기
function moveToReservation() {
    location.href = "/resevation/reservationRead"
}
// 결제 내역 확인
function moveToMyReservation() {
    location.href = "/userPage/userReservation"
}
// 메인화면
function moveToIndex() {
    location.href = "/index"
}