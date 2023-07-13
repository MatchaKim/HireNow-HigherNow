# HireNow-HigherNow! 하이어나우
📑 채용플랫폼 HireNow Backend Repository
- 기업과 인재를 연결해주는 채용플랫폼 프로젝트입니다.
- 프로젝트 기간 23.07.05 (수) ~ 23.07.12 (수)

## Framework

- SpringBoot

## ERD

![mgL92M4V](https://github.com/MatchaKim/HireNow-HigherNow/assets/121302951/e6486298-ee39-474d-9f6b-3a538fd10842)


## API List 

### 공고관련
<br>
- 공고등록    
POST :  `/recruit`

```java
Request -> 200 OK

{
  "companyName": String,
  "recruitTitle": String,
  "companyAddress": String,
  "employmentType": String,
  "wage": String,
  "deadline": String,
  "latitude": String,
  "longitude": String,
  "companyInfo": String,
  "question1": String,
  "question2": String,
  "question3": String,
  "password": String,
}

+ File

```

<br>
- 공고조회    
GET : `/recruit`

```java
Response 200OK

[
    {
        "jobListId": UUID,
        "companyName": String,
        "recruitTitle": String,
        "companyAddress": String,
        "employmentType": String,
        "wage": String,
        "deadline": String,
        "companyInfo": String
    }
]
```
<br>
- 지원이 많은순으로 공고조회    
GET : `/recruit/hot`

```java
Response 200OK
[
    {
        "jobListId": UUID,
        "companyName": String,
        "recruitTitle": String,
        "companyAddress": String,
        "employmentType": String,
        "wage": String,
        "deadline": String,
        "companyInfo": String,
    }
]
```
<br>
- 공고검색    
GET : `/recruit/search/?keyword={검색어}`

```java
Response 200OK
[
    {
        "jobListId": UUID,
        "companyName": String,
        "recruitTitle": String,
        "companyAddress": String,
        "employmentType": String,
        "wage": String,
        "deadline": String,
        "companyInfo": String,
    }
]
```
<br>
- 공고상세    
GET : `/recruit/{jobListId}`

```java
Response 200OK
{
    "jobListId": UUID,
    "companyName": String,
    "recruitTitle": String,
    "companyAddress": String,
    "employmentType": String,
    "wage": String,
    "deadline": String,
    "latitude": Double,
    "longitude": Double,
    "companyInfo": String,
    "question1": String,
    "question2": String,
    "question3": String,
    "createdTime": String
}
```
<br>
- 공고 로고사진 조회    
GET : `/logo/{jobListId}`

```java
Content-Type : image

Response 200OK

로고사진 제공

```



### 지원

- 지원서 작성   
POST : `/apply`

```java
Reqeust -> 200OK
{
  "jobListId": UUID,
  "name": String,
  "gender": String,
  "age": Integer,
  "answer1": String,
  "answer2": String,
  "answer3": String,
}
```
<br>
- 지원자 확인    
POST: `/apply/{jobListId}`

```java
Request
{
	"password":String
}
```

```java
Response 200OK
[
    {
        "applicationId": UUID,
        "name": String,
        "createdTime": String
    }
]
```
<br>
- 지원서 상세    
POST: `/apply/{jobListId}/{applicationId}`

```java
Request
{
	"password":String
}
```

```java
Response 200OK
{
    "applicationId": UUID,
    "name": String,
    "gender": String,
    "age": Integer,
    "answer1": String,
    "answer2": String,
    "answer3": String,
    "jobListId": UUID,
    "createdTime": String
}
```
