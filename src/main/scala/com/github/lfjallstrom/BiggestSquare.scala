package com.github.lfjallstrom

import scala.io.Source

object BiggestSquare {

  def resolve(filename: String) : Int = {
    resolve(Source.fromURL(getClass.getClassLoader.getResource(filename), "UTF-8").getLines.map(_.toInt).toList)
  }

  def resolve(input: List[Int]) : Int = {
    case class Prospect(potential: Int, startPos: Int) {
      def sizeAt(pos: Int) = Math.min(potential, pos - startPos + 1)
    }

    input
      .zipWithIndex
      .foldLeft((0, List.empty[Prospect])) { case ((largest, prospects), (height, currentPos)) =>
        val (completed, newProspects) = {
          // Start a new prospect from current position and truncate the existing prospects to have at most the height of the current tower
          ((Prospect(height, currentPos)) :: prospects.map(prospect => prospect.copy(potential = Math.min(prospect.potential, height))))
            .flatMap { prospect =>
              // Duplicate the prospects with their current size to make sure we catch the blocks even if they don't realize their full potential
              prospect.copy(potential = prospect.sizeAt(currentPos)) :: prospect :: Nil
            }
            // Throw away everything that cannot produce better results we already have
            .filter(prospect => prospect.potential > largest)
            // No need to keep multiple prospects that could produce the same result
            .groupBy(_.potential)
            .map { case (potential, prospects) =>
              // Lets keep the prospect that started at the earliest position as it has the highest probability to become realized
              prospects.minBy(_.startPos)
            }
            .toList
            .partition(prospect => prospect.sizeAt(currentPos) == prospect.potential)
        }

        val newLargest = completed.map(_.potential).reduceOption(_ max _).getOrElse(largest)

        (newLargest, newProspects)
      }
      ._1
  }
}
