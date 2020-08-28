This is the phase 1 project for CSC207Y1 (summer) Software Design.

Project Title: CSC207 Phase1 Project
This is a program that allows users to trade different items with each other.

Prerequisites: intellij.
Before running the program, please make sure that the txt files (4 altogether) are empty and with no blank space.
All files should be in a package named phase1 in the original location relative phase1.
Please do NOT quit the whole program or stop the execution of program when running tests, follow the menu to exit instead.

Running the tests:
For running the program, open class Main in package Main, and click the "run" button.
The main menu UI will appear at the bottom (in terminal)
To test all the available functionality, you need to:
         register at least one ordinary user account into the system
         (ps: The first admin will be automatically created with name "admin" and password "123")
         (ps: Admin users are able to perform ordinary functionalities and other admin functionalities)
         add items into at least two users' lend wish list.

(user should input the serial number (0,1,2,etc.) only to select a menu option)
[main menu]
quit(0): stop 'main' execution

register(2): register an account.
         --> enter username (comma, period, and semicolon are not allowed for username)
             and password (only numbers and letters are allowed for password, else might cause errors.)
NonAdmin login(1): contains all user functions in the program. (Enter correct user name and password to login)
         [login menu]
         --> Edit information(1)
             --> change password (change password or exit)(1)
             --> user freeze system:
                 -->request to remove freeze: request to unfreeze with reasons; option appears only when the account is frozen.
             --> exit: exit to main menu(0)
         --> Trade(2): testable after setting up at least one trade.
             [trade menu]
             --> confirm trades(1): choose an unconfirmed trade to make edition.
                 --> show all unconfirmed trade and enter trade system(input the number before trade id).
             --> complete trade(2): choose an incomplete trade to make edition.
                 --> show all incomplete trade and enter trade system(input the number before trade id).
             --> Trade History(3)
                 --> show all complete trade.
             --> quit(0)
         --> Inventory(3)
             [Inventory menu]
             --> Lend wishes: view a list of items that the user wants to lend
             --> Borrow wishes: view a list of items that the user wants to borrow, entry point of trade system.
                 --> enter [Trade system menu](input item name): one can set up trade only if there are items in borrow wish list.
             --> Edit lend wishes:
                 [edit lend wishes menu]
                 --> add wish: input item name and description to request to add the item the user wants to lend.
                 --> delete wish: delete item(approved by admin) from the wish lend list and from inventory.
             --> Edit borrow wishes:
                 [edit borrow wishes menu]
                 --> add wish: present all the item name; input item name to add wish.
                 --> delete wish: delete wishes from the user's borrow wishes.
             --> Exit: exit to login menu
         --> Market(4): view all the items that can make trade.
                        The items which are included in a trade will not appear in the market.
         --> quit to menu: quit to main menu


Admin login(1): admin has all functions as normal users plus its own functions
                (Enter correct username and password to login)
         [login menu]
         --> Edit information(1)
             [Edit information menu]
             --> Change password(1)
             --> Freeze a user(2)
                 -->freeze a user: freeze a user by entering username.
                 -->unfreeze a user: unfreeze user after user have unfreeze request.
                 -->request to remove freeze:request to unfreeze with reasons.
             --> Change user's limit(3): input user name to change limit of users.
                 --> change trade limit
                 --> change transaction limit
                 --> change the difference between borrowed and lend
             --> add new item into the system(4)
                 --> Add item for yourself: directly add item to the admin's lend wishes list.
                 --> Approve request from users: choose an item to approve(input the number before item)
             --> add new admin(5)
                 --> create new admin: create the name and password of the new administrative user
             --> exit(0)
         --> Other functions are same with normal users.

-----------------------------------------------------------------------------------------------------------------------
Trade System:
set up a new trade with another user (can be ordinary/admin): right after [Inventory menu] Borrow wishes.
--> one way(temporary) (1)
--> one way(permanent) (2)
--> two way(temporary) (3)
--> two way(permanent) (4)
--> exit (0)
[trade menu]
--> if the input trade's status is unconfirmed:
         [confirm menu]
         All unconfirmed trades will appear in sequence.
         --> type 1 to confirm trade: change the status of trade from "unconfirmed" to "incomplete"
         --> type 2 to not confirm trade: change the status of trade from "unconfirmed" to "cancelled"
         return to trade system menu
