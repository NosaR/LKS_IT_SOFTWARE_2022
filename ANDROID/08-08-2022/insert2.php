<?php

$serverName = "DESKTOP-OGQRBL1\RPL_123"; //serverName\instanceName

// Since UID and PWD are not specified in the $connectionInfo array,
// The connection will be attempted using Windows Authentication.

$connectionInfo = array( "Database"=>"db_restaurant_2");
$conn = sqlsrv_connect( $serverName, $connectionInfo);

$id_menu = isset($_POST['Idmenu']) ? $_POST['Idmenu'] : '';
$nama_menu = isset($_POST['Namamenu']) ? $_POST['Namamenu'] : '';
$harga = isset($_POST['Harga']) ? $_POST['Harga'] : '';

if (empty($nama_menu) || empty($harga)) {
    echo "Data Tidak Boleh Kosong";
} else if (empty($id_menu)) {
    $query = sqlsrv_query($conn, "INSERT INTO menu (Idmenu,Namamenu,Harga) VALUES (0, '".$nama_menu."', '".$harga."')");

    if ($query) {
        echo "Data Berhasil Disimpan";
    } else {
        echo "Data Gagal Disimpan";
    }
} else {
    $query = sqlsrv_query($conn, "UPDATE menu SET Namamenu ='".$nama_menu."', Harga='".$harga."' WHERE Idmenu='".$id_menu."'");

    if ($query) {
        echo "Data Berhasil Diubah";
    } else {
        echo "Data Gagal Diubah";
    }
}

?>