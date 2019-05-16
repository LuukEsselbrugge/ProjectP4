#include <SoftwareSerial.h>

String DEVICE_ID = "Kast01";

SoftwareSerial ESP(11, 10); // RX, TX
String inData;


void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  Serial.println("Hello bami!");

  // set the data rate for the ESP port
  ESP.begin(9600);
  tcpConnect();
  
}

void loop() { // run over and over
  tcpReceive();
  
}

void tcpConnect(){
    ESP.println("AT+CIPSTART=\"TCP\",\"10.42.0.1\",1337");
    delay(2000);
    tcpSend(DEVICE_ID);
}

void tcpSend(String d){
    ESP.print("AT+CIPSEND=");
    ESP.println(d.length() + 1);
    delay(300);
    ESP.println(d);
    delay(100);

}


void tcpReceive(){
 while (ESP.available())
    {
        char recieved = ESP.read();
        inData += recieved; 
        if (recieved == '\n')
        {
           Serial.print(inData);
           if(inData.substring(0,4) == "+IPD"){
            String tcpdata = inData.substring(inData.indexOf(':') + 1);

            //Command add
            if(tcpdata[0] == '0'){
              tcpSend("");
            }

            //Command remove
            if(tcpdata[0] == '1'){

           
            }
            
           }

           //If TCP Pipe is broken try and reconnect every 2 seconds
           if(inData == "CLOSED\r\n" || inData == "ERROR\r\n" || inData == "AT+CIPSEND=CLOSED\r\n"){
              tcpConnect();
           }
            inData = ""; // Clear recieved buffer
        }
    }
}
