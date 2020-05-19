package ua.com.converter;

class Result {
    private final int min;
    private final int sec;

    Result() {
        min = 0;
        sec = 0;
    }

    Result(int min, int sec) {
        this.min = min;
        this.sec = sec;
    }

    public String toString() {
        return "" + min + " m " + sec + " s";
    }
}