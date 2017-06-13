pub mod client;

pub mod network;

pub mod test{
	const dog:i32 = super::client::dog;
	fn run_test(){
		super::client::connect();
	}
}
