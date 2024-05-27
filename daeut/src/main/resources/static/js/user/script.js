function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const toggleBtn = document.querySelector('#toggle-btn');
    const formSection = document.querySelector('.form-section');
    sidebar.classList.toggle('active');
    if (sidebar.classList.contains('active')) {
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    } else {
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    }
}

window.onload = function() {
    const sidebar = document.getElementById('sidebar');
    const toggleBtn = document.getElementById('toggle-btn');
    const formSection = document.querySelector('.form-section');

    // 페이지 로드 후 사이드바 초기 상태 설정
    if (window.innerWidth < 768) {
        sidebar.classList.remove('active');
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    } else {
        sidebar.classList.add('active');
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    }
};

window.onresize = function() {
    const sidebar = document.getElementById('sidebar');
    const toggleBtn = document.getElementById('toggle-btn');
    const formSection = document.querySelector('.form-section');

    // 페이지 크기가 변경될 때 사이드바 상태 재설정
    if (window.innerWidth < 768 && sidebar.classList.contains('active')) {
        sidebar.classList.remove('active');
        sidebar.style.maxWidth = "0"; // 사이드바 원래 상태로 복구
        toggleBtn.style.left = '10px'; // 토글 버튼 원래 위치로 복구
        toggleBtn.style.visibility = 'visible'; // 토글 버튼 보이기
    } else if (window.innerWidth >= 768 && !sidebar.classList.contains('active')) {
        sidebar.classList.add('active');
        sidebar.style.maxWidth = "250px"; // 사이드바 너비 설정
        toggleBtn.style.left = '250px'; // 토글 버튼 위치 조정
        toggleBtn.style.visibility = 'hidden'; // 토글 버튼 숨기기
    }
};
