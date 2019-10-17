import scala.collection.mutable.HashMap

class School {
  type DB = HashMap[Int, Seq[String]]

  private var _db = new DB

  def db: DB = _db

  def add(name: String, g: Int) = {
    val updatedRoster = 
      if (_db.contains(g)) {
        _db(g) :+ name
      } else {
        Seq(name)
      }

    _db(g) = updatedRoster
  }

  def grade(g: Int): Seq[String] = _db(g)

  def sorted: DB = new DB
}
