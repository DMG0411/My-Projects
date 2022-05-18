#include "ATM.h"

void ATM_Menu()
{
	printf("%s\n", "^^^^^^Welcome to your bank account!^^^^^^");
	printf("%s\n", "1. Check your balance");
	printf("%s\n", "2. Deposit money");
	printf("%s\n", "3. Withdraw money");
	printf("%s\n", "4. Cancel tranzaction");
}

int main() 
{
	int option;
	ATM u;
	u.SetInitialBalance(800);
	do 
	{
		ATM_Menu();
		printf("%s", "Choose and option:");
		std::cin >> option;
		printf("%s\n","");
		switch (option)
		{
		case 1:
			u.CheckBalance();
			printf("%s\n", "");
			break;
		case 2:
			int deposit_money;
			printf("%s", "Please insert the amount of money you want to deposit:");
			std::cin >> deposit_money;
			printf("%s\n", "Your money have been deposited succesfully!");
			u.Deposit(deposit_money);
			printf("%s\n", "");
			break;
		case 3:
			int withdraw_money;
			printf("%s\n", "Please insert the amount of money you want to withdraw:");
			std::cin >> withdraw_money;
			printf("%s\n", "");
			if (withdraw_money > u.GetBalance())
			{
				printf("%s\n", "Sorry, unsufficient founds, please try again or return to the main menu");
				int choose;
				printf("%s\n", "Choose one option:");
				printf("%s\n", "1. Try to enter another amount of money or 0 to return to main menu");
				printf("%s\n", "2. Return to main menu");
				std::cin >> choose;
				if (choose < 1 || choose > 2)
				{
					printf("%s\n", "You've entered an invalid option, returning to the main menu");
				}
				else
				{
					switch (choose)
					{
					case 1:
						while (withdraw_money > u.GetBalance() && withdraw_money != 0)
						{
							printf("%s", "Please insert the amount of money you want to withdraw:");
							std::cin >> withdraw_money;
							printf("%s\n", "");
							if (withdraw_money == 0)
							{
								break;
							}
							else if (withdraw_money < u.GetBalance())
							{
								printf("%s\n", "You withdrew your money succesfully!");
								u.Withdraw(withdraw_money);
							}
						}break;
					case 2:
						break;
					}
					break;
				}
			}
			else 
			{
				printf("%s\n", "You withdrew your money succesfully!");
				u.Withdraw(withdraw_money);
			}
			printf("%s\n", "");
			break;
		case 4:
			u.Exit();
			printf("%s\n", "");
			break;
		}
	} while (option > 0 && option < 4);
	if (option < 0 || option > 4)
	{
		printf("%s\n", "You've entered an invalid option, the action will be canceled!");
	}
	return 0;
}