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
// document.addEventListener('DOMContentLoaded', function() {
//     var calendarEl = document.getElementById('calendar');
//     var calendar = new FullCalendar.Calendar(calendarEl, {
//       initialView: 'dayGridMonth',
//       height: '700px', // calendar 높이 설정
//       expandRows: true, // 화면에 맞게 높이 재설정
//       slotMinTime: '08:00', // Day 캘린더에서 시작 시간
//       slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
//       // 해더에 표시할 툴바
//       headerToolbar: {
//         right: 'prev,next today',
//       },
//       initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
//       selectable: true, // 달력 일자 드래그 설정가능
//       nowIndicator: true, // 현재 시간 마크
//       dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
//       locale: 'ko', // 한국어 설정
//       // 이벤트 
//       events: [
//         {
//             title: 'Meeting',
//             start: '2024-05-12T10:30:00',
//             end: '2024-05-12T12:30:00'
//         }
//       ]
//     });
//     calendar.render();
//   });

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
            calendarDiv.style.width = '306px'; 
            // calendarDiv.style.top = 'calc(100% + 10px)';
            calendarDiv.style.left = '0';
            calendarDiv.style.display = 'none';
            calendarDiv.style.zIndex = '1000'; 
            
            const calendar = new FullCalendar.Calendar(calendarDiv, {
                initialView: 'dayGridMonth', 
                dayMaxEventRows: true, 
                locale: 'ko', // 한국어 설정
                events: [
                   
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

// 캘린더 DB 연동
// Frontend JavaScript
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        height: '700px', // calendar 높이 설정
        expandRows: true, // 화면에 맞게 높이 재설정
        slotMinTime: '08:00', // Day 캘린더에서 시작 시간
        slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
        headerToolbar: {
            right: 'prev,next today',
        },
        selectable: true, // 달력 일자 드래그 설정가능
        nowIndicator: true, // 현재 시간 마크
        dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
        locale: 'ko', // 한국어 설정
        events: function(fetchInfo, successCallback, failureCallback) {
            fetch('/reservations')
                .then(response => response.json())
                .then(data => {
                    var events = [];
                    data.forEach(reservation => {
                        events.push({
                            title: 'Reservation', // 예약을 나타내는 제목
                            start: reservation.reg_date, // 예약의 등록일자를 시작 시간으로 사용
                            allDay: true // 하루 종일 예약으로 설정
                        });
                    });
                    successCallback(events); // 캘린더에 이벤트 추가
                })
                .catch(error => {
                    console.error('Error fetching reservations:', error);
                    failureCallback(error);
                });
        }
    });
    calendar.render();
});

// 캘린더 DB 연동2 
// $(function () {
//     var request = $.ajax({
//         url: "/full-calendar/calendar-admin",
//         method: "GET",
//         dataType: "json"
//     });

//     request.done(function (data) {
//         var calendarEl = document.getElementById('calendar');

//         var calendar = new FullCalendar.Calendar(calendarEl, {
//             initialDate: new Date(),
//             initialView: 'dayGridMonth',
//             events: data
//         });

//         calendar.render();
//     });

//     request.fail(function( jqXHR, textStatus ) {
//         alert( "Request failed: " + textStatus );
//     });
// });


