#![feature(inclusive_range_syntax)]
/*
struct Node {
    val:i32,
    next: Option<Box<Node>>
}

impl Node {
    fn print_val(&self) {
        println!("{}", self.val);
    }

    fn new(val:i32, next: &Option<Box<Node>>) {
        Node {
            val: val,
            next: next
        }
    }
}

struct List {
    head: Option<Box<Node>>
}

impl List {
    fn new() -> List {
        List{
            head: None
        }
    }

    fn push(&self, val:i32) {
        let new_node = Some(Box::new(Node::new(val, self.head));
        self.head = new_node
    }
}

/* enum list
enum List {
    Cons(i32, Box<List>),
    Nil
} */

*/
fn is_prime(num:usize) -> bool {
    if(num == 1) {
        return true;
    }

    for i in 2..num {
        if(num % i == 0){
            return false;
        }
    }

    true
}

fn n_primes(n:usize) -> Vec<usize> {
    let mut primes:Vec<usize> = Vec::new();
    let mut count:usize = 1;
    while(primes.len() < n){
        if is_prime(count) {
            primes.push(count);
        }
        count += 1;
    }
    primes
}

fn b_sort(arr:&mut Vec<i32>) {
    for i in 0..arr.len() {
        for j in i..arr.len() {
            if arr[i] > arr[j] {
                let temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
    }
}

fn getMin(arr:&Vec<i32>, start:u32, end:u32) -> u32 {
    let mut min = arr[0];
    let mut minIndex:u32 = 0;
    for i in 1..end {
        if arr[i] < min {
            min = arr[i];
            minIndex = i;
        }
    }
    minIndex
}

fn s_sort(arr:&mut Vec<i32>) {
    for i in 0..(arr.len()-1) {
        let index_min:u32 = getMin(arr, i, arr.len());
        let temp = arr[i];
        arr[i] = arr[index_min];
        arr[index_min] = temp;
    }
}

fn main() {
    /*
    let cat:i32 = 5;
    let print_cat = ||{ println!("cat => {}", cat) };
    // inner function does not have access to outer environment
    fn test(x:i32, y:i32) -> i32 {
        x + y
    }
    let one_twenty = 1...20;    //using experimental syntax.
    let odd_thru_twenty = (1..21).filter(|x| x % 2 == 1).collect::<Vec<i32>>();
    println!("{:?}", odd_thru_twenty);
    */

    //let test_list = Cons(3, Box::new(Cons(4, Box::new(Cons( 5, Box::new(Nil))))));
    //println!("First {} primes = {:?}", 100, n_primes(100));

    let mut unsorted_list = vec![3,-1, 16, 2,0, -32];
    s_sort(&mut unsorted_list);
    println!("{:?}", &unsorted_list);

}
