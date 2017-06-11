#[warn(dead_code)]
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

fn main() {
	//Find the silver quarters
	let coins = vec![coin::Quarter(1965), coin::Quarter(1965),coin::Quarter(1999), coin::Penny, coin::Nickel, coin::Quarter(1960)];
	println!("This set of coins has {} silver coins",silver_mining(coins));
		
}
