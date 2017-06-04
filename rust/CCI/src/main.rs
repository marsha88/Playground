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

fn main() {
	
}
