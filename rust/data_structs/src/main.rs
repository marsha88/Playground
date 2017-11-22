fn sort_stack(mut st:Vec<i32>) -> Vec<i32> {
	let mut temp:Vec<i32> = Vec::new();
	let full_stack = st.len();
	while temp.len() < full_stack {
		if let Some(bucket) = st.pop(){
			while !temp.is_empty() && temp[temp.len()-1] > bucket {
				if let Some(to_push) = temp.pop() {
					st.push(to_push);
				}
			}
			temp.push(bucket);
		}
	}

	println!("temp stack => {:?}", temp);
	st
}

struct Node {
	data: i32,
	next: Option<Box<Node>>
}

impl Node {
	fn new(val:i32) -> Node {
		Node {
			data: val,
			next: None
		}
	}

	fn append(&mut self, n:Node) {
		self.next = Some(Box::new(n))
	}

	fn print(&self) {
		if let None = self.next {
			println!("{}", self.data);
		}
		else if let Some(ref x) = self.next {
			println!("{},", self.data);
			x.print();
		}
	}
}

fn main() {
	let mut head:Node = Node::new(1);
	head.append(Node{ data: 2, next: None});
	head.print();
}
