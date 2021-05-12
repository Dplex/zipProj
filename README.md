
### Requirements
- 지역기반 시세 조회
- 특정 아파트 실시간 매매 알림
- 특정 아파트 월 주 일 별 가격 볼수 있는 차트 제공
- 청약정보? APT2YOU
- 경매 매물 조회(급매)
- 지금 이 아파트를 보고있는 사람수 확인 가능
- JWT(Json Web Token) 
- ...

SELECT HOUSE_ID
FROM USER
JOIN HOUSE USING(USER_ID)
WHERE USER_LOGIN_ID = "ABC"


### DB
- Postgresql(GEO 쿼리)
테이블 
유저(user)

| Name | Type | Description |
| --- | ---- | --- |
| USER_ID | BIGINTEGER(21) | PK |
| USER_LOGIN_ID | VARCHAR(22) | 유저 아이디(UQ) |
| USER_LOGIN_PASSWORD | VARCHAR(100) | 패스워드(암호화) |
| USER_KEY | VARCHAR(100) | 유저 토큰키 TBD |

유저_집정보(USER_HOUSE)

| Name | Type | Description |
| --- | ---- | --- |
| USER_HOUSE_ID | BIGINTEGER(21) | PK |
| USER_ID | BIGINTERGER(21) | USER FK |
| HOUSE_ID | BIGINTERGER(21) | HOUSE FK | 

집정보(HOUSE)

| Name | Type | Description |
| --- | ---- | --- |
| HOUSE_ID | VARCHAR(22) | PK |
| HOUSE_NAME | VARCHAR(100) | HOUSE name |
| HOUSE_LATITUDE | DOUBLE | latitude |
| HOUSE_LONGITUDE | DOUBLE | longitude |


### BackEnd(API Server)
- Spring Boot Kotlin 2.2 over
- JPA
- 직방 API 호출 연동(직방, ) : Circuit Breaker(Hystrix)
- 배치모듈(데이터 정제 및 statistics 가공)

### FrontEnd
- React Native 

### CI / CD

