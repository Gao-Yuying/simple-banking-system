# simple-banking-system
A simple banking system with database.
## Supported Operations
The symbol `>` represents the user input. Notice that it's not a part of the input.
### Create account
When the customer chooses 'Create account,' a new credit card number and its PIN is generated and stored to database.
````
1. Create account
2. Log into account
0. Exit
>1

Your card have been created
Your card number:
4000007890000067
Your card PIN:
8369

````
When generating card information, these conditions are satisfied:
- The card number is unique and has 16 digits.
- First 6 digits of card number (IIN) is 400000.
- The 7th–9th digits of card number are 789 (just for fun.)
- The 10th–15th digits represent ID of the customer, which is an auto-increment value starting with 1.
- The last digit (checksum) is calculated using [the Luhn Algorithm.](https://en.wikipedia.org/wiki/Luhn_algorithm)
- The PIN is a random 4-digit number between 0000–9999.

### Log in
When the customer chooses ‘Log into account,’ he's asked to enter card information. The customer could manage his account after logging in successfully.
````
1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000007890000000
Enter your PIN:
>8369

Wrong card number or PIN!

1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000007890000067
Enter your PIN:
>2333

Wrong card number or PIN!

1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000007890000067
Enter your PIN:
>8369

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit

````
### Log out
When the customer chooses ‘Log out,’ he returns to the main menu.
````
1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>5

You have successfully logged out!

1. Create account
2. Log into account
0. Exit

````
### Get balance
Displays the account balance. The balance of a new account is 0.
````
1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 0

````
### Add income
Allows the customer to deposit money to the account.
````
1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 0

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>2

Enter income:
5000
Income was added!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 5000

````
### Do transfer
Allows transferring money to another account. It handles following errors:

- If the user tries to transfer more money than he/she has, it outputs: "`Not enough money!`"
- If the user tries to transfer money to the same account, it outputs: “`You can't transfer money to the same account!`”
- If the receiver's card number doesn’t pass the Luhn algorithm, it outputs: “`Probably you made mistake in the card number. Please try again!`”
- If the receiver's card number doesn’t exist, it outputs: “`Such a card does not exist.`”
- If there is no error, it asks the user how much money they want to transfer and make the transaction.

````
1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000007890000075
Enter your PIN:
>5304

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 5000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000007890000075
You can't transfer money to the same account!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000007890000066
Probably you made mistake in the card number. Please try again!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000007890000067
Enter how much money you want to transfer:
>9999
Not enough money!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000007890000067
Enter how much money you want to transfer:
>4999
Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 1

````
### Close account
Deletes current account from the database.
````
1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000007890000075
Enter your PIN:
>5304

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>4
The account has been closed!

1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000007890000075
Enter your PIN:
>5304

Wrong card number or PIN!

````
### Exit
Exits the program.
````
1. Create account
2. Log into account
0. Exit
>0

Bye!

Process finished with exit code 0

````
