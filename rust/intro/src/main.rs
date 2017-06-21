#[warn(dead_code)]

mod option_copy;
use option_copy::Optional;
use option_copy::Optional::*;

fn add_nums(x:i32, y:i32)->i32{
	x + y
}

fn accept_ref(x: &Vec<i32>)->i32{
	x[0]
}

fn mod_ref(x: &mut Vec<i32>){
		x[0] = 5;
}

fn dangle() -> String {
    let s = String::from("hello");
    s
}

fn slice(word: &str) -> &str{
	&word[..5]
}

fn plus_one(x: Option<i32>) -> Option<i32> {
    match x {
        None => None,
        Some(i) => Some(i + 1),
    }
}
enum coin{
	Penny,
	Nickel,
	Dime,
	Quarter(i32),
	Half_Dollar
}

fn silver_mining(coins:Vec<coin>)->u32{
	/* cycle through coins and determine if they are made of silver*/
	/* return the number of coins that are silver */
	let mut count = 0;
	for x in 0..coins.len(){
		if let coin::Quarter(year) = coins[x]{
			if year <= 1965{
				count += 1;
			}
		}

	}
	count
}

fn messing_with_vectors(){
	let mut a:Vec<i32> = vec![1,2,3,4];
	let mut b = Vec::new();
	b.push(2);
	let val:i32 = b[0];
	let mut c = vec!["hello", "world"];
}

fn unwrap(x: Option<i32>) -> i32{
	match x{
		Some(x) => { return x; },
		None => { return 0; }
	}
}

fn more_strings(){
	let a = String::from("hello,");
	let c = String::from("dog");
	let b = a + &c;


	println!("{}", b);

	for i in "hello".chars(){
		println!("{}", i);
	}
}

fn giveOptionalVal(num:u32) -> Optional<u32>{
	if num < 10{
		return Optional::Nope;
	}
	return Optional::Value(100);
}

fn main() {
	/*
	   let coins = vec![coin::Quarter(1965), coin::Quarter(1965),coin::Quarter(1999), coin::Penny, coin::Nickel, coin::Quarter(1960)];
	   println!("This set of coins has {} silver coins",silver_mining(coins));
	 */
	let test:i32 = unwrap(Some(5));
	//	messing_with_vectors();
	println!("test is {}", test);

	more_strings();
	struct Test{
		value: i32
	}

	let a = Test{ value: 4 };
	println!("{}", a.value);

	let myBool:bool = if(4 < 5){ true }else{ false };
	println!("{}", myBool);

	let mut myVec = vec![1,3,4,6];
	let mut myVecRef = &mut myVec;
	println!("{:?}", myVecRef);

	let tup = ("hello", 3);
	let (hello, three) = tup;

	println!("{}", hello);
	println!("{}", three);

	if let Value(x) = giveOptionalVal(9){
		println!("Value contained: {}", x );
	}
	else{
		println!("Nope.");
	}
}
