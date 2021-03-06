package com.youngcha.liargameapp.data

object KeywordCategoryFactory {

    fun random(): Pair<String, String> = keywordAndCategories.random()

    private val keywordAndCategories = listOf(
        /**
         * Animal
         */
        "동물" to "나무늘보",
        "동물" to "원숭이",
        "동물" to "고릴라",
        "동물" to "고라니",
        "동물" to "고양이",
        "동물" to "기린",
        "동물" to "다람쥐",
        "동물" to "토끼",
        "동물" to "늑대",
        "동물" to "말",
        "동물" to "물개",
        "동물" to "범고래",
        "동물" to "박쥐",
        "동물" to "강아지",
        "동물" to "비버",
        "동물" to "사슴",
        "동물" to "수달",
        "동물" to "여우",
        "동물" to "염소",
        "동물" to "치타",
        "동물" to "펭귄",
        "동물" to "호랑이",
        "동물" to "양",
        "동물" to "얼룩말",
        "동물" to "멧돼지",
        "동물" to "북극곰",
        "동물" to "사자",
        "동물" to "이구아나",
        "동물" to "악어",
        "동물" to "독수리",
        "동물" to "쥐",
        "동물" to "캥거루",
        "동물" to "코알라",
        "동물" to "코뿔소",
        "동물" to "코끼리",
        "동물" to "하마",
        "동물" to "고래",
        "동물" to "낙타",
        "동물" to "물소",
        "동물" to "침팬지",
        "동물" to "스컹크",
        "동물" to "닭",
        "동물" to "두더쥐",
        "동물" to "고슴도치",
        "동물" to "소",
        "동물" to "너구리",
        "동물" to "개미햛기",
        "동물" to "비둘기",
        "동물" to "참새",
        "동물" to "까마귀",
        "동물" to "오랑우탄",
        "동물" to "앵무새",
        "동물" to "팬더",
        "동물" to "표범",
        /**
         * Sports
         */
        "스포츠" to "마라톤",
        "스포츠" to "경보",
        "스포츠" to "멀리뛰기",
        "스포츠" to "높이뛰기",
        "스포츠" to "장대높이뛰기",
        "스포츠" to "창던지기",
        "스포츠" to "원반던지기",
        "스포츠" to "포환던지기",
        "스포츠" to "축구",
        "스포츠" to "농구",
        "스포츠" to "배구",
        "스포츠" to "핸드볼",
        "스포츠" to "야구",
        "스포츠" to "럭비",
        "스포츠" to "미식축구",
        "스포츠" to "당구",
        "스포츠" to "골프",
        "스포츠" to "족구",
        "스포츠" to "테니스",
        "스포츠" to "베드민턴",
        "스포츠" to "스쿼시",
        "스포츠" to "탁구",
        "스포츠" to "수영",
        "스포츠" to "수구",
        "스포츠" to "조정",
        "스포츠" to "도마",
        "스포츠" to "역도",
        "스포츠" to "태권도",
        "스포츠" to "택견",
        "스포츠" to "씨름",
        "스포츠" to "유도",
        "스포츠" to "복싱",
        "스포츠" to "킥복싱",
        "스포츠" to "레슬링",
        "스포츠" to "양궁",
        "스포츠" to "승마",
        "스포츠" to "피겨",
        "스포츠" to "쇼트트랙",
        "스포츠" to "아이스하키",
        "스포츠" to "스키",
        "스포츠" to "사이클",
        /**
         * Food
         */
        "음식" to "돼지국밥",
        "음식" to "순댓국",
        "음식" to "떡국",
        "음식" to "미역국",
        "음식" to "북엇국",
        "음식" to "사골국",
        "음식" to "수제비",
        "음식" to "된장찌개",
        "음식" to "청국장",
        "음식" to "곰탕",
        "음식" to "감자탕",
        "음식" to "삼계탕",
        "음식" to "설렁탕",
        "음식" to "육개장",
        "음식" to "부대찌개",
        "음식" to "갈치조림",
        "음식" to "김밥",
        "음식" to "떡볶이",
        "음식" to "순대",
        "음식" to "라면",
        "음식" to "오뎅",
        "음식" to "간장게장",
        "음식" to "식해",
        "음식" to "치킨",
        "음식" to "파스타",
        "음식" to "마라탕",
        "음식" to "아이스크림",
        "음식" to "돈까스",
        "음식" to "우동",
        "음식" to "초밥",
        "음식" to "쌀국수",
        "음식" to "스테이크",
        "음식" to "붕어빵",
        "음식" to "피자",
        "음식" to "양념게장",
        "음식" to "아메리카노",
        "음식" to "빵",
        "음식" to "라볶이",
        "음식" to "삼겹살",
        "음식" to "양념치킨",
        /**
         * Person
         */
        "인물" to "강동원",
        "인물" to "송강호",
        "인물" to "문재인",
        "인물" to "이명박",
        "인물" to "박근혜",
        "인물" to "김태희",
        "인물" to "전지현",
        "인물" to "이선희",
        "인물" to "김구",
        "인물" to "세종대왕",
        "인물" to "박혁거세",
        "인물" to "김연아",
        "인물" to "박지성",
        "인물" to "손흥민",
        "인물" to "히딩크",
        "인물" to "김동성",
        "인물" to "박세리",
        "인물" to "서장훈",
        "인물" to "이수근",
        "인물" to "강호동",
        "인물" to "유재석",
        "인물" to "강호동",
        "인물" to "박명수",
        "인물" to "이효리",
        "인물" to "신사임당",
        "인물" to "홍길동",
        "인물" to "임꺽정",
        "인물" to "한석봉",
        "인물" to "유관순",
        "인물" to "레오나르도 디카프리오",
        "인물" to "호날두",
        "인물" to "메시",
        "인물" to "차범근",
        "인물" to "박재범",
        "인물" to "아이유",
        "인물" to "백종원",
        "인물" to "지드래곤",
        "인물" to "봉준호",
        "인물" to "이병헌",
        "인물" to "나영석",
        "인물" to "안철수",
        "인물" to "이건희",
        "인물" to "이영애",
        "인물" to "제니",
        "인물" to "버락 오바마",
        "인물" to "박진영",
        "인물" to "이수만",
        "인물" to "김건모",
        "인물" to "이재용",
        "인물" to "일론 머스크",
        "인물" to "빌게이츠",
        "인물" to "아인슈타인",
        "인물" to "에디슨",
        "인물" to "워렌버핏",
        "인물" to "마더 테레사",
        "인물" to "오은영",
        /**
         * Job
         */
        "직업" to "가수",
        "직업" to "간호사",
        "직업" to "간호원",
        "직업" to "개그맨",
        "직업" to "프로그래머",
        "직업" to "개그맨",
        "직업" to "건축가",
        "직업" to "검사",
        "직업" to "경찰",
        "직업" to "경찰관",
        "직업" to "공무원",
        "직업" to "과학자",
        "직업" to "광부",
        "직업" to "교도관",
        "직업" to "교사",
        "직업" to "교수",
        "직업" to "군인",
        "직업" to "신부",
        "직업" to "기자",
        "직업" to "농부",
        "직업" to "대장장이",
        "직업" to "디자이너",
        "직업" to "마술사",
        "직업" to "머슴",
        "직업" to "목수",
        "직업" to "무용가",
        "직업" to "물리학자",
        "직업" to "미용사",
        "직업" to "바이올리니스트",
        "직업" to "발레리나",
        "직업" to "발명가",
        "직업" to "번역가",
        "직업" to "변호사",
        "직업" to "비서",
        "직업" to "사공",
        "직업" to "사냥꾼",
        "직업" to "사진사",
        "직업" to "상인",
        "직업" to "선장",
        "직업" to "성우",
        "직업" to "세무사",
        "직업" to "소방관",
        "직업" to "소설가",
        "직업" to "수녀",
        "직업" to "수의사",
        "직업" to "승무원",
        "직업" to "시인",
        "직업" to "아나운서",
        "직업" to "약사",
        "직업" to "어부",
        "직업" to "연구원",
        "직업" to "영양사",
        "직업" to "예술가",
        "직업" to "요리사",
        "직업" to "우주 비행사",
        "직업" to "택시기사",
        "직업" to "웨이터",
        "직업" to "의사",
        "직업" to "작사가",
        "직업" to "작곡가",
        "직업" to "재판관",
        "직업" to "정치가",
        "직업" to "정치인",
        "직업" to "지휘자",
        "직업" to "CEO",
        "직업" to "집배원",
        "직업" to "천문학자",
        "직업" to "철학자",
        "직업" to "코미디언",
        "직업" to "탤런트",
        "직업" to "통역사",
        "직업" to "파출부",
        "직업" to "판사",
        "직업" to "패션모델",
        "직업" to "평론가",
        "직업" to "화가",
        "직업" to "회계사",
        "직업" to "배우",
        /**
         * Movie
         */
        "영화" to "명량",
        "영화" to "극한직업",
        "영화" to "신과함께",
        "영화" to "국제시장",
        "영화" to "어벤져스",
        "영화" to "겨울왕국",
        "영화" to "베테랑",
        "영화" to "아바타",
        "영화" to "도둑들",
        "영화" to "암살",
        "영화" to "알라딘",
        "영화" to "택시운전사",
        "영화" to "부산행",
        "영화" to "변호인",
        "영화" to "해운대",
        "영화" to "괴물",
        "영화" to "와의 남자",
        "영화" to "인터스텔라",
        "영화" to "기생충",
        "영화" to "설국열차",
        "영화" to "내부자들",
        "영화" to "관상",
        "영화" to "아이언맨",
        "영화" to "스파이더맨",
        "영화" to "디워",
        "영화" to "트랜스포머",
        "영화" to "미션 임파서블",
        "영화" to "써니",
        "영화" to "1987",
        "영화" to "실미도",
        "영화" to "범죄도시",
        "영화" to "곡성",
        "영화" to "타짜",
        "영화" to "미녀는 괴로워",
        "영화" to "다크나이트",
        "영화" to "아저씨",
        "영화" to "전우치",
        "영화" to "킹스맨",
        "영화" to "쉬리",
        "영화" to "해리포터",
        "영화" to "스타워즈",
        "영화" to "반지의 제왕",
        /**
         * Electronics
         */
        "전자기기" to "진공 청소기",
        "전자기기" to "컴퓨터",
        "전자기기" to "모니터",
        "전자기기" to "스피커",
        "전자기기" to "이어폰",
        "전자기기" to "키보드",
        "전자기기" to "마우스",
        "전자기기" to "세탁기",
        "전자기기" to "건조기",
        "전자기기" to "텔레비전",
        "전자기기" to "스마트폰",
        "전자기기" to "전기밥솥",
        "전자기기" to "전자레인지",
        "전자기기" to "헤어드라이어",
        "전자기기" to "커피포트",
        "전자기기" to "다리미",
        "전자기기" to "식기 세척기",
        "전자기기" to "가습기",
        "전자기기" to "노트북",
        "전자기기" to "믹서기",
        "전자기기" to "전기난로",
        "전자기기" to "에어컨",
        "전자기기" to "냉장고",
        "전자기기" to "라디오",
        "전자기기" to "프린터",
        "전자기기" to "선풍기",
        "전자기기" to "토스터",
        "전자기기" to "형광등",
        "전자기기" to "안마의자",
        "전자기기" to "프로젝터",
        "전자기기" to "공기청정기",
        "전자기기" to "로봇 청소기",
        "전자기기" to "스톱워치",
        "전자기기" to "보조배터리",
        "전자기기" to "멀티탭",
        "전자기기" to "비데",
        "전자기기" to "팩스",
        "전자기기" to "마이크",
        "전자기기" to "카메라",
        "전자기기" to "스캐너",
        "전자기기" to "재봉틀",
        "전자기기" to "시계",
        "전자기기" to "계산기",
    )
}
