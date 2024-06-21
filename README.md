# 프로젝트 : DaEut
![logo_w (1)](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/927fa3b1-b3c3-4378-a5d9-838d4db9d9db)
<br>
(시연 영상 링크)

## 목차
**1. 프로젝트 개요**
<br>
- 프로젝트 주제
- 주제 선정 배경
- 기획 의도
- 활용방안 및 기대효과
      
**2. 프로젝트 구조**
<br>
- 주요 기능
- 메뉴 구조도
- Flow Chart
    
**3. 프로젝트 팀 구성 및 역할**
<br>

**4. 프로젝트 수행절차 및 방법**
<br>
- 수행 절차
- 수행 방법 
    
**5. 프로젝트 수행 경과**
<br>
- 요구사항 정의서
- 기능 정의서
- ERD
-  테이블 정의서
-  화면 설계서
- 프로젝트 실제 화면 UI
  
**6. 핵심기능 코드 리뷰**
<br>
- 기능 목표
- 실시간 채팅 구현 과정
- 개선할 점
     
**7. 자체 평가 의견**
<br>
- 개별 평가
- 종합 평가
<br><br>
# 1. 프로젝트 개요
## 1-1. 프로젝트 주제<br>
- 1인 가구를 위한 종합 서비스 플랫폼
## 1-2. 주제 선정 배경<br>
- 증가하는 1인 가구와 그에 따른 1인 가구를 위한 서비스의 수요 증가.
- 외부 활동과 집을 가꾸는 일을 전부 혼자 부담해야 하는 1인 가구를 위한 서비스를 제공.
- 따라서 청소 외의 방역, 빨래, 보안 등 1인 가구에게 필요한 서비스를 결합한 맞춤형 통합 플랫폼을 기획.
## 1-3. 기획 의도<br>
- 청소 및 생활 서비스와 더불어 팁 게시판을 제공하여 사용자의 생활 품질을 향상시키고자 함.
- 현재 존재하는 청소 서비스와 차별점을 두기 위해 다양한 패키지 옵션과 팁 게시판 기능을 구현하여 활용도와 사용자의 만족도를 충족.
- 관리자에게는 효율적인 서비스 관리를 통해 서비스 품질을 높일 수 있도록 보조.
- 이를 통해 사용자는 더 나은 생활을 유지할 수 있으며, 플랫폼은 신뢰성과 지속성을 확보 가능.
## 1-4. 활용방안 및 기대효과<br>
- 다양한 생활 패키지 서비스 제공을 통해 합리적인 가격을 서비스에게 제공하여, 청소 외의 여러가지 서비스를 한 곳에서 예약하고 관리할 수 있도록 한다.
- 생활 팁 게시판 및 리뷰와 댓글을 통해 사용자간 정보를 교류하고 소통한다.
- 실시간 날씨 정보로 사용자는 필요한 정보를 얻는다.
- 사용자와 파트너간 채팅으로 상세하고 즉각적인 Q&A를 가능하게 하여 원활한 소통을 지원한다.
<br><br>
# 2. 프로젝트 구조
### 2-1. 주요 기능<br>
#### 핵심 기능<br>
1. 파트너 : 유저 간의 채팅
2. 회원의 서비스 예약
#### 기타 기능<br>
1. 게시판 기능
2. 페이징 처리
3. 로그인 및 회원가입
    - 소셜 로그인 및 회원가입
    - 회원가입 시 중복 확인
4. 날씨 정보 출력
5. 결제 처리
6. 달력을 통한 일정 관리
#### 서비스<br>
유저는 서비스와 해당 서비스의 파트너를 선택해서 예약을 요청할 수 있고, 파트너는 자신의 일정을 조회하고 유저와의 상담을 할 수 있다.
- 세부 기능
    - 입력값
        - 날짜
        - 위치
        - 전화번호
        - 예약자 이름
    - 서비스
        1. 쿠폰 발급
            - 첫가입 N원
            - 생일 쿠폰 등
        2. 서비스 범위
            - 패키지
        3. 1(파트너) : N(사용자) 채팅 
            - 상세 일정 관리
            - 상세 Q & A
        4. 주문 / 결제
            - 예약 취소 및 환불 처리
       5. 리뷰
            - 등록
            - 별점
            - 썸네일
#### 관리자<br>
관리자는 유저와 파트너, 게시판, 서비스 예약을 관리하고 환불을 처리할 수 있다.
- 세부 기능
    - 팁 게시글 관리
        - 게시글 삭제
        - 댓글 삭제
    - 회원 관리
        - 쿠폰 발급
    - 서비스 관리
        - 예약 관리
        - 환불 관리
    - 파트너 관리
        - 파트너 승인
