## SpringP
주제 : 
투자에 대해 관심이 많은 사람들을 위한 커뮤니티 사이트
  




## 기본셋팅  
	  	
  eclipse
  
  
  Spring MVC 
  
  
  Tomcat 8.5   
  
  
  
  java8    	
  
  
  MySQL  
  
  
  bootstrap   
  
  
  		
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
  /replies/** : 댓글부분    
    
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


  


##  외부 데이터 사용하기

#### jsoup 네이버 주가 크롤링
pom.xml에 jsoup 입력
connection으로 주소연결
페이지에서 해당 데이터가 있는 클래스 찾기
kospiStockDto에 담아서 리스트에 담기
restController로 getjson으로 불러오기
버튼클릭시 페이지 번호를 불러와서 페이지 입력하기(naver주소자체에서 page=1로 데이터가 잘림)

컨트롤러 : BoardController, RestController    
페이지 : /board/list   
데이터(RestController) : /StockList - getJson    
클래스 : JsoupComponent, StockService    
서비스 : BoardService    
모델 : KospiStockDto    
매퍼 : BoardMapper          
            
  


#### 공공데이터 api - 주식시세
공공데이터포털 로그인   
해당 인증키와 request부분 불러오기         
request마다 데이터가 변하므로 각각 request인자를 배열에 저장하여 불러오기 :매개변수로 String ...arg 사용      
반복문을 통해 각각 인자를 입력   
받아온 데이터 json타입으로 파싱   
DTO에 담기   
현재시세가 아닌 개장 하루 전 데이터까지 있으므로 기준일자를 오늘부터 하루 전으로 입력    
20221108 데이터 포맷으로 만들고 만약 해당 데이터가 없을시 하루 전 데이터를 다시 찾음        
dbUpdate페이지에 버튼클릭시 DTO 리스트에 담은 데이터 DB에 저장 - 총 940개이므로 list자체를 insert, 이 때 하루 전 데이터만 저장하기 위해서 기존에 있는 데이터를 update, 만약 기존 데이터가 없으면(신규상장) 데이터 insert    
restController를 사용하여 json 타입으로 바꿔서 getjson으로 불러오기   
데이터 차트를 보여주기 위해 chartjs사용   

- 컨트롤러 : BoardController, RestController    
- 페이지 : 상세페이지 - /board/detail , 종목찾기 : /board/searchList
- 데이터(RestConroller) : 상세페이지 - /Datadetail , 종가데이터 DB저장 - /DBUpdate-Stock    
- 클래스 : ApiExplorer, DateFormat   
- 서비스 : BoardService    
- 모델 : StockDto   
- 페이징처리 : CriteriaVO, PageVO    
- 매퍼 : BoardMapper    
  


#### krx KOSPI 회사정보 csv파일 읽기
 krx에서 csv로 다운로드, 이 때 다시 파일을 encoding : ansii -> utf-8로 다른이름으로 저장(안할시 한글깨짐)   
 파일 경로를 입력하여 파일 불러오기 - 이 때 csv파일은 ,를 기준으로 불러옴   
 빈 파일이나 해당 셀에 ,가 있는경우 데이터를 잘못 불러올 수 있기 때문에 replace와 split로 구분하여 데이터를 잘라옴    
 잘라온 데이터를 DTO리스트에 넣어 데이터 DB저장   
헤더부분에 있는 searchform으로 주식을 search할 때 데이터 사용    

- 컨트롤러 : BoardController, RestController    
- 페이지 : /board/detail   
- 데이터(RestConroller) : 회사정보 db저장 : /DBUpdate_Com     
- 클래스 : CSVReader, DateFormat   
- 서비스 : BoardService    
- 모델 : CompanyInfoDto   
- 매퍼 : BoardMapper    


#### 소셜로그인 - 카카오, 네이버
pom.xml에 github.scribejava 사용, 둘 다 OAuth2.0 방식  
카카오, 네이버 해당 developer 사이트에서 api사용 등록, 클라이언트 아이디와 시크릿, 해당 사이트에 등록한 콜백주소 무조건 입력되어야함   
둘 다 LoginBO 형식은 같지만 혹시 수정사항이 있을 경우 해당 로그인부분만 수정하도록 클래스를 두 개 만들어놓음   
따라서 AuthUrl을 불러오는 값에서 session에 uuid를 붙여서 코드(토큰을 불러오는 키)로 사용하는데, naversession, kakaosession으로 세션을 따로 분리해서 가져옴    
OAuth20Service를 사용하여 builder에 코드를 넣어서 토큰을 불러옴   
토큰을 입력하여 불러온 데이터 값을 각각 콜백 주소에서 파싱 후 원하는 데이터 DB저장    
이 때 id가 기존에 있는 데이터(페이지 자체에서 회원가입한 아이디)와 같으면 안되니깐 naver는 N+ , kakao는 K+를 해당 아이디에 넣고 유효성 검사로 아이디 가입에 특수문자를 막아놓음   
부트스트랩 pattern="정규식" 으로 유효성 검사 만들기   

- 컨트롤러 : MemberController
- 페이지 : /member/login
- 데이터(콜백) : 네이버 - /callbackNaver   ,   카카오 - /callbackKakao 
- 클래스 : 네이버 - NaverOAuthApi , NaverLogin    , 카카오 - KakaoOAuthApi , KakaoLogin      
- 서비스 : MemberService
- 모델 : MemberDto
- 매퍼 : MemberMapper
  


## 에디터 사용     
#### 게시판 에디터 만들기 - execCommand()를 사용 : 현재 deprecated됨(삭제)   
    

#### CKEditor4 사용		  
위지윅(WISIWYG what you see is what you get) 에디터


CKEditor5를 사용하려 했으나 유료기능이 많고 nodeJS로 구현하는게 많아서 버전 다운을 함	


CKEditor4를 홈페이지에서 다운로드		         


프로젝트의 webapp - resource(js,css폴더가 있는 디렉터리)에 다운로드한 파일을 복사 붙여넣기함	


<script>를 사용하여 src= "../resources/ckeditor/ckeditor.js" 로 jsp와 js파일을 연결		
        
        
		   <div id=editor contenteditable="true">		  
                           
                           
로 에디터를 적용할 태그를 만듬		          
                           
                           
새로 javascript파일을 연결하여 CKEDITOR.replace("적용할 태그 아이디")를 작성 ,	
                                                      
             
이 때 적용할 id 앞에 #를 붙이지 않음		       
                           
                           
CKEDITOR.replace("태그아이디",{이미지 업로드 - filebrowserUploadUrl:"업로드를 구현할 컨트롤러 주소"})입력	
                           
                           
서버에 전송하기 전 컨트롤러(before)와 서버에 전송한 후 보여지는 컨트롤러(after) 두개를 구분하여 생성		
                           
                           
before에 multipart를 사용하여 after에 보내질 경로를 작성하고 해당 이미지 파일을 지정한 경로에 전송하여 저장,		
                           
                           
printwriter를 사용하여 ckedior자체 함수에 주소를 전송, after에 받은 주소를 가공하여 뷰에 보여줌	
                           
                           
(주의 : 날짜폴더 경로를 after에 보내주려고 했으나 error발생 - ckeditor함수에 경로를 의미하는 "\\" 문자를 인코딩할 수 없음,	
                           
                           
따라서 \\ 대신에 다른 특수문자를 대체하여 보낸후 after에 다시 \\로 바꿔줌)		        
                           
                           
form으로 db에 저장할 때 에디터에 쓴 내용은 input hidden에 value값으로 따로 보내줌		
                           
                           
이때 해당 에디터 데이터는 CKEDITOR.instances.editor.getData(); 로 가져올 수 있음	
                           
                           
		      
    








