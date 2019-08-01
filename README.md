# Server Calculator
It is a client-server calculator written in Java using standard library.</br>
Clients send their requests to server, server calculates the expression and sends the result to appropriate client.</br>
For calculation of expressions Polish notation was used.
## Prerequisites
You need to have JDK installed on your machine.
## Installation
In order to install the server app run the following command:</br>
`javac Server.java`</br>
In order to install the client app run the following command:</br>
`javac Client.java`</br>
## Usage
Firstly, you need to run your server app:
`java Server 'host ip' 'port'`</br>
For example, `java Server 127.0.0.1 7373`</br>
Now clients can connect to your server. In order to connect to server, you need to run the following command:</br>
`java Client 'host ip' 'port'`</br>
Congratulations! You can send your requests to server!</br>
![Screenshot from 2019-08-01 13-52-01](https://user-images.githubusercontent.com/39864247/62287801-8fde8e00-b463-11e9-94cd-fbd24f21d30c.png)
To stop running client app enter `quit`. And to stop running server app enter `^C`.</br>
