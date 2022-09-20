# Jar File Link

https://github.com/carpedi-em/file/blob/master/search.jar

[jar file](https://github.com/carpedi-em/file/blob/master/search.jar)

```bash
java -jar search.jar
```

# Environment

- Post : 8080
- Java Version : 11
- Open API KEY
  - [KaKao](https://developers.kakao.com)
  - [Naver](https://developers.naver.com)

# API

## 블로그 검색 API

### path
```http
GET /v2/search/blog
```

### Request 
```
curl -v -X GET "http://localhost:8080/v2/search/blog" \
--data-urlencode "query=카카오뱅크" \
```

#### Parameter
|Key|                 Desc                 | Required |
|:---:|:------------------------------------:|:---:|
|query|                 검색어                  |O|
|sort| 정렬 방식 : accuracy(정확도순),recency(최신순)  |X|
|page|    결과 페이지 번호, 1~50 사이의 값, 기본 값 1     |X|
|size| 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10 |X|

### Response
```json
{
  "content": [
    {
      "title": "들어봤니? &#39;카카오뷰&#39;에 갬블러크루가 나타났대!! &#34;소식전달&#34; &#34;정보 전달&#34; 그야말로 생생정보통 &#39;비보이...",
      "contents": "<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>98%</b><b>A4</b>%20%<b>EB</b>%<b>B</b>7%<b>B</b>0%<b>EC</b>%97%90%<b>EC</b>%84%9C%20%EA%<b>B</b>0%<b>AC</b>%<b>EB</b>% <b>B</b>8%94%<b>EB</b>%9F%<b>AC</b>%<b>ED</b>%<b>81%</b><b>AC</b>%<b>EB</b>%A3%A8%<b>EB</b>%A5%BC%20%<b>EB</b>%A7%8C%<b>EB</b>%82%<b>98%</b><b>EB</b>%<b>B</b>3%<b>B</b><b>4%</b><b>EC</b>%84%<b>B</b>8%<b>EC</b>%9A%94.%20%20%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>ED</b>%86%A<b>1%</b>20%<b>EC</b>%84%<b>B</b>8%20%<b>EB</b>%<b>B</b>2%88%<b>EC</b>%A7%<b>B</b>8%20%<b>EB</b>%<b>B</b>7%<b>B</b>0%<b>ED</b>%83%AD%20-%20%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>98%</b><b>A4</b>%20%<b>EB</b>%<b>B</b>7%<b>B</b>0%20%<b>EB</b>%<b>B</b>0...",
      "url": "https://blog.naver.com/gamblerzbboy/222846800576",
      "blogname": "갬블러크루 공식블로그",
      "thumbnail": "",
      "datetime": "2022-08-12T18:35:00.000+09:00"
    },
    {
      "title": "20220827 블루아카이브 샬레의 해피♡발렌타인&amp;침묵과 축연 사전등록 이벤트",
      "contents": "백 관통딜러고 자기 공격력증가에 지속적으로 스턴을 넣는 딜포터라고... 치히로도 백 관통딜러인 것 같음 https://namu.wiki/w/%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b>EA%<b>B</b>0%80%<b>EB</b>%AF%<b>B</b>8%20%<b>EC</b>%<b>B</b><b>9%</b><b>98%</b><b>ED</b>%9E%88%<b>EB</b>%A<b>1%</b>9C EX스킬이 적1인 댐쥐에 중장갑 적이면 추가로 기절을 넣는다고 한다 기본스킬에 공격력 감소도 붙어있고 치명타 댐쥐 강화스킬...",
      "url": "http://kureowl.tistory.com/524",
      "blogname": "쿠빼미의 제2둥지",
      "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/GIfyq1HS8QB",
      "datetime": "2022-08-27T19:50:21.000+09:00"
    },
    {
      "title": "20220708 블루아카이브 학생 소개-체리노(온천)&amp;치나츠(온천)&amp;토모에",
      "contents": "<b>EB</b>%<b>85%</b><b>B</b>8%<b>EB</b>%AF%<b>B</b>8%<b>EC</b>%95%BC%20%<b>EC</b>%<b>B</b><b>9%</b><b>98%</b><b>EB</b>%82%<b>98%</b><b>EC</b>%<b>B</b>8%A0/%<b>EC</b>%<b>98%</b>A8%<b>EC</b>%<b>B</b>2%9C 미들 신비 경장갑 스트라이커고 무기는 권총이라고 한다 일단 신비딜러니까...한섭 블루아카이브 트위터 https://namu.wiki/w/%<b>EC</b>%82%<b>AC</b>%<b>EC</b>%8B%9C%<b>EB</b>%A<b>1%</b>9C%20%<b>ED</b>%86%A0%<b>EB</b>%AA%A8%<b>EC</b>%97%90 배포 1성 백 서포터 관통 특수장갑 저격총이고...",
      "url": "http://kureowl.tistory.com/441",
      "blogname": "쿠빼미의 제2둥지",
      "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/AkPOxQe5iJu",
      "datetime": "2022-07-08T02:42:01.000+09:00"
    },
    {
      "title": "[DJANGO] 소셜로그인 - kakao (스크랩)",
      "contents": "5%<b>AC</b>%<b>ED</b>%<b>98%</b>84%<b>ED</b>%95%<b>98%</b>EA%<b>B</b>8%B0 [Django] allauth로 카카오 로그인 구현하기 쇼핑몰을 개발해보면서 가장 대중화된 구글 로그인 외에 카카오로 로그인 할 수 있는 기능을 구현해보고자 합니다!! 이거 구현하면서 조금 많이 헤맸어서 도움이 되길 바라는 마음으로 작성해 velog.io 아래 링크를 다음으로 참조...",
      "url": "http://hwanghub.tistory.com/177",
      "blogname": "Hwang Hub",
      "thumbnail": "",
      "datetime": "2022-07-31T22:46:10.000+09:00"
    },
    {
      "title": "[Social Login] 구글, 네이버, 카카오 로그인 구현",
      "contents": "<b>B</b><b>4%</b><b>EC</b>%<b>98%</b><b>A4</b>-oauth-%<b>EC</b>%84%9C%<b>EB</b>%<b>B</b><b>9%</b>84%<b>EC</b>%8A%<b>A4</b>-%<b>EB</b>%93%<b>B</b><b>1%</b><b>EB</b>%A<b>1%</b>9D 3. OAuth2 설정 파일을 작성합니다. application-oauth.yml 파일을 작성하고 application.yml 파일에 추가해줍니다. spring: # Security OAuth security: oauth2.client: registration: google: clientId: &#39;GOOGLE_CLIENT_ID&#39; clientSecret: &#39;GOOCLE...",
      "url": "http://developerbee.tistory.com/245",
      "blogname": "Beelog",
      "thumbnail": "",
      "datetime": "2022-06-06T15:16:58.000+09:00"
    },
    {
      "title": "남천시 세계관 캐릭터&amp;노래",
      "contents": "嫌われているんでしょう？... www.nicovideo.jp 모노크롬 블루스카이/하츠네 미쿠/노보루 https://namu.wiki/w/%<b>EB</b>%AA%A8%<b>EB</b>%<b>85%</b><b>B</b>8%<b>ED</b>%<b>81%</b><b>AC</b>%<b>EB</b>%A<b>1%</b><b>AC</b>%E2%88%9E%<b>EB</b>%<b>B</b>8%94%<b>EB</b>%A3%A8%<b>EC</b>%8A%<b>A4</b>%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%9D%B4 【Hatsune Miku】 Monochrome ∞ Blue Sky 【Original・Noboru↑】 - Niconico Video 00:00 / 03:56 Comment...",
      "url": "https://blog.naver.com/kuragegirl/222786961647",
      "blogname": "관해파리의 해저 동굴",
      "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/ERXypHWayGp",
      "datetime": "2022-06-24T12:46:00.000+09:00"
    },
    {
      "title": "(1) 카카오 뱅크에 투자하는게 맞나?",
      "contents": "수익 영향 분석) *Reference - 카카오 뱅크 : https://www.kakaobank.com/Corp/About/Identity - 나무 위키(카카오 뱅크) : https://namu.wiki/w/%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>98%</b><b>A4</b>%<b>EB</b>%<b>B</b><b>1%</b><b>85%</b><b>ED</b>%<b>81%</b><b>AC</b> - 다트(카카오 뱅크_2021.09분기 보고서) : https://dart.fss.or.kr/dsab007/main.do 부족하지만 읽어주셔서 감사합니다...",
      "url": "http://billionaire-hossa.tistory.com/4",
      "blogname": "Maktūb",
      "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/F6AvIHVA2Z5",
      "datetime": "2022-02-20T20:31:22.000+09:00"
    },
    {
      "title": "한국의 80년대를 떠올리게 하는 왕웨이렌의 중단편집 &lt;책물고기&gt;",
      "contents": "<b>EC</b>%86%8C%EA%<b>B</b>8%88%<b>EC</b>%82%<b>B</b>0%<b>EB</b>%A7%A5%<b>EC</b>%97%90%<b>EC</b>%84%9C%20%<b>EB</b>%<b>B</b>3%<b>B</b><b>4%</b><b>EC</b>%84%9D%<b>EC</b>%9D%84%20%<b>EC</b>%A3%BC%<b>EC</b>%9B%8C%<b>EB</b>%93%<b>A4</b>%<b>EC</b>%97%88%<b>EB</b>%8B%<b>A4</b> %<b>EC</b>%82%<b>AC</b>%<b>EC</b>%A7%84%EA%<b>B</b>0%80%20%<b>EC</b>%9D%<b>B</b><b>4%</b><b>EC</b>%83%<b>81%</b><b>EC</b>%97%BD%<b>EC</b>%9D%<b>B</b><b>4%</b>20%<b>EB</b>%<b>B</b>0%94%<b>EB</b>%9D%BC%<b>EB</b>%<b>B</b>3%<b>B</b>8%20%<b>EC</b>%<b>A4</b>%91%EA%<b>B</b>5%AD%20%<b>EC</b>%<b>B</b><b>9%</b>AD%<b>ED</b>%95%<b>98%</b><b>EC</b>%9D%<b>B</b><b>4%</b><b>EC</b>%84%<b>B</b><b>1%</b>20%<b>EC</b>%<b>B</b>0...",
      "url": "https://blog.naver.com/egeyouri/222867373273",
      "blogname": "나의 패랭이역",
      "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/FuoytdyYksy",
      "datetime": "2022-09-05T15:02:00.000+09:00"
    },
    {
      "title": "일자천금 → 기실불연 → 일점일획",
      "contents": "<b>ED</b>%<b>85%</b>8C%<b>EB</b>%84%<b>A4</b>%20%<b>ED</b>%95%99%<b>EB</b>%8B%B9 현대란 쓰레기 X 쓰레기 로 모든 쓰레기를 없앤다며 흠 없는 옥 玉 같이 하나되자며 상속남이 모든 것을 가진 지금...고를 수 있는 자유를 다시 찾음일 수 있다. ​ 비너스는 난교의 상징이었고, 플라톤은 귀족주의자였지만, ​ ​ ​ https://namu.wiki/w/%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EB</b>%A5%BC%205%<b>EC</b>%84...",
      "url": "https://blog.naver.com/knock001/222873730883",
      "blogname": "무르 의 공방",
      "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/ABb0G1ZdJ0E",
      "datetime": "2022-09-13T19:53:00.000+09:00"
    },
    {
      "title": "31일차(금) 업무 + 소셜로그인3(페이스북, 트위터) + 로그파일",
      "contents": "트위터 로그인 https://velog.io/@nittre/Node.jsExpress-passport.js%<b>EB</b>%A<b>1%</b>9C-SNS-%<b>EB</b>%A<b>1%</b>9C%EA%<b>B</b>7%<b>B</b>8%<b>EC</b>%9D%B8-%EA%<b>B</b>5%<b>AC</b>%<b>ED</b>%<b>98%</b>84%<b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>B</b><b>9%</b><b>B</b><b>4%</b><b>EC</b>%<b>98%</b><b>A4</b>%<b>ED</b>%86%A1-%<b>ED</b>%8A%<b>B</b>8%<b>EC</b>%9C%84%<b>ED</b>%84%B0-%<b>ED</b>%8E%<b>98%</b><b>EC</b>%9D%<b>B</b><b>4%</b><b>EC</b>%8A%<b>A4</b>%<b>EB</b>%<b>B</b>6%81 https://ethdemor.wordpress.com/2012/01/26/...",
      "url": "http://ppojjakcoding.tistory.com/369",
      "blogname": "코딩기록",
      "thumbnail": "",
      "datetime": "2022-07-08T11:14:25.000+09:00"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "offset": 10,
    "page_number": 1,
    "page_size": 10,
    "paged": true,
    "unpaged": false
  },
  "last": false,
  "total_pages": 430,
  "total_elements": 4293,
  "size": 10,
  "number": 1,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": false
  },
  "number_of_elements": 10,
  "first": false,
  "empty": false
}
```

## 인기 검색어 API

### path
```http
GET /v2/search/popular
```

### Request
```
curl -v -X GET "http://localhost:8080/v2/search/popular"
```

### Response
```json
{
  "popular": [
    {
      "id": "카카오뱅크",
      "hit": 1
    }
  ]
}
```

# 기능

1. 블로그 검색
2. 인기 검색어 목록(캐시 1초)
3. API 장애시 외부 API 사용

# 외부 라이브러리

- [lombok](https://github.com/projectlombok/lombok)
