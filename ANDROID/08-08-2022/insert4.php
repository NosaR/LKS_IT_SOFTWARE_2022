<?php
$serverName = "DESKTOP-OGQRBL1\RPL_123"; //serverName\instanceName

// Since UID and PWD are not specified in the $connectionInfo array,
// The connection will be attempted using Windows Authentication.

$connectionInfo = array( "Database"=>"db_restaurant_2");
$conn = sqlsrv_connect( $serverName, $connectionInfo);

$nama_menu = $_POST["Namamenu"];
$harga = $_POST["Harga"];

$sql = "INSERT INTO menu (Namamenu,Harga) VALUES('".$nama_menu."', '".$harga."')";
$result = sqlsrv_query($conn, $sql);

if ($sql) {
    echo "Data Berhasil Disimpan";
} else {
    echo "Data Gagal Disimpan";
}

?>