import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class CashRegisterTest {
    @Test
    fun testTransactionBills() {
        val register = CashRegister(Change.max())
        val price = 10L 
        val amountPaid = Change().apply { add(Bill.TWENTY_EURO, 1) }

        val result = register.performTransaction(price, amountPaid)

        assertEquals(1000L, result.total)
        assertEquals(1, result.getCount(Bill.TEN_EURO))    }

    @Test
    fun testTransactionCoins() {
        val register = CashRegister(Change.max())
        val price = 17L
        val amountPaid = Change().apply { add(Bill.TWENTY_EURO, 1) }

        val result = register.performTransaction(price, amountPaid)

        assertEquals(300L, result.total)
        assertEquals(1, result.getCount(Coin.TWO_EURO))
        assertEquals(1, result.getCount(Coin.ONE_EURO))
    }

    @Test
    fun testNotEnoughChangeInRegister() {
        val limitedChange = Change().apply {
            add(Bill.ONE_HUNDRED_EURO, 3)
            add(Bill.FIFTY_EURO,1)
            add(Coin.TWO_EURO, 20)
        }
        val register = CashRegister(limitedChange)

        val price = 4500L 
        val amountPaid = Change().apply {
            add(Bill.FIVE_HUNDRED_EURO, 10)
        }

        assertThrows(CashRegister.TransactionException::class.java) {
            register.performTransaction(price, amountPaid)
        }
    }

    @Test
    fun testLimitedChangeInRegister() {
        val registerChange = Change().apply {
            add(Bill.FIVE_EURO, 2)
        }
        val register = CashRegister(registerChange)

        val price = 90L
        val amountPaid = Change().apply { add(Bill.ONE_HUNDRED_EURO, 1) }

        val result = register.performTransaction(price,amountPaid)


        assertEquals(1000L, result.total)
        assertEquals(result.getCount(Bill.FIVE_EURO), 2)
    }


}
