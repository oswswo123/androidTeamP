import sqlite3
from socket import *



dbpath = "myParking.sqlite";
conn = sqlite3.connect(dbpath);

cur = conn.cursor();

cur.executescript('''
		DROP TABLE IF EXISTS parkings;
		CREATE TABLE parkings(id INTEGER PRIMARY KEY, 
			isFull text not null,
			name text);

		INSERT INTO parkings(isFull) VALUES("N");
		INSERT INTO parkings(isFull) VALUES("N");
		INSERT INTO parkings(isFull) VALUES("N");
		''');

conn.commit();
cur = conn.cursor();

cur.execute("SELECT id, isFull, name FROM parkings;");
item_list = cur.fetchall();
for item in item_list:
	print(item);



server_socket = socket(AF_INET, SOCK_STREAM)
server_socket.setsockopt(SOL_SOCKET,SO_REUSEADDR,1)
server_socket.bind(('',45454))
server_socket.listen(4)
connect, addr = server_socket.accept()

while True:
	msg = connect.recv(1024)
	msg = msg.decode()
	if not msg :
		print("connection faild");

		break
	print (msg) # test print

	msg = msg.split(' ')
	print (msg)

	msg_int = [int(i) for i in msg]

	for i in range(len(msg_int)):
		msg_int[i] = msg_int[i]%10
		if msg_int[i] == 1:
			cur = conn.cursor();
			query = "UPDATE parkings set isFull =? where id =?"
			cur.execute(query,('Y',i+1))
			conn.commit()
		elif msg_int[i] == 0:
			cur = conn.cursor();
			query = "UPDATE parkings set id =? where isFull =?"
			cur.execute(query, ('N',i+1))
			conn.commit()
		else:
			print("unexpected event occur!!")


	item_list = cur.fetchall();
	for item in item_list:
		print(item);


connect.close()
conn.commit()
conn.close()
