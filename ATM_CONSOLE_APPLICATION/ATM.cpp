#include "ATM.h"

void ATM::SetInitialBalance(int y) {
	balance = y;
	printf("%s", "Initial account balance is:");
	printf("%d", balance);
	printf("%s\n", "$.");
}

int ATM::GetBalance()
{
	return balance;
}

void ATM::CheckBalance() {
	printf("%s", "Your account balance is:");
	printf("%d", balance);
	printf("%s\n", "$.");
}

void ATM::Deposit(int y) {
	balance = balance + y;
	printf("%s", "Your new account balance is:");
	printf("%d", balance);
	printf("%s\n", "$.");
}

void ATM::Withdraw(int y) {
	balance = balance - y;
	printf("%s", "Your new account balance is:");
	printf("%d", balance);
	printf("%s\n", "$.");
}

void ATM::Exit()
{
	printf("%s\n", " Please remove your card from the ATM.");
	printf("%s", "Have a nice day!");
}
