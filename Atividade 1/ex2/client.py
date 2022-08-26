from socket import *

serverIP = '192.168.1.106'  # localhost or your server IP address
serverPort = 4567		# use the port number you wish (higher than 1023)

clientSocket = socket(AF_INET, SOCK_STREAM)  # creates a socket (client side)
clientSocket.connect((serverIP, serverPort))
message = input('Enter your lower-case word: ')
encodedMessage = message.encode()
clientSocket.send(encodedMessage) 

modifiedMessage = clientSocket.recv(1500)
decodedMessage = modifiedMessage.decode()

print(decodedMessage)   # print received/decoded message

clientSocket.close()
