#![allow(unused_variables, dead_code)]

fn get_bit(num:u32, bit:u8)->u32{
	let val:u32 = (num & (1 << bit));
	val >> bit
}

fn set_bit(num:u32, bit:u8)->u32{
	num | (1 << bit)
}

fn clear_bit(num:u32, bit:u8)->u32{
	num & !(1 << bit)
}

fn update_bit(num:u32, bit:u8, val:u8)->u32{
	let real_val = val & 1;
	let mut num_ret = num & !(1 << bit);
	if real_val == 1{
		num_ret = num_ret | (1 << bit);
	}

	num_ret
}

use std::io;
use std::io::prelude::*;
use std::string::*;

enum db_command {
	SET(String, i32),
	GET(String),
	UNSET(String),
	NUMEQUALTO(i32),
	END,
	BEGIN,
	ROLLBACK,
	COMMIT
}

/* test validity of given command and parse command */
fn is_valid_command(command: &Vec<&str>) -> Result<db_command, &'static str> {
	println!("{}", command[0]);
	match command[0] {
		"SET" =>
			if command.len() == 3 {
				// return what is returned from parse_key_val
			}
			else {
				Err("Incorrect number of arguments for {} command", command[0])
			}
		"GET" => ,
		"UNSET" => true,
		_ => Err("Unrecognized Command")
	}
}

fn main() {
	let mut a_str = String::new();
	io::stdin().read_line(&mut a_str).expect("read error");
	let mut command_and_args = a_str.split_whitespace()
	  								.collect::<Vec<&str>>();
	if(is_valid_command(&command_and_args)) {
		println!("VALID.");
	}
	else {
		println!("INVALID.");
	}

	/*
    let stdin = io::stdin();
    for line in stdin.lock().lines() {
		let mut iter = line.split_whitespace();
		assert_eq!(Some("SET"), iter.next());
		assert_eq!(Some("a"), iter.next());
		assert_eq!(Some("15"), iter.next());
		println("{:?}", iter.collect());
    }*/
}

/*
	SIMPLE DB TODOS:
	1.) parse command and arguments
	2.) determine if command is valid and if args are valid
	3.) add global stack that contains all sessions. (Add current session to stack when begin is called)
	4.) set up two hashtables for each 'session'


	Useful links:
	- parsing line from stdin: https://users.rust-lang.org/t/reading-and-parsing-a-line-from-stdin-containing-3-integers/7265
*/
