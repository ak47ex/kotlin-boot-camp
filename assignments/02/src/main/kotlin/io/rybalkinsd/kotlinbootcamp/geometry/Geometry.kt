package io.rybalkinsd.kotlinbootcamp.geometry

import kotlin.math.max
import kotlin.math.min

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
data class Point(val x: Int, val y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean =
        when (other) {
            is Point -> other == this
            is Bar -> other.isContainsPoint(this)
            else -> false
        }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
class Bar(firstCornerX: Int, firstCornerY: Int, secondCornerX: Int, secondCornerY: Int) : Collider {

    val leftX = min(firstCornerX, secondCornerX)
    val rightX = max(firstCornerX, secondCornerX)
    val bottomY = min(firstCornerY, secondCornerY)
    val topY = max(firstCornerY, secondCornerY)

    override fun isColliding(other: Collider): Boolean {
        when (other) {
            is Point -> return isContainsPoint(other)
            is Bar -> return isCollidingBar(other) || other.isCollidingBar(this)
        }
        return false
    }

    fun isCollidingBar(other: Bar): Boolean {
        return (leftX in (other.leftX..other.rightX) || rightX in (other.leftX..other.rightX)) &&
                (bottomY in (other.bottomY..other.topY) || topY in (other.bottomY..other.topY))
    }

    fun isContainsPoint(point: Point): Boolean {
        return point.x in (leftX..rightX) && point.y in (bottomY..topY)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Bar) {
            return (leftX == other.leftX) && (rightX == other.rightX) && (bottomY == other.bottomY) && (topY == other.topY)
        }
        return super.equals(other)
    }
}