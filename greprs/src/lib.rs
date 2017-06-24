use std::fs::File;
use std::io::prelude::*;
use std::error::Error;

pub struct Config{
   pub query: String,
   pub file: String
}
impl Config{
    pub fn new(args:&[String]) -> Result<Config, &'static str>{
        if args.len() < 3 {
            return Err("Usage: cargo run <query> <file>");
        }
        Ok(Config{ query: args[1].clone(), file: args[2].clone() })
    }
}

pub fn run(config: Config) -> Result<(), Box<Error>>{
    let mut file = File::open(&config.file).expect("File not found");
    let mut file_content = String::new();
    /* move contents of file to fileContent string */
    file.read_to_string(&mut file_content)?;
    println!("File {} contains\n-------------------------\n{}",
    config.file, file_content);
    Ok(())
}
