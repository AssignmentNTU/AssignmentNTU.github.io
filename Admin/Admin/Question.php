<!DOCTYPE html>

<html lang="en">
<head>
  <title>Question By Admin</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>



<?php include_once("connect.php");
?>
<?php
  $reg = strip_tags(@$_POST['reg']);

  $Question      = strip_tags(@$_POST['Question']);
  $AnswerChoice1 = strip_tags(@$_POST['AnswerChoice1']);
  $AnswerChoice2 = strip_tags(@$_POST['AnswerChoice2']);
  $AnswerChoice3 = strip_tags(@$_POST['AnswerChoice3']);
  $AnswerChoice4 = strip_tags(@$_POST['AnswerChoice4']);
  $CorrectAnswer = strip_tags(@$_POST['CorrectAnswer']);
 
  $emailquery = mysql_query("SELECT Question FROM user WHERE Question='$Question'");
  if($reg && $emailquery == 0){
    if($Question && $AnswerChoice1 && $AnswerChoice2 
      && $AnswerChoice3  && $AnswerChoice4 && $CorrectAnswer ){
      $query = mysql_query("INSERT INTO question VALUES('','$Question','$AnswerChoice1','$AnswerChoice2'
        ,'$AnswerChoice3','$AnswerChoice4','$CorrectAnswer')"); 
    }
  }

  $result = mysql_query("SELECT * FROM question");
  $rows   = mysql_num_rows($result);
  echo "
<script type='text/javascript'>
  document.addEventListener('DOMContentLoaded',function(){
    console.log('test');
    document.getElementById('totalSoal').innerHTML = $rows;
  });
</script>
";
?>

<!-- 
Ini buat css file nya
-->
<style type="text/css">
 #header{
  position: fixed;
  top:0;
  background-color: #ff9900;
  width: 100%;
  height: 100px;
  padding-top: 10px;
  border-bottom: 2px solid #cbcbcb;
 }



#LogOut{
  cursor: pointer;
  margin-top: -20px;
  float: right;
  margin-right: 20px;
}
 .form-group{
  max-width: 40%;
 }

 form{
  padding: 10px;
 }

 form input{
  padding : 10px;
  width: 50%;
  margin: 10px;
 }

 

#loginButton{
  width: 35%;
}


.buttonSubmit{
  font-family:calibri; 
  letter-spacing:1.5px; 
  background-color:#ff9900; 
  border-radius:6px; 
  border:none;
  float:right;
  width: 35%;
  color: white;
  font-size: 22px;

}
input[type="submit"]:hover{
  background-color:#ff6203;
}

</style>



<script type="text/javascript">
//ini buat log out pen
$(document).ready(function(){
  $('#LogOut').click(function(){
      console.log("test clicked admin this");
      window.location = 'index.html';
    });
});
</script>



<body style="background-color: #ededed">

<div id="header">
  <h1 style="color:white; font-family:calibri; font-style:italic; letter-spacing:1.5px; text-align:center">
      Tambah Soal
    </h1>
</div>
<center>
  <div style="margin-top:150px">
    <h2 style="font-family:calibri; letter-spacing:1.5px; text-align:center">
      Jumlah total soal: <b id="totalSoal"></b>
    </h2> 
    <form id= "formEvents" method="POST" action="#" enctype="multipart/form-data"style="font-family:calibri; letter-spacing:1.5px" >
        <input name="Question" id="name_events" type="text" placeholder="Soal" style="font-family:calibri; letter-spacing:1.5px; height:100px;"/><br/>
        <input name="AnswerChoice1" id="Baquet" type="text" placeholder="Pilihan 1" style="font-family:calibri; letter-spacing:1.5px"/><br/>
        <input name="AnswerChoice2" id="Breakouts" type="text" placeholder="Pilihan 2" style="font-family:calibri; letter-spacing:1.5px"/><br/>
        <input name="AnswerChoice3" id="NumberGuest" type="text" placeholder="Pilihan 3" style="font-family:calibri; letter-spacing:1.5px"/><br/>  
        <input name="AnswerChoice4" id="MeetAndFeed" type="text" placeholder="Pilihan 4" style="font-family:calibri; letter-spacing:1.5px"/><br/>
        <input name="CorrectAnswer" id="MeetingSpace" type="text" placeholder="Jawaban benar" style="font-family:calibri; letter-spacing:1.5px"/><br/>
        <table style="width:100%">
          <td style="width:50%">
            <input type="submit" name="reg" class="buttonSubmit" id="SubmitQuestion" value="TAMBAH" /> 
          </td>
          <td style="width:50%">
            <a href="AdminHome.html"
            <button class="btn btn-lg btn-primary btn-block" id="loginButton" onclick = login() style="font-family:calibri; letter-spacing:1.5px">
              SELESAI
            </button>
            <a href="AdminHome.html"
          </td>
        </table>
            
        <div id="post"></div>
    </form>
  </div>
</center>
</body>
</html>