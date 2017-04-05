
use std::mem;
use std::i32;

fn add_nums(x:i32, y:i32)->i32{
	x + y
}

fn main() {
	//call add_nums function
    println!("5 + 3 is {}", add_nums(5,3));

    //printing size of a variable typed by rust compiler
    let mut myVar = 1234567;	//reads as i32
    println!("myVar = {}, size of myVar = {} bytes", myVar, mem::size_of_val(&myVar));

    let c:isize = 10;
    println!("This is OS has a {} bit architecture", mem::size_of_val(&c)*8);


  	let base:i32 = 2;
  	println!("{} ^ 10 = {}",base, i32::pow(base, 10));

  	println!("PI = {}", std::f64::consts::PI);

  	//bitshifting operations example
  	let val = 1;
  	println!("2^10 = {}", val << 10);


}
