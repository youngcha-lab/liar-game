@startuml
!pragma layout smetana

actor 유저 as user
participant 프론트 as fe
participant 서버 as server

title 유저 쿠키 있는 참가자가 방에 입장

autonumber
user -> user: 방 링크 주소창에 입력
user -> fe: 방 입장
fe -> fe: 유저 코드 쿠키 있음
fe -> server: 유저 정보 조회 (GET /api/user)
alt 방의 유저가 맞음
    server -> fe: 방의 유저가 맞음
    fe -> user: 방 입장 완료
else 해당 방의 유저가 아님
    server -> fe: 방의 유저가 아님 (다른 방의 유저거나 잘못된 쿠키)
    fe -> user: 닉네임 생성 화면으로 이동
    user --> fe: 닉네임 생성 후 참가 버튼 클릭
    fe --> server: 유저 생성 및 방에 참가 요청 (POST /api/user)
    server --> server: 새로운 유저 생성
    server --> fe: 유저 토큰 쿠키 전달
    fe --> fe: 유저 토큰 쿠키 저장
    fe --> user: 방 입장 완료
end

@enduml