#### 게시판<br>
사용자들이 서로 생활 팁을 공유하며 소통할 수 있는 게시판이다.
- 세부 기능
    - 작성, 수정, 삭제
    - 조회수
    - 좋아요
    - 키워드 검색
    - 댓글
#### 로그인 및 마이페이지<br>
소셜 로그인 기능과 사이트 자체 가입을 할 수 있고, 시스템 비밀번호를 통해 관리자 가입이 가능하다. 마이 페이지를 이용하여 유저는 나의 정보를 변경하고 예약을 관리하며 리뷰와 예약을 확인할 수 있다. 파트너는 자신의 정보를 변경하고 나의 일정을 관리하며 자신의 리뷰를 볼 수 있다. 또한 관리자는 자신의 페이지에서 유저와 파트너, 게시물과 리뷰를 한 번에 관리할 수 있다.
- 세부기능
    - 회원
        - 카카오 로그인, 회원가입
    - 파트너
        - 사이트 자체 로그인, 회원가입
            - 아이디 중복 확인
    - 마이페이지
        - 유저
            - 사용자 정보 변경
            - 내 예약 관리
            - 좋아요한 글 모아 보기
        - 파트너
            - 사용자 정보 변경
            - 일정 관리
            - 내 리뷰 보기
        - 관리자
           - 게시글 및 리뷰 관리
           - 회원 및 파트너 관리
#### ETC<br>
- 날씨별 생활 정보 출력
<br><br>
### 2-2. 메뉴 구조도<br>
<details>
    <summary>🧊메뉴 구조도</summary>

![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/f1355d61-d788-43e0-8ffc-f6b3e67208ab)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/a2df97b8-eead-4a6c-8ea8-87130176601f)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/04e0677e-70e9-4c38-996b-5431d59fa3bf)


</details>

### 2-3. 플로우 차트<br>
<details>
    <summary>🧊플로우 차트</summary>

![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/630e5a14-d03a-4164-bcea-9b7140f57211)

</details>

<br><br>

# 3. 프로젝트 팀 구성 및 역할
## 팀원<br>
- 인원 : 5명<br>
- 반예진
    - 팁 게시판 구현 / 게시판 페이지 디자인
    - 게시판 기능 (등록 / 수정 / 삭제 / 목록 / 추천 / 조회수 / 댓글)<br>
- 윤준호
    - 유저 페이지, 사이드 바, ERD 설계 / 유저 전체 페이지 디자인 및 구현 (정보 변경, 예약 조회, 리뷰 작성, 파트너 신청)
    - 유저 및 파트너 사이드 바 구현
    - 전체 DB 설계 및 테이블 정의서 작성<br>
- 이종식
    - 메인 페이지, 예약, 결제, 장바구니 디자인 구성
    - 예약 게시글 목록, 등록, 수정, 삭제 구현
    - 장바구니 목록, 등록, 삭제 구현
    - 결제 및 예약 서비스 구현
    - 환불 처리 및 환불 승인 처리 구현
    - 채팅방 및 채팅 기능 구현<br>
- 정다운
    - 로그인 기능(Security5를 통한 기능 구현 & 카카오 소셜 로그인 기능 구현), 이메일 인증, 관리자 페이지
    - 로그인, 회원가입, 관리자 페이지 디자인
    - 기존 팁 게시판에 관리자 권한을 부여하여 관리
    - 관리자의 회원, 파트너, 예약 관리 (목록 / 조회 / 수정 / 삭제)          
    - 구글 이메일 인증을 통해 비밀번호 찾기 기능 구현 <br>
- 황다정
    - 파트너 페이지의 정보 조회 수정, 리뷰 및 자신의 예약 보기 구현
    - Full Calendar API를 이용해 예약 게시글의 캘린더
    - 예약 세부 페이지의 세부 사항 구현(별점 및 리뷰 시스템 구현)
    - 채팅, 예약 게시글 목록, 예약 게시글 세부 페이지, 에러 페이지 디자인<br>
<br><br>

# 4. 프로젝트 수행절차 및 방법
## 4-1. 프로젝트 수행 절차<br>
<details>
    <summary>🧊프로젝트 수행 절차</summary>
      
![간트차트](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/793b4cd2-0d38-44c0-8cdf-1179f4190cd1)

</details>
<br>

