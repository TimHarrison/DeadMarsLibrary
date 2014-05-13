package com.cecilectomy.dmge.Utility;

/**
 * DeadMarsLib TimeSpan Class
 * 
 * @author Daniel Cecil
 */
public class TimeSpan {
        
        long span;
        long start;
        long end;

        /**
         * A TimeSpan of zero length.
         */
        public static final TimeSpan Zero = new TimeSpan(0);

        /**
         * Creates a TimeSpan from a length in Nanoseconds.
         * 
         * @param s Nanoseconds to create TimeSpan from.
         * @return New TimeSpan Object.
         */
        public static TimeSpan fromNanoseconds(long s) {
            return new TimeSpan(s);
        }

        /**
         * Creates a TimeSpan from a length in Milliseconds.
         * 
         * @param s Milliseconds to create TimeSpan from.
         * @return New TimeSpan Object.
         */
        public static TimeSpan fromMilliseconds(long s) {
            return new TimeSpan(s * 1000000L);
        }

        /**
         * Creates a TimeSpan from a length in Seconds.
         * 
         * @param s Seconds to create TimeSpan from.
         * @return New TimeSpan Object.
         */
        public static TimeSpan fromSeconds(double s) {
            return new TimeSpan( (long) (s * 1000000000L));
        }
        
        /**
         * Constructor
         * <p>
         * Constructs a TimeSpan with zero length.
         */
        public TimeSpan() {
            this.setSpan(0L);
        }
        
        /**
         * Constructor
         * <p>
         * Constructs a TimeSpan with specified length in nanoseconds.
         * 
         * @param timeSpan Length in nanoseconds.
         */
        public TimeSpan(long timeSpan) {
            this.setSpan(timeSpan);
        }
        
        /**
         * Constructor
         * <p>
         * Constructs a TimeSpan with a specified start and end time.
         * 
         * @param start Start time in nanoseconds.
         * @param end End time in nanoseconds.
         */
        public TimeSpan(long start, long end) {
            this.setSpan(start, end);
        }
        
        /**
         * Sets this TimeSpan to a length in nanoseconds.
         * 
         * @param timeSpan New TimeSpan length.
         */
        public final void setSpan(long timeSpan) {
            this.span = timeSpan;
            this.start = 0;
            this.end = timeSpan;
        }
        
        /**
         * Sets this TimeSpan to a length in nanoseconds from a specified start and end time.
         * 
         * @param start Start time in nanoseconds.
         * @param end End time in nanoseconds.
         */
        public final void setSpan(long start, long end) {
            this.span = end - start;
            this.start = start;
            this.end = end;
        }
        
        /**
         * Gets the length of this TimeSpan in nanoseconds.
         * 
         * @return Length of TimeSpan in nanoseconds.
         */
        public long getNanoseconds() {
            return this.span;
        }
        
        /**
         * Gets the length of this TimeSpan in milliseconds.
         * 
         * @return Length of TimeSpan in milliseconds.
         */
        public double getMilliseconds() {
            return this.span / 1000000D;
        }
        
        /**
         * Gets the length of this TimeSpan in Seconds.
         * 
         * @return Length of TimeSpan in Seconds.
         */
        public double getSeconds() {
            return this.span / 1000000000D;
        }
        
        /**
         * Gets the starting point of this timespan.
         * 
         * @return start of timespan.
         */
        public long getStart() {
            return this.start;
        }
        
        /**
         * Gets the ending point of this timespan.
         * 
         * @return end of timespan.
         */
        public long getEnd() {
            return this.end;
        }
        
    }
