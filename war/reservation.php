<?php 
require_once('php/sql_states.php');
session_start();

$param = $_POST['content'];
$lang = $_POST['lang'];
$session_id =  $_SESSION['benutzer_id'];

$from = $_POST['from'];
$until = $_POST['until'];
$fromtime = $_POST['fromtime'];
$untiltime = $_POST['untiltime'];

$result = mysql_query('SELECT * FROM navigation WHERE id = '.$param.'');
while($row = mysql_fetch_array($result)){
	$subnavid = $row["subnavid"];    
	$mainnavid = $row["mainnavid"];   
	
	if ($subnavid == 0) {
		$result2 = mysql_query('SELECT * FROM navigation WHERE mainnavid = '.$mainnavid.' AND subnavid = 1');
		while($row2 = mysql_fetch_array($result2))
		{$title = $row2[$lang];}
	}
	else {
		$title = $row[$lang];	
	}
}

if ($lang == 'de') {
	$txt_from = "von";
	$txt_until = "bis";
	$txt_uhr = "Uhr";
	$txt_errorlogin = "Sie müssen sich erst einloggen, bevor Sie eine Reservierung tätigen können";
	$finish = "Danke für Ihre Reservierung";
} else {
	$txt_from = "from";
	$txt_until = "until";
	$txt_uhr = "o'clock";
	$txt_errorlogin = "Please login to reserve";
	$finish = "Thanks for your reservation";
}



if (isset($from) AND isset($until) AND isset($fromtime) AND isset($untiltime))
{
	$eintrag = "INSERT INTO reservations (user, object, von, bis, fromtime, untiltime) VALUES ('".$session_id."','".$title."','".$from."','".$until."','".$fromtime."','".$untiltime."')";
	$eintragen = mysql_query($eintrag);
	
	echo "<h1>".$finish."</h1>";
}
else {	
	
	echo "<h1>".$title."</h1>";
	
	if ($session_id != ""){
		$timestamp = time();
		$uhrzeit = date("H",$timestamp)+1;
		echo "<div style='width: 600px; margin:auto;'>";
		echo '<form id="reservationform" action="" method="post" onsubmit="ajaxFormPost('."'reservation.php', 'seebadPHP', 'reservationform', 'content', '".$lang."', '', '".$param."'".');return false;">';
		echo "<table style='vertical-align:middle;' cellpadding='3px' cellspacing='0' border='0'>";
		echo "<tr><td>".$txt_from."</td><td>";
		echo "<select name='from'>";
		$i=0;
		while($i<=7){ 
		$datum = date("d.m.Y",$timestamp + $i*60*60*24);
		echo "<option>".$datum."</option>"; 
		$i++;
		} 
		echo "</select>";
		echo "</td>"; 
		echo "<td>";
		echo "<select name='fromtime'>";
		$i=$uhrzeit;
		while($i<=24){ 
		echo "<option>".$i."</option>"; 
		$i++;
			if ($i == 24)
				{$i = 0;}
			if ($i == $uhrzeit)
				{break;}
		}
		echo "</td><td>".$txt_uhr."</td>";
		echo "</tr>";
		echo "<tr><td>".$txt_until."</td><td>";
		echo "<select name='until'>"; 
		$i=0;
		while($i<=7){ 
		$datum = date("d.m.Y",$timestamp + $i*60*60*24);
		echo "<option>".$datum."</option>"; 
		$i++;
		} 
		echo "</select>";
		echo "</td>";
		echo "<td>";
		echo "<select name='untiltime'>";
		$i=$uhrzeit;
		while($i<=24){ 
		echo "<option>".$i."</option>"; 
		$i++;
			if ($i == 24)
				{$i = 0;}
			if ($i == $uhrzeit)
				{break;}
		}
		echo "</td><td>".$txt_uhr."</td>";
		echo "</tr>";
		echo"</table></form></div>";
		echo '<a class="button" name="register" id="register" onclick="'."$('#reservationform').submit();".'return false;" href="#">Reservieren</a>';
	} else {
		echo $txt_errorlogin;
	}
}
?>
<?php mysql_close($conn); ?>