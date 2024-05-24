// Daum 우편번호찾기 API
function daumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 각 값 저장
            document.getElementById('userPost').value = data.zonecode;
            document.getElementById('userAddress').value = data.roadAddress;

            // 상세주소 키 입력 이동
            document.getElementById('userAddressDetail').focus();
        }
    }).open();
}


// // TODO : 결제 값 가져와야함
// // 결제 스크립트
// function cancelPayment() {
//     const payment = alert("정말 결제를 취소하시겠습니까? 결제를 취소할 경우 모든 입력값을 잃어버리게 됩니다")
//     if (payment) {
//         location.href = "/resevation/reservationRead"
//     }
// }

$("#paymentBtn").on("click", function () {
    var userName = $("#userName").val()
    console.log(userName)

    var userAddress = $("#userAddress").val() + $("#userAddressDetail").val()
    console.log(userAddress)

    var userPost = $("#userPost").val()
    console.log(userPost)

    var userPhone1 = $("#userPhone1").val() 
    var userPhone2 = $("#userPhone2").val() 
    var userPhone = userPhone1 + userPhone2
    console.log(userPhone)
 
    var serviceName = 'serviceName'
    console.log(serviceName)

    // var productCost = $("#service_price").val() + $("#service_price").val()
    var totalCost = 300
    console.log(totalCost)


    var merchant_uid = "O" + new Date().getTime() // 고유 주문번호 생성 
    console.log(merchant_uid)

    var IMP = window.IMP
    IMP.init('imp52301113') // 고객사 식별코드 입력 
    // request_pay 결제를 요청하는 함수
    IMP.request_pay({
        pg: "html5_inicis",             // 등록된 pg사 (적용된 pg사는 KG이니시스)
        pay_method: "card",             // 결제방식: card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(소액결제)
        buyer_name: userName,           // 주문자
        buyer_addr: userAddress,        // 주소
        buyer_postcode: userPost,       // 우편번호
        buyer_tel: userPhone,           // 전화번호 (필수입력)
        name: serviceName,              // 상품명
        amount: totalCost,              // 금액
        merchant_uid: merchant_uid      // 주문번호
    }, function (res) {
        // 결제 성공
        if (res.success) {
            axios({
                method: "post",
                url: `/payment/validation/${res.imp_uid}`
                // DB 연동 및 저장 필요
            }).then(function (response) {
                window.location.href = "/paymentDone";
            }).catch(function (error) {
                console.error(error);
                alert("결제 성공 후 처리 중 오류가 발생...");
            });
        } else {
            // 결제 실패
            alert("결제 실패... " + res.error_msg);
            window.location.href = "/paymentFalse";
        }
    })
})