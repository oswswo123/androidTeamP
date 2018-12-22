from socket import *
import sqlite3

dbpath = "myParking.sqlite"
connect = sqlite3.connect(dbpath);
cur = connect.cursor();

server_socket = socket(AF_INET, SOCK_STREAM)
server_socket.setsockopt(SOL_SOCKET,SO_REUSEADDR,1)
server_socket.bind(('',45454))
server_socket.listen(4)
conn, addr = server_socket.accept()
while True:
	try:
		msg = conn.recv(1024)
		msg = msg.decode()
		if msg == "select * from parkings;":
			print(msg)
			cur.execute(msg);
			item_list = cur.fetchall();
			for item in item_list:
				conn.sendall(str(item).encode());
		msg = msg.split(' ')
		msg_int = [int(i) for i in msg]

		for i in range(len(msg_int)):
			msg_int[i] = msg_int[i]%10
			if msg_int[i] == 1:
				cur = connect.cursor();
				query = "UPDATE parkings set isFull =?,name=? where id =?"
				cur.execute(query,('Y',None,i+1))
				connect.commit()
			elif msg_int[i] == 0:
				cur = connect.cursor();
				query = "UPDATE parkings set isFull =? where id =?"
				cur.execute(query, ('N',i+1))
				connect.commit()
			else:
				print("unexpected event occur!!")


		item_list = cur.fetchall();
		for item in item_list:
			print(item);

	except KeyboardInterrupt:
		break;
	
connect.commit()
connect.close()
conn.close()
server_socket.close()
