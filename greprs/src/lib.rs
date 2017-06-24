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

pub fn search<'a>(needle:&str, haystack:&'a str) -> Vec<&'a str>{
    let mut results = Vec::new();
    for line in haystack.lines(){
        if line.contains(needle){
            results.push(line);
        }
    }
    results
}

pub fn run(config: Config) -> Result<(), Box<Error>>{
    let mut file = File::open(&config.file).expect("File not found");
    let mut file_content = String::new();
    /* move contents of file to fileContent string */
    file.read_to_string(&mut file_content)?;
    /* print lines containing query */
    for line in search(&config.query, &file_content){
        println!("{}", line);
    }
    Ok(())
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn one_result() {
        let query = "duct";
        let contents = "Rust:\nsafe, fast, productive.\nPick three.";

        assert_eq!(
            vec!["safe, fast, productive."],
            search(query, contents)
        );
    }
}
