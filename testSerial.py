import serial
import time
import io

ser = serial.Serial('/dev/ttyAMA0',9600)
print(serial.__version__)


while True:
    try:
        ser.flush()
        new_data = ser.readline()
        new_data= new_data.split(' ')
        new_data.pop()
        if(new_data[0]==''):
            del new_data[0]
        print new_data

    except KeyboardInterrupt:
        ser.clsoe()
    except serial.serialutil.SerialException:
        #print("error occur!!!!")
        ser.close()
        time.sleep(1)
        ser.open()
        ser.flush()

    
