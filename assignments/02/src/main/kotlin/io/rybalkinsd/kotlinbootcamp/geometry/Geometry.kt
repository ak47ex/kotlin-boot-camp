package io.rybalkinsd.kotlinbootcamp.geometry

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
class Point(var x: Int, var y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        when (other) {
            is Point -> return other == this
            is Bar -> return other.isContainsPoint(this)
        }
        return false
    }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
class Bar(var firstCornerX: Int, var firstCornerY: Int, var secondCornerX: Int, var secondCornerY: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        when (other) {
            is Point -> return isContainsPoint(other)
            is Bar -> return isCollidingBar(other)
        }
        return false
    }

    fun isCollidingBar(other: Bar): Boolean {
        return (firstCornerX in (other.firstCornerX..other.secondCornerX) || secondCornerX in (other.firstCornerX..other.secondCornerX)) &&
                (firstCornerY in (other.firstCornerY..other.secondCornerY) || secondCornerY in (other.firstCornerY..other.secondCornerY))
    }

    fun isContainsPoint(point: Point): Boolean {
        return point.x in (firstCornerX..secondCornerX) && point.y in (firstCornerY..secondCornerY)
    }
}