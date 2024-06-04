/**
 * SweetAlert
 * @param {제목} title 
 * @param {내용} text 
 * @param {아이콘} icon 아이콘 종류 : success,error,warning,info,question
 */
function sweetAlert(title, text, icon) {
    Swal.fire({
        title: title,
        text: text,
        icon: icon          
    })
}


/**
 * 
 * @param {제목} title 
 * @param {내용} text 
 * @param {아이콘} icon 아이콘 종류 : success,error,warning,info,question
 * @param {확인버튼텍스트} confirmButtonText 
 * @param {콜백함수} callback 
 */
function sweetConfirm(title, text, icon, confirmButtonText, callback) {
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: confirmButtonText
      }).then((result) => {
        if (result.isConfirmed) {
            callback()
        }
      });
}