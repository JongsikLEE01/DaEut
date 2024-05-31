function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const toggleBtn = document.querySelector('#toggle-btn');
    const formSection = document.querySelector('.form-section');
    sidebar.classList.toggle('active');
    if (sidebar.classList.contains('active')) {
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        // formSection.style.marginLeft = '250px'; // 폼 섹션 마진 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    } else {
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        formSection.style.marginLeft = '0'; // 폼 섹션 마진 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    }
}

window.onload = function () {
    const sidebar = document.getElementById('sidebar');
    const toggleBtn = document.getElementById('toggle-btn');
    const formSection = document.querySelector('.form-section');

    // 페이지 로드 후 사이드바 초기 상태 설정
    if (window.innerWidth < 768) {
        sidebar.classList.remove('active');
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        formSection.style.marginLeft = '0'; // 폼 섹션 마진 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    } else {
        sidebar.classList.add('active');
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        // formSection.style.marginLeft = '250px'; // 폼 섹션 마진 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    }
};

window.onresize = function () {
    const sidebar = document.getElementById('sidebar');
    const toggleBtn = document.getElementById('toggle-btn');
    const formSection = document.querySelector('.form-section');

    // 페이지 크기가 변경될 때 사이드바 상태 재설정
    if (window.innerWidth < 768 && sidebar.classList.contains('active')) {
        sidebar.classList.remove('active');
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        formSection.style.marginLeft = '0'; // 폼 섹션 마진 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    } else if (window.innerWidth >= 768 && !sidebar.classList.contains('active')) {
        sidebar.classList.add('active');
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        // formSection.style.marginLeft = '250px'; // 폼 섹션 마진 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    }
};


const csrfToken = document.querySelector("meta[name='_csrf']").content
const csrfHeader = document.querySelector("meta[name='_csrf_header']").content

const form = document.getElementById("form")

function deleteSeleteCarts() {
    const check = sweetConfirm("정말로 삭제하시겠습니까?", '삭제된 서비스는 되돌릴 수 없습니다.', 'warning', '삭제', deleteSelectedCarts)
    if (!check) return
}

// 선택삭제
function deleteSelectedCarts() {
    form.submit()
    // 비동기 테스트....
    // const checkboxes = document.querySelectorAll('input.checkbox');
    // const cartNos = [];
    // checkboxes.forEach(checkbox => {
    //     if (checkbox.checked) {
    //         cartNos.push(checkbox.value);
    //     }
    // });
    // console.log('선택된 번호? ', cartNos);
    // let data = {
    //     'cartNos' : cartNos
    // }
    // // AJAX 비동기 요청
    // let request = new XMLHttpRequest();
    // 	request.open("DELETE",'/cart/delete')
    // 	request.setRequestHeader(csrfHeader, csrfToken)
    // 	request.setRequestHeader('Content-Type', 'application/json')
    // 	request.send(JSON.stringify(data))
    // 	// 요청 상태가 변할 때 실행하는 메소드
    // 	request.onreadystatechange = function() {
    //         // 요청 성공
    // 	    if (request.readyState == request.DONE && request.status == 200) {
    //             // location.reload()
    // 	        alert('장바구니 선택 삭제 성공!'); // 응답 메세지 확인
    // 	    }
    // }
}

function deleteCarts() {
    const check = sweetConfirm("정말로 삭제하시겠습니까?", '삭제된 서비스는 되돌릴 수 없습니다.', 'warning', '삭제', deleteAllCarts)
    if (!check) return
}

// 전체삭제
function deleteAllCarts() {
    let userNo = '[[${user.userNo}]]'
    let data = {
        'userNo': userNo
    }
    // AJAX 비동기 요청
    let request = new XMLHttpRequest();
    request.open("DELETE", '/cart/delete/' + userNo)
    request.setRequestHeader(csrfHeader, csrfToken)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify(data))
    // 요청 상태가 변할 때 실행하는 메소드
    request.onreadystatechange = function () {
        // 요청 성공
        location.reload()
        if (request.readyState == request.DONE && request.status == 200) {
            alert('장바구니 비우기 성공!'); // 응답 메세지 확인
        }
    }
}