--> if the input trade's status is incomplete and the first meeting status is incomplete:
         [complete trade]
         --> enter first meeting system <Set-Up-Meeting Session>
         return to trade system menu when action is performed
--> if the input trade's status is incomplete, but the first meeting status is complete:
         --> enter second meeting system
         return to trade system menu each time one action is performed
--> if the input trade's status is complete or cancelled:
         --> can not do anything
         return to trade system menu directly

<Trade Status>
Trade status can be:
    - unconfirmed: the trade is requested by user through an item, but not yet confirmed by the other user
	- cancelled: if first meeting is cancelled or user choose not to confirm the trade
	- incomplete: user choose to confirm the trade, but the trade hasn't been completed
	- completed: the meeting is complete for permanent trade and the second meeting is complete for temporary trade

----------------------------------------------------------------------------------------------------------------------
Meeting System:
For each trade (or transaction), only if the trading proposal is proposed by one user and is accepted by the other user,
both users can then get into the Meeting System, where it is allowed for one of the two users (the meeting attendees)
to set up meeting proposal. As long as the meeting has been officially scheduled, the meeting attendees can then edit
meeting info (including date and time) up to three times, agree meeting proposal before offline transaction take place.
Meeting System has three service sessions: <Set Up Session>, <Edit and Agree Session> and <Confirm Session>.
These sessions would be provided to the users in sequence, according to the current status of the Meeting object (which is stored in the trade system).
For each session, the attendee users can choose to quit the session or complete the session in accordance to the instructions on the UI.
Alternatively, the meeting system can be run and tested individually through ```MeetingSystemDemo.java```.

--> first meeting system will run all the sessions.
--> second meeting system will only run confirm session.

<Set Up Session>
To enter the <Set-Up-Meeting Session>:
    --> login --> Trade --> complete trade --> select the trade
Precondition:
    - there must be "incomplete" trades. That is, a new trade is proposed by one user, and accepted by another user.
	- the meeting is not cancelled
Sample Entry:
<entered the set up session>
	--> enter ‘ok’
		--> enter date-time (requires correct format and a future time)
		--> enter place (can not include "," or ";")
		--> enter (should then see the success info)
	--> enter 1 to quit the current meeting session and back to the UI of trade system
	    or anything else to enter <Edit-Agree-Meeting Session>

<Edit and Agree Session>
To be able to enter <Edit-Agree-Meeting Session>:
    --> login --> Trade --> complete trade --> select the trade
Precondition:
	- meeting must be set up successfully already,
	- meeting is not cancelled, or agreed by both users, or confirmed
Usage Notes:
The user cannot edit the meeting consecutively, one must wait for another user to edit/agree before editing the 2nd time/agreeing.
The users are allowed to enter the <Edit-Agree-Meeting Session> for the forth time.
However, if the user edits its in the fourth time, the meeting will be cancelled;
if the user enters the <Edit-Agree-Meeting Session>, but quits or agrees before edition, meeting will not be cancelled
Sample Entry:
<entered Edit-Agree-Meeting Session>
	—> enter ‘ee’: to edit the date-time-place proposal
		— > enter ‘1’: to change time only
			--> enter a new date-time (requires correct format and a future time)
			    (should then see the success info)
		—> enter ‘2’: to change place only
			--> enter place (not the same as the old)
			    (should then see the success info)
		—> enter ‘3’: to change both time and place
			--> enter new date-time until correct format, future time, not the same as the old
			--> enter place (not the same as the old)
                (should then see the success info)
		—> enter ‘..’: to quit the edition, back to previous menu
		—> enter anything else other above-mentioned instruction:
	—> enter ‘aa’: to agree the date-time-place proposal
	—> enter anything else other than ‘ee’,  ‘aa’ to quit this session

<Confirm Session>
Precondition
To be able to enter this session:
	- meeting must be set up successfully already, and be agreed by BOTH users
	- meeting is not cancelled
Sample Entry
<entered Confirm Session>
	—> enter ‘cc’: to confirm the date-time-place proposal
	—> enter anything else other than ‘cc’: to quit this session

<Meeting Status>
Meeting status can be:
	- cancelled: only when one meeting attendee edits in record over 3 times
	- incomplete: the default meeting status, should being this status before both users agrees or the meeting cancels
	- agreed: both users agree the meeting proposal
	- completed: both users confirm the meeting actually occurred

