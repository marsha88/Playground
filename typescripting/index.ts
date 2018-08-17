const greet = (name: string): void => console.log(`Hello, ${name}`)

const add = (x: number, y: number): number => x + y

const comp = (f: (any) => any, g: (any) => any) => (x: any) => f(g(x))

interface User {
  id: number
  username: string
  password: string
}

const eq = (a: User, b: User) => a.id === b.id
