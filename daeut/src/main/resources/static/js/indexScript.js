// 이미지 슬라이드
$(function() {
    const swiper = new Swiper('.swiper', {
        direction: 'horizontal',         
        loop: true,                         
        autoplay: {                         
            delay: 4000,
            disableOnInteraction: false,        
        },
        speed: 1000,                        
        slidesPerView: 1,                   
        spaceBetween: 0,                    

        pagination: {
            el: '.swiper-pagination',
            type:   'bullets',
            clickable: true,
        },
      });
})


// 부트스트랩
window.addEventListener('DOMContentLoaded', event => {
    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74,
        });
    };
    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});


// 버튼박스
function showService(option) {
    // 모든 출력 요소를 숨김
    document.querySelectorAll('.output').forEach(function(el) {
        el.style.display = 'none';
    });
    // 해당하는 출력 요소를 표시
    document.getElementById('output' + option.charAt(option.length - 1)).style.display = 'block';
}


// 날씨
const API_KEY = ef8952bfbab9356b5066de2f01ab56c1;
window.onload = function() {
    // 실행할 코드 작성
    console.log('페이지가 로드되었습니다.');

    navigator.geolocation.getCurrentPosition(success);
};

// 성공시
const success = (position) => {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;

    getWeather(latitude, longitude);
}

const tempSection = document.querySelector('.temperature');
const placeSection = document.querySelector('.place');
const descSection = document.querySelector('.description');
const iconSection = document.querySelector('.icon');

const getWeather = (lat, lon) => {
    fetch(
      `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`
    )
      .then((response) => {
        // json 변환
        return response.json();
      })
      .then((json) => {
        const temperature = json.main.temp;
        const place = json.name;
        const description = json.weather[0].description;
  
        tempSection.innerText = temperature;
        placeSection.innerText = place;
        descSection.innerText = description;
      })
      .then((json) => {
        const icon = json.weather[0].icon;
        const iconURL = `http://openweathermap.org/img/wn/${icon}@2x.png`;
  
        iconSection.setAttribute('src', iconURL);
      })
      .catch((error) => {
        alert(error);
      });
  }
// 실패시
const fail = () => {
    console.log('좌표를 받아 올 수 없습니다');
}