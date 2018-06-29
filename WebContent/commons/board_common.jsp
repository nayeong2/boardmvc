<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var= "bcode" value= "${param.bcode }"></c:set>
<c:set var= "pg" value= "${param.pg }"></c:set>
<c:set var= "key" value= "${param.key }"></c:set>
<c:set var= "word" value= '${param.word }'></c:set>
<c:set var= "seq" value= "${param.seq }"></c:set>
<form name= "commonform">
	<input type= "hidden" name= "act" id= "act">
	<input type= "hidden" name= "bcode" id= "bcode">
	<input type= "hidden" name= "pg" id= "pg">
	<input type= "hidden" name= "key" id= "key">
	<input type= "hidden" name= "word" id= "word">
	<input type= "hidden" name= "seq" id= "seq">
	
</form>