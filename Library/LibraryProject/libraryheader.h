#pragma once

#include <iostream>
#include <fstream>
#include <string>
#include <iomanip>

using namespace std;

struct Book
{
	string title;
	int number_of_authors;
	string authors_array[4];
	string publisher;
	string isbn;
	double price;
	int number_of_copies;
};

class Book_Rec
{
public:

	Book_Rec();

	string format_isbn(string temp_isbn);

	bool Load_Book_Data(ifstream& Infdb);

	bool Return_Book_Data(string isbn_to_find);

	bool Return_Number_of_Copies(string isbn_to_find);

	bool Return_ISBN(string title_to_find);

	bool Set_Number_of_Copies(string isbn_to_find,
		int quantity_to_set);

	bool Add_to_Number_of_Copies(string isbn_to_find,
		int quantity_to_add);

	bool Subtract_from_Number_of_Copies(string isbn_to_find,
		int quantity_to_subtract);

	void Process_Transactions(ifstream& Inftr);

	void selectionSort();

private:

	int max_size;
	int number_of_books;
	int book_index;

	Book book_records_array[100];
};