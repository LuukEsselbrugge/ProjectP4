#include <SoftwareSerial.h>
#include <FastLED.h>
#include <stdlib.h>

String DEVICE_ID = "Kast1";

//Zie foto uit ontwerp 5 rows 5 cols
int numCol = 5;
int numRows = 5;
//Aantal LEDS per col en row (60 leds op 1 rol 60 / 5 = 12 * 2 rollen)
int LEDPerCol = 12;
int LEDPerRow = 12;

CRGB rowLEDS[60];
CRGB colLEDS[60];

String searchResults[12] = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};

SoftwareSerial ESP(10, 9); // RX, TX
String inData;

void setup() {
  //searchResults[0] = "0101000000255";
  //searchResults[1] = "0101255000000";

  FastLED.addLeds<WS2812, 5, GRB>(rowLEDS, 60);
  FastLED.addLeds<WS2812, 6, GRB>(colLEDS, 60);

  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  Serial.println("Hello bami!");

  // set the data rate for the ESP port
  ESP.begin(19200);
  tcpConnect();
  testMode();

}

void loop() { // run over and over
  tcpReceive();

}



void updateLEDS() {
  FastLED.clear();
  for (int i = 0; i < 12; i++) {
    if (searchResults[i] != "0") {
      CRGB color = CRGB(searchResults[i].substring(4, 7).toInt(), searchResults[i].substring(7, 10).toInt(), searchResults[i].substring(10, 13).toInt() );
      //Plaats resultaat op eerste lege led van gegeven Row

      for (int x = 0; x < LEDPerRow; x++) {
        int num = String(searchResults[i].substring(0, 2)).toInt() * LEDPerRow - LEDPerRow + x;
        if (rowLEDS[num].r == 0 && rowLEDS[num].g == 0 && rowLEDS[num].b == 0 ) {
          rowLEDS[num] = color;
          x = LEDPerRow;
        }
      }
      //Plaats resultaat op eerste lege led van gegeven Col
      for (int x = 0; x < LEDPerCol; x++) {
        int num = String(searchResults[i].substring(0, 2)).toInt() * LEDPerCol - LEDPerCol + x;
        if (colLEDS[num].r == 0 && colLEDS[num].g == 0 && colLEDS[num].b == 0 ) {
          colLEDS[num] = color;
          x = LEDPerCol;
        }
      }
      FastLED.show();
    }
  }
}

void tcpConnect() {
  ESP.println("AT+CIPSTART=\"TCP\",\"10.0.0.101\",1337");
  delay(2000);
  tcpSend(DEVICE_ID);
}

void tcpSend(String d) {

  ESP.print("AT+CIPSEND=");
  ESP.println(d.length() + 2);
  delay(30);
  ESP.println(d);
  delay(300);
}


void tcpReceive() {

  while (ESP.available())
  {
    char recieved = ESP.read();
    inData += recieved;
    if (recieved == '\n')
    {
      Serial.print(inData);
      if (inData.substring(0, 4) == "+IPD") {
        String tcpdata = inData.substring(inData.indexOf(':') + 1);

        //Command add
        if (tcpdata[0] == '0') {
          //tcpSend("");
          if (tcpdata.substring(2, 4).toInt() < 11) {
            searchResults[tcpdata.substring(2, 4).toInt()] = tcpdata.substring(4, 17);
            updateLEDS();
          }
        }

        //Command remove
        if (tcpdata[0] == '1') {
          if (tcpdata.substring(2, 4).toInt() < 11) {
            searchResults[tcpdata.substring(2, 4).toInt()] = "0";
            updateLEDS();
          }
        }

        //Heartbeat
        if (tcpdata[0] == 'a') {
          tcpSend("");
        }

        //Testmode
        if (tcpdata[0] == 't') {
          testMode();
        }

      }

      //If TCP Pipe is broken try and reconnect every 2 seconds
      if (inData.indexOf("CLOSED") != -1 || inData.indexOf("ERROR") != -1 || inData.indexOf("link is n") != -1) {
        tcpConnect();
      } else {

      }
      inData = ""; // Clear buffer
    }
  }
}

void testMode() {
  for (int dot = 0; dot < 60; dot++) {
    rowLEDS[dot] = CRGB::Red;
    colLEDS[dot] = CRGB::Red;
    FastLED.show();
    delay(10);
  }
  delay(100);
  for (int dot = 0; dot < 60; dot++) {
    rowLEDS[dot] = CRGB::Green;
    colLEDS[dot] = CRGB::Green;
    FastLED.show();
    delay(10);
  }
  delay(100);
  for (int dot = 0; dot < 60; dot++) {
    rowLEDS[dot] = CRGB::Blue;
    colLEDS[dot] = CRGB::Blue;
    FastLED.show();
    delay(10);
  }

  delay(500);
  FastLED.clear();
  FastLED.show();
}
