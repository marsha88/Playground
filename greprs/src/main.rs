extern crate greprs;
/* used for file arguments */
use std::env;
use std::process;
use std::io::prelude::*;
use greprs::Config;


fn main() {
    let mut stderr = std::io::stderr();
    let config = Config::new(env::args()).unwrap_or_else(|err|{
        writeln!(&mut stderr, "Problem parsing arguments: {}", err)
        .expect("Problem writing to stderr");
        process::exit(1);
    });
    if let Err(e) = greprs::run(config){
        writeln!(&mut stderr, "Application Error: {}", e)
        .expect("Problem writing to stderr");
        process::exit(1);
    }
}
