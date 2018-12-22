from socket import *
import sqlite3

dbpath = "myParking.sqlite"
connect = sqlite3.connect(dbpath);
cur = connect.cursor();

while True:
	server_socket = socket(AF_INET, SOCK_STREAM)
	server_socket.setsockopt(SOL_SOCKET,SO_REUSEADDR,1)
	server_socket.bind(('',50000))
	server_socket.listen(4)
	conn, addr = server_socket.accept()
	try:
		msg = conn.recv(1024)
		msg = msg.decode()
	
		if not msg :
			print("connection faild");
			continue
	
		print (msg)
		cur.execute(msg);
		item_list = cur.fetchall();
		connect.commit()
		print(item_list);
		conn.sendall(str(item_list).encode()+str("\n").encode());
		'''
		for item in item_list:
			print(item);
			conn.sendall(str(item).encode())
			#conn.sendall("\r".encode())
		'''
		#conn.sendall(str(item_list).encode()+'\n'.encode());
	except KeyboardInterrupt:
		break;
	server_socket.close();
	
connect.commit()
connect.close()
conn.close()
server_socket.close()
