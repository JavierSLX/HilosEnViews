<?php
	require_once("mysql.php");
	
	$db = connectDB();
	$query = "SELECT nombre FROM carrier";

	$resultado = mysqli_query($db, $query);
	$arreglo = array();
	while($row = mysqli_fetch_assoc($resultado))
		$arreglo[] = $row;

	echo json_encode($arreglo);
?>