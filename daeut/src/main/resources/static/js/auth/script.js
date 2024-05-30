// 회원가입
let isIdChecked = false;
let isEmailChecked = false;

function checkDuplicateId() {
    var userId = document.getElementById('userId').value;
    if (!userId) {
        alert("아이디를 입력해주세요.");
        return;
    }
    fetch(`/auth/check-duplicate?userId=${userId}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                alert("이미 사용 중인 아이디입니다.");
                isIdChecked = false;
            } else {
                alert("사용 가능한 아이디입니다.");
                isIdChecked = true;
            }
        });
}

function checkDuplicateEmail() {
    var userEmail = document.getElementById('userEmail').value;
    if (!userEmail) {
        alert("이메일을 입력해주세요.");
        return;
    }
    fetch(`/auth/check-duplicate-email?userEmail=${userEmail}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                alert("이미 사용 중인 이메일입니다.");
                alert(response.json());
                isEmailChecked = false;
            } else {
                alert("사용 가능한 이메일입니다.");
                isEmailChecked = true;
            }
        });
}

function validateForm() {
    const userId = document.getElementById('userId').value;
    const userPassword = document.getElementById('userPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const userName = document.getElementById('userName').value;
    const userBirth = document.getElementById('userBirth').value;
    const userPhone = document.getElementById('userPhone').value;
    const userEmail = document.getElementById('userEmail').value;
    const userAddress = document.getElementById('userAddress').value;

    const userIdPattern = /^[a-zA-Z0-9]{4,12}$/;
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if (!userIdPattern.test(userId)) {
        alert("아이디는 4~12자의 영문 또는 숫자만 가능합니다.");
        return false;
    }
    if (!passwordPattern.test(userPassword)) {
        alert("비밀번호는 8자 이상이어야 하며, 영문, 숫자, 특수문자를 모두 포함해야 합니다.");
        return false;
    }
    if (userPassword !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
    if (!userName) {
        alert("이름을 입력해주세요.");
        return false;
    }
    if (!(userBirth)) {
        alert("생년월일을 입력해주세요.");
        return false;
    }
    if (!/^\d{10,11}$/.test(userPhone)) {
        alert("연락처를 올바르게 입력해주세요.");
        return false;
    }
    if (!userEmail || !/\S+@\S+\.\S+/.test(userEmail)) {
        alert("올바른 이메일 주소를 입력해주세요.");
        return false;
    }
    if (!userAddress) {
        alert("주소를 입력해주세요.");
        return false;
    }
    if (!isIdChecked) {
        alert("아이디 중복 체크를 해주세요.");
        return false;
    }

    return true;
}

document.getElementById('signup-form').onsubmit = function() {
    return validateForm();
};
