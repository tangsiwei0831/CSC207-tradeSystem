# CSC207 Project

This is a program that allows users to trade different items with each other.
 
# Table of contents
 | File | Content |
 |------|:---------------|
 |`FeatureList.md`|the features we have done for phase 2|
 |`VideoAccess.md`|the link for sample usage videos, and basic txt elaborations on test functions|
 |`DesignPattern.md`|the design patterns we have applied with detailed explanations|
 |`src/Main/mainRun.java`|main test file; start testing the functionalities from here :)|
 
# Prerequisites
  - Language: Java
  - IntelliJ
--- 
  - SDK: java version 1.8
  - Project Language level: 8 - Lambdas, type annotations, etc
---
  - Ensure all the database files (.ser format) are empty or do not exist before running
  - Do **NOT** quit the whole program or stop the execution of program when running tests, 
  **follow the menu to exit instead**.

# Tests
Run `phase2/src/Main/mainRun`. To simplify the testing, we provide **three** login windows 
so that 3 users can log in and communicate through the application at the same time.

For better testing all the available functionality, you need to:
  - **log in** an Admin account (automatically created, already exists in the system)
    > username: `admin`, password: `123`
                                                               
  - **register** two regular-user account into the system. 
    e.g.
    > username: `qqq`, password: `123`

    and
    > username: `www`, password: `123`
  - P.S. 
    - ClientUser: ClientUser can only perform ordinary (ClientUser) functionalities
    - AdminUser: AdminUsers are able to perform ordinary (ClientUser) functionalities and admin functionalities
  - add items into at least two users' lend wish list.


# Authors and Contribution 
*(names listed in no particular order)

| Name | Divide of work |
|:------:|:---------------|
|**Yuhan Zheng (Ensouled)**|AdminUser & GUI, Mandatory Extension 1|
|**Yiyun Ding (Amy)**|Trade & GUI, Inventory & GUI, Mandatory Extension 2|
|**Changyan Xu (Rella)**|Meeting System & GUI, Mandatory Extension 3|
|**Yuxin Yang (Amber)**|Point System, Mandatory Extension 4|
|**Siwei Tang**|ClientUser, Mandatory Extension 5|


# Illustrations




# Scope of functionalities 

## For both regular users and admins:
### 1 - Editing basic information
- Change password

### 2 - Inventory
- Users can view all available items in Market.
- Users can add items to WishList-Lend.
- Users can add items to WishList-Borrow (with item suggestion).
### 3 - Trade System
- Users can request trades with other users.
- User can agree/decline the trade requests from other users.
- Users can complete trades (by entering Meeting System).
- Users can view their history of complete trades.

### 4 - Meeting System (within Trade)
- Users can set up the time and place for meeting.
- Users can agree/edit the time and place for meeting in turns.
- Users can confirm the meeting after it has happened.

### 5 - Point System
- Users will get bonus points for each complete trade.
- Users can redeem points for bonus trades which do not count into any trade limits.

### 6 - Setting account status
- Users can set their accounts to be "left away" status for set periods.

### 7 - Request Unfreeze
- Users can make unfreeze request to admins

## For admins only:
### 1 - Freeze/Unfreeze ClientUser
- The admins can set selected ClientUser to freeze status manually.
- The admins can unfreeze the accounts of selected ClientUsers

### 2 - ClientUser Limit System
The admins can change the default limit value for client users.

The thresholds that can be changed:
- Weekly transaction limit 
- Incomplete transaction limit
- Difference between borrow and lend
- Exchange standard for bonus points

### 3 - Creating new admins
- The admins can create other admin accounts in the system.

## Terms in the Program

### User Account Statuses
| Type | Meaning |
|------:|:---------------|
|`freeze status`|???|
|`left status`|???|

### Limits
| Limits | Default Value | Meaning |
|:------|:---------------:|:---------------|
|Trade Limit            |5|The limit of trade in a week |
|Incomplete Trade Limit |2|The limit of existed incomplete trade|
|Diff between borrow and lend |2|The number of borrowed items minus the number of lent items |
|Points exchange for Bonus Trade|5|The number of points needed to exchange for one bonus trade |
|Meeting Edit Limit     |3|If either one of two users does NOT agree meeting proposal before their **4th** time edition (i.e. edits its 4th time), the meeting will be cancelled|

### Lists in Inventory:
| List Type | Meaning |
|------:|:---------------|
|WishList-Borrow|displays all wish-to-borrow items (from other users), i.e. a wishlist|
|WishList-Lend|displays all wish-to-lend items (to other users), which passed Admin's inspect|

### Trade Types:
| Type | Meaning |
|------:|:---------------|
|one way (temporary)|one item in trade, two meetings involved|
|one way (permanent)|one item in trade, one meeting involved|
|two way (temporary)|two items in trade, two meetings involved|
|two way (permanent)|two items in trade, one meeting involved|

### Trade Statuses:
| Status | Meaning |
|------:|:---------------|
|`unconfirmed`| the trade is requested by user through an item, but not yet confirmed by the other user|
|`cancelled`|   if first meeting is cancelled or user choose not to confirm the trade|
|`incomplete`|  user choose to confirm the trade, but the trade hasn't been completed|
|`completed`|   the meeting is complete for permanent trade and the second meeting is complete for temporary trade|

### Meeting Statuses:
| Status | Meaning |
|------:|:---------------|
|`DNE`|          when meeting is not yet set up|
|`INCOMPLETE`|   when meeting can be edited and/or agreed|
|`CANCELLED`|    when one user edits over threshold (more than 3 times)|
|`AGREED`|       when both users agreed the meeting proposal|
|`COMPLETED`|    when both users confirmed the meeting occurred|






# Examples of use
Please see `VideoAccess.md`.




















# ======================================