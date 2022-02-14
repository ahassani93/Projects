#include "libraryheader.h"

int main()
{
	Book_Rec br_object;

	ifstream Infdb;

	Infdb.open("librarydatabase.txt");

	cout << fixed << showpoint << setprecision(2);

	br_object.Load_Book_Data(Infdb);

	while (Infdb && Infdb.peek() != ' ')
	{
		br_object.Load_Book_Data(Infdb);
	}

	Infdb.close();

	br_object.selectionSort();

	ifstream Inftr;

	Inftr.open("librarytransactions.txt");

	br_object.Process_Transactions(Inftr);

	while (Inftr)
	{
		br_object.Process_Transactions(Inftr);
	}

	return 0;
}