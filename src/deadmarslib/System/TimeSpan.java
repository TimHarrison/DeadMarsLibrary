package deadmarslib.System;

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
        public static TimeSpan fromNanoseconds(double s) {
            return new TimeSpan( (long) s);
        }

        /**
         * Creates a TimeSpan from a length in Milliseconds.
         * 
         * @param s Milliseconds to create TimeSpan from.
         * @return New TimeSpan Object.
         */
        public static TimeSpan fromMilliseconds(double s) {
            return new TimeSpan( (long) (s * 1000000L));
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
            this.span = 0L;
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
        public long getMilliseconds() {
            return this.span / 1000000L;
        }
        
        /**
         * Gets the length of this TimeSpan in Seconds.
         * 
         * @return Length of TimeSpan in Seconds.
         */
        public long getSeconds() {
            return this.span / 1000000000L;
        }
        
    }
