document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.like-icon').forEach(icon => {
        icon.addEventListener('click', () => {
            icon.classList.toggle('liked');
        });
    });
});


/* 채팅 임시 스크립트 */
function sendMessage() {
    var messageInput = document.getElementById("message");
    var message = messageInput.value;
    messageInput.value = "";

    if (message.trim() === "") {
        return;
    }

    var chatBox = document.getElementById("chat-box");
    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message");

    var bubbleDiv = document.createElement("div");
    bubbleDiv.classList.add("bubble");
    bubbleDiv.innerText = message;

    var timeSpan = document.createElement("span");
    timeSpan.classList.add("time");
    timeSpan.innerText = getCurrentTime();

    messageDiv.appendChild(bubbleDiv);
    messageDiv.appendChild(timeSpan);
    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight; // 스크롤 맨 아래로 이동

    // 상대방의 임의의 메시지 생성 및 추가 - 나중에 이 부분은 빼고 아래 스크립트도 제거하면 됨
    receiveMessage(generateRandomMessage());
}

function receiveMessage(message) {
    var chatBox = document.getElementById("chat-box");
    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message");
    messageDiv.classList.add("opponent-message"); // 새로운 클래스 추가

    var bubbleDiv = document.createElement("div");
    bubbleDiv.classList.add("bubble");
    bubbleDiv.innerText = message;

    var timeSpan = document.createElement("span");
    timeSpan.classList.add("time");
    timeSpan.innerText = getCurrentTime();

    messageDiv.appendChild(bubbleDiv);
    messageDiv.appendChild(timeSpan);
    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight; // 스크롤 맨 아래로 이동
}


function generateRandomMessage() {
    var messages = [
        "안녕하세요!",
        "오늘 날씨가 참 좋네요.",
        "저도 그렇게 생각해요.",
        "네, 알겠습니다.",
        "어떤 영화를 보는 게 좋을까요?"
    ];
    return messages[Math.floor(Math.random() * messages.length)];
}

function getCurrentTime() {
    var now = new Date();
    var hours = now.getHours();
    var minutes = now.getMinutes();
    var ampm = hours >= 12 ? "오후" : "오전";
    hours = hours % 12;
    hours = hours ? hours : 12; // 0시를 12시로 표시
    minutes = minutes < 10 ? "0" + minutes : minutes;
    var timeString = ampm + " " + hours + ":" + minutes;
    return timeString;
}

function goBack() {
    window.history.back();
}


/* 파일 업로드 */
function previewImages(event) {
    var container = document.getElementById('image-preview-container');
    container.innerHTML = ''; // 기존 이미지 제거
    
    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var image = document.createElement('img');
        image.src = URL.createObjectURL(files[i]);
        image.alt = '이미지 미리보기';
        image.style.maxWidth = '200px';
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

var tagButtons = document.querySelectorAll('.tag-button');

tagButtons.forEach(function(button) {
    button.addEventListener('click', function() {
        button.classList.toggle('selected');
    });
});

// 이미지 슬라이드 
var slideIndex = 0;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("reservation-image-slide");
    if (n >= slides.length) {slideIndex = 0}
    if (n < 0) {slideIndex = slides.length - 1}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndex].style.display = "block";
}