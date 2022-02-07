<?php
class DatabaseConnection
{
    private $connection;

    public function openDatabase()
    {
        try
        {
            $dbHost = ''; //Database host
            $dbName = 'form_validation'; //Database name
            $dbUser = ''; //Database user
            $dbPass = ''; //Database password
      
            $this->connection = new PDO('mysql:host=' . $dbHost . ';dbname=' . $dbName, $dbUser, $dbPass);
            $this->connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
            $this->connection->setAttribute(PDO::MYSQL_ATTR_USE_BUFFERED_QUERY, true);
            $this->connection->setAttribute(PDO::ATTR_AUTOCOMMIT, true);
            $this->connection->setAttribute(PDO::MYSQL_ATTR_LOCAL_INFILE, true);
            $this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      
            return $this->connection;
        }
        catch(PDOException $Exception)
        {
            echo $Exception->getMessage();
        }
    }
}
?>