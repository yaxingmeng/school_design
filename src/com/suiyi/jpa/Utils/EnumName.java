package com.suiyi.jpa.Utils;

public class EnumName {

    public enum AdminType {

        NORMAL_ADMIN(0,"普通管理员"),

        SUPER_ADMIN(1,"超级管理员"),;

        private int value;

        private String caption;

        public int getValue() {
            return value;
        }

        public String getCaption() {
            return caption;
        }

        AdminType(int value, String caption) {
            this.value = value;
            this.caption = caption;
        }

        public static AdminType getOf(Integer v) {
            if (v == null) {
                return null;
            }
            for (AdminType status : values()) {
                if (v == status.getValue()) {
                    return status;
                }
            }
            return null;
        }

        public static String getCaptionByValue(Integer v) {
            AdminType es = getOf(v);
            return es == null ? "" : es.getCaption();
        }
    }
    
    public enum AdminRight {

        ORDER_MANAGE("0","普通管理员"),

        Goods_manage("1","超级管理员"),
        
        ALL_MANAGE("2","全部");

        private String value;

        private String caption;

        public String getValue() {
            return value;
        }

        public String getCaption() {
            return caption;
        }

        AdminRight(String value, String caption) {
            this.value = value;
            this.caption = caption;
        }

        public static AdminRight getOf(String v) {
            if (v == null) {
                return null;
            }
            for (AdminRight status : values()) {
                if (v == status.getValue()) {
                    return status;
                }
            }
            return null;
        }

        public static String getCaptionByValue(String v) {
        	AdminRight es = getOf(v);
            return es == null ? "" : es.getCaption();
        }
    }
    
    
}
