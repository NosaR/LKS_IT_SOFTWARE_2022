<?php
$serverName = "DESKTOP-OGQRBL1\RPL_123"; //serverName\instanceName

// Since UID and PWD are not specified in the $connectionInfo array,
// The connection will be attempted using Windows Authentication.

$connectionInfo = array( "Database"=>"db_restaurant_2");
$conn = sqlsrv_connect( $serverName, $connectionInfo);

$id_pesanan = $_POST["Idpesanan"];
$id_menu = $_POST["Idmenu"];
$id_pelanggan = $_POST["Idpelanggan"];
$jumlah = $_POST["Jumlah"];
$id_user = $_POST["Iduser"];

$sql = "INSERT INTO pesanan3 (Idpesanan,Idmenu,Idpelanggan,Jumlah,Iduser) VALUES('".$id_pesanan."', '".$id_menu."', '".$id_pelanggan."', '".$jumlah."', '".$id_user."')";
$result = sqlsrv_query($conn, $sql);

if ($sql) {
    echo "Data Berhasil Disimpan";
} else {
    echo "Data Gagal Disimpan";
}

?>