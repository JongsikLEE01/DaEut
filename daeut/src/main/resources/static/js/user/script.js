function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const toggleBtn = document.querySelector('#toggle-btn');
    const formSection = document.querySelector('.form-section');
    sidebar.classList.toggle('active');
    if (sidebar.classList.contains('active')) {
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        formSection.style.marginLeft = '250px'; // 폼 섹션 마진 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    } else {
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        formSection.style.marginLeft = '0'; // 폼 섹션 마진 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    }
}

// 페이지 로드 후 실행되는 함수
window.onload = function() {
    // 사이드 바 요소와 토글 버튼 요소 가져오기
    const sidebar = document.getElementById('sidebar');
    const toggleBtn = document.getElementById('toggle-btn');
    
    // 사이드 바 옆을 클릭했을 때 사이드 바 닫기
    document.addEventListener('click', function(e) {
        if (sidebar.classList.contains('active') && !sidebar.contains(e.target) && !toggleBtn.contains(e.target)) {
            sidebar.classList.remove('active');
            sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
            toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
            document.querySelector('.form-section').style.marginLeft = '0'; // 폼 섹션 마진 복구
            toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
        }
    });
};
