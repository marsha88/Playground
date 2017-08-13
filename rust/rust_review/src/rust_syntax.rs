//const is inlined and has no memory space. static does have a memory space.
pub const PI:f32 = 3.14159;
pub static E:f32 = 2.71828;

pub fn use_match(input: i32) -> &'static str {
    match input {
        0 => "zero",
        1 => "one",
        2 => "two",
        _ => "Greater than two."
    }
}

pub enum Coins {
    Penny,
    Nickel,
    Dime,
    Quarter(u8)
}

//using iterators to make nicer abstactions and invoking an if let pattern matching conditional
pub fn silver_quarters(coins: Vec<Coins>) -> Vec<Coins> {
    coins.iter().filter(|coin| { if let Coins::Quarter(n) = coin {
        return n <= 1965;
    }
    return false;});
}
