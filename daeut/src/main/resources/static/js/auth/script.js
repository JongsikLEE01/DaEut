// 회원가입
let isIdChecked = false;
let isEmailChecked = false;

function checkDuplicateId() {
    var userId = document.getElementById('userId').value;
    if (!userId) {
        sweetAlert('경고',"아이디를 입력해주세요.", 'warning');
        return;
    }
    fetch(`/auth/check-duplicate?userId=${userId}`)
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
    fetch(`/auth/check-duplicate-email?userEmail=${userEmail}`)
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
        sweetAlert('경고',"아이디는 4~12자의 영문 또는 숫자만 가능합니다.", 'warning');
        return false;
    }
    if (!passwordPattern.test(userPassword)) {
        sweetAlert('경고',"비밀번호는 8자 이상이어야 하며, 영문, 숫자, 특수문자를 모두 포함해야 합니다.", 'warning');
        return false;
    }
    if (userPassword !== confirmPassword) {
        sweetAlert('경고',"비밀번호가 일치하지 않습니다.", 'warning');
        return false;
    }
    if (!userName) {
        sweetAlert('경고',"이름을 입력해주세요.", 'warning');
        return false;
    }
    if (!(userBirth)) {
        sweetAlert('경고',"생년월일을 입력해주세요.", 'warning');
        return false;
    }
    if (!/^\d{10,11}$/.test(userPhone)) {
        sweetAlert('경고',"연락처를 올바르게 입력해주세요.", 'warning');
        return false;
    }
    if (!userEmail || !/\S+@\S+\.\S+/.test(userEmail)) {
        sweetAlert('경고',"올바른 이메일 주소를 입력해주세요.", 'warning');
        return false;
    }
    if (!userAddress) {
        sweetAlert('경고',"주소를 입력해주세요.", 'warning');
        return false;
    }
    if (!isIdChecked) {
        sweetAlert('경고',"아이디 중복 체크를 해주세요.", 'warning');
        return false;
    }
    if (!isEmailChecked) {
        sweetAlert('경고', "이메일 중복 체크를 해주세요.", 'warning');
        return false;
    }

    return true;
}
document.addEventListener("DOMContentLoaded", function() {
    const errorMessageElement = document.getElementById('errorMessage');
    if (errorMessageElement) {
        const errorMessage = errorMessageElement.value;
        if (errorMessage) {
            sweetAlert('경고', errorMessage, 'warning');
            // sweetAlert('경고',errorMessage);
        }
    }
});


document.getElementById('signup-form').onsubmit = function() {
    return validateForm();
};


