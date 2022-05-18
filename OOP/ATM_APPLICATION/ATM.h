#pragma once
#include <iostream>
class ATM
{
	int balance;
public:
	void SetInitialBalance(int y); // Will set a beggining balance of Y amount of $
	int GetBalance(); // Will return the balance of an user
	void CheckBalance(); // Will print the balance of user
	void Deposit(int y); // Will deposit in the user account, Y amount of $
	void Withdraw(int y); // Will withdraw from the user account, Y amount of $
	void Exit(); // Will close the ATM

	
};