## 4-2. 수행방법<br>
- 사용 언어 <br>
  + <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/CSS-1572B6?style=flat&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat&logo=jquery&logoColor=white0">
- 프레임워크 <br>
  + <img src="https://img.shields.io/badge/BootStrap-7952B3?style=flat&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot 2.7.17-6DB33F?style=flat&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-6DB33F?style=flat&logo=MyBatis&logoColor=white"> 
- 개발도구 <br>
  + <img src="https://img.shields.io/badge/openjdk 17-686767?style=flat&logo=openjdk&logoColor=black"/> <img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=flat&logo=visualstudiocode&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
- 라이브러리 <br>
  + <img src="https://img.shields.io/badge/Lombok-6DB33F?style=flat&logo=Lombok&logoColor=white"> <img src="https://img.shields.io/badge/Devtools-6DB33F?style=flat&logo=springboot&logoColor=white">
- 사용 DB : <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/> 
- 참조 API : <img src="https://img.shields.io/badge/ProtOne-007396?style=flat&logo=Iamport&logoColor=white"> <img src="https://img.shields.io/badge/OpenWeather-007396?style=flat&logo=Zxing&logoColor=white"> <img src="https://img.shields.io/badge/FullCalendar-007396?style=flat&logo=Zxing&logoColor=white">
- 협업 Tools : <img src="https://img.shields.io/badge/trello-0052CC?style=flat&logo=trello&logoColor=white"/> <img src="https://img.shields.io/badge/github-181717?style=flat&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/GoogleDrive-4285F4?style=flat&logo=GoogleDrive&logoColor=white"/> <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat&logo=Figma&logoColor=white"/>
<br><br>

# 5. 프로젝트 수행 경과
## 5-1. 요구사항 정의서<br>
<details>
    <summary>🧊요구사항 정의서</summary>

![요구사항정의서](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/a3fbe5a1-e714-48f1-9be0-5403b7d86da2)

</details>

## 5-2. 기능 정의서<br>
<details>
    <summary>🧊기능 정의서</summary>

![기능정의서1](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/ec5b18fd-0cca-4b2a-a7de-6ed16b23eebf)
![기능정의서2](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/e143306b-da35-4760-affc-b6c81b7abff2)
![기능정의서3](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/ca51b062-97c3-4e45-828f-69f82c988bac)
![기능정의서4](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/96597457-bd5d-4d24-8210-ed9044299409)
      
</details>

## 5-3. ERD<br>
<details>
    <summary>🧊ERD</summary>
      
![ERD](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/fd51df14-9a63-4f8e-ac67-ce2d318c5397)

</details>

## 5-4. 테이블 정의서<br>
<details>
    <summary>🧊테이블 정의서</summary>

![테이블정의서1](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/eef4772e-d57c-480f-a02d-bda8aa960432)
![테이블정의서2](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/58f48755-6f59-4552-92d5-e32cd2f34b2d)
![테이블정의서3](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/106d60af-d743-4801-be90-906f5c6806b3)
![테이블정의서4](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/c1a9e28b-1728-45fa-aa97-7d2e0ba40a04)
![테이블정의서5](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/70cbd362-cddb-4ff9-aaeb-b5a793be32dc)
![테이블정의서6](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2bc0ac02-79bc-4e52-958a-a0b8dcf23fef)
![테이블정의서7](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/c862e741-ade5-4737-81a0-fa21b2302ce6)
![테이블정의서8](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/62bacddd-82a1-4ef9-b41c-3892c27a7cc6)
![테이블정의서9](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/da3e9d6e-41c5-467c-848b-490f65bc5d06)
![테이블정의서10](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2f247665-5f20-4ab2-829c-9cb1a85e5308)
![테이블정의서11](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/57e929d6-bb40-442c-8731-cc78eb9332a4)
![테이블정의서12](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/568bb83e-589e-4416-9f98-02f5eb7c9734)
![테이블정의서13](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/cd3471d7-652b-4110-af5c-116de87d9b43)
![테이블정의서14](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b0cea7bc-4e6e-4287-a1cf-fb0f0f40d90c)
![테이블정의서15](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/5df47404-f4ef-4476-b58b-76f083159b2c)
![테이블정의서16](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/de409ace-efc5-450e-91a3-13a2ff6d6ec7)
![테이블정의서17](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/ff533ac0-d700-4b83-a807-48ef43d5193c)


</details>

## 5-5. 화면 설계서<br>
<details>
    <summary>🧊메인 및 로그인</summary>

