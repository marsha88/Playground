
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

fn main() {
	let a:&str = "Hello, world";
	let hello:&str = slice(a);
	println!("{:?}",str);
}
