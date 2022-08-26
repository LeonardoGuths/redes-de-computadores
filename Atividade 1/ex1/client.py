# Course: Computer Networks
# Prof. Guilherme Correa
# Federal University of Pelotas (UFPEL)
# May 2022
#
# Example of UDP application
# Lower case to upper case converter (client side)
# Requirements: Python3

from socket import *

serverIP = '192.168.1.106'  # localhost or your server IP address
serverPort = 4567		# use the port number you wish (higher than 1023)

clientSocket = socket(AF_INET, SOCK_DGRAM)  # creates a socket (client side)

message = input('Enter your lower-case word: ')
encodedMessage = message.encode()
clientSocket.sendto(encodedMessage, (serverIP, serverPort))     # sends the message to server (no need to use connect() before, since it is UDP)

modifiedMessage, serverIP = clientSocket.recvfrom(1500)         # 1500 bytes are read from the UDP socket
decodedMessage = modifiedMessage.decode()

print(decodedMessage)   # print received/decoded message

clientSocket.close()
