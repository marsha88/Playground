mod traits;
pub use self::traits::Print;

pub struct MyInt {
    val: i32
}

impl MyInt {
    pub fn new(val:i32) -> MyInt {
        MyInt{ val: val }
    }

    //only this file has access to get_int
    fn get_int(&self) -> &i32{
        &self.val
    }

    pub fn print_int(&self) {
        println!("{}", self.get_int());
    }
}

impl Print for MyInt {
    fn print_struct(&self) {
        println!("MyInt {{ val: {} }}", self.val);
    }
}
