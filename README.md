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
- Menu Structure
- Project Structure
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

# 1. 프로젝트 개요
## 1-1. 프로젝트 주제
- 1인 가구를 위한 종합 서비스 플랫폼
## 1-2. 주제 선정 배경
- 증가하는 1인 가구와 그에 따른 1인 가구를 위한 서비스의 수요 증가.
- 외부 활동과 집을 가꾸는 일을 전부 혼자 부담해야 하는 1인 가구를 위한 서비스를 제공.
- 따라서 청소 외의 방역, 빨래, 보안 등 1인 가구에게 필요한 서비스를 결합한 맞춤형 통합 플랫폼을 기획.
## 1-3. 기획 의도
- 청소 및 생활 서비스와 더불어 팁 게시판을 제공하여 사용자의 생활 품질을 향상시키고자 함.
- 현재 존재하는 청소 서비스와 차별점을 두기 위해 다양한 패키지 옵션과 팁 게시판 기능을 구현하여 활용도와 사용자의 만족도를 충족.
- 관리자에게는 효율적인 서비스 관리를 통해 서비스 품질을 높일 수 있도록 보조.
- 이를 통해 사용자는 더 나은 생활을 유지할 수 있으며, 플랫폼은 신뢰성과 지속성을 확보 가능.

# 2. 프로젝트 구조

# 3. 프로젝트 팀 구성 및 역할
## 팀원
- 인원 : 5명
- 반예진
    - 팁 게시판 구현 / 게시판 페이지 디자인
    - 게시판 기능 (등록 / 수정 / 삭제 / 목록 / 추천 / 조회수 / 댓글)
- 윤준호
    - 유저 페이지, 사이드 바, ERD 설계 / 유저 전체 페이지 디자인 및 구현 (정보 변경, 예약 조회, 리뷰 작성, 파트너 신청)
    - 유저 및 파트너 사이드 바 구현
    - 전체 DB 설계 및 테이블 정의서 작성
- 이종식
    - 메인 페이지, 예약, 결제, 장바구니 디자인 구성
    - 예약 게시글 목록, 등록, 수정, 삭제 구현
    - 장바구니 목록, 등록, 삭제 구현
    - 결제 및 예약 서비스 구현
    - 환불 처리 및 환불 승인 처리 구현
    - 채팅방 및 채팅 기능 구현
- 정다운
    - 로그인 기능(Security5를 통한 기능 구현 & 카카오 소셜 로그인 기능 구현), 이메일 인증, 관리자 페이지
    - 로그인, 회원가입, 관리자 페이지 디자인
    - 기존 팁 게시판에 관리자 권한을 부여하여 관리
    - 관리자의 회원, 파트너, 예약 관리 (목록 / 조회 / 수정 / 삭제)
    - 구글 이메일 인증을 통해 비밀번호 찾기 기능 구현 
- 황다정
    - 파트너 페이지의 정보 조회 수정, 리뷰 및 자신의 예약 보기 구현
    - Full Calendar API를 이용해 예약 게시글의 캘린더
    - 예약 세부 페이지의 세부 사항 구현(별점 및 리뷰 시스템 구현)
    - 채팅, 예약 게시글 목록, 예약 게시글 세부 페이지, 에러 페이지 디자인

# 4. 프로젝트 수행절차 및 방법
## 4-2. 수행방법
- 사용 언어 : <img src="https://camo.githubusercontent.com/e11bf98e7a9c4795e17eaede4499d4520ed924d9a0f3162a0b10820350b405fb/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a6176612d3030373339363f7374796c653d666c6174266c6f676f3d6a617661266c6f676f436f6c6f723d7768697465" data-canonical-src="https://img.shields.io/badge/java-007396?style=flat&amp;logo=java&amp;logoColor=white" style="max-width: 100%;">
- 개발도구 : <img src="https://camo.githubusercontent.com/d625623d4b2669fff64435f7cfb76bc89822c7dbfa3cf869debf603df09457cd/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f76697375616c73747564696f636f64652d3030374143433f7374796c653d666c6174266c6f676f3d76697375616c73747564696f636f6465266c6f676f436f6c6f723d7768697465" data-canonical-src="https://img.shields.io/badge/visualstudiocode-007ACC?style=flat&amp;logo=visualstudiocode&amp;logoColor=white" style="max-width: 100%;">  
- 라이브러리 : 
- 사용 DB : 
- 참조 API : 
- 협업 Tools : <img src="https://camo.githubusercontent.com/cafe322517fa3cf031631e2e8b06992124186216aee8a1a8cc3aad086bcc6c1e/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f7472656c6c6f2d3030353243433f7374796c653d666c6174266c6f676f3d7472656c6c6f266c6f676f436f6c6f723d7768697465" data-canonical-src="https://img.shields.io/badge/trello-0052CC?style=flat&amp;logo=trello&amp;logoColor=white" style="max-width: 100%;"> <img src="https://camo.githubusercontent.com/18129633ca4fdac8b4cd138bca0bc454bb8d17637c4d186158773604a0dfedae/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6769746875622d3138313731373f7374796c653d666c6174266c6f676f3d676974687562266c6f676f436f6c6f723d7768697465" data-canonical-src="https://img.shields.io/badge/github-181717?style=flat&amp;logo=github&amp;logoColor=white" style="max-width: 100%;">
  
# 5. 프로젝트 수행 경과

# 6. 핵심기능 코드 리뷰

# 7. 자체 평가 의견

## 버전
- MySQL
- Java 17
- SpringBoot 3.x.x




