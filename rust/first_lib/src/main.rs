extern crate first_lib;

use first_lib::test;
use first_lib::client::Letters::{A, B, C};

mod Test{
	pub mod Another{
		pub fn intro(){
			println!("Welcome to the inner module.");
		}
	}
}

/* Trait and Generic Example */
trait Printable{
	fn print(&self);
}

struct Point{
	x: i32,
	y: i32
}

impl Printable for Point{
	fn print(&self){
		println!("{},{}", self.x, self.y);
	}
}

struct Line<T:Printable>{
	p1: T,
	p2: T
}

/* T where T implements the trait Printable */
impl<T> Line<T> where T:Printable{
	fn printCoordinates(&self){
		self.p1.print();
		self.p2.print();
	}
}

fn longest<'a>(x:&'a str, y: &'a str) -> &'a str{
	if x.len() > y.len(){ x }else{ y }
}

fn return_function(x:i32) -> Box<Fn(i32)->i32>{
	Box::new(move |y| x*y)
}

fn main(){
	let a = A;
	Test::Another::intro();

	let p1 = Point{ x: 1, y: 7};
	let p2 = Point{ x: 4, y: 16};
	let line = Line{ p1, p2 };
	line.printCoordinates();

	let str1 = "Hello";
	let str2 = "Good-bye";
	println!("{}", longest(str1, str2));

	/* Destructuring Tuple Struct */
	struct TupleStruct (u8, u8, u8);
	let b = TupleStruct(1,2,3);
	let TupleStruct(h, i, j) = b;
	println!("{},{},{}", h, i, j);

	let clojure = return_function(2);
	println!("{}", clojure(3));
}
