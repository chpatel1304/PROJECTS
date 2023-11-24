#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10 
#define RST_PIN 9

MFRC522 mfrc522(SS_PIN, RST_PIN); // Create MFRC522 instance.

byte card_ID[4]; //card UID size 4byte
byte Name1[4]={0x70,0xDE,0x7D,0xA2};//first UID card
byte Name2[4]={0xC0,0x1F,0xC3,0xA3};//second UID card
byte Name3[4]={0xA0,0xBD,0xC6,0x57};//third UID card
byte Name4[4]={0x30,0x0F,0xC2,0xA3};//foruth uid card
byte Name5[4]={0xBC,0x3D,0xC5,0x38};//fifth uid card

int NumbCard[5];
int j=0;        

int const BlueLed=6;
int const GreenLed=5;

String Name;//user name
long Number;//user number  
String Batch;

void setup() {
  Serial.begin(9600); // Initialize serial communications with the PC
  SPI.begin();  // Initialises SPI bus
  mfrc522.PCD_Init(); // Initialises MFRC522 card
  
  Serial.println("CLEARSHEET");                 // clears starting at row 1
  Serial.println("LABEL,Date,Time,Name,Number,Batch");// make four columns (Date,Time,[Name:"user name"]line 48 & 52,[Number:"user number"]line 49 & 53)

  pinMode(BlueLed,OUTPUT);
  pinMode(GreenLed,OUTPUT);
   }
    
void loop() {
   if ( ! mfrc522.PICC_IsNewCardPresent()) {
  return;
 }
 // Select one of the cards
 if ( ! mfrc522.PICC_ReadCardSerial()) {
  return;//if read card serial(0) returns 1, the uid struct contians the ID of the read card.
 }
 
 for (byte i = 0; i < mfrc522.uid.size; i++) {
     card_ID[i]=mfrc522.uid.uidByte[i];

       if(card_ID[i]==Name1[i]){
       Name="Mr.Ekal Sorathiya";
       Number=101;
       Batch="2";
       j=0;
      }
      else if(card_ID[i]==Name2[i]){
       Name="Mr.Chirag Patel";
       Number=102;
       Batch=2;
       j=1;
      }
      else if(card_ID[i]==Name3[i]){
       Name="Mr.Krashna Mehta ";
       Number=103;
       Batch=2;
       j=2;
      }
      else if(card_ID[i]==Name4[i]){
       Name="Mr.Vraj Shah ";
       Number=104;
       Batch=2;
       j=3;
      }
      else if(card_ID[i]==Name5[i]){
       Name="Ms.Yasvi Vaghashiya";
       Number=105;
       Batch=2;
       j=4;
      }
      else{
          digitalWrite(GreenLed,LOW);
          digitalWrite(BlueLed,HIGH);
          goto cont;
     }
}
      if(NumbCard[j] == 1){
        digitalWrite(BlueLed,HIGH);
        digitalWrite(GreenLed,HIGH);
      }
      else{
      NumbCard[j] = 1;
      Serial.println("DATA,DATE,TIME," + Name);
      Serial.println(Batch);
      //send the Name to excel,
      Serial.print(",");
      Serial.println(Number); //send the Number to excel
      digitalWrite(GreenLed,HIGH);
      digitalWrite(BlueLed,LOW);
      delay(30);
      Serial.println("SAVEWORKBOOKAS,Names/WorkNames");
      }
      delay(1000);
cont:
delay(1000);
digitalWrite(GreenLed,LOW);
digitalWrite(BlueLed,LOW);
}