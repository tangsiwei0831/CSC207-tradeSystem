# Feature List for Phase 2

Since we only have 5 members in total, we did the mandatory extensions, one elective extension and the GUI (counted as two).

## Mandatory Extension (5 extensions)

### 1. Reverse System - Undo
- Reverse change password action
- Reverse send unfreeze requests action (If the requests have been agreed by admin user, then 
the action is not available to undo)
- Reverse inventory-related actions
  - undo adding item to wish lend list requests. (If the requests have been processed by admin user, then
  the action is not available to undo)
  - undo deleting item from wish lend list. -> add the item back
  - undo adding item to wish borrow list requests. -> delete the added item
  - undo deleting item from wish borrow list. -> add the item back
  
### 2. Automatic Item Suggests
In Request Trade Session, users need to input a second item to request a two-way trade. 
Users need to choose the second item from his "wish to lend" list. 
If an item is **both in current user's "wish to borrow list" and in target user's "wish to borrow list"**, 
then the item will be added to the "suggest list".
On the left side of the frame, the system will print out the users' "wish to lend list" and "suggest list".

### 3. Explore System 
Visitor users can explore the program without register and login. By simply click on `Explore` button on the Login 
user interface, visitor can explore the market where all available-to-trade items are exhibiting. Visitor users cannot
communicate with other users (neither regular users nor admin users), cannot make trade (or joining in trade community),
 and do not have an explicit account.

### 4. ClientUser Limit System
Only the admin user can log in and change the default limit value for client users.
After logging in, click the edit info button, the admin user can choose which limit to reset.

The thresholds that can be changed:
- Weekly transaction limit 
- Incomplete transaction limit
- Difference between borrow and lend
- Exchange standard for bonus points

### 5. Additional status for accounts
The user can set the "left status" to be true by set up the end date. 
This means that the user would be regarded as "left away" starting from the present time till the end date. 
The account in "left status" is the same as the frozen account, but it only sustains the period set by user. 

## Chosen Extension (3 extensions)

### 4 - Points System
For each complete trade, the user will get one bonus point. The user can redeem points to get bonus trades. 
The bonus trade is an extra chance for users to trade without it counting towards being frozen.
Each bonus trade is worth 5 points. The admin can change the "exchange standard" (the number of points needed for 
one bonus trade) in ClientUser Limit System.

### 8 - GUI
Watch videos to see how to use the GUI system.
See more in `VideoAccess.md`.