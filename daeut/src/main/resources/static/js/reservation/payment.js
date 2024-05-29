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


// 포트원
$("#paymentBtn").on("click", function () {
    var userName = $("#userName").val()

    var userAddress = $("#userAddress").val() + $("#userAddressDetail").val()

    var userPost = $("#userPost").val()

    var userPhone1 = $("#userPhone1").val() 
    var userPhone2 = $("#userPhone2").val() 
    var userPhone = userPhone1 + userPhone2
 
    var serviceName = 'serviceName'

    // var productCost = $("#service_price").val() + $("#service_price").val()
    var totalCost = 300

    var today = new Date();   
    var hours = today.getHours();       // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds; // 고유 주문번호 생성 

    var IMP = window.IMP
    IMP.init('imp52301113')             // 고객사 식별코드 입력 

    IMP.request_pay({
        // 결제 정보 가져오기
        pg: "kcp",                      // 등록된 pg사
        pay_method: "card",             // 결제방식: card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(소액결제)
        buyer_name: userName,           // 주문자
        buyer_addr: userAddress,        // 주소
        buyer_postcode: userPost,       // 우편번호
        buyer_tel: userPhone,           // 전화번호 (필수입력)
        name: serviceName,              // 상품명
        amount: totalCost,              // 금액
        merchant_uid: makeMerchantUid   // 고유 주문번호
        
    }, function (res) {
        // 결제 성공
        if (res.success) {
            axios({
                method: "post",
                url: `/payment/validation/${res.imp_uid}`
                // DB 연동 및 저장 필요
            }).then(function (response) {
                window.location.href = `/reservation/paymentDone`; // url 뒤 주문번호 추가
            }).catch(function (error) {
                console.error(error);
                alert("결제 성공 후 처리 중 오류가 발생...");
            });
        } else {
            // 결제 실패
            alert("결제 실패... " + res.error_msg);
            window.location.href = "/reservation/paymentFalse";
        }
    })
})

// 토스
// ------ 클라이언트 키로 객체 초기화 ------
// var clientKey = 'test_ck_0RnYX2w5322vpPpbD4Rl3NeyqApQ'
// var tossPayments = TossPayments(clientKey)

// // 변수 가져오기
// var userPhone1 = $("#userPhone1").val() 
// var userPhone2 = $("#userPhone2").val() 
// var userPhone = userPhone1 + userPhone2
// var userName = $("#userName").val()
// var serviceName = 'serviceName'

// $("#paymentBtn").on("click", function () {
//     // ------ 결제창 띄우기 ------
//     tossPayments.requestPayment('카드', { // 결제수단 파라미터 (카드, 계좌이체, 가상계좌, 휴대폰 등)
//         // 결제 정보 파라미터
//         // 더 많은 결제 정보 파라미터는 결제창 Javascript SDK에서 확인하세요.
//         // https://docs.tosspayments.com/reference/js-sdk

//         amount: 100,                                                     // 결제 금액
//         orderId: 'Mbl5RqO0KhTKoAPj4qNa9',                                // 주문 ID(주문 ID는 상점에서 직접 만들어주세요.)
//         orderName: serviceName,                                          // 제품 명
//         customerName: userName,                                          // 구매자 이름
//         customerMobilePhone: userPhone,                                  // 구매자 전화번호
//         successUrl: window.location.origin + 'reservation/paymentDone',            // 결제 성공 시 이동할 페이지
//         failUrl: window.location.origin + '/reservation/paymentFalse',              // 결제 실패 시 이동할 페이지
//     })

//     // ------결제창을 띄울 수 없는 에러 처리 ------
//     // 메서드 실행에 실패해서 reject 된 에러를 처리하는 블록입니다.
//     // 결제창에서 발생할 수 있는 에러를 확인하세요. 
//     // https://docs.tosspayments.com/reference/error-codes#결제창공통-sdk-에러
//     .catch(function (error) {
//         if (error.code === 'USER_CANCEL') {
//             // 결제 고객이 결제창을 닫았을 때 에러 처리
//             alert("결제 취소..."+error)
//         } else if (error.code === 'INVALID_CARD_COMPANY') {
//             // 유효하지 않은 카드 코드에 대한 에러 처리
//             alert("유효하지 않은 카드..."+error)
//         }
//     });
// });