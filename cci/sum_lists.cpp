#include <iostream>

using namespace std;

class Node {
	public:
	Node(int val, Node * next) {
		this->val = val;	
		this->next = next;
	}
	Node * next;
	int val;
};


/* recursive solution for same sized linked lists */
int sum_list(Node * a, Node * b, Node * sum) {
	if(a->next == NULL && b->next == NULL){
		int fresh_val = (a->val + b->val);
		Node * fresh_node = new Node(fresh_val % 10, sum);
		sum = fresh_node;
		return fresh_val / 10;
	}
	else {
		int fresh_val = (a->val + b->val + sum_list(a->next, b->next, sum));
		Node * fresh_node = new Node(fresh_val % 10, sum)
		return fresh_val / 10;
	}
}

Node * sum(Node * a, Node * b) {
	if(leng(a) == leng(b)) {
		Node * my_sum = NULL;
		sum_list(a, b, my_sum);
		return my_sum;	
	}	
	else if(leng(a) > leng(b)) {
		int diff = leng(a) - leng(b);
		Node * temp = a;
		while(diff > 0) {
			temp = temp->next;	
		}	
		Node * summed = NULL;	
		sum_list(temp, b, summed);
		
		/* attached new list to end of a	
	}
}


int main() {


return 0;
}
