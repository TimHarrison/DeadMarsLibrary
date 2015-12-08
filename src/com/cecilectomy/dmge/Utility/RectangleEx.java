package com.cecilectomy.dmge.Utility;

import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class RectangleEx extends Rectangle {
	
    public int getTop() {
        return this.y;
    }
    
    public static int getTop(Rectangle r) {
        return r.y;
    }
    
    public int getBottom() {
        return this.y + this.height;
    }
    
    public static int getBottom(Rectangle r) {
        return r.y + r.height;
    }
    
    public int getLeft() {
        return this.x;
    }
    
    public static int getLeft(Rectangle r) {
        return r.x;
    }
    
    public int getRight() {
        return this.x + this.width;
    }
    
    public static int getRight(Rectangle r) {
        return r.x + r.width;
    }
    
    public Point getTopLeft() {
        return new Point(this.getTop(), this.getLeft());
    }
    
    public static Point getTopLeft(Rectangle r) {
        return new Point(getTop(r), getLeft(r));
    }
    
    public Point getTopRight() {
        return new Point(this.getTop(), this.getRight());
    }
    
    public static Point getTopRight(Rectangle r) {
        return new Point(getTop(r), getRight(r));
    }
    
    public Point getBottomLeft() {
        return new Point(this.getBottom(), this.getLeft());
    }
    
    public static Point getBottomLeft(Rectangle r) {
        return new Point(getBottom(r), getLeft(r));
    }
    
    public Point getBottomRight() {
        return new Point(this.getBottom(), this.getRight());
    }
    
    public static Point getBottomRight(Rectangle r) {
        return new Point(getBottom(r), getRight(r));
    }

    public boolean touchingTop(RectangleEx target) {
        return (	this.getBottom() 	>= target.getTop()
                && 	this.getBottom() 	<= target.getTop() 		+ (target.getHeight() / 2)
                && 	this.getRight() 	>= target.getLeft() 	+ (target.getWidth() / 5)
                && 	this.getLeft() 		<= target.getRight() 	- (target.getWidth() / 5));
    }
    
    public static boolean touchingTop(RectangleEx r1, RectangleEx r2) {
        return (	r1.getBottom() 	>= r2.getTop()
                && 	r1.getBottom() 	<= r2.getTop() 		+ (r2.getHeight() / 2)
                && 	r1.getRight() 	>= r2.getLeft() 	+ (r2.getWidth() / 5)
                && 	r1.getLeft() 	<= r2.getRight() 	- (r2.getWidth() / 5));
    }
    
    public static boolean touchingTop(Rectangle r1, Rectangle r2) {
        return (	getBottom(r1) 	>= getTop(r2)
                && 	getBottom(r1) 	<= getTop(r2) 		+ (r2.getHeight() / 2)
                && 	getRight(r1) 	>= getLeft(r2) 		+ (r2.getWidth() / 5)
                && 	getLeft(r1) 	<= getRight(r2) 	- (r2.getWidth() / 5));
    }

    public boolean touchingBottom(RectangleEx target) {
        return (	this.getTop() 		<= target.getBottom()
                && 	this.getTop() 		>= target.getBottom() 	- (target.getHeight() / 2)
                && 	this.getRight() 	>= target.getLeft() 	+ (target.getWidth() / 5)
                && 	this.getLeft() 		<= target.getRight() 	- (target.getWidth() / 5));
    }
    
    public static boolean touchingBottom(RectangleEx r1, RectangleEx r2) {
        return (	r1.getTop() 	<= r2.getBottom()
                && 	r1.getTop() 	>= r2.getBottom() 	- (r2.getHeight() / 2)
                && 	r1.getRight() 	>= r2.getLeft() 	+ (r2.getWidth() / 5)
                && 	r1.getLeft() 	<= r2.getRight() 	- (r2.getWidth() / 5));
    }
    
    public static boolean touchingBottom(Rectangle r1, Rectangle r2) {
        return (	getTop(r1) 		<= getBottom(r2)
                && 	getTop(r1) 		>= getBottom(r2) 	- (r2.getHeight() / 2)
                && 	getRight(r1) 	>= getLeft(r2) 		+ (r2.getWidth() / 5)
                && 	getLeft(r1) 	<= getRight(r2) 	- (r2.getWidth() / 5));
    }

    public boolean touchingLeft(RectangleEx target) {
        return (	this.getRight() 	<= target.getRight()
                && 	this.getRight() 	>= target.getLeft() 	- (target.getWidth() / 5)
                && 	this.getTop() 		<= target.getBottom() 	- (target.getHeight() / 5)
                && 	this.getBottom() 	>= target.getTop() 		+ (target.getHeight() / 5));
    }
    
    public static boolean touchingLeft(RectangleEx r1, RectangleEx r2) {
        return (	r1.getRight() 	<= r2.getRight()
                && 	r1.getRight() 	>= r2.getLeft() 	- (r2.getWidth() / 5)
                && 	r1.getTop() 	<= r2.getBottom() 	- (r2.getHeight() / 5)
                && 	r1.getBottom() 	>= r2.getTop() 		+ (r2.getHeight() / 5));
    }
    
    public static boolean touchingLeft(Rectangle r1, Rectangle r2) {
        return (	getRight(r1) 	<= getRight(r2)
                && 	getRight(r1) 	>= getLeft(r2) 	- (r2.getWidth() / 5)
                && 	getTop(r1) 		<= getBottom(r2) 	- (r2.getHeight() / 5)
                && 	getBottom(r1) 	>= getTop(r2) 		+ (r2.getHeight() / 5));
    }

    public boolean touchingRight(RectangleEx target) {
        return (	this.getLeft() 		>= target.getLeft()
                && 	this.getLeft() 		<= target.getRight() 	+ (target.getWidth() / 5)
                && 	this.getTop() 		<= target.getBottom() 	- (target.getHeight() / 5)
                && 	this.getBottom() 	>= target.getTop() 		+ (target.getHeight() / 5));
    }
    
    public static boolean touchingRight(RectangleEx r1, RectangleEx r2) {
        return (	r1.getLeft() 	>= r2.getLeft()
                && 	r1.getLeft() 	<= r2.getRight() 	+ (r2.getWidth() / 5)
                && 	r1.getTop() 	<= r2.getBottom() 	- (r2.getHeight() / 5)
                && 	r1.getBottom() 	>= r2.getTop() 		+ (r2.getHeight() / 5));
    }
    
    public static boolean touchingRight(Rectangle r1, Rectangle r2) {
        return (	getLeft(r1) 	>= getLeft(r2)
                && 	getLeft(r1) 	<= getRight(r2) 	+ (r2.getWidth() / 5)
                && 	getTop(r1) 		<= getBottom(r2) 	- (r2.getHeight() / 5)
                && 	getBottom(r1) 	>= getTop(r2) 		+ (r2.getHeight() / 5));
    }
}
