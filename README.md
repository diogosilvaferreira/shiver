shiver
======

Java Terminal Application

![alt tag](http://imagizer.imageshack.us/a/img673/2599/rdzaZR.png)

Shiver terminal is a Java based application to test the behaviour of your server/webapp during a huge number of requests.

###Current features:

1. Insert a proxy configuration
2. Remove a proxy configuration
3. Set a default proxy to make requests
4. List registered proxies in the database
5. Execute "n" requests to a website

###Getting started

The basic way to run the application is simple typing the command ``` java -jar shiver-1.0.0-RELEASE.jar ```. If you are in a Windows plataform you just need to run the file ```shiver-run.bat``` locate inside the classpath of the project.

During the startup the aplication will try to create the database folder/files (Apache Derby) in the root path of the system (example: C:\shiver\database).

###Commands

![alt tag](http://imageshack.com/a/img540/1194/dT86SR.png)
```proxy list``` - list the registered proxies in the database.
___

![alt tag](http://imageshack.com/a/img674/1251/b3lVNs.png)
```proxy add``` - register a new proxy configuration.

Parameters:

```--host``` - the proxy host (required)

```--port``` - the proxy port (required)

```--user``` - the username for authentication (not required)

```--password``` - the password for authentication (not required)

___

![al tag](http://imageshack.com/a/img913/5115/UjSD9Q.png)

```proxy remove``` - removes a proxy configurarion from the database.

Parameters:

```--id``` - the proxy id to remove (required)
___

![alt tag](http://imageshack.com/a/img673/7158/iT6dCa.png)

```proxy set``` - sets a default proxy to execute requests.

Parameters:

```--id``` - the proxy id to set as default (required)

___

![alt tag](http://imageshack.com/a/img661/5519/J65y8w.png)

```swim``` - executes a get request in a specified url.

Parameters:

```--target``` - the target website url (required)

```--sharks``` - the number of requests (required)

```--mixed``` - true for make requests using multiple proxies, false otherwise (required)
