int cds0 = A0;
int cds1 = A1;
int cds2 = A2;
int len = 3; 
int cds[3]= {A0,A1,A2};
int cdsValue[3]={0,0,0};
int FULL = 1;
int EMPTY = 0;
int parking[3]={0,0,0};
int i;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:

  for(i=0;i<len;i++){
      cdsValue[i]=analogRead(cds[i]);
      if(cdsValue[i]>200) parking[i]=FULL;
      else parking[i]=EMPTY;
      Serial.print(parking[i]);
      delayMicroseconds(500);
      Serial.print(" ");
      delayMicroseconds(500);
      //Serial.print(cdsValue[i]);
      //Serial.print(" ");
      if(i==len-1) Serial.print("\n");
  }
 
  delay(1000);
 }
