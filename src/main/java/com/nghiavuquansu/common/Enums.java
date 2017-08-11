package com.nghiavuquansu.common;

public class Enums {
    public static enum GoogleAPIAccount {
        INSTALLED_APPLICATIONS("Installed applications", 1), SERVICE_ACCOUNTS("Service accounts", 2);

        private GoogleAPIAccount(String strValue, Integer value) {
            this.strValue = strValue;
            this.value = value;
        }

        private final String strValue;
        private final Integer value;

        public String getStrValue() {
            return strValue;
        }

        public Integer getValue() {
            return value;
        }
    }
}
