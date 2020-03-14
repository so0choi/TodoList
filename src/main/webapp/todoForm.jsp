<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add List</title>
    <link rel="stylesheet" href="todoForm_css.css">
</head>
<body>
    <div id="content">
     <h1>할일 등록</h1>
        <form action="addTodo" method="POST">
            <div id="addcontent">
                <div class="words">어떤일인가요?</div>
                <input type="text" maxlength=24 placeholder="졸업하기(24자까지)" class="words"name="title" required="required">
                <div class="words">누가 할일인가요?</div>
                <input type="text" placeholder="최소영" class="words" name="name" id="name" required="required">
                <div class="words">우선순위를 선택하세요</div>
                <div class="words">
                <input type="radio" name="sequence" value="1" checked="checked" id="op1"><label for="op1">1순위</label>
                <input type="radio" name="sequence" value="2" id="op2"><label for="op2">2순위</label>
                <input type="radio" name="sequence" value="3" id="op3"><label for="op3">3순위</label>
                </div>

                <div class="buttons">
            
                    <button type="button"  id="back" class="left" onclick="location.href='main'">이전</button>
                    <input type="reset" id="reset" class="right" value="내용지우기"></input>
                    <input type="submit" id="submit" class="right" value="제출"></input>
                </div>
            
            </div>
        </form>
    </div>
</body>
</html>