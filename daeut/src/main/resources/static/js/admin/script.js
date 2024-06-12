// 회원가입
function checkDuplicateId() {
    var userId = document.getElementById('userId').value;
    if (!userId) {
        sweetAlert('경고',"아이디를 입력해주세요.", 'warning');
        return;
    }
    fetch(`/admin/check-duplicate?userId=${userId}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                sweetAlert('경고',"이미 사용 중인 아이디입니다.", 'warning');
                isIdChecked = false;
            } else {
                sweetAlert('😊',"사용 가능한 아이디입니다.", 'success');
                isIdChecked = true;
            }
        });
}

function checkDuplicateEmail() {
    var userEmail = document.getElementById('userEmail').value;
    if (!userEmail) {
        sweetAlert('경고',"이메일을 입력해주세요.", 'warning');
        return;
    }
    fetch(`/admin/check-duplicate-email?userEmail=${userEmail}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                sweetAlert('경고',"이미 사용 중인 이메일입니다.", 'warning');
                sweetAlert('경고',response.json());
                isEmailChecked = false;
            } else {
                sweetAlert('😊',"사용 가능한 이메일입니다.", 'success');
                isEmailChecked = true;
            }
        });
}
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

function previewThumbnail(event) {
    var container = document.getElementById('image-thumbnail-container');
    container.innerHTML = ''; // 기존 이미지 제거
    
    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var image = document.createElement('img');
        image.src = URL.createObjectURL(files[i]);
        image.alt = '이미지 미리보기';
        image.style.maxWidth = '119px';
        image.style.marginTop = '150px';
        container.appendChild(image);
    }
}
