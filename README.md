### Chatroom

To create a chatroom by communicating between server-side and client-side over HTTP Websocket. This project leverages Spring Boot and RabbitMQ to implement the main function. In the server-side, for the scalability, I used the message queue(RabbitMQ) as the system's broker serving a large number of client's message exchange requests. Moreover, make use of STOMP to provide publish/subscribe pattern. It is efficient to talk between server-side and client-side rather than long pooling mechanism.

The docker-compose file illustrated the deployment of architecture in production. There are main three parts which are Load Balance(Nginx), backend services and RabbitMQ. To achieve high traffic need, We can allocate enough backend services for client's connection through Load Balance to meet this requirement. Besides, RabbitMQ also provides a good role in a large distributed system.
