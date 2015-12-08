package com.cecilectomy.dmge.Utility;

public class TimeSpan {
        
        private long span;
        private long start;
        private long end;
        
        public static final TimeSpan Zero = new TimeSpan(0);
        
        public static TimeSpan fromNanoseconds(long s) {
            return new TimeSpan(s);
        }
        
        public static TimeSpan fromMilliseconds(long s) {
            return new TimeSpan(s * 1000000L);
        }
        
        public static TimeSpan fromSeconds(double s) {
            return new TimeSpan( (long) (s * 1000000000L));
        }
        
        public TimeSpan() {
            this.setSpan(0L);
        }
        
        public TimeSpan(long timeSpan) {
            this.setSpan(timeSpan);
        }
        
        public TimeSpan(long start, long end) {
            this.setSpan(start, end);
        }
        
        public final void setSpan(long timeSpan) {
            this.span = timeSpan;
            this.start = 0;
            this.end = timeSpan;
        }
        
        public final void setSpan(long start, long end) {
            this.span = end - start;
            this.start = start;
            this.end = end;
        }
        
        public long getNanoseconds() {
            return this.span;
        }
        
        public double getMilliseconds() {
            return this.span / 1000000D;
        }
        
        public double getSeconds() {
            return this.span / 1000000000D;
        }
        
        public long getStart() {
            return this.start;
        }
        
        public long getEnd() {
            return this.end;
        }
        
    }
