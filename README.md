Space Discovery is an Android application for astronauts.
It allows users to:
*** - define their location in the solar system (DLSS);
*** - look at the map of the galaxy (GM); (my version and common)
*** - get information about celestial bodies and satellites (CBS) (subitem: add new celestial bodies and satellites to the database (ANBS))
*** - connect to the nearest stations (CNS).

--- DEVELOPMENT STEPS ---

1. Create UI basement:
-- a) login;+
-- b) burger menu;+
-- c) basic functions (DLSS, GM, CBS, ANBS, CNS);
2. Create and connect to database:
-- a) create database scheme;
-- b) create and configure database in the application;
3. Fill local info:
-- a) GM;
-- b) CBS;
4. Access to remote info:
-- a) configure test emulating servers or real ones;
-- b) configure data exchange;
5. Fixes and little features:
-- a) validation;
-- b) GUI features;
-- c) best practices in android development;


===TIPS===

it's better to use ListView (RecyclerView) and custom adapters (Adapter class) for enumerations displaying https://o7planning.org/ru/10435/android-listview-tutorial

ViewModel, API, MVVM pattern, Retrofit 2, Dagger

	
	==TASKS==

1. location UI (the map of the solar system with the indicator showing our location, textviews on the side pane:
last updating, quality of connection with the satellites (based on the statistics of queries and successful responses))
-->> +- (dummy map and TextView data have been created. Find out how Google map navigator works and if it is possible to do the same with Space map. Maybe, there's already something like this)

2. map of galaxy UI (map of the galaxy, possibility to set the indicator manually, date of the last update, status of the update request if any, update button (blocked if update has been requested))
-->> +- (dummy map and TextView data have been created. Consider how to further develop this section)		  

3. celestial bodies and satellites UI (list of bodies and satellites, possibility to click on one of them and navigate to the location section where it is displayed on the solar system interactive map)
 3.1 -- create item fragment with body type, name, its description and distance+
 3.2 -- create RecyclerView to manage items+
 3.3 -- create Service containing local info for items+
-->> +- (list created. Find out how Google map navigator works and if it is possible to do the same with Space map. Maybe, there's already something like this)

4. stations UI (list of the stations (space stations, planet bases, spaceships etc. - anything accessible to connect))
 3.1 -- create item fragment with type of station, name, its description and quality of communication, messages history
 3.2 -- create RecyclerView to manage items
 3.3 -- create Service containing local info for items
-->> messages history is a separate class, it'll be opened with the button. Also prepareData() methods in both cases should be simplified

after creation the layouts mentioned above we should create architecture schemes to determine the functions that have to be implemented by the remote servers and the ones that have to be implemented in our app.
Therefore create the list of remote servers and find out how to emulate them.
after that emulate them.









The application can be used for communication and navigation when flying in spaceship in the Solar System. It is perspective application. Current technologies don't allow using things like that because
space flight is still an experiment and not being churned out. As for the current situation, the application can be used as space communication and navigation simulator in thematic phone games.
It can be useful for astronauts, space scientists. It is going to help them train, create the Solar System satellite network and investigate the things related with communication and navigation
in the space.

It is a navigation and communication module that will be used in space.


1. MVVM pattern (node.js server)
2. integrate dependency injection
3. implement settings: language etc.







https://github.com/LikeLinus114/SpaceDiscovery3 - the main app

https://github.com/LikeLinus114/SpaceInfoServer - the emulator of a server providing space information