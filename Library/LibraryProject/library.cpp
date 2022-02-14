#include "libraryheader.h"

Book_Rec::Book_Rec()
{
	max_size = 100;
	number_of_books = 0;
	book_index = 0;
}

string Book_Rec::format_isbn(string temp_isbn)
{
	string isbn = "";

	for (int i = 0; i < temp_isbn.size(); i++)
	{
		if (temp_isbn[i] != '-')
		{
			isbn += temp_isbn[i];
		}
	}

	return isbn;
}

bool Book_Rec::Load_Book_Data(ifstream& Infdb)
{
	if (number_of_books >= max_size)
	{
		cout << "No more room for books.\n";

		return false;
	}
	else
	{
		string temp_title;

		getline(Infdb, temp_title);

		if (temp_title != "" && temp_title != " ")
		{
			book_records_array[book_index].title = temp_title;

			Infdb >> book_records_array[book_index].number_of_authors;

			Infdb.ignore(10, '\n');

			if (book_records_array[book_index].number_of_authors > 4 ||
				book_records_array[book_index].number_of_authors <= 0)
			{
				cout << "Number of authors can't be zero, "
					<< "negative, or more than 4.\n";
			}
			else
			{
				for (int i = 0; i <
					book_records_array[book_index].number_of_authors; i++)
				{
					getline(Infdb,
						book_records_array[book_index].authors_array[i]);
				}
			}

			getline(Infdb, book_records_array[book_index].publisher);

			getline(Infdb, book_records_array[book_index].isbn);

			book_records_array[book_index].isbn =
				format_isbn(book_records_array[book_index].isbn);

			for (int i = 0; i < number_of_books; i++)
			{
				if (i != book_index && book_records_array[book_index].isbn ==
					book_records_array[i].isbn)
				{
					cout << "This book already exists in the database.\n";
				}
			}

			Infdb >> book_records_array[book_index].price;

			if (book_records_array[book_index].price < 0)
			{
				cout << "Price can't be negative, "
					<< "so the positive value will be assumed.\n";

				book_records_array[book_index].price =
					book_records_array[book_index].price * -1;
			}

			Infdb >> book_records_array[book_index].number_of_copies;

			if (book_records_array[book_index].number_of_copies < 0)
			{
				cout << "Number of copies can't be negative, "
					<< "so the positive number will be assumed.\n";

				book_records_array[book_index].number_of_copies =
					book_records_array[book_index].number_of_copies * -1;
			}

			Infdb.ignore(10, '\n');

			book_index++;
			number_of_books++;

			return true;
		}
	}
}

bool Book_Rec::Return_Book_Data(string isbn_to_find)
{
	bool found = false;

	for (int i = 0; i < number_of_books; i++)
	{
		if (book_records_array[i].isbn == isbn_to_find)
		{
			found = true;

			cout << "Title: " << book_records_array[i].title << "\n";

			cout << "Authors: ";

			for (int j = 0; j < book_records_array[i].number_of_authors; j++)
			{
				cout << book_records_array[i].authors_array[j] << " ";
			}

			cout << "\nPublisher: "
				<< book_records_array[i].publisher << "\n";
			cout << "ISBN: "
				<< book_records_array[i].isbn << " ";
			cout << "Price: "
				<< book_records_array[i].price << " ";
			cout << "Number of Copies: "
				<< book_records_array[i].number_of_copies << "\n\n";
		}
	}

	return found;
}

bool Book_Rec::Return_Number_of_Copies(string isbn_to_find)
{
	bool found = false;

	for (int i = 0; i < number_of_books; i++)
	{
		if (book_records_array[i].isbn == isbn_to_find)
		{
			found = true;

			cout << "there are "
				<< book_records_array[i].number_of_copies
				<< " copies.\n";
		}
	}

	return found;
}

bool Book_Rec::Return_ISBN(string title_to_find)
{
	bool found = false;

	for (int i = 0; i < number_of_books; i++)
	{
		if (book_records_array[i].title == title_to_find)
		{
			found = true;

			cout << "For the book: " << book_records_array[i].title
				<< " the ISBN is " << book_records_array[i].isbn << "\n";
		}
	}

	if (found == false)
	{
		cout << title_to_find << " not found.\n";
	}

	return found;
}

