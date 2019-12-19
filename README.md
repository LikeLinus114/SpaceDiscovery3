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





The application can be used for communication and navigation when flying in spaceship in the Solar System. It is perspective application. Current technologies don't allow using things like that because
space flight is still an experiment and not being churned out. As for the current situation, the application can be used as space communication and navigation simulator in thematic phone games.
It can be useful for astronauts, space scientists. It is going to help them train, create the Solar System satellite network and investigate the things related with communication and navigation
in the space.

It is a navigation and communication module that will be used in space.









//All the further tasks are a serios scientific research. It's time-consuming and there are no guarantees of success. 

1. prepare chat for interaction with another app.
2. create another app.
3. implement settings: language etc.






https://github.com/LikeLinus114/SpaceDiscovery3 - the main app

https://github.com/LikeLinus114/SpaceInfoServer - the emulator of a server providing space information






https://stackoverflow.com/questions/29040319/android-communicating-two-apps-in-separate-devices
There's an important information about connection between two android apps for communication in space.
"
	In these scenarios, while there is nothing theoretically stopping sockets working, in practice many mobile operators will not allow incoming socket connections. In addition you would need to find the public IP address of the Mobile, which is possible but is extra complexity. If your solution will only ever run on a single operators network you can experiment and see if it works, but if not you may find it better and easier to use a server in the 'middle':

		Device A connectes to server
		Device B connectes to server
		Device A asks server for addresses of connected devices and 'discovers' device B
		Device A send a message for device B. It actually sends the messages to the server with an indication that it is to be sent to device B
		The server notifies device B that a message is available for it (using some sort of message notification like Google Cloud Messaging for example, or simply by the devices polling regularly to see if they have any messages).
		Device B retrieves the messages from the server
"