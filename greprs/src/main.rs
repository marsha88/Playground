/* used for file arguments */
use std::env;
/* used for file io */
use std::fs::File;
use std::io::prelude::*;

struct Config{
    query: String,
    file: String
}
impl Config{
    fn new(args:&[String]) -> Config{
        if args.len() < 3{
            panic!("Usage: cargo run <query> <file>");
        }
        Config{ query: args[1].clone(), file: args[2].clone() }
    }
}

fn main() {
    let args:Vec<String> = env::args().collect();
    let config = Config::new(&args);
    /* grab file */
    let mut file = File::open(&config.file).expect("File not found");
    let mut file_content = String::new();
    /* move contents of file to fileContent string */
    file.read_to_string(&mut file_content).expect("Reading file failed.");
    println!("File {} contains\n-------------------------\n{}",
    config.file, file_content);
}
