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