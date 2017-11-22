#include <iostream>
#include <vector>
using namespace std;
void printList(vector<int> a) {
	for(int i = 0; i < a.size(); i++)
		cout << a[i] << ",";

	cout << endl;
}

int findLIS(vector <int> s) {
	printList(s);
	int longestSeq = 0;
	for(int i = 0; i < s.size(); i++) {
		int lastCheck = i, currentSeq = 0;
		for(int j = i; j < s.size(); j++) {
			if(s[j] > s[lastCheck] || lastCheck == j) {
				currentSeq++;
				lastCheck = j;
			}
		}
		if(currentSeq > longestSeq) {
			longestSeq = currentSeq;
		}
	}
	return longestSeq;
}

int main() {
	int myints[] = {1,2,3};
	vector<int> test(myints, myints + sizeof(myints) / sizeof(int) ); 
	cout << findLIS(test) << endl;		
	return 0;
}

