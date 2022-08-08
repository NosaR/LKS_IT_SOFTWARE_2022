<?php
// Koneksi ke database
$db['host'] = "localhost"; //host
$db['user'] = "root"; //username database
$db['pass'] = ""; //password database
$db['name'] = "api_data"; //nama database
$mysqli = mysqli_connect($db['host'], $db['user'], $db['pass'], $db['name']);

if (mysqli_connect_errno()){
	echo "Koneksi database gagal : " . mysqli_connect_error();
}