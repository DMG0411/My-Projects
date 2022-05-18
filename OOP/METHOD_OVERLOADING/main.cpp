#include "Math.cpp"

void main()
{
	// Adding 2 integers ->
	printf("%d\n", Math::Add(2, 6));

	// Adding 3 integers ->
	printf("%d\n", Math::Add(2, 5, 7));

	// Adding 2 double that returns integer value ->
	printf("%d\n", Math::Add(3.1, 5.7));

	// Adding 3 double that returns integer value ->
	printf("%d\n", Math::Add(3.1, 5.5, 8.1));

	// Multiplicate 2 integers ->
	printf("%d\n", Math::Mul(2, 6));

	// Multiplicate 3 integers ->
	printf("%d\n", Math::Mul(2, 5, 7));

	// Multiplicate 2 double that returns integer value ->
	printf("%d\n", Math::Mul(3.1, 5.7));

	// Multiplicate 3 double that returns integer value ->
	printf("%d\n", Math::Mul(3.1, 5.5, 8.1));


	// Sums a list of defined number of integers, short or double values and returns integer value
	// Syntax: Add(number of the list elements, a1, a2, ...); ->
	printf("%d\n", Math::Add(7, 5, 5.1, 7, 2, 8, 1, 5, 6));

}