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

function previewImagesUpdate(event) {
    var container = document.getElementById('image-preview-container');
    container.innerHTML = ''; // 기존 이미지 제거
    
    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var image = document.createElement('img');

        var fileNo = getFileNoFromServer(); // 서버로부터 파일 번호를 가져오는 함수
        image.src = '/file/img/' + fileNo; // Thymeleaf 코드가 이미 서버 측에서 해결됐다고 가정
        image.alt = '이미지 미리보기';
        image.style.maxWidth = '200px';
        image.style.marginTop = '10px';
        container.appendChild(image);
    }
}

// 파일 번호를 가져오는 함수
function getFileNoFromServer() {
    let fileNo = "[[${service.fileNo}]]"
    return fileNo;
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

// 태그 클릭 이벤트
var tagButtons = document.querySelectorAll('.tag-button');

tagButtons.forEach(function(button) {
    button.addEventListener('click', function() {
        button.classList.toggle('selected');
    });
});

// 이미지 슬라이드 
var slideIndex = 0;

// 페이지 로딩이 완료되면 실행
window.onload = function() {
    showSlides(); // 슬라이드 표시
};

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("reservation-image-slide");
    if (slides.length > 0) {
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slideIndex].style.display = "block";
    } else {
        console.log("reservation-image-slide 클래스를 가진 요소가 없습니다.");
    }
}

// 엔터키로 채팅보내기
function enterSend(e){
    if(e.keyCode == 13) sendMessage()
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

// 캘린더 
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth'
    });
    calendar.render();
  });

// 캘린더 나오게 하기 
document.addEventListener('DOMContentLoaded', function () {
    const calendarToggleBtn = document.querySelector('.reservation-calender');
    let calendarVisible = false;
    let calendarDiv;

    function toggleCalendar() {
        if (calendarVisible) {
            calendarDiv.style.display = 'none';
            calendarVisible = false;
        } else {
            calendarDiv.style.display = 'block';
            calendarVisible = true;
        }
    }

    calendarToggleBtn.addEventListener('click', function () {
        if (!calendarDiv) {
            calendarDiv = document.createElement('div');
            calendarDiv.id = 'calendar';
            calendarDiv.style.position = 'absolute';
            calendarDiv.style.width = '306px'; // 캘린더의 가로 길이
            calendarDiv.style.top = 'calc(100% + 10px)'; // 캘린더의 아래에 10px 여백 추가
            calendarDiv.style.left = '0';
            calendarDiv.style.display = 'none';
            calendarDiv.style.zIndex = '1000'; // 캘린더가 위에 나타나도록 zIndex 설정
            
            const calendar = new FullCalendar.Calendar(calendarDiv, {
                initialView: 'dayGridMonth', // 월별 달력으로 설정
                dayMaxEventRows: true, // 여러 이벤트를 한 줄로 표시
                events: [
                    // 캘린더에 표시할 이벤트를 추가하세요 (예: { title: '이벤트 이름', start: '시작 일자' })
                ]
            });

            // 캘린더를 삽입할 위치
            const calendarContainer = document.getElementById('calendarContainer');
            calendarContainer.appendChild(calendarDiv);

            calendar.render();
        }
        toggleCalendar();
    });
});