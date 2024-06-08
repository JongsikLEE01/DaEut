# 남은 할일 적어두기

### 이종식
- 예약 ~ 주문까지 수정할 부분
    - ✔ reservation.html 페이징 개수 변경
    - ✔ reservationRead.htrml 테스트 및 확인
    - userChatRoom, partnerChatRoom 페이징 처리
    - ✔ payment.html 쿠폰 css 삭제, 유효성 검사
    - ✔ 주문 취소 할 경우 예약 시간 입력 없는 경우 에러 발생
        1. not null -> null로 ddl 변경
        2. 페이지별로 null 처리 로직 필요 

- 환불 처리
    - CancelMapper.xml 기본 CRUD 생성
    - Cancel, CancelService, CancelMapper, CancelServiceImpl 생성
    - 로직 작성
    - userReservation.html, partnerReservation.html 연동
    - DB 연동 및 테스트 

### 반예진
- 팁 게시판 댓글 관련
- 팁 게시판 페이지당 보이는 게시글 9개로 변경(현재 10개)
- 팁 게시판 수정 화면에서 파일 첨부하는 기능
  
### 정다운
- 예약 삭제
- 회원 리뷰 되면 받아서 리뷰 관리 기능
- 게시판 받아서 관리..
- 파트너 파일 되면 받아서 관리 기능(+) 
- 이메일 인증
- 소셜로그인

### 윤준호


### 황다정