![화면설계도1](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/8ca60a22-11a0-41b3-82a0-116c82beb39a)
![화면설계도2](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/065abef7-d523-4173-b5ee-28874ba25bdd)
![화면설계도3](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/f4803324-b4ea-45a5-8bbb-58f41d5ebe18)
![화면설계도4](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/8cba0213-318e-42bc-a2c3-2509a6116b2a)
![화면설계도5](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/abf0ea71-2095-4a3f-a0fe-557bcc321f6a)
![화면설계도6](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/66a61248-7d2f-47e3-9f38-dfa8fc4b1457)
![화면설계도7](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/61476393-6e08-40f7-93fb-f78b925a5faf)

</details>

<details>
    <summary>🧊예약 및 결제, 채팅</summary>

![화면설계도9](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2cc78008-9f58-40f4-99d7-0094d2f088fb)
![화면설계도10](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/fca6c74d-5343-4fde-b9d2-18efbf7462ee)
![화면설계도8](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/40de2ee5-23fb-4c35-bab6-cb1d65ce59cc)
![화면설계도11](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/0f099d58-7c3a-49ec-8e30-fdbdfee09000)
![화면설계도12](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/73c622b1-a516-4f16-91f7-49642a63ee61)
![화면설계도13](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/6c20165e-a2dc-403b-af67-e5ec3cabfb3b)
![화면설계도19](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/46920253-fd07-494e-b79b-c7e37f95190a)

</details>

<details>
    <summary>🧊팁 게시판</summary>

![화면설계도37](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/4489ecf4-52a0-44b8-978b-3c7f6cb7bcd7)
![화면설계도38](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/fc4ecfef-ce6f-4880-9cb9-3b1a79f35c8e)

</details>

<details>
    <summary>🧊사용자</summary>

![화면설계도14](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2396c950-5413-4778-8f62-d32df8de861b)
![화면설계도15](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/e960ea61-09bd-4575-90a3-c478e95ee9d6)
![화면설계도16](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/4ba6997d-07b7-4e9c-9a30-3ba7a0901127)
![화면설계도17](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2f3eefe5-1811-40d7-b325-c7045db935b2)
![화면설계도18](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/cba42d89-1d83-465d-9f82-8e4419bbd232)
![화면설계도21](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/a0405bea-e025-4fd2-9a09-93140a7f7651)
![화면설계도22](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/45a36681-18a7-46dc-b8b6-f9e47ad5a655)
![화면설계도23](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/3504d182-5a88-480d-8e3b-7cc932b86a15)

</details>

<details>
    <summary>🧊파트너</summary>

![화면설계도31](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/8248641d-6971-4b8b-90d9-5ac91a3bc8e8)
![화면설계도34](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/86a4958d-5279-475a-acf6-09c0ba95b4ce)
![화면설계도35](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/32f390f8-28d3-45a8-bdff-fe0db02f7720)
![화면설계도36](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/560b2a23-a9d3-40ba-95f2-7df32fcb4bb0)

</details>

<details>
    <summary>🧊관리자</summary>

![화면설계도20](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/353cf090-4119-4b21-8508-4b6fe2dad272)
![화면설계도24](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/cec4088a-329e-4051-a5f2-44a6cf7912fe)
![화면설계도25](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/12f40848-4443-48b2-bc6f-a2054d62bdb0)
![화면설계도26](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2084c6ab-d6fd-4bd3-abc7-bb1dcfc1cb5e)
![화면설계도27](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/795dc231-4749-4fbb-a9e5-685c0ecda655)
![화면설계도28](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/18cf494a-4851-41b6-bfc7-f0d575429f2f)
![화면설계도29](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/254c8c26-c023-4973-adc7-a7d70a5c8ccd)
![화면설계도30](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/1e5a1ff2-c680-4856-8aef-56c1722a1b01)
![화면설계도32](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/063f96fd-f442-4c73-8f56-c1f9d2f3f821)

</details>

<details>
    <summary>🧊에러 페이지</summary>

![화면설계도39](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/fb9a7514-4e62-42fa-8884-c64b1d58d980)
![화면설계도40](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/6310b5d0-578f-4f08-8ebd-5f9e91106a47)
![화면설계도41](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/edf8b296-abe7-4c37-884c-3ec9d8984e59)

</details>

## 5-6. 실제 화면<br>
<details>
    <summary>🧊메인 및 로그인</summary>
      
