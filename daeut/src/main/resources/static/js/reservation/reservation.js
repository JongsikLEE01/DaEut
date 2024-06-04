// document.addEventListener('DOMContentLoaded', () => {
//     document.querySelectorAll('.like-icon').forEach(icon => {
//         icon.addEventListener('click', () => {
//             icon.classList.toggle('liked');
//         });
//     });
// });

/* 파일 업로드 */
function previewImages(event) {
    var container = document.getElementById('image-preview-container');
    container.innerHTML = ''; // 기존 이미지 제거
    
    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var image = document.createElement('img');
        image.src = URL.createObjectURL(files[i]);
        image.alt = '이미지 미리보기';
        image.style.maxWidth = '250px';
        image.style.marginTop = '10px';
        container.appendChild(image);
    }
}

function previewThumbnail(event) {
    var container = document.getElementById('image-thumbnail-container');
    container.innerHTML = ''; // 기존 이미지 제거
    
    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var image = document.createElement('img');
        image.src = URL.createObjectURL(files[i]);
        image.alt = '이미지 미리보기';
        image.style.maxWidth = '250px';
        image.style.marginTop = '10px';
        container.appendChild(image);
    }
}


function addOptionForm() {
    var container = document.getElementById('option-form-container');
    var newOptionForm = document.createElement('div');
    newOptionForm.classList.add('option-input'); // 옵션 입력 폼에 클래스 추가
    newOptionForm.innerHTML = `
        <input type="text" name="optionName[]" placeholder="옵션명" required>
        <input type="number" name="price[]" placeholder="가격" required>
        <textarea name="description[]" rows="2" cols="50" placeholder="옵션 설명" required></textarea>
    `;
    // 새로운 옵션 창을 버튼 위로 추가
    container.insertBefore(newOptionForm, document.querySelector('.addOption'));
}

// 화면 스크롤 이동

    document.addEventListener("DOMContentLoaded", function() {
        document.querySelector(".reservation-menu").addEventListener("click", function(e) {
            if (e.target && e.target.nodeName === "LI") {
                var targetId = e.target.getAttribute("data-target");
                document.getElementById(targetId).scrollIntoView({ behavior: "smooth" });
            }
        });
});