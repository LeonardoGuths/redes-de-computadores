from socket import *

serverIP = '192.168.1.106'  # localhost or your server IP address
serverPort = 4567		# use the port number you wish (higher than 1023)

clientSocket = socket(AF_INET, SOCK_DGRAM)  # creates a socket (client side)
option = input ('Select server function:\n1. Lower to Upper\n2. Upper to Lower\n')
if (option == '1'):
    message = input('Enter your lower-case word: ')
    message = "CB+" + message
else:
    message = input('Enter your upper-case word: ')
    message = "CA+" + message

encodedMessage = message.encode()
clientSocket.sendto(encodedMessage, (serverIP, serverPort))     # sends the message to server (no need to use connect() before, since it is UDP)

modifiedMessage, serverIP = clientSocket.recvfrom(1500)         # 1500 bytes are read from the UDP socket
decodedMessage = modifiedMessage.decode()

print(decodedMessage)   # print received/decoded message

clientSocket.close()
