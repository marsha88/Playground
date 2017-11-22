#include <iostream>

using namespace std;

struct tnode {
	int value;
	tnode * left;
	tnode * right;
	tnode(int value, tnode * left, tnode * right) {
		this->value = value;
		this->left = left;
		this->right = right;
	}
	~tnode() {
		cout << "called" << endl;
		free(left);
		free(right);		
	}

	void addNode(int val) {
		
	}

};

int addWithTwo(int num) {
	return num + 2;
}

int main() {
	tnode * root = new tnode(20, nullptr, nullptr);
	
	delete(root);

	return 0;
}


