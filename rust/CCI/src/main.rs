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

/* Need:

*/

fn shortest(grid:&Vec<Vec<i32>>, pos:(usize, usize), dv:i32, path:Vec<(usize, usize)>) -> Vec<(usize, usize)> {
	let (x, y) = pos;
	if grid[x][y] == dv {
		path
	}
	else {
		//return min path returned from each neighbor.
	}
}

fn shortest_path(grid:&Vec<Vec<i32>>) -> Vec<(usize, usize)> {
	let start_pos = (0, 0);
	let desired_val = 5;
	shortest(grid, (0,0), desired_val, vec![start_pos])
}


fn main() {
	println!("Running Shortest Path.");
}
