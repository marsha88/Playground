mod rust_structs;
use rust_structs::MyInt;
use rust_structs::traits::Print;

mod rust_syntax;
use rust_syntax::*;

fn main() {
    
    let test_int = MyInt::new(5);
    test_int.print_int();
    test_int.print_struct();

    println!("Match found: {}",use_match(2));

    println!("PI = {}", PI);
    println!("e = {}", E);

}
