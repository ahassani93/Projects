<?php
require_once __DIR__.'/DatabaseConnection.php';

date_default_timezone_set('America/Detroit');

class PdoUtils extends DatabaseConnection
{
	private $statementObj;
	private $connection;
	private $db;
	private $error;

	//A method that connects to the database
	private function dbConnect()
	{
		$this->db = new DatabaseConnection();
		$this->connection = $this->db->openDatabase();
	}

	//A method that creates the bindings
	private function Bind($bindings)
	{
		foreach ($bindings as $bindee)
		{
			switch($bindee[2])
			{
				case 'int' : $this->statementObj->bindParam($bindee[0],$bindee[1], PDO::PARAM_INT);
				case 'str' : $this->statementObj->bindParam($bindee[0],$bindee[1], PDO::PARAM_STR);
			}
		}
	}

	//A method that executes each statement and catches errors, if any
	private function executeStatement()
	{
		try
		{
			$this->statementObj->execute();
		}
		catch (PDOException $Exception)
		{
			$error = $Exception->getMessage();
			echo $error;
			$this->error = true;
		}
	}

	//A method for all select queries with bindings
	public function boundQuery($sql, $bindings)
	{
		$this->error = false;
		$this->dbConnect();
		$this->statementObj = $this->connection->prepare($sql);
		$this->Bind($bindings);
		$this->executeStatement();
		$this->connection = null;

		if(!$this->error)
		{
			return $this->statementObj->fetchAll(PDO::FETCH_ASSOC);
		}
		else
		{
			return 'error';
		}
	}

	//A method for all select queries without bindings
	public function unboundQuery($sql)
	{
		$this->error = false;
		$this->dbConnect();
		$this->statementObj = $this->connection->prepare($sql);
		$this->executeStatement();
		$this->connection = null;

		if(!$this->error)
		{
			return $this->statementObj->fetchAll(PDO::FETCH_ASSOC);
		}
		else
		{
			return 'error';
		}
	}

	//A method for all other queries such as insert, update, delete
	public function boundInsertUpdateDelete($sql, $bindings)
	{
		$this->error = false;
		$this->dbConnect();
		$this->statementObj = $this->connection->prepare($sql);
		$this->Bind($bindings);
		$this->executeStatement();
		$this->connection = null;

		if(!$this->error)
		{
			return 'no error';
		}
		else
		{
			return 'error';
		}
	}
}
?>