bool Book_Rec::Set_Number_of_Copies(string isbn_to_find,
	int quantity_to_set)
{
	bool found = false;

	for (int i = 0; i < number_of_books; i++)
	{
		if (book_records_array[i].isbn == isbn_to_find)
		{
			found = true;

			book_records_array[i].number_of_copies = quantity_to_set;

			cout << "For " << isbn_to_find << " there are now "
				<< quantity_to_set << " copies.\n";
		}
	}

	if (found == false)
	{
		cout << isbn_to_find << " not found.\n";
	}

	return found;
}

bool Book_Rec::Add_to_Number_of_Copies(string isbn_to_find,
	int quantity_to_add)
{
	bool found = false;

	for (int i = 0; i < number_of_books; i++)
	{
		if (book_records_array[i].isbn == isbn_to_find)
		{
			found = true;

			book_records_array[i].number_of_copies += quantity_to_add;

			cout << "For " << isbn_to_find << " we added "
				<< quantity_to_add << " copies.\n";
		}
	}

	if (found == false)
	{
		cout << isbn_to_find << " not found.\n";
	}

	return found;
}

bool Book_Rec::Subtract_from_Number_of_Copies(string isbn_to_find,
	int quantity_to_subtract)
{
	bool found = false;

	for (int i = 0; i < number_of_books; i++)
	{
		if (book_records_array[i].isbn == isbn_to_find)
		{
			found = true;

			if (quantity_to_subtract >
				book_records_array[i].number_of_copies)
			{
				cout << "This subtraction can't be done, "
					<< "because it will result in a "
					<< "negative number of copies.\n";
			}
			else
			{
				book_records_array[i].number_of_copies -=
					quantity_to_subtract;

				cout << "For " << isbn_to_find << " we subtracted " 
					<< quantity_to_subtract << " copies.\n";
			}
		}
	}

	if (found == false)
	{
		cout << isbn_to_find << " not found.\n";
	}

	return found;
}