![실제화면8](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/e8f5eead-14b5-46da-a285-89a302f51963)
![실제화면9](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/c963f11f-c283-4411-ad56-c82c84ee9992)
![실제화면1](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b21e3817-c8dc-4eb3-9998-d4dfd01750a4)
![실제화면2](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/3d85d734-8e2d-42ec-b433-425757c9a694)
![실제화면3](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/fb6f02b0-ad5f-478f-8d93-d60d9f121717)
![실제화면4](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/d71dbd04-f93b-468d-a942-9cc8d128738e)
![실제화면5](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/6e4d5652-695d-4676-a5ed-bc9e29c94023)
![실제화면6](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/134a0ceb-3c86-4dc3-9e52-5e625f04c29a)
![실제화면7](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/e0758692-fbd4-4f88-aa91-d14eb5814dd4)

</details>

<details>
    <summary>🧊예약 및 결제</summary>

![실제화면10](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/5235da66-68c9-4590-827d-c3c511b8fb8a)
![실제화면11](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/9612bd51-943c-480d-bbd5-6697738b0fff)
![실제화면12](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/28d77ea8-79a4-45e4-8140-c55594b5d93c)
![실제화면13](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/19191512-57be-4b01-bcf7-7048d736b3b1)
![실제화면14](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/346c1044-f2fc-4084-a67d-c0b114309038)
![실제화면15](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/9e9560ec-c3d5-4373-97dc-7dd166eb4d7f)
![실제화면16](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b43d1a77-c3b4-46b3-a0cc-19ebaff15604)
![실제화면17](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/a4668443-a7ec-4378-b436-864e541c3e39)
![실제화면18](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/2d1be101-ed26-4ec2-b512-408579717200)

</details>

<details>
    <summary>🧊팁 게시판</summary>

![실제화면19](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/68435933-7ebc-47c8-9fe6-a792c6ca9319)
![실제화면20](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/73c66f84-968b-4853-a22c-db004c85be4d)
![실제화면22](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b6a59ccb-34b5-48c5-9cc9-7dd311938b7a)
![실제화면21](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b038bb91-b8ab-41b6-9c9d-14fef30d9931)

</details>

<details>
    <summary>🧊사용자</summary>

![실제화면23](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/636b2c11-75a2-4bd6-b2da-67b7961f8506)
![실제화면24](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/8cb90140-05f8-41ad-82b4-03422bcc5e72)
![실제화면25](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/18f41b0d-5725-4c43-a4bd-cd1ac16f5634)
![실제화면26](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/1f83eadb-f840-4ab0-b2d0-91f2b04599c9)
![실제화면28](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/1e21dfe8-5c9a-4a03-86c0-bd95b701c08f)
![실제화면30](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/02cb5072-a5c9-4f75-b177-b46fdc4e3d5e)
![실제화면31](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/3b6a15ad-bbf3-4211-a9af-f0cef52db65c)
![실제화면29](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/890a370a-8e77-471a-830d-b57b82d2c3bf)

</details>

<details>
    <summary>🧊파트너</summary>

![실제화면32](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b8c4dbfc-79f6-4c9d-8227-12e7e96e1507)
![실제화면33](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/72d616c7-cc0d-4627-9134-042e29cafeb8)
![실제화면34](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/48e1cf16-e74d-4380-894d-74b3263f0fc3)
![실제화면35](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/0c24f7aa-fe1e-4ca5-912b-2cdcfd7be76b)
![실제화면36](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/16690f19-d309-4dae-ab23-e30ec6eed49e)
![실제화면38](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/b5a61da7-7755-4352-93c5-2556806812c1)
![실제화면39](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/8ba69734-709e-4bbf-85eb-054fee1c71f7)


</details>

<details>
    <summary>🧊관리자</summary>

