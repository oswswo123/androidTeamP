#serial import
import serial
import time
import io
#socket client import
from socket import *

HOST = '210.123.39.66'
PORT = 45454

import serial
ser = serial.Serial('/dev/ttyAMA0',9600)


print(serial.__version__)

client_socket = socket(AF_INET,SOCK_STREAM)
client_socket.connect((HOST,PORT))

#client_socket.recv(1024)


while True:
	try:
		ser.flush()
		new_data = ser.readline()
		new_data= new_data.split(' ')
		new_data.pop()
		if(new_data[0]==''):
			del new_data[0]

		print new_data
		msg = " ".join(new_data)
		client_socket.sendall(msg.encode())
		print("complete send data")


	except KeyboardInterrupt:
		ser.close()
		client_socket.close()
		
	except serial.serialutil.SerialException:
		#print("error occur!!!!")
		ser.close()
		time.sleep(1)
		ser.open()
		ser.flush()
