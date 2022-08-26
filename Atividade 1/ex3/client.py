from socket import *

serverIP = '192.168.1.106'  # localhost or your server IP address
serverPort = 4567		    # use the port number you wish (higher than 1023)

clientSocket = socket(AF_INET, SOCK_STREAM) # creates a socket (client side)
clientSocket.connect((serverIP, serverPort))

option = input ('Select server function:\n1. Lower to Upper\n2. Upper to Lower\n')
if (option == '1'):
    message = input('Enter your lower-case word: ')
    message = "CB+" + message
else:
    message = input('Enter your upper-case word: ')
    message = "CA+" + message

encodedMessage = message.encode()
clientSocket.send(encodedMessage)       # sends the message to server

modifiedMessage = clientSocket.recv(1500)
decodedMessage = modifiedMessage.decode()

print(decodedMessage)       # print received/decoded message

clientSocket.close()
