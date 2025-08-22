/**
 * The CashRegister class holds the logic for performing transactions.
 *
 * @param change The change that the CashRegister is holding.
 */
class CashRegister(private val change: Change) {
    /**
     * Performs a transaction for a product/products with a certain price and a given amount.
     *
     * @param price The price of the product(s).
     * @param amountPaid The amount paid by the shopper.
     *
     * @return The change for the transaction.
     *
     * @throws TransactionException If the transaction cannot be performed.
     */
    fun performTransaction(price: Long, amountPaid: Change): Change {
        val realPrice = price * 100
        if (realPrice > amountPaid.total) throw TransactionException("Not enough for pay full price")
            var changeNeeded = amountPaid.total - realPrice
            val result = Change.none()
            if (changeNeeded == 0L) {
                return Change.none()
            }

            for (denomination in Bill.values()) {
                val countNeeded = (changeNeeded / denomination.minorValue).toInt()
                val registerChange = change.getCount(denomination)

                if (registerChange >= countNeeded) {
                    result.add(denomination, countNeeded)
                    changeNeeded -= denomination.minorValue.toLong() * countNeeded
                    change.remove(denomination, countNeeded)
                } else {
                    result.add(denomination, registerChange)
                    changeNeeded -= denomination.minorValue.toLong() * registerChange
                    change.remove(denomination, registerChange)
                }
            }

            for (coin in Coin.values()) {
                val numOfCount = (changeNeeded / coin.minorValue).toInt()
                val registerChange = change.getCount(coin)

                if (registerChange >= numOfCount) {
                    result.add(coin, numOfCount)
                    changeNeeded -= coin.minorValue.toLong() * numOfCount
                    change.remove(coin, numOfCount)
                } else {
                    result.add(coin, registerChange)
                    changeNeeded -= coin.minorValue.toLong() * registerChange
                    change.remove(coin, registerChange)
                }
            }

        if (changeNeeded > 0) throw TransactionException("Not enough change to give back to customer")

        return result
    }

    class TransactionException(message: String, cause: Throwable? = null) : Exception(message, cause)
}
