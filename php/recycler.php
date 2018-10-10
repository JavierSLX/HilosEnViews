<?php
	require_once("mysql.php");
	
	$db = connectDB();
	$query = "SELECT * FROM cliente";

	$resultado = mysqli_query($db, $query);
	$arreglo = array();
	while($row = mysqli_fetch_assoc($resultado))
		$arreglo[] = $row;

	echo json_encode($arreglo);
?>