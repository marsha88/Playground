/* A function type must be specified as the first parameter of the return function in prepend.
   The head and tail function will both be passed in and they each have different return types.
   Need a different solution.
*/

/* ListType will be used to return multiple types from a function */

enum ListType{
    Head(/* function that returns i32 */),
    Tail(/* function that returns the same function type. */)
}

struct ListFunction(fn(i32, ListFunction, bool) -> /* selector function */ );

fn prepend<F, S>(head:i32, tail:F, empty:bool) -> S
where F: Fn(i32, F, bool) -> S,
    S: Fn(/* the problem lies here*/){
    return function(selector){
        return selector(head, tail, empty);
    }
}

fn head(list:F) -> i32{
    return list(|head, tail, empty| head);
}

fn tail(list:F) -> F where F: Fn(i32, F, bool){
    return list(|head, tail, empty| tail);
}


fn main(){


}
