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
        // TODO: Implement logic. Do not change the public interface of the performTransaction() function.
        // if price is higher than the amountPaid, throw a transactionException
        // if price is the same as the amountPaid, return Change.none
        // determine the change needed by getting the value of the amountPaid and subtracting it from the price
        // determine what change the register is currently holding
        // go through each denomination starting from the highest and cycle through each bill to determine if it can be subtracted from the changeNeeded

        return Change.none()
    }

    class TransactionException(message: String, cause: Throwable? = null) : Exception(message, cause)
}
