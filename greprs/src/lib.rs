use std::fs::File;
use std::io::prelude::*;
use std::error::Error;

pub struct Config{
   pub query: String,
   pub file: String
}
impl Config{
    pub fn new(mut args: std::env::Args) -> Result<Config, &'static str>{
        args.next();

        let query = match args.next() {
            Some(x) => x,
            None => return Err("Didn't get a query string"),
        };

        let file = match args.next(){
            Some(x) => x,
            None => return Err("Didn't get a file name")
        };

        Ok(Config{ query, file })
    }
}

pub fn search<'a>(needle:&str, haystack:&'a str) -> Vec<&'a str>{
    haystack.lines()
            .filter(|line| line.contains(needle))
            .collect()
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