void Book_Rec::Process_Transactions(ifstream& Inftr)
{
	char command;

	Inftr >> command;

	switch (command)
	{
	case 'P':
	{
		for (int i = 0; i < number_of_books; i++)
		{
			Return_Book_Data(book_records_array[i].isbn);
		}
		break;
	}
	case 'T':
	{
		string title_to_process;

		Inftr.ignore(1, ' ');

		getline(Inftr, title_to_process);

		bool found = false;

		for (int i = 0; i < number_of_books; i++)
		{
			if (book_records_array[i].title == title_to_process)
			{
				found = true;

				cout << "For the book: " << title_to_process << " ";

				Return_Number_of_Copies(book_records_array[i].isbn);
			}
		}

		if (found == false)
		{
			cout << title_to_process << " not found.\n";
		}

		cout << "\n";

		break;
	}
	case 'I':
	{
		string isbn_to_process;

		Inftr >> isbn_to_process;

		bool found = false;

		for (int i = 0; i < number_of_books; i++)
		{
			if (book_records_array[i].isbn == format_isbn(isbn_to_process))
			{
				found = true;

				cout << "For " << format_isbn(isbn_to_process) << " ";

				Return_Number_of_Copies(format_isbn(isbn_to_process));
			}
		}

		if (found == false)
		{
			cout << format_isbn(isbn_to_process) << " not found.\n";
		}

		cout << "\n";

		break;
	}
	case 'S':
	{
		string isbn_to_process;

		int set_quantity_to_process;

		Inftr >> isbn_to_process >> set_quantity_to_process;

		if (set_quantity_to_process < 0)
		{
			cout << "This transaction can't be done, "
				<< "because the number of copies to set is negative.\n";
		}
		else
		{
			Set_Number_of_Copies(isbn_to_process,
				set_quantity_to_process);
		}

		cout << "\n";

		break;
	}
	case 'R':
	{
		string isbn_to_process;

		int subtract_quantity_to_process;

		Inftr >> isbn_to_process >> subtract_quantity_to_process;

		Subtract_from_Number_of_Copies(format_isbn(isbn_to_process),
			subtract_quantity_to_process);

		cout << "\n";

		break;
	}
	case 'A':
	{
		string isbn_to_process;

		int add_quantity_to_process;

		Inftr >> isbn_to_process >> add_quantity_to_process;

		Add_to_Number_of_Copies(format_isbn(isbn_to_process),
			add_quantity_to_process);

		cout << "\n";

		break;
	}
	case 'N':
	{
		string title;
		int number_of_authors;
		string authors_array[4];
		string publisher;
		string isbn;
		string temp_isbn;
		double price;
		int number_of_copies;

		Inftr.ignore(10, '\n');

		getline(Inftr, title);

		Inftr >> number_of_authors;

		Inftr.ignore(10, '\n');

		if (number_of_authors > 4 || number_of_authors <= 0)
		{
			cout << "Number of authors can't be zero, "
				<< "negative, or more than 4.\n";
		}

		for (int i = 0; i < number_of_authors; i++)
		{
			getline(Inftr, authors_array[i]);
		}

		getline(Inftr, publisher);

		getline(Inftr, temp_isbn);

		isbn = format_isbn(temp_isbn);

		Inftr >> price >> number_of_copies;

		if (number_of_copies < 0)
		{
			number_of_copies = 0;
		}

		Inftr.ignore(10, '\n');

		int current = 0;

		if (number_of_books >= max_size)
		{
			cout << "No more room for books.\n";
		}
		else
		{
			while (current < number_of_books &&
				book_records_array[current].title < title)
			{
				current++;
			}

			if (current != number_of_books &&
				book_records_array[current].title == title)
			{
				cout << "This book already exists in the database.\n";
			}

			for (int x = number_of_books; x > current; x--)
			{
				book_records_array[x] = book_records_array[x - 1];
			}

			book_records_array[current].title = title;

			book_records_array[current].number_of_authors =
				number_of_authors;

			for (int y = 0; y < number_of_authors; y++)
			{
				book_records_array[current].authors_array[y] =
					authors_array[y];
			}

			book_records_array[current].publisher = publisher;

			book_records_array[current].isbn = isbn;

			book_records_array[current].price = price;

			book_records_array[current].number_of_copies =
				number_of_copies;

			cout << "Added the new book: " << title << "\n";

			number_of_books++;
		}

		cout << "\n";

		break;
	}
	case 'W':
	{
		string author_to_process;

		Inftr.ignore(1, ' ');

		getline(Inftr, author_to_process);

		cout << "Book list for " << author_to_process << ":\n";

		for (int i = 0; i < number_of_books; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (book_records_array[i].authors_array[j] ==
					author_to_process)
				{
					cout << book_records_array[i].title << "\n";
				}
			}
		}

		cout << "\n";

		break;
	}
	case 'F':
	{
		bool found = false;

		string title_to_process;

		Inftr.ignore(1, ' ');

		getline(Inftr, title_to_process);

		for (int i = 0; i < number_of_books; i++)
		{
			if (book_records_array[i].title == title_to_process)
			{
				found = true;

				cout << "Title: " << book_records_array[i].title << "\n";

				cout << "Authors: ";

				for (int j = 0; j <
					book_records_array[i].number_of_authors; j++)
				{
					cout << book_records_array[i].authors_array[j] << " ";
				}

				cout << "\nPublisher: "
					<< book_records_array[i].publisher << "\n";
				cout << "ISBN: "
					<< book_records_array[i].isbn << " ";
				cout << "Price: "
					<< book_records_array[i].price << " ";
				cout << "Number of Copies: "
					<< book_records_array[i].number_of_copies << "\n";
			}
		}

		if (found == false)
		{
			cout << title_to_process << " not found.\n";
		}

		cout << "\n";

		break;
	}
	case 'B':
	{
		string title_to_process;

		Inftr.ignore(1, ' ');

		getline(Inftr, title_to_process);

		Return_ISBN(title_to_process);

		cout << "\n";

		break;
	}
	default:
		cout << "";
		break;
	}
}

void Book_Rec::selectionSort()
{
	Book temp_array;

	int small = 0;

	for (int i = 0; i < number_of_books - 1; i++)
	{
		small = i;

		for (int j = i + 1; j < number_of_books; j++)
		{
			if (book_records_array[j].title <
				book_records_array[small].title)
			{
				small = j;
			}
		}

		temp_array = book_records_array[i];

		book_records_array[i] = book_records_array[small];

		book_records_array[small] = temp_array;
	}
}