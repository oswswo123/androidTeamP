int len = 6; 
int cds[6]= {A0,A1,A2,A3,A4,A5};
int cdsValue[6]={0,0,0,0,0,0};
int FULL = 1;
int EMPTY = 0;
int parking[6]={0,0,0,0,0,0};
int i;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600); //Serial 통신
}

void loop() {
  // put your main code here, to run repeatedly:

  for(i=0;i<len;i++){
      cdsValue[i]=analogRead(cds[i]); // Vout read
      if(cdsValue[i]>250) parking[i]=FULL;
      else parking[i]=EMPTY;
      Serial.print(parking[i]);
      //Serial.print(cdsValue[i]);
      delayMicroseconds(500);
      Serial.print(" ");
      delayMicroseconds(500);
      //Serial.print(cdsValue[i]);
      //Serial.print(" ");
      if(i==len-1) Serial.print("\n");
  }
 
  delay(1000);
 }
