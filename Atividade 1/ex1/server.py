# Course: Computer Networks
# Prof. Guilherme Correa
# Federal University of Pelotas (UFPEL)
# May 2022
#
# Example of UDP application
# Lower case to upper case converter (server side)
# Requirements: Python3

from socket import *

serverIP = '192.168.1.106'  # localhost or your server IP address
serverPort = 4567		# use the port number you wish (higher than 1023)

serverSocket = socket(AF_INET,SOCK_DGRAM)	# creates a socket (server side)
serverSocket.bind((serverIP, serverPort))	# bind() associates the socket with its local address [bind() is used in the server side]

print("Server is on!")

while 1:
	message, clientIP = serverSocket.recvfrom(1500)		# 1500 bytes are read from the UDP socket
	decodedMessage = message.decode()
	modifiedMessage = decodedMessage.upper()
	encodedMessage = modifiedMessage.encode()
	serverSocket.sendto(encodedMessage, clientIP)		# sends converted (upper-case) sentence
