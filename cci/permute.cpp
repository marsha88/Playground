#include <stdio.h>
#include <string.h>


void swap(char * s, int i1, int i2) {
	char temp = *(s + i1);
	*(s + i1) = *(s + i2);
	*(s + i2) = temp;
}

void permutate_iter(char * s, int index) {
	if(index == strlen(s) - 1) {
		printf("%s\n", s);
	}
	else {
		for(int i = 0; i < strlen(s); i++) {
			swap(s, i, index);
			permutate_iter(s, index + 1);
			swap(s, i, index);
		}
	}
}

void permutate(char * s) {
	permutate_iter(s, 0);
}


int main() {
	char word[] = "clayton";	
	permutate(word);

	return 0;
}
