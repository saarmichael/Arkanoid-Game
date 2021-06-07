package arkanoidgame;

// 315240937
// Michael Saar

/**
 * this class represents objects that keep a counter variable.
 *
 * @author mmichael Saar
 * @since 26-05-2021
 */
public class Counter {

    private int count;

    /**
     * Constructor.
     *
     * @param c the initial value.
     */
    public Counter(int c) {
        this.count = c;
    }

    /**
     * empty Constructor.
     * set initial value to be 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * @param number the amount to add to Counter
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * increase Counter by 1.
     */
    public void increase() {
        this.count++;
    }

    /**
     * subtract number from current count.
     *
     * @param number number to subtract.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * subtract 1 from current count.
     */
    public void decrease() {
        this.count--;
    }

    /**
     * getter.
     *
     * @return the current amount.
     */
    public int getValue() {
        return this.count;
    }

    /**
     * set new value to Counter.
     *
     * @param c the value to set.
     */
    public void setCount(int c) {
        this.count = c;
    }

    @Override
    public String toString() {
        return "Counter{" + "count=" + count + '}';
    }
}