package net.yefremov.sample.codegen.schema

/**
 * Represents a fully qualified type name.
 * @author Dmitriy Yefremov
 */
case class TypeName(fullName: String) {

  val (packageName, shortName) = {
    val lastDot = fullName.lastIndexOf('.')
    require(lastDot > 0, "not a valid name")
    (fullName.take(lastDot), fullName.drop(lastDot + 1))
  }

}
