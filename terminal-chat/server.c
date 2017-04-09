#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>

#define PORT 3000

int main(int argc, char * argv[]){
	
	int main_socket = socket(PF_INET, SOCK_STREAM, 0);
	if( main_socket < 0 ){
		fprintf(stderr, "Error: main_socket < 0");
		exit(1);
	}
	struct sockaddr_in serverAddress;
	memset( &serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = INADDR_ANY;

	if( bind(main_socket, (struct sockaddr *)&serverAddress, sizeof(serverAddress) ) < 0 ){
		fprintf(stderr, "Error: bind < 0");		
		exit(1);
	}

	if( listen(main_socket, 5) < 0 ){
		fprintf(stderr, "Error: listen < 0");	
		exit(1);
	}
		

	int connect_socket = accept( main_socket, NULL, NULL);

	
	return 0;
}
