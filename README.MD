# 프로젝트 설명

압축 파일은
ERD캡쳐, ERD 테이블, 구현 동영상으로 이루어져있습니다.

</br></br></br>

# 작업환경

IDE : Eclipse 2024-03v
library : gson-2.9.0, mariadb-java-client-2.4.0
서버 : apache-tomcat-8.5.100
DBMS : mariadb-10.11.7

</br></br></br>

# ERD

테이블은 3가지 와이파이, 히스토리, 북마크로 나뉩니다

- ### `와이파이`

1. OPENAPI에서 가져온 데이터를 그대로 입력하는 테이블입니다.
2. 삭제시 데이터를 삭제하는 것이 아니라 use_YN을 0과1로 변경하는 것으로 표시하였습니다.
3. 설치년도의 경우 OPEN API에서 받아오는 데이터가 단순 4글자라 varchar(4)로 데이터를 저장하도록 하였습니다.
4. OPEN API의 데이터를 전부 확인할 수 없었고 일부 데이터는 아예 값이 들어있지 않은경우도 있어서 id, x좌표, y좌표, 작업일자, 사용여부를 제외하고는 전부 Nullable하게 설정하였습니다.

- ### `히스토리`

1. 히스토리 탭에서 조회할 수 있는 데이터 테이블입니다.
2. 마찬가지로 사용여부를 use_YN으로 표시하였습니다.
3. 테이블의 모든 데이터는 Null데이터가 들어가지 않습니다. x좌표, y좌표는 row의 생성 조건이고 조회일자, 사용여부는 생성 시 입력되는 필수값이기 때문입니다.

- ### `북마크`

1. 북마크 탭, 북마크 그룹 탭, 와이파이 상세 셀렉트 박스에서 조회할 수 있는 데이터 테이블입니다.
2. 마찬가지로 사용여부를 use_YN으로 표시하였습니다.
3. 와이파이ID, 와이파이이름, 업데이트일은 Nullable하게 설정했습니다. 업데이트일은 수정 시에만 입력되고 와이파이ID, 와이파이 이름은 와이파이 상세에서 북마크에 추가하였을 때에만 입력되기 때문입니다. 북마크들 중 북마크 보기 탭에 조회되는 북마크의 필터를 와이파이ID, 와이파이 이름의 존재여부로 하였습니다.

### 연관관계

1. 와이파이와 북마크는 일대일(one-to-one)관계입니다. 북마크 테이블은 와이파이ID를 FK로 사용합니다. 북마크 탭의 레코드들은 와이파이 탭의 id를 가질 수 있지만 하나의 북마크 레코드는 오로지 하나의 와이파이 레코드에만 연관될 수 있기때문입니다.

<br/><br/><br/>

# 데이터베이스

제가 선택한 데이터베이스는 MariaDB입니다. 제가 원하는 회사에서는 주로 MariaDB, PostgreSQL을 사용하여서 그 중 하나인 MariaDB를 사용해보았습니다.

1. mariaDB boolean type

- mariaDB에서는 boolean type이 tinyint 타입으로 0일때 false, 1일때 true를 나타내어 ERD에서 boolean값으로 정한 column들은 tinyint로 db에 저장하였습니다.
  <br/><br/><br/>

# 프로젝트 구조

📦webapp<br/>
┣ 📂META-INF <br/>
┃ ┗ 📜MANIFEST.MF <br/>
┣ 📂WEB-INF <br/>
┃ ┣ 📂classes <br/>
┃ ┗ 📂lib<br/>
┃ ┃ ┗ 📜gson-2.9.0.jar <br/>
┣ 📜bookmark.jsp <br/>
┣ 📜bookmark_delete.jsp <br/>
┣ 📜bookmark_insert.jsp <br/>
┣ 📜bookmark_update.jsp <br/>
┣ 📜history.jsp <br/>
┣ 📜index.jsp <br/>
┣ 📜matched_bookmark.jsp <br/>
┣ 📜matched_bookmark_delete.jsp <br/>
┣ 📜wifi_detail.jsp <br/>
┗ 📜wifi_save_result.jsp <br/>

📦java<br/>
┣ 📂jar<br/>
┃ ┗ 📜gson-2.9.0.jar<br/>
┣ 📂javacode<br/>
┃ ┣ 📂servlet<br/>
┃ ┃ ┣ 📜delete_bookmark_servlet.java<br/>
┃ ┃ ┣ 📜delete_history_servlet.java<br/>
┃ ┃ ┣ 📜get_wifi_sevlet.java<br/>
┃ ┃ ┣ 📜insert_bookmark_servlet.java<br/>
┃ ┃ ┣ 📜insert_matched_bookmark_servlet.java<br/>
┃ ┃ ┣ 📜save_wifi_servlet.java<br/>
┃ ┃ ┗ 📜update_bookmark_servlet.java<br/>
┃ ┣ 📜bookmark_dao.java<br/>
┃ ┣ 📜history_dao.java<br/>
┃ ┣ 📜Util.java<br/>
┃ ┣ 📜wifi_dao.java<br/>
┃ ┣ 📜wifi_dto.java<br/>
┃ ┗ 📜wifi_with_distance.java<br/>
┗ 📂repository<br/>
┃ ┣ 📜bookmark_repo.java<br/>
┃ ┣ 📜history_repo.java<br/>
┃ ┗ 📜wifi_repo.java<br/>

### webapp

1. 페이지 메인이 되는 resource(wifi,bookmark,matchedbookmark) + method 형식으로 이름을 지었습니다.

### java/javacode

1. db와 통신하는 repository,
2. jsp에서 요청을 받아 처리하고 다시 jsp로 fowarding하는 servlet
3. OPENAPI, 사용자 입력, db에서 가져온 테이블 정보를 객체로 변환한 dao,dto 로 구성되어있습니다.
   > 프로젝트 코드에는 db에서 받아온 dao를 dto나 viewModel로 변환하지않고 JSP에서 바로 사용하고 있습니다. 이렇게하면 view와 model 결합이 높아져서 유지보수, 확장에 불리하지만 과제 특성상 그런 작업들이 불필요하다고 느껴서 중간에서 변환하는 작업은 하지 않았습니다.
4. 페이지 로드 시 바로 호출되어야하는 코드들은 jsp에서 repository를 사용하였고, 이벤트(submit) 발생 시 호출되어야하는 코드들은 servlet을 호출해서 거기서 repository를 호출하도록 하였습니다.
