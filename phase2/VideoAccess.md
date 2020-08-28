# Videos

## Google Drive Video Link
Link: https://drive.google.com/drive/folders/1unT-WmSSiGtjKi7qOSXFZHjPwjTrSJvI?usp=sharing

## Videos' Content

### `V1_RegisterLogin.mp4`✅ (Rella)
- Login: Admin & Client User
- Register: Client User *2
- Change Password
- Creates new Admin user：Admin
    
### `V2_WishList_AddLendableItems.mp4`✅ (Rella)
- Add items to wish-to-lend list
- Admin agrees/decline requests (agreed items will appear in the market)
    
### `V3_WishList_AddWishList.mp4`✅ (Rella)
- Add item from the Market to Wishlist
- Delete item in the Wishlist
    
### `V4_TradeItems_TwoWay_Temporary.mp4`✅ (Rella)
- Initialise a TwoWay-Temporary Trade (ClientUser A): <MeetingStatus: `INCOMPLETE`>
- Add second item to trade (ClientUser B)
- Agree trading request (ClientUser B)
- First Meeting (to be COMPLETE, not CANCELLED)
    - `Setup`: initialise a time and place
        - check violating inputs (*only future time allowed*, etc.)
    - `Edit`: time and/or place, 
        - check violating inputs (*the same as time & place as former ones NOT ALLOWED*, etc.)
    - `Agree` proposal before meeting occurs : <MeetingStatus: `AGREED`>
    - `Confirm` proposal after meeting occurs: <MeetingStatus: `CONFIRMED`>
- Second Meeting
    - (Automatically generates; the same time, but exactly 1 month after; the same place)
    - `Confirm` proposal after meeting occurs: <MeetingStatus: `CONFIRMED`>
     
     
### `V5_Mandatory3_VisitorExplore.mp4`✅ (Rella)
- Explore Market (where all not-in-trade item be) as a visitor (no login required)
    
### `V6_Mandatory2_ItemSuggestion.mp4`✅ (Amy)
Two users -- Amy and Tom
- Amy's WishLend:  `[iPad, iPhone, toy car]`
- Amy's WishBorrow: `[horror film]`
- Tom's WishLend: `[horror film]`
- Tom's WishBorrow: `[iPad, iPhone]`

Amy requests a trade for the item "horror film" with Tom.
- WishLend List shows: `[iPad, iPhone, toy car]`
- Suggested List shows: `[iPad, iPhone]`

    
### `V7_Mandatory5.mp4`✅ (Siwei Tang)

Two Users- Admin4
- set the end date(date must after datetime now)
- submit, then back and then log out
- log in again, the freeze status and left status would change to true

### `V8_FreezeSystem.mp4`✅ (Amy)

- Froze because of trade limit in a week:
 
  - Trade limit = 1, after agreeing two requested trade by tom, 
amy's freeze status becomes true.

- Froze because of incomplete trade limit:
 
  - Trade limit = 1, after agreeing two requested trade by tom, 
amy's freeze status becomes true.

- Froze because of difference between borrow and lend:

  - Diff limit = 1, after borrow two items from amy, 
tom's freeze status becomes true.

- Froze because admin user freeze users:

  - Admin set amy's status to true, and amy's status becomes true.


- If user's account is frozen due to out of limit, only unfreeze user is useless:

  - Amy's account is frozen due to incomplete limit out of bounds. After admin unfreeze
  amy's account, the account is still frozen.

  - Method 1: Reduce the limit by waiting for 1 week to pass, or complete trades, 
  and then admin unfreeze users. 
    - Amy completes a trade so that the trade number is in the limit, then admin unfreeze Amy,
    amy's freeze status becomes false.
  
  - Method 2: Admin increase user's limit, and unfreeze users.
    - admin change amy's incomplete trade limit to 2, and then unfreeze amy. amy's freeze status
    becomes true.
   
### `V9_ElectiveExtension_PointSystem.mp4`✅ (Yuxin Yang)
Demonstration of Point System
- Complete a trade to get one bonus point. 
    - The system will automatically add one bonus point to user for each complete trade
- Redeem points for a bonus trade which does not count into limits
    
### `V10_Mandatory1_Reverse-Password&UnfreezeTicket.mp4`❤️ (Ensouled)
- Amy change password from 123 to 1234. Admin reverses the system, and the password
change back to 123
- Amy requests to unfreeze account, the requests appear in admin's unfreeze system. 
Admin reverses action, amy's requests disappeared in admin's unfreeze system.
     
### `V11_Mandatory4_AdjustThresholds.mp4`✅ (Yuxin Yang)
Demonstration of ClientUser Limit System
- Adjust weekly trade limit for selected user
- Adjust incomplete trade limit for selected user
- Adjust difference between borrow and lend for selected user
- Adjust bonus point exchange standard for all users
    
### `V12_xxx.mp4`
- a
    
### `V13_xxx.mp4`
- a

====================================

## Video Script
---

### Users

| username | password | notes |
|:---------|:---------|:------|
|admin |123 |(already exists in system, login only)|
|admin2 |123|(create by admin)|
|qqq |123 |(new: register new user, change password to “123abcABC” )|
|www |123 |(new: register new user)|

### Inventory 
| item | description |
|:---------|:---------|
|iPad|1st generation|
|iPhone|latest released|
|toy car|red and shiny|
|tea cup|vintage|
|horror film|home-made|
|---|---|
|daydream|fresh and healthy|
|trash|aka. garbage|
|love|my pure emotion and feeling|


## == TESTS ==

## Register and login, create admin, change password


## Request adding items to inventory, Agree/Decline requests
add above items to inventory,
-  the items from the upper section  — agree request
-  the items from the lower section  — decline request


## Mandatory #3 - Market
before login -> explore (view an item)
after login -> inventory/market  (view an item)


## Point System
Every complete trade (trade status == complete)，the system will automatically give the user add one point；every 5
points，user can exchange for bonus trade
TBA

## Mandatory #1 -  Roll Back
TBA

## Mandatory #2 - Suggest items to lend
TBA

## Mandatory #4 - Admin adjust all threshold
TBA

## Mandatory #5 - User set Left Status
User set the end time, it means that if the time now is before that end
time, the left status and freeze status to be true. 
