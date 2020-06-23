<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h2>글 수정</h2>
   <form action="update.do" method="post" enctype="multipart/form-data">
   	  <input type="hidden" name="searchOption" value="${map.searchOption }" />
   	  <input type="hidden" name="keyword" value="${map.keyword }" />
   	  <input type="hidden" name="curPage" value="${map.curPage }" />
   	  <input type="hidden" name="board_time" value="${map.board_time }" />
      <input type="hidden" name="board_num" value="${dto.board_num }" />
      <input type="hidden" name="saveFile" value="${fileDto.file_oriName }" />
      <input type="hidden" name="board_typeCode" value="${dto.board_typeCode }" />
      <input type="hidden" name="board_writer" value="${dto.board_writer }" />
     <div >
         <label for="board_title">글제목</label>
         <input  type="text" id="board_title" name="board_title" value="${dto.board_title }" required="required"/>
      </div>
      <div >
         <label for="board_content">내용</label>
         <textarea  name="board_content" id="board_content" required="required">${dto.board_content }</textarea>
      </div>
      <div>
      	<label for="file1">첨부파일</label>
      	<input type="file" name="file1" id="file1" />
      </div>
      <button type="submit" onclick="submitContents(this);">수정확인</button>
      <button onclick="historyback();">취소</button>
   </form>
</div>
<!-- SmartEditor 에서 필요한 javascript 로딩  -->
<script src="${pageContext.request.contextPath }/SmartEditor/js/HuskyEZCreator.js"></script>
<script>
   var oEditors = [];
   
   //추가 글꼴 목록
   //var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
   
   nhn.husky.EZCreator.createInIFrame({
      oAppRef: oEditors,
      elPlaceHolder: "board_content",
      sSkinURI: "${pageContext.request.contextPath}/SmartEditor/SmartEditor2Skin.html",   
      htParams : {
         bUseToolbar : true,            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
         bUseVerticalResizer : true,      // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
         bUseModeChanger : true,         // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
         //aAdditionalFontList : aAdditionalFontSet,      // 추가 글꼴 목록
         fOnBeforeUnload : function(){
            //alert("완료!");
         }
      }, //boolean
      fOnAppLoad : function(){
         //예제 코드
         //oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
      },
      fCreator: "createSEditor2"
   });
   
   function pasteHTML() {
      var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
      oEditors.getById["board_content"].exec("PASTE_HTML", [sHTML]);
   }
   
   function showHTML() {
      var sHTML = oEditors.getById["board_content"].getIR();
      alert(sHTML);
   }
      
   function submitContents(elClickedObj) {
      oEditors.getById["board_content"].exec("UPDATE_CONTENTS_FIELD", []);   // 에디터의 내용이 textarea에 적용됩니다.
      
      // 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
      
      try {
         elClickedObj.form.submit();
      } catch(e) {}
   }
   
   function setDefaultFont() {
      var sDefaultFont = '궁서';
      var nFontSize = 24;
      oEditors.getById["board_content"].setDefaultFont(sDefaultFont, nFontSize);
   }
</script>
</body>
</html>