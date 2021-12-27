package com.meli.xmen.model;

import lombok.Getter;

@Getter
public class Sequence {
    private final Coordinate initial;
    private final Coordinate end;
    private double slope;
    private double c;

    public Sequence(int xInitial, int yInitial, int xEnd, int yEnd) {
        this.initial = new Coordinate(xInitial, yInitial);
        this.end = new Coordinate(xEnd, yEnd);
        setSlope();
    }

    public boolean isInRange(int x, int y) {
        return x >= initial.getX() && x <= end.getX() && y >= initial.getY() && y <= end.getY() && isInLine(x, y);
    }

    public boolean isInInverseRange(int x, int y) {
        return x <= initial.getX() && x >= end.getX() && y >= initial.getY() && y <= end.getY() && isInLine(x, y);
    }

    public boolean isInLine(int x, int y) {
        if (Double.isNaN(slope))
            return x == initial.getX();
        return y == slope * x + c;
    }

    public void setSlope() {
        if (end.getX() - initial.getX() == 0) {
            this.slope = Double.NaN;
            this.c = initial.getX();
        } else {
            this.slope = Math.round((end.getY() - initial.getY()) / (double) (end.getX() - initial.getX()) * 10) / 10.0;
            this.c = initial.getY() - this.slope * initial.getX();
        }
    }
}
