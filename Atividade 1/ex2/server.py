from socket import *

serverIP = '192.168.1.106'  # localhost or your server IP address
serverPort = 4567			# use the port number you wish (higher than 1023)

serverSocket = socket(AF_INET,SOCK_STREAM)	# creates a socket (server side)
serverSocket.bind((serverIP, serverPort))	# bind() associates the socket with its local address [bind() is used in the server side]
serverSocket.listen(1)						# server starts to listen TCP solicitations

print("Server is on!")

while 1:
	connectionSocket, addr = serverSocket.accept()		# server waits in .accept() to requisitions
	message = serverSocket.recv(1500)
	decodedMessage = message.decode()
	modifiedMessage = decodedMessage.upper()
	encodedMessage = modifiedMessage.encode()
	serverSocket.send(encodedMessage)					# sends converted (upper-case) sentence
