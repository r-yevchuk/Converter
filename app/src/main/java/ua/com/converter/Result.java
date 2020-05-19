package ua.com.converter;

class Result {
    int min;
    int sec;

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