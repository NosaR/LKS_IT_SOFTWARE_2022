<?php
header('Access-Control-Allow-Origin: *');
include "koneksi_sqlsrv.php";
//$conn = new mysqli('localhost', 'root', '', 'api_data');

$id=strtoupper($_GET['id']);
$sql = "SELECT * FROM kendaraan where nopol='$id'";
$result = mysqli_query($mysqli, $sql);

$array = array();
if (mysqli_num_rows($result) > 0) {
    
    while($row = mysqli_fetch_array($result)) {
        $data = array(
            'nopol' => $row['nopol'],
            'warna' => $row['warna'],
            'noka' => $row['noka'],
            'nosin' => $row['nosin'],
            'pemilik' => $row['pemilik'],
            'nomor_lp' => $row['nomor_lp'],
            'tempat_kejadian' => $row['tempat_kejadian'],
            'waktu_kejadian' => $row['waktu_kejadian'],
            'tempat_dilaporkan' => $row['tempat_dilaporkan'],
            
        );
        array_push($array, $data);
    }
}

echo json_encode($array);