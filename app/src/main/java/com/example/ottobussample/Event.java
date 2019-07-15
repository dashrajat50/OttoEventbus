package com.example.ottobussample;

public class Event {
    public static class FragmentToActivity {
        private String message;

        public FragmentToActivity(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ActivityToFragment {
        private String message;

        public ActivityToFragment(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
