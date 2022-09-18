# Jar File Link

---

[]()


# Environment

---

- Post : 8080
- Java Version : 11
- Open API KEY
  - [KaKao](https://developers.kakao.com)
  - [Naver](https://developers.naver.com)

# API

---

## 블로그 검색 API

### path
```http
GET /v2/search/blog
```

### Request 
```http
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

## 인기 검색어 API

### path
```http
GET /v2/search/popular
```

### Request
```http
curl -v -X GET "http://localhost:8080/v2/search/popular"
```

# 기능

---

1. 블로그 검색
2. 인기 검색어 목록
3. API 장애시 외부 API 사용

# 외부 라이브러리

---

- [lombok](https://github.com/projectlombok/lombok)
