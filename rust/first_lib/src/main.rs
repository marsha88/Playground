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

impl<T:Printable> Line<T>{
	fn printCoordinates(&self){
		self.p1.print();
		self.p2.print();
	}
}

fn main(){
	let a = A;
	Test::Another::intro();

	let p1 = Point{ x: 1, y: 7};
	let p2 = Point{ x: 4, y: 16};
	let line = Line{p1: p1, p2: p2};
	line.printCoordinates();

}
