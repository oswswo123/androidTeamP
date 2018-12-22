import sqlite3
from socket import *

dbpath = "Member.sqlite"
conn = sqlite3.connect(dbpath);

cur = conn.cursor();

#cur.executescript('''
#		DROP TABLE IF EXISTS members;
#		CREATE TABLE members(memberID text not null,
#			memberPw text not null,
#			parkTF text not null);
#		''');


while True:
	server_socket = socket(AF_INET, SOCK_STREAM)
	server_socket.setsockopt(SOL_SOCKET,SO_REUSEADDR,1)
	server_socket.bind(('',54545))
	server_socket.listen(4)
	connect, addr = server_socket.accept()
	try:
		msg = connect.recv(1024)
		msg = msg.decode()
		print (msg)
		new_msg = msg.split(" ");

		if not msg : 
			print("connection faild");
			continue;
		
		if new_msg[0] =='INSERT':
			print("insert code")
			cur.execute(msg);
			conn.commit();
			cur.execute("select * from members;")
			item_list = cur.fetchall();
			connect.sendall(str(item_list).encode()+"\n".encode());
		elif new_msg[0] == 'SELECT':
			print("select code")
			cur.execute(msg);
			conn.commit();
			item_list = cur.fetchall();
			connect.sendall(str(item_list).encode()+"\n".encode());
			
		
	except KeyboardInterrupt:
		break;
	server_socket.close();

conn.commit();
connect.close();
conn.close();
server_socket.close();
	
	
