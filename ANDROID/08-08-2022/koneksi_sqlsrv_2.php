<?php
$serverName = "DESKTOP-OGQRBL1\RPL_123"; //serverName\instanceName

// Since UID and PWD are not specified in the $connectionInfo array,
// The connection will be attempted using Windows Authentication.

$connectionInfo = array( "Database"=>"db_restaurant_2");
$conn = sqlsrv_connect( $serverName, $connectionInfo);

$sql = "SELECT * FROM transaksi";
$result = sqlsrv_query($conn, $sql);

$array = array();
    
    while($row = sqlsrv_fetch_array($result)) {
        $data = array(
            'Idtransaksi' => $row['Idtransaksi'],
            'Idpesanan' => $row['Idpesanan'],
            'Total' => $row['Total'],
            'Bayar' => $row['Bayar'],
            
        );
        array_push($array, $data);
    }

echo json_encode($array);
?>