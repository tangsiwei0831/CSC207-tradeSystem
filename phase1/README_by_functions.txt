This is the phase 1 project for CSC207Y1 (summer) Software Design.

Instructions to run the systems:

Running this program requires intellij.
Before running the program, make sure that all txt files (4 files altogether) are EMPTY and with no blank space.
For running the program, open class Main in package Main, and click the "run" button.
The main menu UI will appear at the bottom (in terminal)

1. ===CREATE ACCOUNT===
To create a non-administrative account first, type "2" to choose the "register" option.
For selecting a menu option, the input must be the serial number before the option.
Inputting things other than numbers may break the program.
The system will automatically create the first Admin with name "admin" and password "123".

2. The "quit" option stops the execution of main method.

3. =======LOGIN=======
Login: this is the entry point of trying all other functionalities in the system.

4. ====FUNCTIONALITIES FOR CLIENT USERS====
** View weekly trade limit & incomplete trade limit:
    these two limits can be found above the main menu once login.
** Change password: --> Edit information
** Request unfreeze:
    --> Edit information --> ClientUser Freeze System --> Request unfreeze

** View list of items in wishlist:
    --> Inventory --> Lend wishes/Borrow wishes
** View top 3 most frequent trade partners:
    --> Trade --> Trade History
** View most recent three items (trades):
    --> Trade --> Trade History
** Request adding items to available list:
    --> Inventory --> Edit lend wishes --> add wish
   the description of item must not contain comma, period, or semicolon.
   the items are not added until the admin approve.
** Add items that user wishes to borrow:
    --> Inventory --> Edit borrow wishes --> add wish
   Users must add items to their wish lists before trading the items

** Set up trade with other users:
    --> Inventory --> Borrow wishes --> Select item
   user should type the exact name of item to select.
** Confirm (agree) trade:
    --> Trade --> confirm trades --> select trade number --> type anything to continue --> confirm meeting
   this function means to AGREE the trade to happen.
   the "trade number" is the serial number placed before trade id.
   after confirming, the trade status will change from "unconfirmed" to "incomplete".
   user must type integers (any number) to exit this session.
** Set up meeting time & place (right after confirming trade):
    --> continue with current trade --> Set-Up-Meeting Session
   the time and place must not contain comma, period, or semicolon.
   setting up meeting does not count into the the number of editing time.
** Edit meeting time & place
    --> Trade --> complete trade --> select trade --> continue with current trade --> Enter "ee"
   ???when
** Agree meeting time & place
    --> Trade --> complete trade --> select trade --> continue with current trade --> Enter "aa"
   only when both users in the trade agree the proposed time & place, the meeting status will turn into "agreed".
** Confirm that the meeting has completed:
    --> Trade --> complete trade --> select trade --> continue with current trade --> Enter "cc"
   this <Confirm-Meeting Session> only appears when the meeting status is "agreed".


5. ====ADDITIONAL FUNCTIONALITIES FOR ADMIN====
Administrative users can perform the same functions as ordinary users, plus a few more admin functionalities.
** Freeze/unfreeze user account:
    --> Edit information --> Freeze a user --> Freeze user/unfreeze user
    only when the users send unfreeze request, the admin can view and unfreeze the users.
** Change threshold values (weekly trade limit, incomplete trade limit, lent-borrowed difference):
    --> Edit information --> Change user's limit
** Add new items to user's lists:
    --> Edit information --> add new item into the system --> Approve request from users
   The admin can approve only one item at a time.
** Add items directly to own lend wish list:
    --> Edit information --> add new item into the system --> Add item for yourself
   The admin has option to add his/her item directly
** Add subsequent administrative users to the system:
    --> Edit information --> create new admin