![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/9d2be97d-f7fe-4088-9425-da2172742105)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/7c79475c-7323-47ad-948a-d358fbfcc9d5)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/182d6be2-b6db-4b4e-a634-90ef3f48a566)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/944dabbc-aa9a-4175-8e60-3990e7b980ec)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/c7b835da-8b28-4c30-846c-55f3e67f1ee3)
![image](https://github.com/JongsikLEE01/MSA5_MainProject/assets/160221884/76f96ebf-e27a-4201-8bea-55519da9647d)


</details>
<br><br>

# 6. 핵심기능 코드 리뷰

# 7. 자체 평가 의견
## 7-1. 개별 평가<br>
- 반예진
    - 화면 설계 단계의 중요성을 크게 느꼈으며 제가 구현할 기능에서 쓰이는 게 무엇인지 정확히 알고 적용하는 게 중요하다고 느꼈습니다. 평소에 배운 것을 응용하는 부분에서 어려움이 많이 있었는데 이번 프로젝트를 계기로 응용하는 방법에 대해 조금 더 배우는 시간이 되었습니다.<br>
- 윤준호
    - 중간에 DB를 바꿀 일이 많아서 기능 구현에 어려움이 많았고, 시간이 부족하여 뺀 기능도 많아서 아쉬웠습니다. 다음부터는 예시로 쓸 페이지를 참고하여, 미리 DB가 쓰이는 부분을 체크하고 작성하는 것이 좋을 것 같습니다. 또한 Spring Security를 사용하여 작성하며 CRUD 구조와 MVC 패턴에 대해 더 자세히 알게 된 것 같습니다.<br>
- 이종식
    - 여러 api를 이용해서 기능을 구현하는 것이  재미있었고 portone을 이용해서 주문과 결제를 구현하는 것과 WebSocket을 사용해서 실시간 채팅을 구현하는 것이 가장 기억에 남았습니다. 기술적인 어려운 부분도 많았고 기간 내에 원하던 기능을 모두 구현하지 못해 아쉬운 부분도 있었지만 이런 과정들 속에서 네트워크와 WebSocket에 대해서 더 공부를 하게 되고 기간 내에 끝낼 수 있는 능력을 기를 수 있다고 생각합니다.<br>
- 정다운
    - 사용자가 파트너로 등록되기 위해서는 단순히 권한을 부여하는 것이 아니라 사용자가 파트너 신청을 하고, 관리자가 이를 승인함으로써 파트너로 등록되는 시스템을 구현하는 것이었습니다. 이러한 방식은 조금 복잡할 수 있지만, 테스트를 통해 구현되는 과정을 보며 즐거웠습니다. 초기에는 각자가 구현한 부분을 소통 없이 통합하다 보니 문제가 생기기도 했지만, 서로의 피드백을 주고 받으며 소통의 중요성을 깨달았고, 시간이 지날수록 이에 적응해 나갔습니다. 처음에는 기능을 구현하는 것에 대한 염려도 있었지만, 반복하면서 점점 자신감을 갖게 되었습니다. 또한, 소셜 로그인을 완벽하게 구현하지 못해 아쉬웠고 개발하는데 시간 분배의 중요성도 알게 되었습니다. <br>
- 황다정
    - 래퍼런스와 사이트들을 보며 프로젝트의 방향을 설계하고 페이지를 구성하는 일이 즐거웠습니다. 캘린더 기능이 쉽게 되지 않아 생각보다 오래 붙잡고 있었던 부분이 아쉽습니다. Spring을 사용한 프로젝트를 통해 많은 공부를 하게 되었습니다. 또한 예약 세부 페이지 구현 시 날짜를 클릭하면 캘린더가 새롭게 뜨는 스크립트와 CSS 구현을 하지 못한 부분이 아쉬웠습니다. 다음 기회에 부족한 부분을 채우고 더 정진하여 팀 프로젝트에 도움이 되고 싶습니다.<br>
  
## 7-2. 팀 평가
### 한계점<br>
- 서비스 예약, 실시간 채팅, 게시판과 같은 필수 기능은 모두 구현하였으나 유저의 내가 좋아하는 게시글을 모아 볼 수 있는 옵션 기능을 구현하지 못하였다.소셜 로그인 기능을 자세히 구현하지 못해 팁게시판 댓글 작성을 하지 못하고 파트너 신청이 불가하는 등 여러 제약이 걸렸다.
### 개선점<br>
- static과 같은 공통적으로 사용하는 파일 및코드와 팀원들이 개별적으로 사용하는 파일을 제대로 분리하지 않아 시간이 필요했지만, 적절한 관리를 통해서 모두 같은 개발 환경에서 프로젝트를 진행하도록 한다.
### 문제 해결 방법<br>
- 프로젝트를 설계할 때, 시간이 오래 걸려도 프로젝트 기간을 생각해 목표를 철저히 세우고 중간에 변경이 될 수 있는 부분을 최소화해야 한다고 생각하며, 틈틈이 깃허브를 통해 파일을 합치고 팀원과 소통을 원활히 할 수 있도록 하여 오류를 줄인다. 또한, 기능 구현을 위한 시간 및 역할 분배를 잘 하여 원하는 기능을 최대한 할 수 있도록 한다.

<br><br>

## 버전
- MySQL
- Java 17
- SpringBoot 3.x.x




