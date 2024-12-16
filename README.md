# Spring Batch를 활용한 정산시스템
- 해당 정산시스템은 고객에게 유료 API를 파는 프로그램으로, <br>
  고객이 API를 사용할때마다 과금이 되고, 해당 내역을 기록하여 고객에게 청구해야한다.

### 요구사항
1. 서비스팀에서는 유료 API 사용 이력을 남긴다.
2. 유료 API 사용 내역을 파일로 정산팀에 전달한다. (임시 랜덤 데이터 생성)
3. 정산팀은 1일 단위로 정산을 한다.
4. 매주 금요일에는 일주일 치 하루 정산을 집계해서 DB에 저장하고 고객사에 이메일을 발송한다.


### 일일정산배치 예시 화면
![image](https://github.com/user-attachments/assets/f63ba61e-8487-4661-893b-ee23c20e0903)

### 주간정산배치 예시 화면
![image](https://github.com/user-attachments/assets/78296c8f-2c46-45dc-ab04-443d0b8ff6bd)
