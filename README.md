# Country List And Details APP

Architecture Design Pattern - MVVM (Model View ViewModel)

ARCHITECTURE BASE APP
-------------------------

BASE Architecture is added from my own repo which contains the basic boilerplate code. <br>
And application logic written on top of it.<br>
[Base Architecture Fork From own repo](https://github.com/vickycj/Base-Architecture)


Tech Stack
-----------

- Programming language    - **Kotlin**
- NetWork API             - **RetroFit**
- Dependancy Injection    - **Dagger 2 for Kotlin**
- Offline Storing         - **Room ORM (Arch components)**
- Streams                 - **RxAndroid and LiveData**
- Architecture Components - **ViewModel, LiveData, Android JetPAck Components**
- Testing Framework       - **Junit, Mockito , Expresso, AndroidJunit4**

APP FLOW
-------------

**Country List Fragment** :<br>
Launch APP - Checks Local DB - No data - Fetches from API - Populates in DB -
Loads data from Local DB - search functionality filters the data and shows - Onclick any item moves to Detail Activity

**Country Details Fragment** : <br>
Shows the detailed info of the particular country

**Weather Fragment** : <br>
Click the Overflow button - Weather - Shows the weather for the current user location in the day and night format.
