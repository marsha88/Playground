mod rust_structs;
use rust_structs::MyInt;
use rust_structs::Print;

fn main() {
    let test_int = MyInt::new(5);
    test_int.print_int();
    test_int.print_struct();
}
