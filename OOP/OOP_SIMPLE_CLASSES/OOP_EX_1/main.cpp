#include "NumberList.h"

void main()
{
	NumberList::Init(); // Initializing the counter with 0
	NumberList::Add(6); // Adding numbers to the array, max to 10 | counter = 1
	NumberList::Add(7); // counter = 2
	NumberList::Add(17); // counter = 3
	NumberList::Add(12); // counter = 4
	NumberList::Add(2); // counter = 5
	NumberList::Add(5); // counter = 6
	NumberList::Add(9); // counter = 7
	NumberList::Add(1); // counter = 8
	NumberList::Add(51); // counter = 9
	NumberList::Add(3); // counter = 10
	NumberList::Add(23); // counter will be 11 but it will exceed 10 so the number won't be added;
	printf("%s", "The unsorted array: ");
	NumberList::Print(); // Print method
	printf("s", "The sorted array: ");
	NumberList::Sort(); // Sorting method
	NumberList::Print();
}