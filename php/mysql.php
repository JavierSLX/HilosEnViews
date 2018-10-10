<?php
	//Abre la conexion a la base de datos de enlace
    function connectDB()
    {
        $db = new mysqli('187.189.152.4', 'javiersl', 'javiersl', 'recargasprocel');

        if($db->connect_error)
            die();
        else
        {
            $db->set_charset("utf8");
            return $db;
        }
    }
?>