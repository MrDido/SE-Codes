Per poter avviare i file bisogna installare RabbitMQ..per poter installare RabbitMQ bisogna prima installare Erlang.

https://www.erlang.org/
https://www.rabbitmq.com/

Una volta installato RabbitMQ scrivere su terminale:

sudo rabbitmq-plugins enable rabbitmq_management 
(Per poter creare l'interfaccia per gestire il server da browser)

Aggiungere utenti "admin" e "test" con i seguenti comandi:

User "admin" con password "password"
sudo rabbitmqctl add_user admin password
sudo rabbitmqctl set_user_tags admin administrator
sudo rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"

User "test" con password "test"
sudo rabbitmqctl add_user test test
sudo rabbitmqctl set_permissions -p / test ".*" ".*" ".*"


Per avviare il server RabbitMq digitare:
sudo rabbitmq-server start
