package io.beaniejoy.junittest;

public class Study {
    private StudyStatus status;

    private int limit;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 합니다.");
        }

        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
