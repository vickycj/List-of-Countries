# Country List And Details APP

Architecture Design Pattern - MVVM (Model View ViewModel) 

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

**Splash Activity** :<br>
Launch APP - Checks Local DB - No data - Fetches from API - Populates in DB - Moves to Main Activity

**Main Activity** : <br>
Loads data from Local DB - search functionality filters the data and shows - Onclick any item moves to Detail Activity

**Details Activity** : <br>
Shows the detailed info of the particular country
