@file:Suppress("unused")
package org.sert2521.bunnybots2019.utils

import kotlin.math.pow

private fun Trajectory.j1() = j
private fun Trajectory.j2() = 0.0
private fun Trajectory.j3() = -j
private fun Trajectory.j4() = 0.0
private fun Trajectory.j5() = -j
private fun Trajectory.j6() = 0.0
private fun Trajectory.j7() = j

private fun Trajectory.a1(t: Double) = j1() * t
private fun Trajectory.a2() = a
private fun Trajectory.a3(t: Double) = a2() + j3() * (t - t2)
private fun Trajectory.a4() = 0.0
private fun Trajectory.a5(t: Double) = j5() * (t - t4)
private fun Trajectory.a6() = -a
private fun Trajectory.a7(t: Double) = a6() + j7() * (t - t6)

private fun Trajectory.v1(t: Double) = 0.5 * j1() * t.pow(2)
private fun Trajectory.v2(t: Double) = v1(t1) + a1(t1) * (t - t1)
private fun Trajectory.v3(t: Double) = v2(t2) + a2() * (t - t2) + 0.5 * j3() * (t - t2).pow(2)
private fun Trajectory.v4() = v
private fun Trajectory.v5(t: Double) = v4() + a4() * (t - t4) + 0.5 * j5() * (t - t4).pow(2)
private fun Trajectory.v6(t: Double) = v5(t5) + a5(t5) * (t - t5)
private fun Trajectory.v7(t: Double) = v6(t6) + a6() * (t - t6) + 0.5 * j7() * (t - t6).pow(2)

private fun Trajectory.d1(t: Double) = (1.0 / 6.0) * j1() * t.pow(3)
private fun Trajectory.d2(t: Double) = d1(t1) + v1(t1) * (t - t1) + 0.5 * a1(t1) * (t - t1).pow(2)
private fun Trajectory.d3(t: Double) = d2(t2) + v2(t2) * (t - t2) + 0.5 * a2() * (t - t2).pow(2) + (1.0 / 6.0) * j3() * (t - t2).pow(3)
private fun Trajectory.d4(t: Double) = d3(t3) + v3(t3) * (t - t3)
private fun Trajectory.d5(t: Double) = d4(t4) + v4() * (t - t4) + 0.5 * a4() * (t - t4).pow(2) + (1.0 / 6.0) * j5() * (t - t4).pow(3)
private fun Trajectory.d6(t: Double) = d5(t5) + v5(t5) * (t - t5) + 0.5 * a5(t5) * (t - t5).pow(2)
private fun Trajectory.d7(t: Double) = d6(t6) + v6(t6) * (t - t6) + 0.5 * a6() * (t - t6).pow(2) + (1.0 / 6.0) * j7() * (t - t6).pow(3)

fun Trajectory.j(t: Double) = when {
    t < 0 -> throw IllegalArgumentException("t cannot be negative")
    t <= t1 -> j1()
    t <= t2 -> j2()
    t <= t3 -> j3()
    t <= t4 -> j4()
    t <= t5 -> j5()
    t <= t6 -> j6()
    t <= t7 -> j7()
    else -> throw IllegalArgumentException("t cannot be greater than t7")
}

fun Trajectory.a(t: Double) = when {
    t < 0 -> throw IllegalArgumentException("t cannot be negative")
    t <= t1 -> a1(t)
    t <= t2 -> a2()
    t <= t3 -> a3(t)
    t <= t4 -> a4()
    t <= t5 -> a5(t)
    t <= t6 -> a6()
    t <= t7 -> a7(t)
    else -> throw IllegalArgumentException("t cannot be greater than t7")
}

fun Trajectory.v(t: Double) = when {
    t < 0 -> throw IllegalArgumentException("t cannot be negative")
    t <= t1 -> v1(t)
    t <= t2 -> v2(t)
    t <= t3 -> v3(t)
    t <= t4 -> v4()
    t <= t5 -> v5(t)
    t <= t6 -> v6(t)
    t <= t7 -> v7(t)
    else -> throw IllegalArgumentException("t cannot be greater than t7")
}

fun Trajectory.d(t: Double) = when {
    t < 0 -> throw IllegalArgumentException("t cannot be negative")
    t <= t1 -> d1(t)
    t <= t2 -> d2(t)
    t <= t3 -> d3(t)
    t <= t4 -> d4(t)
    t <= t5 -> d5(t)
    t <= t6 -> d6(t)
    t <= t7 -> d7(t)
    else -> throw IllegalArgumentException("t cannot be greater than t7")
}
