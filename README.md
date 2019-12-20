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

	

The application can be used for communication and navigation when flying in spaceship in the Solar System. It is perspective application. Current technologies don't allow using things like that because
space flight is still an experiment and not being churned out. As for the current situation, the application can be used as space communication and navigation simulator in thematic phone games.
It can be useful for astronauts, space scientists. It is going to help them train, create the Solar System satellite network and investigate the things related with communication and navigation
in the space.

It is a navigation and communication module that will be used in space.




1. create description fragment.




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

or we can prepare each app as a server and client together. In this case, we need to make the Android app work as an http-server: https://github.com/NanoHttpd/nanohttpd