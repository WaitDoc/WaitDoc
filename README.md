# 🏣 기다리닥 (WaitDoc)
> 병원 원격 서비스 이용 프로젝트 </br>
> 개발 기간 : 4주 (2023.6 ~ 2023.7)</br>
> 프로젝트 유형 : 4인 팀 프로젝트</br>
</br>
</br>

## 서비스 소개
### 병원 대기시간을 최소화 시켜주는 사용자 편의 플랫폼

<img src="https://velog.velcdn.com/images/heeco/post/ae6da74d-3b0c-45eb-b3fb-b3b91a3a75ca/image.PNG" />

우리 모두의 삶은 무수히 많은 '기다림'으로 가득 차 있습니다.  </br>
어떤 기다림이든 간에, 그것은 종종 우리에게 시간을 낭비하게 만들고, 스트레스를 주며, 때로는 우리의 하루를 더욱 지치게 만듭니다.</br>

* 기다리닥 = 기다림 + Doctor</br>

`기다리닥`은 병원 진료 대기 시간을 최소화하고, 그 동안의 공백 시간을 최대한 활용할 수 있도록 돕는 웹 서비스입니다.</br>
</br>
</br>

## 팀원 소개 및 역할

<div align=center> 
  <br>
    <table>
        <tr>
          <td align="center">신희수</td>
          <td align="center">양혜정</td>
          <td align="center">이명준</td>
          <td align="center">조정빈</td>
        </tr>
        <tr>
          <td align="center"><a href="https://github.com/Heeesuu" target="_blank">@Heeesuu</a></td>
          <td align="center"><a href="https://github.com/39-Y" target="_blank">@Y39</a></td>
          <td align="center"><a href="https://github.com/Cualone" target="_blank" width="160">@Cualone</a></td>
          <td align="center"><a href="https://github.com/jojeongbin" target="_blank">@jojeongbin</a></td>
        </tr>
        <tr>
          <td align="center">Backend</td>
          <td align="center">Backend</td>
          <td align="center">Backend</td>
          <td align="center">Backend</td>
        </tr>
        <tr>
          <td align="center">관리자, 원격줄서기,</br> 블랙리스트, 알림관리</td>
          <td align="center">병원 API, 검색, 태그,</br>병원 추천 관리</td>
          <td align="center">위치 API, 로그인, </br>서버 관리</td>
          <td align="center">채팅, 게시물, </br>회원정보 관리</td>
        </tr>
  </table>
</div>
<br>
<br>


## 사용한 기술 스택

### Front-End

<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/tailwindcss-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white">
<img src="https://img.shields.io/badge/daisyui-5A0EF8?style=for-the-badge&logo=daisyui&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">


### Back-End

<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">
<img src="https://img.shields.io/badge/Springsecurity-3CB371?style=for-the-badge&logo=Springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/JPA-808080.svg?style=for-the-badge&logo=Hibernate&logoColor=white">
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/Apache Kafka-845135?style=for-the-badge&logo=Apache Kafka&logoColor=white">


그 외: 병원 공공데이터 API, 카카오 위치 API, Spring Oauth 2.0, 네이버 Object Storage, 네이버 클라우드 플랫폼
### Distribution

<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/nginx-%23009639?style=for-the-badge&logo=nginx&logoColor=white">
<img src="https://img.shields.io/badge/Jenkins-2456ED?style=for-the-badge&logo=Jenkins&logoColor=white">

### Editor

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

### Communication

![github](https://img.shields.io/badge/github-181717.svg?style=for-the-badge&logo=github&logoColor=white) 
![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![discord](https://img.shields.io/badge/discord-5865F2.svg?style=for-the-badge&logo=discord&logoColor=white) 

### ETC

- **Authentication**: 카카오, 네이버 로그인 API

- **Image Processing**: CDN+
</br>
</br>


## 주요 기능 및 설명

#### 시작 페이지 & 로그인
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/5c46afb5-7dde-43f9-8fb5-2ea4af7840bb)

</br>
</br>

#### 병원 검색 기능
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/1b830342-f7d8-48ba-877b-3dd45a6f9654)


</br>

* 국립 중앙 의료원 - 전국 병.의원 찾기 서비스 오픈 API 사용
* 25개 진료과목 외 5가지 추가 카테고리를 통해 병원 검색 가능
* 카테고리 다중 선택 가능
* 사용자 위치와 가까운 순서대로 검색 결과 제공
* 사용자 위치가 제공되지 않을 시 이름 순으로 검색 결과 제공

</br>
</br>


#### 인근 병원 조회
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/9b88ccc0-c7e3-42f2-adf2-2c227ade1d9c)

</br>

* 카카오 맵 API 사용
* 사용자 인근의 가까운 약국과 병원 위치 기본 제공
* 위치 검색을 통해 약국과 병원 위치 검색 가능

</br>
</br>

#### 원격 줄서기 & 알림
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/92b51583-f55a-4d6e-8907-1324228ecbb7)
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/bded78fb-4dc4-4105-a54f-cc02631daf65)

</br>

* Apache Kafka를 사용하여 원격 줄서기 기능을 제공
* 사용자는 이를 통해 실시간으로 원격 줄서기 가능
* Kafka를 통해 전달된 줄서기 요청은 신속하게 처리되고 대기열에 추가됨
* 첫번째, 5번째 순서마다 알림을 전송

</br>
</br>

#### 병원 상세페이지 & 병원 관리자 신청
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/3de13a5a-7d83-4ad0-89ee-0b213a628ea6)

</br>

* 병원 관리자를 신청하여 줄서기 대기를 직접 관리 가능

</br>
</br>

#### 줄서기 목록 & 블랙리스트 처리
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/0336f374-b47b-4827-ae66-a6f79ae95904)

</br>

* 병원 관리자 권한을 부여 받으면 줄서기 목록 관리 가능
* 줄서기 후 방문하지 않을 시 Noshow 버튼으로 미방문 횟수 카운트 가능
* 미방문 횟수 3회가 되면 사용자는 해당 병원에 원격 줄서기 불가능

</br>
</br>

#### 1:1채팅 - 병원 문의
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/14e44828-fb26-4bbf-8087-5b0244414fb0)

</br>

* 병원 관리자 (상담사) - 사용자 간 1:1 채팅을 통한 문의 기능
* 병원 관리자 권한을 가지면 병원 문의 관리 기능
* 멀티 유저간의 상호작용과 실시간 통신가능, 원활한 의사소통과 실시간 업데이트 제공

</br>
</br>

#### 모바일 서류 등록
![image](https://github.com/WaitDoc/WaitDoc/assets/103115883/a0957e92-590f-4809-8166-6b6f34db609e)

</br>

* 모바일로 필요한 서류를 저장하고 사용 가능
* 상세정보를 통해 등록 날짜, 제목, 내용, 등록한 사진 확인 가능

</br>
</br>


---





