## SpringP
주제 : 
투자에 대해 관심이 많은 사람들을 위한 커뮤니티 사이트

## 패키지 설명
**component**     
: DB에 저장하지는 않지만 다른곳에서 불러온 데이터를 DTO에 저장하여 사용하는 곳


**controller**     
: 용도별로 분류,     
  / : 기본 메인페이지    
  /member/** : 각각 회원이 개인적으로 사용하는 곳    
  /board/** : 주식 관련, 모두가 읽을 수 있는 곳    
  /community/** : 일반 회원들의 커뮤니티관련    
  /admin/** : 페이지 관리자만 사용가능한 곳    
  /reply/** : 댓글부분    
    
**RestController**    
: 뷰 없이 json타입 데이터를 받아올 때 사용   
    RestConrollerCom: 주가 정보 불러오는 컨트롤러
  /reply/** : 댓글부분, 댓글이 비동기 식으로 처리하기 위해 Restcontroller로 사용

    
**mapper**     
: 데이터베이스의 기본 CRUD하는 곳   
용도별로 분류   


**model**   
VO     
: 기본적인 용도 - getter, setter만 가지는 곳, readonly   
데이터를 저장하지 않고 용도별로 변수를 넣어두는 곳    
DTO     
: 데이터를 저장하는 변수들이 있는 곳   
현재 DTO와 Entity가 같아서 모두를 DTO로 정함   


**service**   
mapper를 통해 데이터를 받거나 component로 받은 데이터들을 가공하는 곳    
















