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

