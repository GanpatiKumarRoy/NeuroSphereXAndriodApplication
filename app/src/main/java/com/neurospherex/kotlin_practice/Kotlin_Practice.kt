package com.neurospherex.kotlin_practice

class Solution {
    // 121. Best Time to Buy and Sell Stock.
    fun maxProfit(prices: IntArray): Int {
        if (prices.size <= 1) {
            return 0
        }

        var minPrice = Int.MAX_VALUE
        var maxProfit = 0
        for(price in prices){
            if(minPrice > price){
                minPrice = price
            } else if (maxProfit < price - minPrice) {
                maxProfit = price - minPrice
            }
        }

        return maxProfit
    }

    // 4. Median of Two Sorted Arrays
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val totalArraySize = nums1.size + nums2.size
        val isArrayEven = totalArraySize % 2 == 0
        val midSize = totalArraySize / 2

        var i = 0
        var j = 0
        var count = 0
        var left = 0
        var right = 0

        while (count <= midSize) {
            left = right
            if (i < nums1.size && (j >= nums2.size || nums1[i] < nums2[j])) {
                right = nums1[i]
                i++
            } else {
                right = nums2[j]
                j++
            }
            count++
        }

        return if (isArrayEven) (left + right) / 2.0 else right.toDouble()
    }
}

// Execute the code.
fun main() {
    val solution = Solution()
    /*val num1 = intArrayOf(2,2,4,4)
    val num2 = intArrayOf(2,2,2,4,4)*/
    // val average = solution.findMedianSortedArrays(num1, num2)
    val prices = intArrayOf(7,1,5,3,6,4)
    // val prices = intArrayOf(7,9,12,10,8,14)
    val profit = solution.maxProfit(prices)
    println("The maximum profit is: $profit")
}