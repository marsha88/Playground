#include <stdio.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>

#define PORT 3000

int main(int argc, char *argv[]){

	int main_socket = socket(AF_INET, SOCK_STREAM, 0);	
	if( main_socket < 0 ){
		fprintf(stderr, "Error: main_socket < 0");
	}
	
	struct sockaddr_in serverAddr;
	memset( &serverAddr, 0, sizeof(serverAddr) );

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons( PORT ); 


	return 0;
}
