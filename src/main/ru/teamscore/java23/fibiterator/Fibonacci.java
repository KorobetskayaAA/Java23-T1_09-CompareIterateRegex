package ru.teamscore.java23.fibiterator;

import java.math.BigInteger;
import java.util.Iterator;

public class Fibonacci implements Iterable<BigInteger> {
    private BigInteger prevNumber = new BigInteger("0");
    private BigInteger curNumber = new BigInteger("1");

    private class FibonacciIterator implements Iterator<BigInteger> {

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public BigInteger next() {
            BigInteger sum = curNumber.add(prevNumber);
            prevNumber = curNumber;
            curNumber = sum;
            return prevNumber;
        }
    }
    private Iterator<BigInteger> fibonacciIterator = new FibonacciIterator();


    @Override
    public Iterator<BigInteger> iterator() {
        return fibonacciIterator;
    }
}
