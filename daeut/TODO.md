# 남은 할일 적어두기

### 이종식
- 예약 ~ 주문까지 수정할 부분
    - ✔ reservation.html 페이징 개수 변경
    - ✔ reservationRead.htrml 테스트 및 확인
    - ✔ payment.html 쿠폰 css 삭제, 유효성 검사
    - ✔ 주문 취소 할 경우 예약 시간, 주소 입력 없는 경우 에러 발생
        - not null -> null로 ddl 변경
    - ✔ 주문 시 주소 값 넘겨주기
        - payment address 컬럼 추가
    - reservation.html 썸네일 안나옴

- 환불 처리
    - ✔ CancelMapper.xml 기본 CRUD 생성
    - ✔ Cancel, CancelService, CancelMapper, CancelServiceImpl 생성
    - 로직 작성
        - userReservation.html
            - ✔ 취소 사유, 환불 계좌, 은행, 예금주 입력하는 html 추가
            - ✔ 데이터 처리
        - adminReservation에서 승인 처리 
            - 취소 한 경우 승인처리 할 수 있도록 예약 내역에 버튼 추가
        - partnerReservation.html
            - 환불 승인 된 경우 예약 내역에서 목록 출력 X

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
- 유저 리뷰 작성 기능 완성
- 유저 파트너 신청 파일 첨부 추가
- 외래키 제약 조건 넣어서 동작 확인 하기
- 유저 탈퇴 처리

### 황다정
