<?php
$serverName = "DESKTOP-OGQRBL1\RPL_123"; //serverName\instanceName

// Since UID and PWD are not specified in the $connectionInfo array,
// The connection will be attempted using Windows Authentication.

$connectionInfo = array( "Database"=>"db_restaurant_2");
$conn = sqlsrv_connect( $serverName, $connectionInfo);

$sql = "SELECT * FROM menu";
$result = sqlsrv_query($conn, $sql);

$array["menu"] = array();

    while ($row = sqlsrv_fetch_array($result)) {
        array_push($array["menu"], 
        $data = array(
            'Idmenu' => $row["Idmenu"],
            "Namamenu" => $row["Namamenu"],
            "Harga" => $row["Harga"],
        ));
    }
echo json_encode($array);

?>