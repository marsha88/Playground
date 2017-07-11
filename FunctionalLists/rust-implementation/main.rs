/* A function type must be specified as the first parameter of the return function in prepend.
   The head and tail function will both be passed in and they each have different return types.
   Need a different solution.
*/

/* ListType will be used to return multiple types from a function */
/*
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
*/

enum List{
    Cons(i32, Box<List>),
    Nil
}

fn tail(list:Box<List>) -> Box<List>{
    if let List::Cons(val, b) = *list{
        return b;
    }
    return Box::new(List::Nil);
}

fn printList(x:Box<List>){
    if let List::Cons(val, b) = *x{
        println!("{}", val);
        printList(b);
    }
}

fn filter(list:Box<List>, func:fn(i32)->bool) -> Box<List>{
    if let List::Cons(val, b) = *list{
        if func(val){
            return Box::new(List::Cons(val, filter(b, func)));
        }
    }
    return Box::new(List::Nil);
}


fn map(list:Box<List>, func:fn(i32)->i32) -> Box<List>{
    if let List::Cons(val, b) = *list{
        return Box::new(List::Cons(func(val), map(b, func)));
    }
    return Box::new(List::Nil);
}

fn main(){
    let myList = Box::new(List::Cons(3,
        Box::new(List::Cons(2,
            Box::new(List::Cons(22,
                Box::new(List::Nil)))))));

    let underTen = filter(myList, |x:i32| -> bool{
        if x < 10{ true }else{ false }
    });

    let factorsOfTen = map(underTen, |x:i32| -> i32{ x*10 });

    printList(factorsOfTen);
}
