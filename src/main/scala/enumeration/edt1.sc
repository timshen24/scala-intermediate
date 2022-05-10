enum Direction:
  case North, East, South, West

import Direction.{North, South, East, West}
def invert(dir: Direction): Direction =
  dir match
    case North => South
    case East => West
    case South => North
    case West => East

invert(North)
invert(East)

North.ordinal  // 0
East.ordinal   // 1
South.ordinal  // 2
West.ordinal   // 3

Direction.values

Direction.valueOf("North")           // North
Direction.valueOf("East")            // EastDirection.valueOf("Up"
//Direction.valueOf("Up")



